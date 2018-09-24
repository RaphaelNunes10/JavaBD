package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Reserva extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JLabel lbRG = new JLabel("RG: ");
	static JLabel lbEntrada = new JLabel("Entrada: ");
	static JLabel lbSaida = new JLabel("Saida: ");
	static JLabel lbQuarto = new JLabel("N° do quarto: ");
	
	static JTextField tfRG = new JTextField();
	static JTextField tfEntrada = new JTextField();
	static JTextField tfSaida = new JTextField();
	static JTextField tfQuarto = new JTextField();
	
	static JButton btCadastrar = new JButton("Cadastrar");
	static JButton btCancelar = new JButton("Cancelar");
	
	public Reserva() {
		setTitle("Controle de Clientes");
		setBounds(0, 0, 250, 250);
		setLayout(null);
		
		criarElementos();
		
		add(lbRG);
		add(lbEntrada);
		add(lbSaida);
		add(lbQuarto);
		
		add(tfRG);
		add(tfEntrada);
		add(tfSaida);
		add(tfQuarto);
		
		add(btCadastrar);
		add(btCancelar);
		
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((tela.width - getSize().width) / 2,
				(tela.height - getSize().height) / 2);
	}
	
	public static void criarElementos() {
		
		lbRG.setBounds(10, 10, 100, 25);
		tfRG.setBounds(100, 10, 120, 25);
		lbEntrada.setBounds(10, 40, 100, 25);
		tfEntrada.setBounds(100, 40, 120, 25);
		lbSaida.setBounds(10, 70, 100, 25);
		tfSaida.setBounds(100, 70, 120, 25);
		lbQuarto.setBounds(10, 100, 100, 25);
		tfQuarto.setBounds(100, 100, 50, 25);
		
		btCadastrar.setBounds(10, 170, 100, 25);
		btCancelar.setBounds(120, 170, 100, 25);
	}
	
	public static void definirEventos() {
		
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	}

}
