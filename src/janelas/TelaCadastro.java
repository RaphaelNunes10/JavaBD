package janelas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import funcoes.FuncoesCliente;
import funcoes.FuncoesReserva;
import main.Main;

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
	static JMenuItem miReserva = new JMenuItem("Reserva");
	static JMenuItem miProduto = new JMenuItem("Produto");
	static JMenuItem miColaboradores = new JMenuItem("Colaboradores");
	
	static JMenu mnHistorico = new JMenu("Historico");
	static JMenuItem miClientes_hist = new JMenuItem("Clientes");
	
	static JMenu mnDesenvolvedor = new JMenu("Desenvolvedor");
	static JMenuItem miAdcionais = new JMenuItem("Adcionais");

	static JLabel lbNome = new JLabel("Nome ");
	static JLabel lbRg = new JLabel("Rg ");
	static JLabel lbTelefone1 = new JLabel("Phone ");
	static JLabel lbTelefone2 = new JLabel("Phone(alternativo) ");
	static JLabel lbEmail = new JLabel("Email ");
	
	static JLabel lbCep = new JLabel("CEP ");
	static JLabel lbCidade = new JLabel("Cidade/UF ");
	static JLabel lbBairro = new JLabel("Bairro ");
	static JLabel lbRua = new JLabel("Rua ");
	static JLabel lbNu = new JLabel("n# ");

	public static JTextField tfNome = new JTextField(150);
	public static JTextField tfRg = new JTextField(14);
	public static JTextField tfTelefone1 = new JTextField(20);
	public static JTextField tfTelefone2 = new JTextField(20);
	public static JTextField tfEmail = new JTextField(150);
	
	public static JTextField tfCep = new JTextField(20);
	public static JTextField tfCidade = new JTextField(50);
	public static JTextField tfBairro = new JTextField(150);
	public static JTextField tfRua = new JTextField(150);
	public static JTextField tfNu = new JTextField(10);
	
	static JButton btBuscaCep = new JButton("Buscar");

	public static Object[][] quartos = { { "", "", "", "", "" } };

	public static String[] colunasQuarto = { "1-10", "11-20", "21-30", "31-40", "41-50" };

	public static DefaultTableModel modeloQuartos = new DefaultTableModel();

	public static JTable tbQuartos = new JTable(modeloQuartos);

	static JScrollPane painelScrollQuartos = new JScrollPane(tbQuartos);

	static JButton btInserir = new JButton("Inserir");
	static JButton btBuscar = new JButton("Buscar");
	static JButton btAlterar = new JButton("Editar");
	static JButton btExcluir = new JButton("Excluir");
	static JButton btSair = new JButton("Sair");

	public static Object[][] dados = { { "", "", "", "", "", "" } };

	public static String[] colunasCliente = { "N# Quarto", "Nome", "RG", "Phone Quarto", "Entrada", "Saida" };

	public static DefaultTableModel modeloCliente = new DefaultTableModel();

	public static JTable tbDados = new JTable(modeloCliente);

	static JScrollPane painelScrollClientes = new JScrollPane(tbDados);

	public TelaCadastro() {
		setTitle("Controle de Clientes");
		setBounds(0, 0, 950, 600);
		setLayout(null);

		criarElementos();

		menuBar.add(mnArquivo);
		mnArquivo.add(miLogout);
		mnArquivo.addSeparator();
		mnArquivo.add(miSair);
		
		menuBar.add(mnCadastros);
		mnCadastros.add(miReserva);
		mnCadastros.add(miProduto);
		mnCadastros.add(miColaboradores);
		
		menuBar.add(mnHistorico);
		mnHistorico.add(miClientes_hist);
		
		menuBar.add(mnDesenvolvedor);
		mnDesenvolvedor.add(miAdcionais);

		setJMenuBar(menuBar);

		add(lbNome);
		add(lbRg);
		add(lbTelefone1);
		add(lbTelefone2);
		add(lbEmail);
		
		add(lbCep);
		add(lbCidade);
		add(lbBairro);
		add(lbRua);
		add(lbNu);
		
		add(btBuscaCep);

		add(tfNome);
		add(tfRg);
		add(tfTelefone1);
		add(tfTelefone2);
		add(tfEmail);
		
		add(tfCep);
		add(tfCidade);
		add(tfBairro);
		add(tfRua);
		add(tfNu);

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
		lbNome.setBounds(15, 10, 200, 25);
		tfNome.setBounds(10, 30, 225, 25);
		lbRg.setBounds(15, 50, 200, 25);
		tfRg.setBounds(10, 70, 225, 25);
		lbTelefone1.setBounds(15, 90, 200, 25);
		tfTelefone1.setBounds(10, 110, 225, 25);
		lbTelefone2.setBounds(15, 130, 200, 25);
		tfTelefone2.setBounds(10, 150, 225, 25);
		lbEmail.setBounds(15, 170, 200, 25);
		tfEmail.setBounds(10, 190, 225, 25);
		
		lbCep.setBounds(255, 10, 200, 25);
		tfCep.setBounds(250, 30, 200, 25);
		
		btBuscaCep.setBounds(450, 30, 25, 25);
		
		lbCidade.setBounds(255, 50, 200, 25);
		tfCidade.setBounds(250, 70, 225, 25);
		lbBairro.setBounds(255, 90, 200, 25);
		tfBairro.setBounds(250, 110, 225, 25);
		lbRua.setBounds(255, 130, 200, 25);
		tfRua.setBounds(250, 150, 225, 25);
		lbNu.setBounds(255, 170, 200, 25);
		tfNu.setBounds(250, 190, 225, 25);

		tbQuartos.setBounds(500, 30, 422, 183);
		tbQuartos.setFillsViewportHeight(true);

		painelScrollQuartos.setBounds(500, 30, 422, 183);

		btInserir.setBounds(10, 225, 90, 25);
		btBuscar.setBounds(103, 225, 90, 25);
		btAlterar.setBounds(196, 225, 90, 25);
		btExcluir.setBounds(289, 225, 90, 25);
		btSair.setBounds(382, 225, 90, 25);

		tbDados.setBounds(10, 260, 913, 265);
		tbDados.setFillsViewportHeight(true);

		painelScrollClientes.setBounds(10, 260, 913, 265);
	}

	/**
	 * Define as a��es de cada elemento na tela.
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
		
		miReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.reserva.setVisible(true);
				FuncoesReserva.atualizarAdcionais();
			}
		});
		
		miProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		miColaboradores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//---
		
		miClientes_hist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.histCli.setVisible(true);
			}
		});
		
		//---
		
		miAdcionais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.menuDev.setVisible(true);
			}
		});
		
		//---
		
		btBuscaCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncoesCliente.buscarEndereco();
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
