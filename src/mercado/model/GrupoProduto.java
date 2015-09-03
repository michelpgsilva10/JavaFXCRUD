package mercado.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GrupoProduto {

	private final IntegerProperty codigo;
	private final StringProperty nome;
	private final FloatProperty promocao;
	private final FloatProperty margemLucro;
	
	public GrupoProduto() {
		this(new Integer(0), null, new Float(0.0), new Float(0.0));
	}
	
	public GrupoProduto(Integer codigo, String nome, Float promocao, Float margemLucro) {
		this.codigo = new SimpleIntegerProperty(codigo.intValue());
		this.nome = new SimpleStringProperty(nome);
		this.promocao = new SimpleFloatProperty(promocao);
		this.margemLucro = new SimpleFloatProperty(margemLucro);
	}
	
	public Integer getCodigo() {
		return this.codigo.get();
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo.set(codigo.intValue());
	}
	
	public String getNome() {
		return this.nome.get();
	}
	
	public void setNome(String nome) {
		this.nome.set(nome);
	}
	
	public Float getPromocao() {
		return this.promocao.get();
	}
	
	public void setPromocao(Float promocao) {
		this.promocao.set(promocao);
	}
	
	public Float getMargemLucro() {
		return this.margemLucro.get();
	}
	
	public void setMargemLucro(Float margemLucro) {
		this.margemLucro.set(margemLucro);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNome();
	}
	
}
