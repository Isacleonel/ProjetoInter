package model.Beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BuscaCadastroCidadeFiltro {
	private final IntegerProperty Codigo;
	private final StringProperty Nome;
	private final StringProperty Estado;
	

	public BuscaCadastroCidadeFiltro() {
		this.Codigo = null;
		this.Nome = null;
		this.Estado = null;
	}

	public BuscaCadastroCidadeFiltro(int c, String n, String a) {
		Codigo = new SimpleIntegerProperty(c);
		Nome = new SimpleStringProperty(n);
		Estado = new SimpleStringProperty(a);	
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

	public String getEstado() {
		return Estado.get();
	}

	public void setEstado(String nome) {
		this.Estado.set(nome);
	}

	public StringProperty EstadoProperty() {
		return Estado;
	}
	
	

}