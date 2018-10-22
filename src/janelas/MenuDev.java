package janelas;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MenuDev extends JFrame {
	
	public static JLabel lbAdcionais = new JLabel("Adcionais");
	
	public static JLabel lbNome = new JLabel("Nome");
	public static JLabel lbValor = new JLabel("Valor");
	public static JLabel lbMax = new JLabel("Quantidade Maxima");
	
	public static JTextField tfNome = new JTextField();
	public static JTextField tfValor = new JTextField();
	public static JTextField tfMax = new JTextField();
	
	public MenuDev() {
		
		criarElementos();
		
		setLayout(null);
		setBounds(0, 0, 500, 500);
		
		add(lbAdcionais);
		
		add(lbNome);
		add(tfNome);
		
		add(lbValor);
		add(tfValor);
		
		add(lbNome);
		add(tfNome);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((tela.width - getSize().width) / 2,
				(tela.height - getSize().height) / 2);
		
	}
	
	public static void criarElementos() {
		
		lbAdcionais.setBounds(10, 10, 200, 25);
		
		lbNome.setBounds(10, 30, 200, 25);
		tfNome.setBounds(10, 50, 225, 25);
		
		lbValor.setBounds(10, 70, 200, 25);
		tfValor.setBounds(10, 100, 225, 25);
		
		lbMax.setBounds(10, 110, 200, 25);
		tfMax.setBounds(10, 130, 225, 25);
		
	}
	
	public static void definirEventos() {
		
	}

}
