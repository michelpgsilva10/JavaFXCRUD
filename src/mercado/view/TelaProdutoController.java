package mercado.view;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mercado.TelaPrincipal;
import mercado.handler.ProdutoHandler;
import mercado.model.Produto;

public class TelaProdutoController {
	
	@FXML
	private TableView<Produto> tabelaProdutos;
	@FXML
	private TableColumn<Produto, Integer> codigoColumn;
	@FXML
	private TableColumn<Produto, String> nomeColumn;
	@FXML
	private TableColumn<Produto, Integer> estoqueColumn;
	@FXML
	private TableColumn<Produto, Float> valorCompraColumn;
	@FXML
	private TableColumn<Produto, Float> promocaoColumn;
	@FXML
	private TableColumn<Produto, Float> margemLucroColumn;
	
	private TelaPrincipal telaPrincipal;

	@FXML
	public void initialize() {
		codigoColumn.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("codigo"));
		nomeColumn.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
		
		try {
			tabelaProdutos.setItems(ProdutoHandler.buscarProdutos(telaPrincipal.getConexao()));
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			Alert alertSQL = new Alert(AlertType.ERROR);
			alertSQL.setTitle("Erro ao Buscar Dados");
			alertSQL.setHeaderText("Não foi possível retornar os produtos!");
			alertSQL.setContentText(sqle.getMessage());
			
			alertSQL.showAndWait();
		}
	}
	
	public void setTelaPrincipal(TelaPrincipal telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
	}
	
}
