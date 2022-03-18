package modelo;

public class Endereco {
	private int id;			//autoincrementado dentro do create() sobrescrito no DAOEndereco
	private String logradouro;
	private String bairro;
	
	
	public Endereco(String logradouro, String bairro) {
		this.logradouro = logradouro;
		this.bairro = bairro;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}