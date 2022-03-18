package modelo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
import java.util.ArrayList;


public class Contato {
	private String nome;
	private String nascimento;
	private Endereco endereco;
	private ArrayList<Telefone> telefones = new ArrayList<Telefone>();


	public Contato(String nome, LocalDate nascimento, Endereco endereco) {
		this.nome = nome;
		this.nascimento = nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}


	public LocalDate getNascimento() {
		return LocalDate.parse(nascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public String getNascimentoStr() {
		return nascimento;
	}

	public String getEnderecoStr() {
		return endereco.getLogradouro()+" - " + endereco.getBairro();
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public ArrayList<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(ArrayList<Telefone> telefones) {
		this.telefones = telefones;
	}

	public void adicionar(Telefone p){
		telefones.add(p);
	}
	public void remover(Telefone p){
		telefones.remove(p);
	}
	public Telefone localizarTelefone(String numero){
		for(Telefone p : telefones){
			if(p.getNumero().equals(numero))
				return p;
		}
		return null;
	}

	public int getIdade() {
		//calcular a idade usando a data de nascimento e data do computador
		Integer anoNasc = getNascimento().getYear();
		Integer anoAtual = LocalDate.now().getYear();
		Integer result = anoAtual - anoNasc; //;
		return result;
	}


	@Override
	public String toString() {
		String texto = "Nome: " + nome + ", Nascimento: "+getNascimentoStr() + 
				", Endereco: "+getEnderecoStr();
		texto += ", Telefones:";
		if (telefones.isEmpty())
			texto += " vazia";
		else 	
			for(Telefone p: telefones) 
				texto += " " + p.getNumero() ;

		return texto ;
	}


}