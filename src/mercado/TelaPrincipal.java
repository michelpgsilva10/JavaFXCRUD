package mercado;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mercado.util.ConexaoBD;

public class TelaPrincipal extends Application {

	private Stage primaryStage;
	private BorderPane telaPrincipal;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Gerenciamento de Produtos");
		primaryStage.setResizable(false);
		
		ConexaoBD conexao = new ConexaoBD();
		
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
			loader.setLocation(TelaPrincipal.class.getResource("view/TelaProduto.fxml"));
			
			BorderPane telaProduto = (BorderPane) loader.load();
			
			telaPrincipal.setCenter(telaProduto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
