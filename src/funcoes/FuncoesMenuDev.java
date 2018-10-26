package funcoes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import janelas.MenuDev;
import main.Conexao;
import main.Main;

public class FuncoesMenuDev {
	
	public static void inserir() {
		String sql = "INSERT Adcionais VALUES (\"0\",?,?,?)";
		
		try {
			Conexao.abrirConexao();
			
			PreparedStatement statement = Conexao.conex.prepareStatement(sql);
			
			String nome = MenuDev.tfNome.toString();
			statement.setString(1, nome);
			
			String valor = MenuDev.tfValor.toString();
			statement.setString(2, valor);
			
			String qtdMax = MenuDev.tfValor.toString();
			statement.setString(3, qtdMax);
			
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
