package model.Beans;


public class Cidade {
	
	private int CodigoCidade, CodigoEstado;
	private String NomeCidade, NomeEstado;
	
	
	public int getCodigo() {
		return CodigoCidade;
	}
	public void setCodigo(int codigo) {
		this.CodigoCidade = codigo;
	}
	
	public String getNome() {
		return NomeCidade;
	}
	public void setNome(String nome) {
		this.NomeCidade = nome;
	}
	
	public int getCodigoEstado() {
		return CodigoEstado;
	}
	public void setCodigoEstado(int codigo) {
		this.CodigoEstado = codigo;
	}
	
	public String getNomeEstado() {
		return NomeEstado;
	}
	public void setNomeEstado(String nome) {
		this.NomeEstado = nome;
	}

}
