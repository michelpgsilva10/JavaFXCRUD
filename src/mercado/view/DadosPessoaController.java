package mercado.view;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import mercado.handler.GrupoProdutoHandler;
import mercado.handler.ProdutoHandler;
import mercado.model.GrupoProduto;
import mercado.model.Produto;
import mercado.util.ConexaoBD;

public class DadosPessoaController {

	@FXML
	private TextField nomeField;
	@FXML
	private TextField estoqueField;
	@FXML
	private TextField valorCompraField;
	@FXML
	private TextField promocaoField;
	@FXML
	private TextField margemLucroField;
	@FXML
	private ComboBox<GrupoProduto> grupoProdutoCombo;

	private Produto produto;
	private Stage dialogStage;
	private boolean clickSalvar = false;
	private ConexaoBD conexao;

	@FXML
	public void initialize() {
		try {
			conexao = new ConexaoBD();
			
			grupoProdutoCombo.setItems(GrupoProdutoHandler.listarGrupoProduto(conexao.getConexao()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alertSQL = new Alert(AlertType.ERROR);
			alertSQL.setTitle("Busca Grupo Produto");
			alertSQL.setHeaderText("Não foi possível retornar os registros de Grupo Produto.");
			alertSQL.setContentText(e.getMessage());
			
			alertSQL.showAndWait();
		}
	}

	@FXML
	public void salvarHandle() {
		
		try {
			GrupoProduto gpProduto = (GrupoProduto) grupoProdutoCombo.getValue();
			
			Produto produto = new Produto(0, nomeField.getText(), Integer.parseInt(estoqueField.getText()),
					Float.parseFloat(valorCompraField.getText()), Float.parseFloat(promocaoField.getText()),
					Float.parseFloat(margemLucroField.getText()), gpProduto, gpProduto.getNome());
			
			this.produto = produto;
			ProdutoHandler.incluirProduto(conexao.getConexao(), produto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setClickSalvar(true);
		
		Alert alertaRegistroSalvo = new Alert(AlertType.CONFIRMATION);
		alertaRegistroSalvo.setTitle("Inclusão de Produto");
		alertaRegistroSalvo.setHeaderText("Registro salvo com sucesso");
		alertaRegistroSalvo.setContentText("O novo produto foi salvo com sucesso no banco de dados.");
		
		alertaRegistroSalvo.showAndWait();
		
		getDialogStage().close();
	}
	
	@FXML
	public void fecharConexao() {
		try {
			conexao.getConexao().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void cancelarHandle() {
		getDialogStage().close();
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Stage getDialogStage() {
		return this.dialogStage;
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public boolean isClickSalvar() {
		return clickSalvar;
	}
	
	public void setClickSalvar(boolean clickSalvar) {
		this.clickSalvar = clickSalvar;
	}

}
