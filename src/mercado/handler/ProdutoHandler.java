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
			Produto prod = new Produto(resultado.getInt("codigo"), resultado.getString("nome"),
					resultado.getInt("estoque"), resultado.getFloat("valorcompra"), resultado.getFloat("promocao"),
					resultado.getFloat("margemlucro"));

			produtos.add(prod);
		}

		return produtos;
	}

	public static ObservableList<Produto> buscarProdutoNome(Connection conexao, String nomeProduto)
			throws SQLException {
		ObservableList<Produto> produtos = FXCollections.observableArrayList();
		PreparedStatement consulta = null;

		consulta = conexao.prepareStatement("SELECT * FROM produto WHERE UPPER(nome) LIKE ?");
		consulta.setString(1, "%" + nomeProduto.toUpperCase() + "%");

		ResultSet resultado = consulta.executeQuery();

		while (resultado.next()) {
			Produto prod = new Produto(resultado.getInt("codigo"), resultado.getString("nome"),
					resultado.getInt("estoque"), resultado.getFloat("valorcompra"), resultado.getFloat("promocao"),
					resultado.getFloat("margemlucro"));
			
			produtos.add(prod);
		}

		return produtos;
	}

}
