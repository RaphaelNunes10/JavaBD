package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class FuncoesCliente {

	/**
	 * Atualiza os dados no JTable.
	 */
	public static void atualizar() {
		String sql = "SELECT  Quarto.Id, Cliente.Nome, Cliente.Rg, Quarto.Ramal, Reserva.Entrada, Reserva.Saida FROM Cliente INNER JOIN Reserva ON Cliente.rg = Reserva.Rg_Cliente INNER JOIN Quarto ON Reserva.Id_Quarto = Quarto.id";

		try {
			Connection conex = DriverManager.getConnection(Conexao.url, Conexao.user, Conexao.senha);

			PreparedStatement statement = conex.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			if (result.next()) {

				TelaCadastro.modeloCliente.setRowCount(0);

				result.beforeFirst();

				while (result.next()) {
					String idQuarto = result.getString(1);
					String nomeCliente = result.getString(2);
					String rgCliente = result.getString(3);
					String ramalQuarto = result.getString(4);
					String entradaReserva = result.getString(5);
					String SaidaReserva = result.getString(6);

					TelaCadastro.modeloCliente.addRow(
							new Object[] { idQuarto, nomeCliente, rgCliente, ramalQuarto, entradaReserva, SaidaReserva });
				}

				TelaCadastro.tbDados = new JTable(TelaCadastro.dados, TelaCadastro.colunasCliente);
			}

			conex.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar dados!");
		}
		
	}
	
	/**
	 * Insere os dados inseridos nos elementos da janela no banco.
	 */

	public static void inserir() {
		String sql = "INSERT INTO Cliente VALUES (?,?,?,?,?)";

		try {
			Connection conex = DriverManager.getConnection(Conexao.url, Conexao.user, Conexao.senha);

			PreparedStatement statement = conex.prepareStatement(sql);
			
			String rg = TelaCadastro.tfRg.getText();
			statement.setString(1, rg);

			String nome = TelaCadastro.tfNome.getText();
			statement.setString(2, nome);

			String email = TelaCadastro.tfEmail.getText();
			statement.setString(3, email);
			
			String telefone1 = TelaCadastro.tfTelefone1.getText();
			statement.setString(4, telefone1);
			
			String telefone2 = TelaCadastro.tfTelefone2.getText();
			statement.setString(5, telefone2);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
				atualizar();
			}

			conex.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados!");
		}
	}

	/**
	 * Busca dados no banco de acordo com os dados inseridos nos elementos da janela.
	 */
	public static void buscar() {
		String sql = "SELECT  Quarto.Id, Cliente.Nome, Cliente.Rg, Quarto.Ramal, Reserva.Entrada, Reserva.Saida FROM Cliente INNER JOIN Reserva ON Cliente.rg = Reserva.Rg_Cliente INNER JOIN Quarto ON Reserva.Id_Quarto = Quarto.id WHERE Cliente.Rg=? OR Cliente.Nome=?";

		try {
			Connection conex = DriverManager.getConnection(Conexao.url, Conexao.user, Conexao.senha);

			PreparedStatement statement = conex.prepareStatement(sql);

			String rg = TelaCadastro.tfRg.getText();
			statement.setString(1, rg);

			String nome = TelaCadastro.tfNome.getText();
			statement.setString(2, nome);

			ResultSet result = statement.executeQuery();

			if (!result.next()) {
				atualizar();
			} else {

				TelaCadastro.modeloCliente.setRowCount(0);

				result.beforeFirst();

				while (result.next()) {
					String idQuarto = result.getString(1);
					String nomeCliente = result.getString(2);
					String rgCliente = result.getString(3);
					String ramalQuarto = result.getString(4);
					String entradaReserva = result.getString(5);
					String SaidaReserva = result.getString(6);

					TelaCadastro.modeloCliente.addRow(
							new Object[] { idQuarto, nomeCliente, rgCliente, ramalQuarto, entradaReserva, SaidaReserva });
				}

				TelaCadastro.tbDados = new JTable(TelaCadastro.dados, TelaCadastro.colunasCliente);
			}

			conex.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar!");
		}

	}

	/**
	 * Exibe uma mensagem para alterar nome e e-mail do usuario.
	 */
	public static void alterar() {
		String aux = "";
		String sql = "UPDATE Cliente SET Nome=?, Email=?, Telefone=?, Celular=? WHERE Rg=?";

		try {
			Connection conex = DriverManager.getConnection(Conexao.url, Conexao.user, Conexao.senha);

			PreparedStatement statement = conex.prepareStatement(sql);

			aux = JOptionPane.showInputDialog(null, "Insira o Rg do cliente a ser alterado: ");
			statement.setString(5, aux);

			aux = JOptionPane.showInputDialog(null, "Insira o novo nome: ");
			statement.setString(1, aux);

			aux = JOptionPane.showInputDialog(null, "Insira o novo e-mail: ");
			statement.setString(2, aux);
			
			aux = JOptionPane.showInputDialog(null, "Insira o novo phone: ");
			statement.setString(3, aux);
			
			aux = JOptionPane.showInputDialog(null, "Insira o novo phone alternativo: ");
			statement.setString(4, aux);

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso!");
				atualizar();
			}

			conex.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar dados!");
		}
	}

	/**
	 * Busca e exclui os dados no banco de acordo com os dados inseridos nos elementos da janela.
	 */
	public static void excluir() {

		String sql = "DELETE FROM Cliente WHERE Rg=?";

		try {
			Connection conex = DriverManager.getConnection(Conexao.url, Conexao.user, Conexao.senha);

			PreparedStatement statement = conex.prepareStatement(sql);

			String rg = TelaCadastro.tfRg.getText();
			statement.setString(1, rg);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso!");
				atualizar();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao exluir dados!");
		}
	}

	/**
	 * Encerra a aplicacao.
	 */
	public static void sair() {
		System.exit(0);
	}

}
