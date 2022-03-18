/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POO - Programação Orientada a Objetos
 * Prof. Fausto Ayres
 *
 */
package fachada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import dao.*;
import modelo.*;

public class Fachada {

	private static String nome;
	private static String nascimento;

	private static DAOContato daoContato = new DAOContato();
	private static DAOEndereco daoEndereco = new DAOEndereco();
	private static DAOTelefone daoTelefone = new DAOTelefone();

	public static void inicializar() { DAO.open(); }

	public static void finalizar() { DAO.close(); }

	public static Contato criarContato(String nome, String nascimento, String logradouro, String bairro) throws Exception {
		nome = nome.trim();
		nascimento = nascimento.trim();
		logradouro = logradouro.trim();

		DAO.begin();

		Contato contato = daoContato.read(nome);

		if (contato != null) {
			DAO.rollback();
			throw new Exception("Contato:" + nome + "já cadastrado(a).");
		}

		LocalDate data;
		try {
			DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			data = LocalDate.parse(nascimento, parser);
		} catch (DateTimeParseException e) {
			throw new Exception("Data de nascimento com Formato Inválido");
		}

		Endereco endereco = daoEndereco.read(logradouro);

		if (endereco == null) {
			endereco = new Endereco(logradouro, bairro);
		}

		// Novo Contato
		contato = new Contato(nome, data, endereco);

		daoContato.create(contato);

		DAO.commit();

		return contato;
	}

	public static Telefone adicionarTelefoneContato(String numero, String nome)	throws Exception {
		nome = nome.trim();
		numero = numero.trim();

		DAO.begin();

		Contato contato = daoContato.read(nome);

		// Checar se contato já existe
		if (contato == null) {
			DAO.rollback();
			throw new Exception("adicionar telefone - contato inexistente:" + nome); }

		Telefone telefone = contato.localizarTelefone(numero);

		// Checar se o número já está cadastrado
		if (telefone != null) {
			DAO.rollback();
			throw new Exception("adicionar telefone - numero duplicado para o contato:" + numero +"/"+nome);
		} else {
			telefone = new Telefone(numero);
		}

		contato.adicionar(telefone);
		daoContato.update(contato);
		telefone.adicionar(contato);
		daoTelefone.update(telefone);

		DAO.commit();
		
		return telefone;

	}

	public static void removerTelefoneContato(String numero, String nome) throws  Exception{
		nome = nome.trim();
		numero = numero.trim();

		DAO.begin();
		
		Contato contato = daoContato.read(nome);
		if (contato == null) {
			DAO.rollback();
			throw new Exception("remover telefone - contato inexistente:" + nome); }

		Telefone telefone = contato.localizarTelefone(numero);
		if(telefone == null) {
			DAO.rollback();
			throw new Exception("remover telefone - contato nao possui o numero :" + numero +"/"+nome); }
		
		contato.remover(telefone);
		daoContato.update(contato);
		telefone.remover(contato);
		daoTelefone.update(telefone);
		
		// Caso o Telefone fique orfao de contato
		if (telefone.getContatos().size()==0) {
			daoTelefone.delete(telefone);
		}
	
		DAO.commit();

	}

	public static void alterarTelefone(String numero, String novo) throws Exception {
		novo = novo.trim();
		numero = numero.trim();

		DAO.begin();
		
		// Checar se numero realmente existe na base de dados
		Telefone telefone1 = daoTelefone.read(numero);
		if (telefone1 == null) {
			DAO.rollback();
			throw new Exception("alterar telefone - numero inexistente:" + numero); }

		// Checar existência telefone
		Telefone telefone2 = daoTelefone.read(novo);
		if (telefone2 != null) {
			DAO.rollback();
			throw new Exception("alterar telefone - numero existente:" + novo); }
		
		telefone1.setNumero(novo);
		daoTelefone.update(telefone1);

		DAO.commit();
	}

	public static List<Contato> listarContatos(String caracteres) {
		
		List<Contato> listaContatos = new ArrayList<>();
		
		if (caracteres.isEmpty()) {
			return daoContato.readAll();
		} else {
			for (Contato i : daoContato.readAll()) {
				if (i.getNome().startsWith(caracteres)) {
					listaContatos.add(i);
				}
			}
		}
		return listaContatos;
	}
			
		
	public static List<Telefone> listarTelefones(String digitos) {
		
		List<Telefone> listaTelefones = new ArrayList<>();
		
		if(digitos.isEmpty()) {
			return daoTelefone.readAll();
		} else {
			for (Telefone i : daoTelefone.readAll()) {
				if (i.getNumero().contains(digitos)) {
					listaTelefones.add(i);
				}
			}
		}
		return listaTelefones;
	}


	public static List<Endereco> listarEnderecos() { return daoEndereco.readAll(); }
		

	public static List<Contato> consultaA(String bairro) {
		
		List<Contato> listaBairros = new ArrayList<>();
		
		for (Contato i : daoContato.readAll()) {
			if (i.getEnderecoStr().contains(bairro)) {
				listaBairros.add(i);
		}
	}		
		return listaBairros;
}
	
	public static List<Telefone> consultaB() {
		
		List<Telefone> listaFixos = new ArrayList<>();
		
		for (Telefone i : daoTelefone.readAll()) {
			if (i.getNumero().startsWith("3")) {
				listaFixos.add(i);
			}
		}
	return listaFixos;
}

//	public static List<Telefone> consultaB() { return daoTelefone.temTelefoneFixo(); }
	
	/*
	 * NOVO MÉTODO
	 */
	public static void apagarContato(String nome) throws Exception {
		nome = nome.trim();

		DAO.begin();

		Contato contato = daoContato.read(nome);

		if (contato == null) {
			DAO.rollback();
			throw new Exception("Contato: " + nome + " não existe.");
		}

		daoContato.delete(contato);

		DAO.commit();

	}
}


