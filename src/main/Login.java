package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
	
private static final long serialVersionUID = 1L;
	
	static JButton btEntrar = new JButton("Entrar");
	static JButton btSair = new JButton("Sair");
	
	static JLabel lbLogin = new JLabel("Hotel Sergio do Mar");
	static JLabel lbUsuario = new JLabel("Usuário: ");
	static JLabel lbSenha = new JLabel("Senha: ");
	
	static JTextField tfUsuario = new JTextField();
	static JPasswordField tfSenha = new JPasswordField();
	
	public Login() {
		
		criarElementos();
		
		setLayout(null);
		setBounds(0, 0, 410, 260);
		
		add(lbLogin);
		add(lbUsuario);
		add(lbSenha);
		add(tfUsuario);
		add(tfSenha);
		add(btEntrar);
		add(btSair);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((tela.width - getSize().width) / 2,
				(tela.height - getSize().height) / 2);
	}

	public static void criarElementos() {
		
		lbLogin.setBounds(50, 5, 300, 80);
		lbUsuario.setBounds(40, 90, 100, 30);
		lbSenha.setBounds(40, 130, 100, 30);
		tfUsuario.setBounds(140, 90, 230, 30);
		tfSenha.setBounds(140, 130, 230, 30);
		btEntrar.setBounds(30, 180, 110, 30);
		btSair.setBounds(270, 180, 110, 30);
		
	}
	
	public static void definirEventos() {
		
		btEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncoesLogin.logar();
			}
		});
		
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncoesLogin.sair();
			}
		});
		
	}

}
