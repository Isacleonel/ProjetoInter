package model.Beans;


public class Matricula {

	private int CodigoMatricula, CodigoAluno, CodigoModalidade, CodigoProfessor, DiaPagamento, ValorMensal, ValorPersonal;
	private String NomeAluno, NomeModalidade, PlanoMatricula, HoraInicio, HoraFim, NomeProfessor;
	
	
	
	
	public int getCodigo() {
		return CodigoMatricula;
	}

	public void setCodigo(int codigo) {
		this.CodigoMatricula = codigo;
	}
	
	
	public int getCodigoAluno() {
		return CodigoAluno;
	}

	public void setCodigoAluno(int codigo) {
		this.CodigoAluno = codigo;
	}
	
	public String getNomeAluno() {
		return NomeAluno;
	}

	public void setNomeAluno(String nome) {
		this.NomeAluno = nome;
	}
	
	
	public int getCodigoModalidade() {
		return CodigoModalidade;
	}

	public void setCodigoModalidade(int codigo) {
		this.CodigoModalidade = codigo;
	}
	
	public String getNomeModalidade() {
		return NomeModalidade;
	}

	public void setNomeModalidade(String nome) {
		this.NomeModalidade = nome;
	}
	
	public String getPlanoMatricula() {
		return PlanoMatricula;
	}

	public void setPlanoMatricula(String nome) {
		this.PlanoMatricula = nome;
	}
	
	
	public String getHorarioIni() {
		return HoraInicio;
	}

	public void setHorarioIni(String codigo) {
		this.HoraInicio = codigo;
	}
	
	public String getHorarioFim() {
		return HoraFim;
	}

	public void setHorarioFim(String codigo) {
		this.HoraFim = codigo;
	}
	
	
	public int getCodigoProfessor() {
		return CodigoProfessor;
	}

	public void setCodigoProfessor(int codigo) {
		this.CodigoProfessor = codigo;
	}
	
	public String getNomeProfessor() {
		return NomeProfessor;
	}

	public void setNomeProfessor(String nome) {
		this.NomeProfessor = nome;
	}
	
	
	public int getDiaPagamento() {
		return DiaPagamento;
	}

	public void setDiaPagamento(int codigo) {
		this.DiaPagamento = codigo;
	}
	
	
	
	public int getValorMensal() {
		return ValorMensal;
	}

	public void setValorMensal(int codigo) {
		this.ValorMensal = codigo;
	}
	
	
	public int getValorPersonal() {
		return ValorPersonal;
	}

	public void setValorPersonal(int codigo) {
		this.ValorPersonal = codigo;
	}
	
	

}
