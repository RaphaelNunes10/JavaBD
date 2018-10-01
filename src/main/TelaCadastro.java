package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JMenuBar menuBar = new JMenuBar();

	static JMenu mnArquivo = new JMenu("Arquivo");
	static JMenuItem miLogout = new JMenuItem("Logout");
	static JMenuItem miSair = new JMenuItem("Sair");
	
	static JMenu mnCadastros = new JMenu("Cadastros");
	static JMenuItem miEstadia = new JMenuItem("Estadia");
	
	static JMenu mnHistorico = new JMenu("Histórico");
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

	static String[] colunasQuarto = { "1-10", "11-20", "21-30", "31-40", "41-50" };

	static DefaultTableModel modeloQuartos = new DefaultTableModel();

	static JTable tbQuartos = new JTable(modeloQuartos);

	static JScrollPane painelScrollQuartos = new JScrollPane(tbQuartos);

	static JButton btInserir = new JButton("Inserir");
	static JButton btBuscar = new JButton("Buscar");
	static JButton btAlterar = new JButton("Alterar");
	static JButton btExcluir = new JButton("Excluir");
	static JButton btSair = new JButton("Sair");

	static Object[][] dados = { { "", "", "", "", "", "" } };

	static String[] colunasCliente = { "N# Quarto", "Nome", "RG", "Phone Quarto", "Entrada", "Saida" };

	static DefaultTableModel modeloCliente = new DefaultTableModel();

	static JTable tbDados = new JTable(modeloCliente);

	static JScrollPane painelScrollClientes = new JScrollPane(tbDados);

	public TelaCadastro() {
		setTitle("Controle de Clientes");
		setBounds(0, 0, 950, 550);
		setLayout(null);
		
		
		
		criarElementos();

		menuBar.add(mnArquivo);
		mnArquivo.add(miLogout);
		mnArquivo.addSeparator();
		mnArquivo.add(miSair);
		
		menuBar.add(mnCadastros);
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

		modeloQuartos.addColumn("1-10");
		modeloQuartos.addColumn("11-20");
		modeloQuartos.addColumn("21-30");
		modeloQuartos.addColumn("31-40");
		modeloQuartos.addColumn("41-50");

		add(painelScrollQuartos);
		
		FuncoesCliente.buscarQuartos();

		add(btInserir);
		add(btBuscar);
		add(btAlterar);
		add(btExcluir);
		add(btSair);

		modeloCliente.addColumn("N# Quarto");
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
	 * Define as aï¿½ï¿½es de cada elemento na tela.
	 */
	public static void definirEventos() {
		
		miLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.cadastro.dispose();
				Main.login.setVisible(true);
			}
		});
		
		miSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//---
		
		miEstadia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.reserva.setVisible(true);
			}
		});
		
		//---
		
		miClientes_hist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.histCli.setVisible(true);
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
				FuncoesCliente.inserir();
			}
		});

		btBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncoesCliente.buscar();
			}
		});

		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncoesCliente.alterar();
			}
		});

		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncoesCliente.excluir();
			}
		});

		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncoesCliente.sair();
			}
		});

	}

}
