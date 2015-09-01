package mercado.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Produto {

	private final IntegerProperty codigo;
	private final StringProperty nome;
	private final IntegerProperty estoque;
	private final FloatProperty valorCompra;
	private final FloatProperty promocao;
	private final FloatProperty margemLucro;
	
	public Produto() {
		this(null, null, null, null, null, null);
	}
	
	public Produto(Integer codigo, String nome, Integer estoque, Float valorCompra, Float promocao, Float margemLucro) {
		this.codigo = new SimpleIntegerProperty(codigo.intValue());
		this.nome = new SimpleStringProperty(nome);
		this.estoque = new SimpleIntegerProperty(estoque);
		this.valorCompra = new SimpleFloatProperty(valorCompra);
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
	
	public Integer getEstoque() {
		return this.estoque.get();
	}
	
	public void setEstoque(Integer estoque) {
		this.estoque.set(estoque.intValue());
	}
	
	public Float getValorCompra() {
		return this.valorCompra.get();
	}
	
	public void setValorCompra(Float valorCompra) {
		this.valorCompra.set(valorCompra);
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
	
}
