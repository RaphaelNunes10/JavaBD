package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HistoricoClientes extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JTextField tfPesquisa = new JTextField();
	static JButton btPesquisar = new JButton("Pesquisar");
	
	static JLabel lbRg =  new JLabel("RG: ");
	static JLabel lbCampoRg =  new JLabel("-");
	
	static JLabel lbNome = new JLabel("Nome: ");
	static JLabel lbCampoNome = new JLabel("-");
	
	static JLabel lbEmail =  new JLabel("E-mail: ");
	static JLabel lbCampoEmail =  new JLabel("-");
	
	static JLabel lbPhone =  new JLabel("Phone: ");
	static JLabel lbCampoPhone =  new JLabel("-");
	
	static JLabel lbPhone2 =  new JLabel("Phone (alternativo): ");
	static JLabel lbCampoPhone2 =  new JLabel("-");
	
	static JLabel lbEndereco = new JLabel("Endereco: ");
	static JLabel lbCampoEndereco = new JLabel("-");
	
	static Object[][] estadia = { { "", "" } };
	
	static String[] colunasEstadia = { "Entrada", "Saída" };
	
	static DefaultTableModel modeloEstadia = new DefaultTableModel();
	
	static JTable tbEstadia = new JTable(modeloEstadia);
	
	static JScrollPane painelScrollEstadia = new JScrollPane(tbEstadia);
	
	public HistoricoClientes() {
		
		setTitle("Controle de Clientes");
		setBounds(0, 0, 500, 300);
		setLayout(null);
		
		criarElementos();
		
		add(tfPesquisa);
		add(btPesquisar);
		
		add(lbRg);
		add(lbCampoRg);
		
		add(lbNome);
		add(lbCampoNome);
		
		add(lbEmail);
		add(lbCampoEmail);
		
		add(lbPhone);
		add(lbCampoPhone);
		
		add(lbPhone2);
		add(lbCampoPhone2);
		
		add(lbEndereco);
		add(lbCampoEndereco);
		
		modeloEstadia.addColumn("Entrada");
		modeloEstadia.addColumn("Saída");
		
		add(painelScrollEstadia);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((tela.width - getSize().width) / 2,
				(tela.height - getSize().height) / 2);
	}
	
	public static void criarElementos() {
		
		tfPesquisa.setBounds(10, 10, 350, 25);
		btPesquisar.setBounds(360, 10, 100, 25);
		
		lbRg.setBounds(10, 40, 200, 25);
		lbCampoRg.setBounds(130, 40, 120, 25);
		
		lbNome.setBounds(10, 70, 200, 25);
		lbCampoNome.setBounds(130, 70, 120, 25);
		
		lbEmail.setBounds(10, 100, 200, 25);
		lbCampoEmail.setBounds(130, 100, 120, 25);
		
		lbPhone.setBounds(10, 130, 200, 25);
		lbCampoPhone.setBounds(130, 130, 120, 25);
		
		lbPhone2.setBounds(10, 160, 200, 25);
		lbCampoPhone2.setBounds(130, 160, 120, 25);
		
		lbEndereco.setBounds(10, 190, 200, 25);
		lbCampoEndereco.setBounds(130, 190, 200, 25);
		
		tbEstadia.setBounds(300, 40, 160, 200);
		
		painelScrollEstadia.setBounds(300, 40, 160, 200);
		
	}
	
	public static void definirEventos() {
		
		btPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncoesHistCli.buscar();
			}
		});
		
	}
	
	

}
