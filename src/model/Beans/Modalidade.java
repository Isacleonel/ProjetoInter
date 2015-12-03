package model.Beans;


public class Modalidade {
	
	private int CodigoModalidade;
	private String NomeModalidade, ObsModalidade="";
	
	
	public int getCodigo() {
		return CodigoModalidade;
	}
	public void setCodigo(int codigo) {
		this.CodigoModalidade = codigo;
	}
	public String getNome() {
		return NomeModalidade;
	}
	public void setNome(String nome) {
		this.NomeModalidade = nome;
	}
	public String getObservacao() {
		return ObsModalidade;
	}
	public void setObservacao(String obs) {
		this.ObsModalidade = obs;
	}
	

		
	
	

}
