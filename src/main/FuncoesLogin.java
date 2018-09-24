package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncoesLogin {
	
	static TelaCadastro interfce = new TelaCadastro();
	
	public static void sair() {
		System.exit(0);
	}
	
	public static void logar() {
		String sql = "SELECT usuario, senha FROM Usuario WHERE usuario = ? AND senha = ?";
		
		try {
			Connection conex = DriverManager.getConnection(Conexao.url, Conexao.user, Conexao.senha);
			
			PreparedStatement statement = conex.prepareStatement(sql);
			
			String usuario = Login.tfUsuario.getText();
			statement.setString(1, usuario);
			
			String senha = Login.tfSenha.getText();
			statement.setString(2, senha);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				interfce.setVisible(true);
				FuncoesCliente.atualizar();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
