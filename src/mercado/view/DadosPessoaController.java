package mercado.view;

import java.sql.SQLException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
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
	@FXML
	private Button salvarButton;

	private Produto produto;
	private Stage dialogStage;
	private boolean clickSalvar = false;
	private ConexaoBD conexao;
	private int tipoModificacao;

	@FXML
	public void initialize() {
		try {
			conexao = new ConexaoBD();
			
			grupoProdutoCombo.setItems(GrupoProdutoHandler.listarGrupoProduto(conexao.getConexao()));	
			
			grupoProdutoCombo.setConverter(new StringConverter<GrupoProduto>() {
				
				@Override
				public String toString(GrupoProduto object) {
					// TODO Auto-generated method stub
					if (object != null)
						return object.getNome();
					else
						return null;
				}
				
				@Override
				public GrupoProduto fromString(String string) {
					// TODO Auto-generated method stub
					return null;
				}
			});
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alertSQL = new Alert(AlertType.ERROR);
			alertSQL.setTitle("Busca Grupo Produto");
			alertSQL.setHeaderText("Não foi possível retornar os registros de Grupo Produto.");
			alertSQL.setContentText(e.getMessage());
			
			alertSQL.showAndWait();
		}
	}
	
	public void adicionarDados(Produto produto) {
		if (produto != null) {
			nomeField.setText(produto.getNome());
			estoqueField.setText(produto.getEstoque().toString());
			valorCompraField.setText(produto.getValorCompra().toString());
			promocaoField.setText(produto.getPromocao().toString());
			margemLucroField.setText(produto.getMargemLucro().toString());
			grupoProdutoCombo.getSelectionModel().select(produto.getGpProduto());
			
			nomeField.requestFocus();
		} else {
			nomeField.setText("");
			estoqueField.setText("");
			valorCompraField.setText("");
			promocaoField.setText("");
			margemLucroField.setText("");
			grupoProdutoCombo.getSelectionModel().select(null);
		}
	}

	@FXML
	public void salvarHandle() {
		
		try {
			
			if (tipoModificacao == 0) {
				GrupoProduto gpProduto = (GrupoProduto) grupoProdutoCombo.getValue();
				
				Produto produto = new Produto(0, nomeField.getText(), Integer.parseInt(estoqueField.getText()),
						Float.parseFloat(valorCompraField.getText()), Float.parseFloat(promocaoField.getText()),
						Float.parseFloat(margemLucroField.getText()), gpProduto);
				
				this.produto = produto;
				ProdutoHandler.incluirProduto(conexao.getConexao(), produto);
			} else {
				produto.setNome(nomeField.getText());
				produto.setEstoque(Integer.parseInt(estoqueField.getText()));
				produto.setValorCompra(Float.parseFloat(valorCompraField.getText()));
				produto.setPromocao(Float.parseFloat(promocaoField.getText()));
				produto.setMargemLucro(Float.parseFloat(margemLucroField.getText()));
				produto.setGpProduto(grupoProdutoCombo.getSelectionModel().getSelectedItem());
				
				if (tipoModificacao == 1)
					ProdutoHandler.editarProduto(conexao.getConexao(), produto);
				else {
					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alertaRegistroSalvo = new Alert(AlertType.CONFIRMATION);
			alertaRegistroSalvo.setTitle("Erro no Produto");
			alertaRegistroSalvo.setHeaderText("Erro ao alterar/incluir produto");
			alertaRegistroSalvo.setContentText(e.getMessage());
			
			alertaRegistroSalvo.showAndWait();
		}
		
		setClickSalvar(true);
				
		if (tipoModificacao == 0) {
			Alert alertaRegistroSalvo = new Alert(AlertType.CONFIRMATION);
			
			alertaRegistroSalvo.setTitle("Inclusão de Produto");
			alertaRegistroSalvo.setHeaderText("Registro salvo com sucesso");
			alertaRegistroSalvo.setContentText("O novo produto foi salvo com sucesso no banco de dados.");
			
			alertaRegistroSalvo.showAndWait();
		} else if (tipoModificacao == 1) {
			Alert alertaRegistroSalvo = new Alert(AlertType.CONFIRMATION);
			
			alertaRegistroSalvo.setTitle("Edição de Produto");
			alertaRegistroSalvo.setHeaderText("Registro alterado com sucesso");
			alertaRegistroSalvo.setContentText("O produto foi alterado com sucesso no banco de dados.");
			
			alertaRegistroSalvo.showAndWait();
		} else if (tipoModificacao == 2) {
			Alert alertaRegistroSalvo = new Alert(AlertType.CONFIRMATION);
			
			alertaRegistroSalvo.setTitle("Exclusão de Produto");
			alertaRegistroSalvo.setHeaderText("Registro excluído com sucesso");
			alertaRegistroSalvo.setContentText("O produto foi excluído com sucesso no banco de dados.");
			
			alertaRegistroSalvo.showAndWait();
		}		
		
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
		
		adicionarDados(produto);
	}
	
	public void setTipoModificacao(int tipoModificacao) {
		this.tipoModificacao = tipoModificacao;
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
