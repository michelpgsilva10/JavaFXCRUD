package mercado.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mercado.model.GrupoProduto;

public class GrupoProdutoHandler {

	public static ObservableList<GrupoProduto> listarGrupoProduto(Connection conexao) throws SQLException {
		ObservableList<GrupoProduto> gpProduto = FXCollections.observableArrayList();
		PreparedStatement consulta = null;

		consulta = conexao.prepareStatement("SELECT * FROM grupoproduto");

		ResultSet resultado = consulta.executeQuery();

		while (resultado.next()) {
			GrupoProduto gpProd = new GrupoProduto(resultado.getInt("codigo"), resultado.getString("nome"),
					resultado.getFloat("promocao"), resultado.getFloat("margemlucro"));
			
			gpProduto.add(gpProd);
		}

		return gpProduto;
	}

}
