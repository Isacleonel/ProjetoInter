package model.Beans;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BuscaCadastroContaReceberFiltro {
	private final IntegerProperty Codigo;
	private final StringProperty Nome;
	private final StringProperty Servico;
	//private final DateProperty Vencimento;
	private final IntegerProperty Valor;
	private final StringProperty Situacao;

	public BuscaCadastroContaReceberFiltro() {
		this.Codigo = null;
		this.Nome = null;
		this.Servico = null;
		//this.Vencimento = null;
		this.Valor = null;
		this.Situacao = null;
				
	}

	public BuscaCadastroContaReceberFiltro(int c, String n, String s, int b, String e) {
		Codigo = new SimpleIntegerProperty(c);
		Nome = new SimpleStringProperty(n);
		Servico = new SimpleStringProperty(s);
		//Vencimento = new SimpleDateProperty(a);
		Valor = new SimpleIntegerProperty(b);
		Situacao = new SimpleStringProperty(e);	
	}

	public int getCodigo() {
		return Codigo.get();
	}

	public void setCodigo(int cod) {
		this.Codigo.set(cod);
	}

	public IntegerProperty CodigoProperty() {
		return Codigo;
	}

	public String getNome() {
		return Nome.get();
	}

	public void setNome(String nome) {
		this.Nome.set(nome);
	}

	public StringProperty NomeProperty() {
		return Nome;
	}

	public String getServico() {
		return Servico.get();
	}

	public void setServico(String servico) {
		this.Servico.set(servico);
	}

	public StringProperty ServicoProperty() {
		return Servico;
	}
	
	
	
	/*
	
	public String getVencimento() {
		return Vencimento.get();
	}

	public void setVencimento(String ven) {
		this.Vencimento.set(ven);
	}

	public StringProperty VencimentoProperty() {
		return Vencimento;
	}
	
	*/
	
	
	public int getValor() {
		return Valor.get();
	}

	public void setValor(int val) {
		this.Valor.set(val);
	}

	public IntegerProperty ValorProperty() {
		return Valor;
	}
	
	
	
	
	public String getSituacao() {
		return Situacao.get();
	}

	public void setSituacao(String sit) {
		this.Situacao.set(sit);
	}

	public StringProperty SituacaoProperty() {
		return Situacao;
	}
	

}