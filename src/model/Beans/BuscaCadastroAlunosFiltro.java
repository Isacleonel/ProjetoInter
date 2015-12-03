package model.Beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BuscaCadastroAlunosFiltro {
	private final IntegerProperty Codigo;
	private final StringProperty Nome;
	private final StringProperty Status;

	public BuscaCadastroAlunosFiltro() {
		this.Codigo = null;
		this.Nome = null;
		this.Status = null;
	}

	public BuscaCadastroAlunosFiltro(int c, String n, String s) {
		Codigo = new SimpleIntegerProperty(c);
		Nome = new SimpleStringProperty(n);
		Status = new SimpleStringProperty(s);
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

	public String getStatus() {
		return Status.get();
	}

	public void setStatus(String status) {
		this.Status.set(status);
	}

	public StringProperty StatusProperty() {
		return Status;
	}

}