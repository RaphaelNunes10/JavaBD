package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Botoes extends Funcoes {

	/**
	 * Atualiza os dados no JTable.
	 */
	public static void atualizar() {
		String sql = "SELECT * FROM Usuario";

		try {
			Connection conex = DriverManager.getConnection(Interface.url, Interface.user, Interface.senha);

			Statement statement = conex.createStatement();
			ResultSet result = statement.executeQuery(sql);

			Interface.modeloCliente.setRowCount(0);

			while (result.next()) {
				String nQuarto = result.getString(1);
				String nome = result.getString(2);
				String rg = result.getString(3);

				Interface.modeloCliente.addRow(new Object[] { nome });
			}

			Interface.tbDados = new JTable(Interface.dados, Interface.colunasCliente);

			conex.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao procurar por dados!");
		}
	}
	
	/**
	 * Insere os dados inseridos nos elementos da janela no banco.
	 */

	public static void inserir() {
		String nome = "", email = "";
		String sql = "INSERT INTO Usuario (Nome,Email) VALUES (?, ?)";

		try {
			Connection conex = DriverManager.getConnection(Interface.url, Interface.user, Interface.senha);

			PreparedStatement statement = conex.prepareStatement(sql);

			nome = Interface.tfNome.getText();
			statement.setString(1, nome);

			email = Interface.tfEmail.getText();
			statement.setString(2, email);

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
		String sql = "SELECT * FROM Usuario WHERE Id=? OR Nome=? OR Email=?";

		try {
			Connection conex = DriverManager.getConnection(Interface.url, Interface.user, Interface.senha);

			PreparedStatement statement = conex.prepareStatement(sql);

			//String id = Interface.tfId.getText();
			//statement.setString(1, id);

			String nome = Interface.tfNome.getText();
			statement.setString(2, nome);

			String email = Interface.tfEmail.getText();
			statement.setString(3, email);

			ResultSet result = statement.executeQuery();

			if (!result.next()) {
				atualizar();
			} else {

				Interface.modeloCliente.setRowCount(0);

				result.beforeFirst();

				while (result.next()) {
					String idUsuario = result.getString(1);
					String nomeUsuario = result.getString(2);
					String emailUsuario = result.getString(3);

					Interface.modeloCliente.addRow(
							new Object[] { idUsuario, nomeUsuario, emailUsuario });
				}

				Interface.tbDados = new JTable(Interface.dados, Interface.colunasCliente);
			}

			conex.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar!");
		}

	}

	/**
	 * Exibe uma mensagem para alterar nome e e-mail do usu�rio.
	 */
	public static void alterar() {
		String aux = "";
		String sql = "UPDATE Usuario SET nome=?, email=? WHERE nome=?";

		try {
			Connection conex = DriverManager.getConnection(Interface.url, Interface.user, Interface.senha);

			PreparedStatement statement = conex.prepareStatement(sql);

			aux = JOptionPane.showInputDialog(null, "Insira o nome do usu�rio a ser alterado: ");
			statement.setString(3, aux);

			aux = JOptionPane.showInputDialog(null, "Insira o novo nome: ");
			statement.setString(1, aux);

			aux = JOptionPane.showInputDialog(null, "Insira o novo e-mail: ");
			statement.setString(2, aux);

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				JOptionPane.showMessageDialog(null, "Usu�rio atualizado com sucesso!");
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

		String sql = "DELETE FROM Usuario WHERE id=? OR nome=? OR email=?";

		try {
			Connection conex = DriverManager.getConnection(Interface.url, Interface.user, Interface.senha);

			PreparedStatement statement = conex.prepareStatement(sql);

			//String id = Interface.tfId.getText();
			//statement.setString(1, id);

			String nome = Interface.tfNome.getText();
			statement.setString(2, nome);

			String email = Interface.tfEmail.getText();
			statement.setString(3, email);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				JOptionPane.showMessageDialog(null, "Usu�rio excluido com sucesso!");
				atualizar();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao exluir dados!");
		}
	}

	/**
	 * Encerra a aplica��o.
	 */
	public static void sair() {
		System.exit(0);
	}

}
