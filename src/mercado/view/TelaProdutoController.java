package mercado.view;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mercado.TelaPrincipal;
import mercado.handler.ProdutoHandler;
import mercado.model.GrupoProduto;
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
		nomeGPColumn.setCellValueFactory(new PropertyValueFactory<Produto, String>("nomeGP"));
	}

	@FXML
	public void buscarHandle() {
		ObservableList<Produto> produtosNome;
		try {
			produtosNome = ProdutoHandler.buscarProdutoNome(this.telaPrincipal.getConexao(),
					buscarField.getText());
			
			tabelaProdutos.getItems().clear();
			
			tabelaProdutos.setItems(produtosNome);
			
			for (Produto p : produtosNome) 
				System.out.println(p.getNome() + ":" + p.getNomeGrupoProduto());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@FXML
	public void incluirHandle() {
		Produto produto = new Produto();
		boolean salvarClicked = telaPrincipal.iniciarDadosProduto(produto);		
		
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
	public void sairHandle() {
		this.telaPrincipal.getPrimaryStage().close();
	}

	public void setTelaPrincipal(TelaPrincipal telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
		
		for (Produto p : this.telaPrincipal.getListaProdutos()) {
			System.out.println(p.getNomeGrupoProduto());
		}

		tabelaProdutos.setItems(this.telaPrincipal.getListaProdutos());
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
