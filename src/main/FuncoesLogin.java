package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class FuncoesLogin {
	
	public static void sair() {
		System.exit(0);
	}
	
	public static void logar() {
		
		String sql = "SELECT Nome, Senha FROM Usuario WHERE Nome = ? AND Senha = ?";
		
		try {
			Conexao.abrirConexao();
			
			PreparedStatement statement = Conexao.conex.prepareStatement(sql);
			
			String usuario = Login.tfUsuario.getText();
			statement.setString(1, usuario);
			
			char[] senha = Login.tfSenha.getPassword();
			statement.setString(2, String.valueOf(senha));
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				Main.login.dispose();
				Main.cadastro.setVisible(true);
				FuncoesCliente.atualizar();
			} else {
				JOptionPane.showMessageDialog(null, "Usuario nao existe!");
				Conexao.fecharConexao();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
