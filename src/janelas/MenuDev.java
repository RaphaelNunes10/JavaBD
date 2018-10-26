package janelas;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MenuDev extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JLabel lbAdcionais = new JLabel("__Adcionais______________________");
	
	public static JLabel lbNome = new JLabel("Nome");
	public static JLabel lbValor = new JLabel("Valor(%)");
	public static JLabel lbMax = new JLabel("Quantidade Maxima");
	
	public static JTextField tfNome = new JTextField();
	public static JTextField tfValor = new JTextField();
	public static JTextField tfMax = new JTextField();
	
	static JButton btCadastrar = new JButton("Cadastrar");
	static JButton btAlterar = new JButton("Alterar");
	static JButton btCancelar = new JButton("Cancelar");
	
	public MenuDev() {
		
		criarElementos();
		
		setLayout(null);
		setBounds(0, 0, 500, 500);
		
		add(lbAdcionais);
		
		add(lbNome);
		add(tfNome);
		
		add(lbValor);
		add(tfValor);
		
		add(lbMax);
		add(tfMax);
		
		add(btCadastrar);
		add(btAlterar);
		add(btCancelar);
		
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((tela.width - getSize().width) / 2,
				(tela.height - getSize().height) / 2);
		
	}
	
	public static void criarElementos() {
		
		lbAdcionais.setBounds(10, 10, 235, 25);
		
		lbNome.setBounds(10, 30, 200, 25);
		tfNome.setBounds(10, 50, 225, 25);
		
		lbValor.setBounds(10, 70, 200, 25);
		tfValor.setBounds(10, 90, 225, 25);
		
		lbMax.setBounds(10, 110, 200, 25);
		tfMax.setBounds(10, 130, 225, 25);
		
		btCadastrar.setBounds(30, 420, 100, 25);
		btAlterar.setBounds(190, 420, 100, 25);
		btCancelar.setBounds(350, 420, 100, 25);
		
	}
	
	public static void definirEventos() {
		
	}

}
