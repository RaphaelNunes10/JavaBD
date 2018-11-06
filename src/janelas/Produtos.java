package janelas;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.Conexao;

public class Produtos extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JLabel lbNome = new JLabel("Nome");
	public static JLabel lbValor = new JLabel("Valor");
	public static JLabel lbQtd = new JLabel("Quantidade");
	
	public static JTextField tfNome = new JTextField();
	public static JTextField tfValor = new JTextField();
	public static JTextField tfQtd = new JTextField();
	
	public static JButton btInserir = new JButton("Inserir");
	
	public Produtos() {
		setTitle("Controle de Clientes");
        setBounds(0, 0, 455, 230);
        setLayout(null);
		
		criarElementos();
		
		add(lbNome);
		add(tfNome);
		
		add(lbValor);
		add(tfValor);
		
		add(lbQtd);
		add(tfQtd);
		
		btInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("pencil.png")));
		
		add(btInserir);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((tela.width - getSize().width) / 2,
                (tela.height - getSize().height) / 2);
	}
	
	public static void criarElementos() {
		lbNome.setBounds(10, 10, 100, 25);
		tfNome.setBounds(100, 10, 225, 25);
		
		lbValor.setBounds(10, 40, 100, 25);
		tfValor.setBounds(100, 40, 225, 25);
		
		lbQtd.setBounds(10, 70, 100, 25);
		tfQtd.setBounds(100, 70, 225, 25);
		
		btInserir.setBounds(170, 150, 100, 25);
	}
	
	public static void definirFuncoes() {
		
		btInserir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String sql = "INSERT INTO Produto (Nome, Valor, EstoQtd) VALUES (?,?,?)";
               
               Conexao.abrirConexao();
               
               try {
				PreparedStatement statement = Conexao.conex.prepareStatement(sql);
				
				String nome = tfNome.getText();
				statement.setString(1, nome);
				
				String valor = tfValor.getText();
				statement.setString(2, valor);
				
				String qtd = tfQtd.getText();
				statement.setString(3, qtd);
				
				int rowsInserted = statement.executeUpdate();
				
				if (rowsInserted > 0) {
					JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!");
				}
				
				
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Erro ao inserir produto!\n" + e1);
			}
            }
        });
	}

}
