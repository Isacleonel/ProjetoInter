package model.Beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BuscaCadastroEstadoFiltro {
	private final IntegerProperty Codigo;
	private final StringProperty Nome;
	

	public BuscaCadastroEstadoFiltro() {
		this.Codigo = null;
		this.Nome = null;
	}

	public BuscaCadastroEstadoFiltro(int c, String n) {
		Codigo = new SimpleIntegerProperty(c);
		Nome = new SimpleStringProperty(n);
		
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


}