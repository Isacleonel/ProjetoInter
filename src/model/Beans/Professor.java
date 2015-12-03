package model.Beans;

import java.sql.Date;

public class Professor {

	private int CodigoProfessor, CodigoModalidade, CodigoCidade, CodigoEstado, AnoProfessor;
	private String NomeProfessor, SexoProfessor, CpfProfessor, IdentidadeProfessor="", FormacaoProfessor, NomeModalidade, TurnoProfessor,
			NomeCidade, NomeEstado, BairroProfessor="", EnderecoProfessor="", CelularProfessor="", FoneProfessor="", EmailProfessor="";
	Date NascimentoProfessor, CadastroProfessor;

	public int getCodigo() {
		return CodigoProfessor;
	}

	public void setCodigo(int codigo) {
		this.CodigoProfessor = codigo;
	}

	public String getNome() {
		return NomeProfessor;
	}

	public void setNome(String nome) {
		this.NomeProfessor = nome;
	}
	
	
	public String getSexo() {
		return SexoProfessor;
	}

	public void setSexo(String sexo) {
		this.SexoProfessor = sexo;
	}

	public String getCpf() {
		return CpfProfessor;
	}

	public void setCpf(String cpf) {
		this.CpfProfessor = cpf;
	}

	public String getIdentidade() {
		return IdentidadeProfessor;
	}

	public void setIdentidade(String identidade) {
		this.IdentidadeProfessor = identidade;
	}
	
	public Date getNascimento() {
		return NascimentoProfessor;
	}

	public void setNascimento(Date nascimento) {
		this.NascimentoProfessor = nascimento;
	}
	
	public String getFormacao() {
		return FormacaoProfessor;
	}

	public void setFormacao(String formacao) {
		this.FormacaoProfessor = formacao;
	}
	
	public int getAno() {
		return AnoProfessor;
	}

	public void setAno(int ano) {
		this.AnoProfessor = ano;
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
	
	public String getTurno() {
		return TurnoProfessor;
	}

	public void setTurno(String turno) {
		this.TurnoProfessor = turno;
	}
	
	public int getCodigoCidade() {
		return CodigoCidade;
	}

	public void setCodigoCidade(int codigo) {
		this.CodigoCidade = codigo;
	}
	
	public String getNomeCidade() {
		return NomeCidade;
	}

	public void setNomeCidade(String nome) {
		this.NomeCidade = nome;
	}
	
	
	public int getCodigoEstado() {
		return CodigoEstado;
	}

	public void setCodigoEstado(int estado) {
		this.CodigoEstado = estado;
	}
	
	public String getNomeEstado() {
		return NomeEstado;
	}

	public void setNomeEstado(String nome) {
		this.NomeEstado = nome;
	}
	
	public String getBairro() {
		return BairroProfessor;
	}

	public void setBairro(String bairro) {
		this.BairroProfessor = bairro;
	}

	public String getEndereco() {
		return EnderecoProfessor;
	}

	public void setEndereco(String endereco) {
		this.EnderecoProfessor = endereco;
	}
	
	public String getCelular() {
		return CelularProfessor;
	}

	public void setCelular(String celular) {
		this.CelularProfessor = celular;
	}

	public String getFone() {
		return FoneProfessor;
	}

	public void setFone(String fone) {
		this.FoneProfessor = fone;
	}

	public Date getCadastro() {
		return CadastroProfessor;
	}

	public void setCadastro(Date cadastro) {
		this.CadastroProfessor = cadastro;
	}
	
	public String getEmail() {
		return EmailProfessor;
	}

	public void setEmail(String email) {
		this.EmailProfessor = email;
	}



	

}
