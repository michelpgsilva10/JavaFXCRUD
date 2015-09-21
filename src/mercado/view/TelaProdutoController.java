package mercado.view;

import java.sql.SQLException;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
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
	@FXML
	private TableColumn<Produto, String> nomeGPColumn;
	@FXML
	private TextField buscarField;
	@FXML
	private Button buscarButton;
	@FXML
	private Button sairButton;

	private TelaPrincipal telaPrincipal;
	private Produto produto;

	@FXML
	public void initialize() {
		codigoColumn.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("codigo"));
		nomeColumn.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
		estoqueColumn.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("estoque"));
		valorCompraColumn.setCellValueFactory(new PropertyValueFactory<Produto, Float>("valorCompra"));
		promocaoColumn.setCellValueFactory(new PropertyValueFactory<Produto, Float>("promocao"));
		margemLucroColumn.setCellValueFactory(new PropertyValueFactory<Produto, Float>("margemLucro"));

		/*
		 * Para pegar dados de uma coluna que faz referência a outra classe,
		 * basta criar um método que retorna o Property do atributo que se
		 * queira mostrar na tabela e dentro do setCellValueFactory no
		 * controller instanciar a classe Callback (Ligar corretamente no
		 * SceneBuilder a variável da coluna, para não dar
		 * nullPointerException). Chamar através do método getValue() o atributo
		 * que chamará o Property do atributo desejado.
		 */
		nomeGPColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Produto, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Produto, String> param) {
						// TODO Auto-generated method stub
						return param.getValue().getGpProduto().nomeGPProperty();
					}
				});

	}

	@FXML
	public void buscarHandle() {
		ObservableList<Produto> produtosNome;
		try {
			produtosNome = ProdutoHandler.buscarProdutoNome(this.telaPrincipal.getConexao(), buscarField.getText());

			tabelaProdutos.getItems().clear();

			tabelaProdutos.setItems(produtosNome);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void incluirHandle() {
		Produto produto = new Produto();
		boolean salvarClicked = telaPrincipal.iniciarDadosProduto(produto, 0);

		try {
			if (salvarClicked) {
				ObservableList<Produto> produtos = ProdutoHandler.buscarProdutos(telaPrincipal.getConexao());
				telaPrincipal.setListaProdutos(produtos);
				tabelaProdutos.setItems(produtos);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void editarHandle() {
		Produto produto = tabelaProdutos.getSelectionModel().getSelectedItem();
		
		if (produto != null) {
			boolean salvarClicked = telaPrincipal.iniciarDadosProduto(produto, 1);			
			
				try {
					if (salvarClicked) {
						ObservableList<Produto> produtos;
						produtos = ProdutoHandler.buscarProdutos(telaPrincipal.getConexao());
						telaPrincipal.setListaProdutos(produtos);
					  	tabelaProdutos.setItems(produtos);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  	
		} else {
			Alert selecaoProduto = new Alert(AlertType.INFORMATION);
			selecaoProduto.setTitle("Selecionar Produto");
			selecaoProduto.setHeaderText("Nenhum produto selecionado.");
			selecaoProduto.setContentText("Você deve selecionar um produto para editar seus dados.");
			selecaoProduto.showAndWait();
		}
	}

	@FXML
	public void sairHandle() {
		this.telaPrincipal.getPrimaryStage().close();
	}

	public void setTelaPrincipal(TelaPrincipal telaPrincipal) {
		this.telaPrincipal = telaPrincipal;

		tabelaProdutos.setItems(this.telaPrincipal.getListaProdutos());
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
