package aplicacao_console;

import fachada.Fachada;
import modelo.*;

public class AplicacaoConsole {

	public void teste1() {
		try {
			Fachada.inicializar();
			Contato contato;				
			Telefone telefone;		

			contato = Fachada.criarContato("Jo�o", "11/01/1990","Avenida Nego, 30","Tamba�");
			telefone = Fachada.adicionarTelefoneContato("99900000", "Jo�o");
			telefone = Fachada.adicionarTelefoneContato("99911111", "Jo�o");
			telefone = Fachada.adicionarTelefoneContato("99922222", "Jo�o");
			System.out.println("Contato criado: "+contato);
			contato = Fachada.criarContato("Maria", "11/02/2000","Avenida Alm. Tamandar�, 100","Tamba�");
			telefone = Fachada.adicionarTelefoneContato("32470000", "Maria");
			telefone = Fachada.adicionarTelefoneContato("32471111", "Maria");
			telefone = Fachada.adicionarTelefoneContato("99933333", "Maria");
			System.out.println("Contato criado: "+contato);
			contato = Fachada.criarContato("Jos�", "11/03/2001","Avenida Guarabira, 30","Mana�ra");
			telefone = Fachada.adicionarTelefoneContato("99944444", "Jos�");
			telefone = Fachada.adicionarTelefoneContato("32472222", "Jos�");
			System.out.println("Contato criado: "+contato);
			contato = Fachada.criarContato("Ana", "11/04/2002","Avenida Guarabira, 30","Mana�ra");
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
			for(Contato c : Fachada.consultaA("Tamba�")) 
				System.out.println(c);
			System.out.println("\n-------------------Consulta B----");
			for(Telefone t : Fachada.consultaB()) 
					System.out.println(t);

			Fachada.alterarTelefone("99900000", "99900001");
			
			Fachada.removerTelefoneContato("99944444", "Jos�");
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
			contato = Fachada.criarContato("teste1", "11/11/1990","Avenida Nego, 30","Tamba�");
			contato = Fachada.criarContato("teste1", "11/11/1990","Avenida Nego, 30","Tamba�");
			System.out.println("*************1--->Nao lan�ou exce��o para criar contato "); 
		}catch (Exception e) {
			System.out.println("1ok--->"+e.getMessage());}

		try {
			telefone = Fachada.adicionarTelefoneContato("22222222", "teste1");
			telefone = Fachada.adicionarTelefoneContato("22222222", "teste1");
			System.out.println("*************2--->Nao lan�ou exce��o para add telefone"); 
		}catch (Exception e) {
			System.out.println("2ok--->"+e.getMessage());}

		try {
			Fachada.removerTelefoneContato("22222222", "teste1");
			Fachada.alterarTelefone("22222222", "33333333");
			System.out.println("*************3--->Nao lan�ou exce��o para alterar telefone "); 
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