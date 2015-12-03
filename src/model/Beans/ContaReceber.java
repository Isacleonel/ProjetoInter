package model.Beans;

import java.sql.Date;

public class ContaReceber {

	private int CodigoContaReceber, CodigoMatricula, ValorContaReceber, DiaMatricula;
	private String NomeAluno, ServicoContaReceber, ObservacaoContaReceber, SituacaoContaReceber;
	private Date VencimentoContaReceber;
	
	
	
	public int getCodigo() {
		return CodigoContaReceber;
	}

	public void setCodigo(int codigo) {
		this.CodigoContaReceber = codigo;
	}
	
	
	public int getCodigoMatricula() {
		return CodigoMatricula;
	}

	public void setCodigoMatricula(int codigo) {
		this.CodigoMatricula = codigo;
	}
	
	public String getNomeAluno() {
		return NomeAluno;
	}

	public void setNomeAluno(String nome) {
		this.NomeAluno = nome;
	}
	
	
	public String getServico() {
		return ServicoContaReceber;
	}

	public void setServico(String a) {
		this.ServicoContaReceber = a;
	}
	
	
	public Date getVencimento() {
		return VencimentoContaReceber;
	}

	public void setVencimento(Date a) {
		this.VencimentoContaReceber = a;
	}
	
	public int getDia() {
		return DiaMatricula;
	}

	public void setDia(int codigo) {
		this.DiaMatricula = codigo;
	}
	
	
	public int getValor() {
		return ValorContaReceber;
	}

	public void setValor(int n) {
		this.ValorContaReceber = n;
	}
	
	public String getObservacao() {
		return ObservacaoContaReceber;
	}

	public void setObservacao(String a) {
		this.ObservacaoContaReceber = a;
	}
	
	
	public String getSituacao() {
		return SituacaoContaReceber;
	}

	public void setSituacao(String a) {
		this.SituacaoContaReceber = a;
	}
	
	
	

}
