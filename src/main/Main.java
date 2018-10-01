package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;

	/*
	 * CRIAR TABELA:
	 * 
	 * Usuario ----------------------------------------------
	 * id int(11) NOT NULL
	 * PRIMARY KEY AUTO_INCREMENT Nome varchar(50) NOT NULL Email varchar(50) NOT
	 * NULL Posicao varchar(50) NOT NULL Cargo varchar(50) NOT NULL
	 * ----------------------------------------------
	 */
	
	static JFrame login =  new Login();
	static JFrame cadastro = new TelaCadastro();
	static JFrame reserva = new Reserva();
	static JFrame histCli = new HistoricoClientes();

	public static void main(String[] args) {
		
		try {
			
			Class.forName(Conexao.driver);

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {			
					login.setVisible(true);
					Login.definirEventos();
					TelaCadastro.definirEventos();
					Reserva.definirEventos();
					HistoricoClientes.definirEventos();
				}
			});
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Driver não encontrado");
		}
	}
}
