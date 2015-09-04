package mercado.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GrupoProduto {

	private final IntegerProperty codigoGP;
	private final StringProperty nomeGP;
	private final FloatProperty promocaoGP;
	private final FloatProperty margemLucroGP;
	
	public GrupoProduto() {
		this(new Integer(0), null, new Float(0.0), new Float(0.0));
	}
	
	public GrupoProduto(Integer codigo, String nome, Float promocao, Float margemLucro) {
		this.codigoGP = new SimpleIntegerProperty(codigo.intValue());
		this.nomeGP = new SimpleStringProperty(nome);
		this.promocaoGP = new SimpleFloatProperty(promocao);
		this.margemLucroGP = new SimpleFloatProperty(margemLucro);
	}
	
	public Integer getCodigo() {
		return this.codigoGP.get();
	}
	
	public void setCodigo(Integer codigo) {
		this.codigoGP.set(codigo.intValue());
	}
	
	public String getNome() {
		return this.nomeGP.get();
	}
	
	public void setNome(String nome) {
		this.nomeGP.set(nome);
	}
	
	public Float getPromocao() {
		return this.promocaoGP.get();
	}
	
	public void setPromocao(Float promocao) {
		this.promocaoGP.set(promocao);
	}
	
	public Float getMargemLucro() {
		return this.margemLucroGP.get();
	}
	
	public void setMargemLucro(Float margemLucro) {
		this.margemLucroGP.set(margemLucro);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNome();
	}
	
}
