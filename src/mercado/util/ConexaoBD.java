package mercado.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConexaoBD {

	private Connection conexao;
	private boolean conectado = false;
	
	public ConexaoBD() {
		String url = "jdbc:postgresql://localhost/DSBBD";
		
		try {
			Class.forName("org.postgresql.Driver");
			this.conexao = DriverManager.getConnection(url, "postgres", "15109");			
			this.conectado = true;
		} catch (ClassNotFoundException cnfe) {
			// TODO Auto-generated catch block
			Alert alertFalhaClasse = new Alert(AlertType.ERROR);
			alertFalhaClasse.setTitle("Falha na Conexão");
			alertFalhaClasse.setHeaderText("A conexão com o banco de dados falhou");
			alertFalhaClasse.setContentText(cnfe.getMessage());
			
			alertFalhaClasse.showAndWait();
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			Alert alertFalhaClasse = new Alert(AlertType.ERROR);
			alertFalhaClasse.setTitle("Falha na Conexão");
			alertFalhaClasse.setHeaderText("A conexão com o banco de dados falhou");
			alertFalhaClasse.setContentText(sqle.getMessage());
			
			alertFalhaClasse.showAndWait();
		}
	}
	
	public Connection getConexao() {
		return this.conexao;
	}
	
	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
	
	public boolean isConectado() {
		return conectado;
	}
	
	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}
	
}
