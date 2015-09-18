package mercado.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mercado.model.GrupoProduto;
import mercado.model.Produto;

public class ProdutoHandler {

	public static ObservableList<Produto> buscarProdutos(Connection conexao) throws SQLException {
		ObservableList<Produto> produtos = FXCollections.observableArrayList();
		PreparedStatement consulta = null;

		consulta = conexao.prepareStatement("SELECT a.codigo codigo_produto, a.nome nome_produto, a.estoque,"
				+ " a.valorcompra, a.promocao promocao_produto, a.margemlucro margem_lucro_produto, a.grupo,"
				+ " b.codigo codigo_gp_produto, b.nome nome_gp_produto, b.promocao promocao_gp_produto,"
				+ " b.margemlucro margem_lucro_gp_produto" + " FROM produto a"
				+ " JOIN grupoproduto b ON(a.grupo = b.codigo)");

		ResultSet resultado = consulta.executeQuery();

		while (resultado.next()) {
			GrupoProduto gpProd = new GrupoProduto(resultado.getInt("codigo_gp_produto"),
					resultado.getString("nome_gp_produto"), resultado.getFloat("promocao_gp_produto"),
					resultado.getFloat("margem_lucro_gp_produto"));
			
			Produto prod = new Produto(resultado.getInt("codigo_produto"), resultado.getString("nome_produto"),
					resultado.getInt("estoque"), resultado.getFloat("valorcompra"), resultado.getFloat("promocao_produto"),
					resultado.getFloat("margem_lucro_produto"), gpProd);

			produtos.add(prod);
		}

		return produtos;
	}

	public static ObservableList<Produto> buscarProdutoNome(Connection conexao, String nomeProduto)
			throws SQLException {
		ObservableList<Produto> produtos = FXCollections.observableArrayList();
		PreparedStatement consulta = null;

		consulta = conexao.prepareStatement("SELECT a.codigo codigo_produto, a.nome nome_produto, a.estoque,"
				+ " a.valorcompra, a.promocao promocao_produto, a.margemlucro margem_lucro_produto, a.grupo,"
				+ " b.codigo codigo_gp_produto, b.nome nome_gp_produto, b.promocao promocao_gp_produto,"
				+ " b.margemlucro margem_lucro_gp_produto" + " FROM produto a"
				+ " JOIN grupoproduto b ON(a.grupo = b.codigo)" + "	WHERE UPPER(a.nome) LIKE ?");
		consulta.setString(1, "%" + nomeProduto.toUpperCase() + "%");

		ResultSet resultado = consulta.executeQuery();

		while (resultado.next()) {
			GrupoProduto gpProd = new GrupoProduto(resultado.getInt("codigo_gp_produto"),
					resultado.getString("nome_gp_produto"), resultado.getFloat("promocao_gp_produto"),
					resultado.getFloat("margem_lucro_gp_produto"));

			Produto prod = new Produto(resultado.getInt("codigo_produto"), resultado.getString("nome_produto"),
					resultado.getInt("estoque"), resultado.getFloat("valorcompra"),
					resultado.getFloat("promocao_produto"), resultado.getFloat("margem_lucro_produto"), gpProd);

			produtos.add(prod);
		}

		return produtos;
	}

	public static int incluirProduto(Connection conexao, Produto produto) throws SQLException {
		PreparedStatement insert = null;

		insert = conexao
				.prepareStatement("INSERT INTO produto (nome, estoque, valorcompra, promocao, margemlucro, grupo)"
						+ " VALUES (?, ?, ?, ?, ?, ?)");

		insert.setString(1, produto.getNome());
		insert.setInt(2, produto.getEstoque());
		insert.setFloat(3, produto.getValorCompra());
		insert.setFloat(4, produto.getPromocao());
		insert.setFloat(5, produto.getMargemLucro());
		insert.setInt(6, produto.getGpProduto().getCodigo());

		return insert.executeUpdate();
	}

}
