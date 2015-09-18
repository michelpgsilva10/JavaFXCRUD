package mercado;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mercado.handler.ProdutoHandler;
import mercado.model.Produto;
import mercado.util.ConexaoBD;
import mercado.view.DadosPessoaController;
import mercado.view.TelaProdutoController;

public class TelaPrincipal extends Application {

	private Stage primaryStage;
	private BorderPane telaPrincipal;
	private ObservableList<Produto> produtos;
	private ConexaoBD conexao;
	
	public TelaPrincipal() {
		conexao = new ConexaoBD();
		try {
			produtos = ProdutoHandler.buscarProdutos(conexao.getConexao());
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			Alert alertSQL = new Alert(AlertType.ERROR);
			alertSQL.setTitle("Erro ao Buscar Dados");
			alertSQL.setHeaderText("Não foi possível retornar os produtos!");
			alertSQL.setContentText(sqle.getMessage());
			
			alertSQL.showAndWait();
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Gerenciamento de Produtos");
		primaryStage.setResizable(false);		
		
		if (conexao.isConectado()) {
			iniciarTelaPrincipal();
			iniciarTelaProduto();
		}	
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void iniciarTelaPrincipal() {
				
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TelaPrincipal.class.getResource("view/TelaPrincipal.fxml"));
			
			telaPrincipal = (BorderPane) loader.load();
			
			Scene scene = new Scene(telaPrincipal);
			primaryStage.setScene(scene);
			
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void iniciarTelaProduto() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TelaPrincipal.class.getResource("view/ListaProdutos.fxml"));
			
			BorderPane telaProduto = (BorderPane) loader.load();
			
			telaPrincipal.setCenter(telaProduto);
			
			TelaProdutoController controller = loader.getController();
			controller.setTelaPrincipal(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean iniciarDadosProduto(Produto produto) {
				
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TelaPrincipal.class.getResource("view/DadosPessoa.fxml"));
			
			BorderPane dadosPessoa = (BorderPane) loader.load();
			
			Stage dadosPessoaDialog = new Stage();
			dadosPessoaDialog.setTitle("Dados do Produto");
			dadosPessoaDialog.initModality(Modality.WINDOW_MODAL);
			dadosPessoaDialog.initOwner(primaryStage);
			
			Scene scene = new Scene(dadosPessoa);
			dadosPessoaDialog.setScene(scene);
			
			DadosPessoaController controller = loader.getController();
			controller.setDialogStage(dadosPessoaDialog);
			controller.setProduto(produto);		
			
			dadosPessoaDialog.showAndWait();
			
			return controller.isClickSalvar();			
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
			return false;
		} 
	}
	
	public ObservableList<Produto> getListaProdutos() {
		return this.produtos;
	}
	
	public void setListaProdutos(ObservableList<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public Connection getConexao() {
		return this.conexao.getConexao();
	}
	
	public Stage getPrimaryStage() {
		return this.primaryStage;
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}
