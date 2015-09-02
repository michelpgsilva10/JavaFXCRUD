package mercado.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import mercado.model.Produto;

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
	private ComboBox<Produto> grupoProdutoCombo;

	private Produto produto;
	private Stage dialogStage;
	private boolean clickSalvar = false;

	@FXML
	public void initialize() {

	}

	@FXML
	public void salvarHandle() {
		Produto produto = new Produto(0, nomeField.getText(), Integer.parseInt(estoqueField.getText()),
				Float.parseFloat(valorCompraField.getText()), Float.parseFloat(promocaoField.getText()),
				Float.parseFloat(margemLucroField.getText()));
		
		this.produto = produto;
		
		setClickSalvar(true);
		
		Alert alertaRegistroSalvo = new Alert(AlertType.CONFIRMATION);
		alertaRegistroSalvo.setTitle("Inclusão de Produto");
		alertaRegistroSalvo.setHeaderText("Registro salvo com sucesso");
		alertaRegistroSalvo.setContentText("O novo produto foi salvo com sucesso no banco de dados.");
		
		alertaRegistroSalvo.showAndWait();
		
		getDialogStage().close();
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
