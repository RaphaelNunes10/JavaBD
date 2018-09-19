package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Interface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static String driver = "com.mysql.jdbc.Driver";
	final static String url = "jdbc:mysql://localhost:3306/empregadora"; // alterar conex�o e porta de acordo com o
																			// server mySQL sendo utilizado no momento.
	final static String user = "root";
	final static String senha = "root";

	static JMenuBar menuBar = new JMenuBar();

	static JMenu mnArquivo = new JMenu("Arquivo");
	static JMenuItem miLogin = new JMenuItem("Login");
	static JMenuItem miLogout = new JMenuItem("Logout");
	static JMenuItem miSair = new JMenuItem("Sair");
	
	static JMenu mnCadastros = new JMenu("Cadastros");
	static JMenuItem miClientes = new JMenuItem("Clientes");
	static JMenuItem miEstadia = new JMenuItem("Estadia");
	
	static JMenu mnHistorico = new JMenu("Hist�rico");
	static JMenuItem miClientes_hist = new JMenuItem("Clientes");
	
	static JMenu mnSobre = new JMenu("Sobre");

	static JLabel lbNome = new JLabel("Nome: ");
	static JLabel lbRg = new JLabel("Rg: ");
	static JLabel lbTelefone1 = new JLabel("Phone: ");
	static JLabel lbTelefone2 = new JLabel("Phone(alternativo): ");
	static JLabel lbEmail = new JLabel("Email: ");

	static JTextField tfNome = new JTextField(150);
	static JTextField tfRg = new JTextField(14);
	static JTextField tfTelefone1 = new JTextField(20);
	static JTextField tfTelefone2 = new JTextField(20);
	static JTextField tfEmail = new JTextField(150);

	static Object[][] quartos = { { "", "", "", "", "" } };

	static String[] colunasQuarto = { "0-10", "11-20", "21-30", "31-40", "41-50" };

	static DefaultTableModel modeloQuartos = new DefaultTableModel();

	static JTable tbQuartos = new JTable(modeloQuartos);

	static JScrollPane painelScrollQuartos = new JScrollPane(tbQuartos);

	static JButton btInserir = new JButton("Inserir");
	static JButton btBuscar = new JButton("Buscar");
	static JButton btAlterar = new JButton("Alterar");
	static JButton btExcluir = new JButton("Excluir");
	static JButton btSair = new JButton("Sair");

	static Object[][] dados = { { "", "", "", "", "", "" } };

	static String[] colunasCliente = { "Nº Quarto", "Nome", "RG", "Phone Quarto", "Entrada", "Saida" };

	static DefaultTableModel modeloCliente = new DefaultTableModel();

	static JTable tbDados = new JTable(modeloCliente);

	static JScrollPane painelScrollClientes = new JScrollPane(tbDados);

	public Interface() {
		setTitle("Controle de Clientes");
		setBounds(0, 0, 950, 550);
		setLayout(null);
		
		JOptionPane.showMessageDialog(null, "Conexão aberta!");

		criarElementos();

		menuBar.add(mnArquivo);
		mnArquivo.add(miLogin);
		mnArquivo.add(miLogout);
		mnArquivo.addSeparator();
		mnArquivo.add(miSair);
		
		menuBar.add(mnCadastros);
		mnCadastros.add(miClientes);
		mnCadastros.add(miEstadia);
		
		menuBar.add(mnHistorico);
		mnHistorico.add(miClientes_hist);
		
		menuBar.add(mnSobre);

		setJMenuBar(menuBar);

		add(lbNome);
		add(lbRg);
		add(lbTelefone1);
		add(lbTelefone2);
		add(lbEmail);

		add(tfNome);
		add(tfRg);
		add(tfTelefone1);
		add(tfTelefone2);
		add(tfEmail);

		modeloQuartos.addColumn("0-10");
		modeloQuartos.addColumn("11-20");
		modeloQuartos.addColumn("21-30");
		modeloQuartos.addColumn("31-40");
		modeloQuartos.addColumn("41-50");

		add(painelScrollQuartos);

		add(btInserir);
		add(btBuscar);
		add(btAlterar);
		add(btExcluir);
		add(btSair);

		modeloCliente.addColumn("Nº Quarto");
		modeloCliente.addColumn("Nome");
		modeloCliente.addColumn("RG");
		modeloCliente.addColumn("Phone Quarto");
		modeloCliente.addColumn("Entrada");
		modeloCliente.addColumn("Saida");

		add(painelScrollClientes);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((tela.width - getSize().width) / 2,
				(tela.height - getSize().height) / 2);
		setVisible(true);

	}

	/**
	 * Cria e posiciona os elementos da janela.
	 */
	public static void criarElementos() {
		lbNome.setBounds(10, 10, 100, 25);
		tfNome.setBounds(150, 10, 322, 25);
		lbRg.setBounds(10, 40, 100, 25);
		tfRg.setBounds(150, 40, 322, 25);
		lbTelefone1.setBounds(10, 70, 100, 25);
		tfTelefone1.setBounds(150, 70, 322, 25);
		lbTelefone2.setBounds(10, 100, 400, 25);
		tfTelefone2.setBounds(150, 100, 322, 25);
		lbEmail.setBounds(10, 130, 100, 25);
		tfEmail.setBounds(150, 130, 322, 25);

		tbQuartos.setBounds(500, 10, 422, 170);
		tbQuartos.setFillsViewportHeight(true);

		painelScrollQuartos.setBounds(500, 10, 422, 170);

		btInserir.setBounds(10, 160, 90, 25);
		btBuscar.setBounds(103, 160, 90, 25);
		btAlterar.setBounds(196, 160, 90, 25);
		btExcluir.setBounds(289, 160, 90, 25);
		btSair.setBounds(382, 160, 90, 25);

		tbDados.setBounds(10, 190, 913, 290);
		tbDados.setFillsViewportHeight(true);

		painelScrollClientes.setBounds(10, 190, 913, 290);
	}

	/**
	 * Define as a��es de cada elemento na tela.
	 */
	public static void definirEventos() {
		
		miLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		miLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		miSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//---
		
		miClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		miEstadia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame reserva = new Reserva();
			}
		});
		
		//---
		
		miClientes_hist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//---
		
		mnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//---

		btInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Botoes.inserir();
			}
		});

		btBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Botoes.buscar();
			}
		});

		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Botoes.alterar();
			}
		});

		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Botoes.excluir();
			}
		});

		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Botoes.sair();
			}
		});

	}

}