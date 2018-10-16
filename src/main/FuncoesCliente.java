package main;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.util.Date;

public class FuncoesCliente {

	public static void buscarQuartos() {
		
		for (int i = 0; i < 10; i++) {
			TelaCadastro.modeloQuartos.addRow(new Object[] { i+1, i+11, i+21, i+31, i+41 });
	}
		
	TelaCadastro.tbQuartos = new JTable(TelaCadastro.quartos, TelaCadastro.colunasQuarto);
	
	}

	public static void checarEstadia() {
		String sql = "SELECT saida FROM Reserva";

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date data = new Date();

		try {
			Conexao.abrirConexao();

			PreparedStatement statement = Conexao.conex.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				String saida = result.getString(1);

				if (saida == sdfDate.format(data)) {
					String sql2 = "UPDATE Reserva SET Id_Quarto=\"-\", Entrada=\"-\", Saida \"-\" WHERE Saida>?";

					PreparedStatement statement2 = Conexao.conex.prepareStatement(sql2);

					statement2.setString(1, sdfDate.format(data));

					int rowsUpdated = statement2.executeUpdate();
					if (rowsUpdated > 0) {
						atualizar();
					}
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao checar estadia!");
		}
	}
	
	public static void buscarEndereco() {
		
		WebServiceCep wsCep = WebServiceCep.searchCep(TelaCadastro.tfCep.getText());
		
		if (wsCep.wasSuccessful()) {
		TelaCadastro.tfCidade.setText(wsCep.getCidade() + "/" + wsCep.getUf());
		TelaCadastro.tfBairro.setText(wsCep.getBairro());
		TelaCadastro.tfRua.setText(wsCep.getLogradouro());
		} else {
			JOptionPane.showMessageDialog(null, "CEP Incorreto!");
		}
	}

	/**
	 * Atualiza os dados no JTable.
	 */
	public static void atualizar() {
		String sql = "SELECT  Quarto.Id, Cliente.Nome, Cliente.Rg, Quarto.Ramal, Reserva.Entrada, Reserva.Saida FROM Cliente INNER JOIN Reserva ON Cliente.rg = Reserva.Rg_Cliente INNER JOIN Quarto ON Reserva.Id_Quarto = Quarto.id";

		try {
			Conexao.abrirConexao();

			PreparedStatement statement = Conexao.conex.prepareStatement(sql);

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

					TelaCadastro.modeloCliente.addRow(new Object[] { idQuarto, nomeCliente, rgCliente, ramalQuarto,
							entradaReserva, SaidaReserva });
				}

				TelaCadastro.tbDados = new JTable(TelaCadastro.dados, TelaCadastro.colunasCliente);
			}
			
			checarEstadia();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar dados!");
		}

	}

	/**
	 * Insere os dados inseridos nos elementos da janela no banco.
	 */

	public static void inserir() {
		String sql = "INSERT INTO Cliente VALUES (?,?,?,?,?)";
		String sql2 = "INSERT INTO Endereco (CEP, RgCliente, Cidade, Rua, Bairro, Numero) VALUES (?,?,?,?,?,?)";

		try {
			Conexao.abrirConexao();

			PreparedStatement statement = Conexao.conex.prepareStatement(sql);
			PreparedStatement statement2 = Conexao.conex.prepareStatement(sql2);

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
			
			//--
			
			String cep = TelaCadastro.tfCep.getText();
			statement2.setString(1, cep);
			
			String rgCliente = TelaCadastro.tfRg.getText();
			statement2.setString(2, rgCliente);
			
			String cidade = TelaCadastro.tfCidade.getText();
			statement2.setString(3, cidade);
			
			String rua = TelaCadastro.tfRua.getText();
			statement2.setString(4, rua);
			
			String bairro = TelaCadastro.tfBairro.getText();
			statement2.setString(5, bairro);
			
			String numero = TelaCadastro.tfNu.getText();
			statement2.setString(6, numero);

			int rowsInserted = statement.executeUpdate();
			int rowsInserted2 = statement2.executeUpdate();
			if (rowsInserted > 0 && rowsInserted2 > 0) {
				Main.reserva.setVisible(true);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados!");
		}
	}

	/**
	 * Busca dados no banco de acordo com os dados inseridos nos elementos da
	 * janela.
	 */
	public static void buscar() {
		String aux = "";
		String[] opcoes = { "RG", "Nome", "E-mail", "Phone1", "Phone2" };
		String sql = "SELECT  Quarto.Id, Cliente.Nome, Cliente.Rg, Quarto.Ramal, Reserva.Entrada, Reserva.Saida FROM Cliente INNER JOIN Reserva ON Cliente.rg = Reserva.Rg_Cliente INNER JOIN Quarto ON Reserva.Id_Quarto = Quarto.id WHERE Cliente.Rg=? OR Cliente.Nome=? OR Cliente.Email=? OR Cliente.Telefone=? OR Cliente.Celular=?";

		try {
			Conexao.abrirConexao();

			PreparedStatement statement = Conexao.conex.prepareStatement(sql);

			int selecao = JOptionPane.showOptionDialog(null, "Buscar por: ", "Busca", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
			
			if (selecao == 0) {
				aux = JOptionPane.showInputDialog(null, "Insira o RG:");
				statement.setString(1, aux);
			} else if (selecao == 1) {
				aux = JOptionPane.showInputDialog(null, "Insira o Nome:");
				statement.setString(2, aux);
			} else if (selecao == 2) {
				aux = JOptionPane.showInputDialog(null, "Insira o E-mail:");
				statement.setString(3, aux);
			} else if (selecao == 3) {
				aux = JOptionPane.showInputDialog(null, "Insira o Telefone:");
				statement.setString(4, aux);
			} else if (selecao == 4) {
				aux = JOptionPane.showInputDialog(null, "Insira o Telefone(alternativo):");
				statement.setString(5, aux);
			} else {
				JOptionPane.showMessageDialog(null, "Opcao Invalida!");
			}

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

					TelaCadastro.modeloCliente.addRow(new Object[] { idQuarto, nomeCliente, rgCliente, ramalQuarto,
							entradaReserva, SaidaReserva });
				}

				TelaCadastro.tbDados = new JTable(TelaCadastro.dados, TelaCadastro.colunasCliente);
				Conexao.fecharConexao();
			}

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
			Conexao.abrirConexao();

			PreparedStatement statement = Conexao.conex.prepareStatement(sql);

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

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar dados!");
		}
	}

	/**
	 * Busca e exclui os dados no banco de acordo com os dados inseridos nos
	 * elementos da janela.
	 */
	public static void excluir() {
		String aux = "";
		String[] opcoes = { "Rg", "Nome" };
		String sql = "DELETE FROM Cliente WHERE Rg=? OR Nome=?";

		try {
			Conexao.abrirConexao();
			
			PreparedStatement statement = Conexao.conex.prepareStatement(sql);

			int selecao = JOptionPane.showOptionDialog(null, "Excluir por: ", "Excluir", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
			
			if (selecao == 0) {
				aux = JOptionPane.showInputDialog(null, "Insira o RG:");
				statement.setString(1, aux);
			} else if (selecao == 1) {
				aux = JOptionPane.showInputDialog(null, "Insira o Nome:");
				statement.setString(2, aux);
			} else {
				JOptionPane.showMessageDialog(null, "Opcao Invalida!");
			}

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
