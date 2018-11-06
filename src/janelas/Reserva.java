package janelas;

import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import funcoes.FuncoesReserva;
import main.Main;

class ModeloAdcionais extends DefaultTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

public class Reserva extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    static JLabel lbRg = new JLabel("RG: ");
    static JLabel lbEntrada = new JLabel("Entrada: ");
    static JLabel lbSaida = new JLabel("Saida: ");
    static JLabel lbQuarto = new JLabel("Cat(Quarto): ");

    public static JTextField tfRg = new JTextField();
    public static JTextField tfEntrada = new JTextField();
    public static JTextField tfSaida = new JTextField();
    public static JTextField tfQuarto = new JTextField();

    static JButton btCadastrar = new JButton("Cadastrar");
    static JButton btAlterar = new JButton("Alterar");
    static JButton btCancelar = new JButton("Cancelar");

    public Reserva() {
        setTitle("Controle de Clientes");
        setBounds(0, 0, 455, 230);
        setLayout(null);

        criarElementos();

        add(lbRg);
        add(lbEntrada);
        add(lbSaida);
        add(lbQuarto);

        add(tfRg);
        add(tfEntrada);
        add(tfSaida);
        add(tfQuarto);
        
        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("alterar.png")));
        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("cancel.png")));
        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("pencil.png")));
        
        add(btCadastrar);
        add(btAlterar);
        add(btCancelar);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((tela.width - getSize().width) / 2,
                (tela.height - getSize().height) / 2);
    }

    public static void criarElementos() {

        lbRg.setBounds(10, 10, 100, 25);
        tfRg.setBounds(100, 10, 225, 25);
        lbEntrada.setBounds(10, 40, 100, 25);
        tfEntrada.setBounds(100, 40, 225, 25);
        lbSaida.setBounds(10, 70, 100, 25);
        tfSaida.setBounds(100, 70, 225, 25);
        lbQuarto.setBounds(10, 100, 100, 25);
        tfQuarto.setBounds(100, 100, 225, 25);

        btCadastrar.setBounds(10, 150, 100, 25);
        btAlterar.setBounds(170, 150, 100, 25);
        btCancelar.setBounds(330, 150, 100, 25);
    }

    public static void definirEventos() {

        btCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FuncoesReserva.inserir();
            }
        });
        
        btAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.reserva.dispose();
            }
        });

    }

}
