package aplicacao_console;

import fachada.Fachada;
import modelo.*;

public class AplicacaoConsole {

	public void teste1() {
		try {
			Fachada.inicializar();
			Contato contato;				
			Telefone telefone;		

			contato = Fachada.criarContato("João", "11/01/1990","Avenida Nego, 30","Tambaú");
			telefone = Fachada.adicionarTelefoneContato("99900000", "João");
			telefone = Fachada.adicionarTelefoneContato("99911111", "João");
			telefone = Fachada.adicionarTelefoneContato("99922222", "João");
			System.out.println("Contato criado: "+contato);
			contato = Fachada.criarContato("Maria", "11/02/2000","Avenida Alm. Tamandaré, 100","Tambaú");
			telefone = Fachada.adicionarTelefoneContato("32470000", "Maria");
			telefone = Fachada.adicionarTelefoneContato("32471111", "Maria");
			telefone = Fachada.adicionarTelefoneContato("99933333", "Maria");
			System.out.println("Contato criado: "+contato);
			contato = Fachada.criarContato("José", "11/03/2001","Avenida Guarabira, 30","Manaíra");
			telefone = Fachada.adicionarTelefoneContato("99944444", "José");
			telefone = Fachada.adicionarTelefoneContato("32472222", "José");
			System.out.println("Contato criado: "+contato);
			contato = Fachada.criarContato("Ana", "11/04/2002","Avenida Guarabira, 30","Manaíra");
			telefone = Fachada.adicionarTelefoneContato("99944444", "Ana");
			telefone = Fachada.adicionarTelefoneContato("32472222", "Ana");
			System.out.println("Contato criado: "+contato);
			contato = Fachada.criarContato("Paulo", "11/05/2003","Avenida Argemiro de Figueiredo, 100","Bessa");
			telefone = Fachada.adicionarTelefoneContato("32473333", "Paulo");
			System.out.println("Contato criado: "+contato);


			System.out.println("\n---------Listagem de Contatos-----");
			for(Contato c : Fachada.listarContatos("")) 
				System.out.println(c);
			System.out.println("\n--------Listagem de Telefones-----");
			for(Telefone t : Fachada.listarTelefones("")) 
				System.out.println(t);
			System.out.println("\n---------Listagem de Enderecos----");
			for(Endereco e : Fachada.listarEnderecos()) 
				System.out.println(e.getLogradouro()+ " - " +e.getBairro());
			
			System.out.println("\n---------Contatos com nomes que iniciam com \"Jo\"----");
			for(Contato c : Fachada.listarContatos("Jo")) 
				System.out.println(c);
			System.out.println("\n---------Telefones que possuem \"11\"----");
			for(Telefone t : Fachada.listarTelefones("11")) 
				System.out.println(t);

			System.out.println("\n-------------------Consulta A----");
			for(Contato c : Fachada.consultaA("Tambaú")) 
				System.out.println(c);
			System.out.println("\n-------------------Consulta B----");
			for(Telefone t : Fachada.consultaB()) 
					System.out.println(t);

			Fachada.alterarTelefone("99900000", "99900001");
			
			Fachada.removerTelefoneContato("99944444", "José");
			Fachada.removerTelefoneContato("99944444", "Ana");
		
			Fachada.apagarContato("Ana");
			Fachada.apagarContato("Paulo");

			System.out.println("\n---------Listagem de Contatos-----");
			for(Contato c : Fachada.listarContatos("")) 
				System.out.println(c);
			System.out.println("\n--------Listagem de Telefones-----");
			for(Telefone t : Fachada.listarTelefones("")) 
				System.out.println(t);
			System.out.println("\n---------Listagem de Enderecos-----");
			for(Endereco e : Fachada.listarEnderecos()) 
				System.out.println("ID: " + e.getId() +" - "+ e.getLogradouro()+ " - " +e.getBairro());

			Fachada.finalizar();

		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}		
	}

	public  void teste2() {
		try {
			Fachada.inicializar();
		}catch (Exception e) {
			System.out.println(e.getMessage());}

		Contato contato;
		Telefone telefone;
		
		try {
			contato = Fachada.criarContato("teste1", "11/11/1990","Avenida Nego, 30","Tambaú");
			contato = Fachada.criarContato("teste1", "11/11/1990","Avenida Nego, 30","Tambaú");
			System.out.println("*************1--->Nao lançou exceção para criar contato "); 
		}catch (Exception e) {
			System.out.println("1ok--->"+e.getMessage());}

		try {
			telefone = Fachada.adicionarTelefoneContato("22222222", "teste1");
			telefone = Fachada.adicionarTelefoneContato("22222222", "teste1");
			System.out.println("*************2--->Nao lançou exceção para add telefone"); 
		}catch (Exception e) {
			System.out.println("2ok--->"+e.getMessage());}

		try {
			Fachada.removerTelefoneContato("22222222", "teste1");
			Fachada.alterarTelefone("22222222", "33333333");
			System.out.println("*************3--->Nao lançou exceção para alterar telefone "); 
		}catch (Exception e) {
			System.out.println("3ok--->"+e.getMessage());}


		try {
			Fachada.finalizar();
		}catch (Exception e) {
			System.out.println(e.getMessage());}
	}


	public static void main (String[] args) {
		AplicacaoConsole aplicacaoConsole = new AplicacaoConsole();
		aplicacaoConsole.teste1();
		//aplicacaoConsole.teste2();
	}
}