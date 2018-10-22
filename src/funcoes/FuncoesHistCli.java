package funcoes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;

import janelas.HistoricoClientes;
import main.Conexao;

public class FuncoesHistCli {
	
	public static void buscar() {
		String sql = "SELECT Cliente.Rg, Cliente.Nome, Cliente.Email, Cliente.Telefone, Cliente.Celular, Reserva.Entrada, Reserva.Saida FROM Cliente INNER JOIN Reserva ON Cliente.Rg = Reserva.Rg_Cliente WHERE RG=?";
		
		Conexao.abrirConexao();

		try {
			PreparedStatement statement = Conexao.conex.prepareStatement(sql);
			
			String rg = HistoricoClientes.tfPesquisa.getText();
			statement.setString(1, rg);
			
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				HistoricoClientes.lbCampoRg.setText(result.getString(1));
				HistoricoClientes.lbCampoNome.setText(result.getString(2));
				HistoricoClientes.lbCampoEmail.setText(result.getString(3));
				HistoricoClientes.lbCampoPhone.setText(result.getString(4));
				HistoricoClientes.lbCampoPhone2.setText(result.getString(5));
				
				HistoricoClientes.modeloEstadia.setRowCount(0);

				result.beforeFirst();
				
				while (result.next()) {
					String entrada = result.getString(6);
					String saida = result.getString(7);
					
					HistoricoClientes.modeloEstadia.addRow(new Object[] { entrada, saida });
				}
				
				HistoricoClientes.tbEstadia = new JTable(HistoricoClientes.estadia, HistoricoClientes.colunasEstadia);
				
			} else {
				HistoricoClientes.lbCampoRg.setText("-");
				HistoricoClientes.lbCampoNome.setText("-");
				HistoricoClientes.lbCampoEmail.setText("-");
				HistoricoClientes.lbCampoPhone.setText("-");
				HistoricoClientes.lbCampoPhone2.setText("-");
				
				HistoricoClientes.modeloEstadia.setRowCount(0);

				result.beforeFirst();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
	}

}
