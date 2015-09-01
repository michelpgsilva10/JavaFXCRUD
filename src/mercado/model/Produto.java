package mercado.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Produto {

	private final IntegerProperty codigo;
	private final StringProperty nome;
	
	public Produto() {
		this(null, null);
	}
	
	public Produto(Integer codigo, String nome) {
		this.codigo = new SimpleIntegerProperty(codigo.intValue());
		this.nome = new SimpleStringProperty(nome);
	}
	
}
