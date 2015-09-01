package mercado.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mercado.model.Produto;

public class ProdutoHandler {

	public static ObservableList<Produto> buscarProdutos(Connection conexao) throws SQLException {
		ObservableList<Produto> produtos = FXCollections.observableArrayList();
		PreparedStatement consulta = null;
		
		consulta = conexao.prepareStatement("SELECT * FROM produto");
		
		ResultSet resultado = consulta.executeQuery();
		
		while (resultado.next()) {
			Produto prod = new Produto();
			
			prod.setCodigo(resultado.getInt("codigo"));
			prod.setNome(resultado.getString("nome"));
			prod.setEstoque(resultado.getInt("estoque"));
			prod.setValorCompra(resultado.getFloat("valorcompra"));
			prod.setPromocao(resultado.getFloat("promocao"));
			prod.setMargemLucro(resultado.getFloat("margemlucro"));
			
			produtos.add(prod);
		}
		
		return produtos;
	}
	
}
