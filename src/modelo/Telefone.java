package modelo;

import java.util.ArrayList;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programa��o Orientada a Objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

public class Telefone {
	private String numero;
	private ArrayList<Contato> contatos = new ArrayList<>() ;

	public Telefone(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public ArrayList<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(ArrayList<Contato> contatos) {
		this.contatos = contatos;
	}

	public void adicionar(Contato p){
		contatos.add(p);
	}
	public void remover(Contato p){
		contatos.remove(p);
	}

	public Contato localizar(String nome){
		for(Contato p : contatos){
			if(p.getNome().equals(nome))
				return p;
		}
		return null;
	}

	@Override
	public String toString() {
		String texto = "N�mero: " + numero;
		texto += ", Contatos:";

		for(Contato p: contatos) 
			texto += " " + p.getNome() ;

		return texto ;
	}

}