package funcoes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import janelas.Reserva;
import janelas.TelaCadastro;
import main.Conexao;
import main.Main;

public class FuncoesReserva {
	
	public static boolean confere = false;
	
	public static void atualizarAdcionais() {
		String sql = "SELECT * FROM Adcionais";
		
		try {
			Conexao.abrirConexao();
			
			PreparedStatement statement = Conexao.conex.prepareStatement(sql);

			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
			
			Reserva.modeloAdcionais.setRowCount(0);

			result.beforeFirst();
			
			while (result.next()) {
				String nome = result.getString(2);
				String valor = result.getString(3);
				String qtd = "";

				Reserva.modeloAdcionais.addRow(new Object[] { nome, valor, qtd });
			}

			Reserva.tbAdcionais = new JTable(Reserva.adcionais, Reserva.colunasAdcionais);
			
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conferir adcionais!");
		}
	}

	public static void conferir() {
		String sql = "SELECT Rg FROM Cliente WHERE Rg=?";

		try {
			Conexao.abrirConexao();
			
			PreparedStatement statement = Conexao.conex.prepareStatement(sql);

			String rg = Reserva.tfRg.getText();
			statement.setString(1, rg);

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				confere = true;
			} else {
				confere = false;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conferir cliente!");
		}
	}

	public static void inserir() {
		String sql = "INSERT INTO Reserva (Rg_Cliente, Entrada, Saida, Id_Quarto) VALUES (?,?,?,?)";

		if (confere = true) {
			try {
				
				Conexao.abrirConexao();

				PreparedStatement statement = Conexao.conex.prepareStatement(sql);

				String rg = Reserva.tfRg.getText();
				statement.setString(1, rg);
				
				String entrada = Reserva.tfEntrada.getText();
				statement.setString(2, entrada);

				String saida = Reserva.tfSaida.getText();
				statement.setString(3, saida);
				
				String quarto = Reserva.tfQuarto.getText();
				statement.setString(4, quarto);

				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
					Main.reserva.dispose();
					FuncoesCliente.atualizar();
				}

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao inserir dados!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Usuário não existe!");
		}
	}
	
	public static void inserirAdcionais() {
		String sql = "";

		if (confere = true) {
			try {
				
				Conexao.abrirConexao();

				PreparedStatement statement = Conexao.conex.prepareStatement(sql);


				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
					Main.reserva.dispose();
					FuncoesCliente.atualizar();
				}

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao inserir dados!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Usuário não existe!");
		}
	}
	
}
