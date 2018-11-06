package janelas;

/*

@@@@ CELSO FEZ (EXCLUIR)@@@

 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import funcoes.FuncoesCliente;
import funcoes.Dados;
import main.Conexao;

public class EditarReserva extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField txRG, txNome, txEmail, txCelular, txTelefone;
    private JLabel lbRG, lbNome, lbEmail, lbCelular, lbTelefone;
    private JButton btBuscar, btProx, btAnt, btAlterar, btDeleta;
    ;
    
     private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
//   
//    private Connection conex;
//    private PreparedStatement stmt;
//    private ResultSet rs;

    public EditarReserva() {
        InicializaComponentes();
        DefineEventos();
        AlterarDados();
        carregaDAO();
    }

    private void InicializaComponentes() {
        new Conexao();
		conn = Conexao.getConnection();

        ImageIcon icon = new ImageIcon("search.png");
        ImageIcon icon1 = new ImageIcon("back.png");
        ImageIcon icon2 = new ImageIcon("prox.png");
        ImageIcon icon3 = new ImageIcon("salvar.png");
        ImageIcon icon4 = new ImageIcon("excluir.png");

        lbRG = new JLabel("RG");
        txRG = new JTextField(14);
        lbNome = new JLabel("Nome");
        txNome = new JTextField(150);
        lbEmail = new JLabel("E-mail");
        txEmail = new JTextField(150);
        lbCelular = new JLabel("Celular");
        txCelular = new JTextField(20);
        lbTelefone = new JLabel("Telefone");
        txTelefone = new JTextField(20);
        btBuscar = new JButton("Buscar", icon);
        btProx = new JButton("Proximo", icon2);
        btAnt = new JButton("Anterior", icon1);
        btAlterar = new JButton("Alterar", icon3);
        btDeleta = new JButton("Deleta", icon4);

        lbRG.setBounds(20, 23, 35, 15);
        txRG.setBounds(40, 20, 145, 20);
        lbNome.setBounds(20, 57, 35, 15);
        txNome.setBounds(60, 55, 125, 20);
        lbEmail.setBounds(20, 85, 35, 20);
        txEmail.setBounds(60, 87, 125, 20);
        lbCelular.setBounds(20, 120, 40, 20);
        txCelular.setBounds(65, 122, 120, 20);
        lbTelefone.setBounds(20, 152, 55, 20);
        txTelefone.setBounds(75, 152, 110, 20);

        btBuscar.setBounds(195, 20, 95, 20);
        btBuscar.setHorizontalTextPosition(JButton.LEFT);
//        btBuscar.setIcon(new ImageIcon("main.teste/im.png"));
        btAnt.setBounds(10, 190, 90, 20);
        btAlterar.setBounds(50, 220, 90, 20);
        btDeleta.setBounds(150, 220, 90, 20);
        btProx.setBounds(190, 190, 90, 20);

        add(lbTelefone);
        add(txTelefone);
        add(lbCelular);
        add(txCelular);
        add(lbEmail);
        add(txEmail);
        add(lbNome);
        add(txNome);
        add(lbRG);
        add(txRG);
        
                        btBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("search.png")));
        add(btBuscar);
                        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("pencil.png")));
        add(btAlterar);
                        btProx.setIcon(new javax.swing.ImageIcon(getClass().getResource("Prox.png")));
        add(btProx);
                        btAnt.setIcon(new javax.swing.ImageIcon(getClass().getResource("back.png")));
        add(btAnt);
                        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("alterar.png")));
        add(btAlterar);
                        btDeleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("excluir.png")));
        add(btDeleta);
    }

    public void carregaDAO() {
        try {
            stmt = conn.prepareStatement("select RG, Nome, Email, Telefone, Celular from Cliente");
            rs = stmt.executeQuery();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro..." + erro.toString());
        }
    }

    private void DefineEventos() {
        btAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dados dados = new Dados();
                FuncoesCliente insere = new FuncoesCliente();

                if (txRG.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Digite o CPF");
                } else {
                    dados.setTxRG(Integer.parseInt(txRG.getText()));
                    dados.setTxNome(txNome.getText());
                    dados.setTxEmail(txEmail.getText());
                    dados.setTxCelular(txCelular.getText());
                    dados.setTxTelefone(txTelefone.getText());

                    insere.alterar(dados);
                }

            }
        });

        btProx.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {

                    rs.next();

                    if (rs.isAfterLast()) {
                        rs.last();
                    } else if (rs.isBeforeFirst()) {
                        rs.first();
                    } else {

                        txRG.setText(rs.getString("RG"));
                        txNome.setText(rs.getString("Nome"));
                        txEmail.setText(rs.getString("Email"));
                        txCelular.setText(rs.getString("Celular"));
                        txTelefone.setText(rs.getString("Telefone"));
                    }

                    //rs.close();
                    //stmt.close();
                } catch (SQLException s) {
                    throw new RuntimeException(s);
                }

            }
        });
        btAnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    rs.previous();

                    if (rs.isAfterLast()) {
                        rs.last();
                    } else if (rs.isBeforeFirst()) {
                        rs.first();
                    } else {
                        txRG.setText(rs.getString("RG"));
                        txNome.setText(rs.getString("Nome"));
                        txEmail.setText(rs.getString("Email"));
                        txCelular.setText(rs.getString("Celular"));
                        txTelefone.setText(rs.getString("Telefone"));
                    }

//                rs.close();
//                stmt.close();
                } catch (SQLException s) {
                    throw new RuntimeException(s);
                }

            }
        });

        btBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dados dados = new Dados();
                FuncoesCliente Busca = new FuncoesCliente();
                String sql = "select Nome, Email, Telefone, Celular from Cliente where RG=?";
                if (txRG.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Digite o RG");
                } else {
                    try {
                        new Conexao();
						Connection conn = Conexao.getConnection(); //Minha conexÃ£o estÃ¡ OK
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, Integer.parseInt(txRG.getText())); //O campo Ã© nÃºmerico
                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {
                            try {

                                txNome.setText(rs.getString("Nome"));
                                txEmail.setText(rs.getString("Email"));
                                txTelefone.setText(rs.getString("Telefone"));
                                txCelular.setText(rs.getString("Celular"));

                            } catch (SQLException ex) {
                                System.err.print("Erro ao inserir no JTextField " + ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Verifique o RG ");
                        }
                        try {
                            stmt.close();
                            conn.close();
                        } catch (SQLException ex) {
                            System.err.println("Erro ao fechar conexoes " + ex);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na consulta " + ex);

                    }

//                Busca.Carrega(Integer.parseInt(txRG.getText()));
//                dados.setTxRG(Integer.parseInt(txRG.getText()));
//                    txNome.setText(dados.getTxNome());
//                    txEmail.setText(dados.getTxEmail());
//                    txCelular.setText(dados.getTxCelular());
//                    txTelefone.setText(dados.getTxTelefone());
//                    Busca.Carrega(dados);
                }
            }
        });

        btDeleta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (txRG.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Digite o RG");
                } else {
                    try {
                        new Conexao();
						Connection conn = Conexao.getConnection(); //Minha conexÃ£o estÃ¡ OK
                        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Cliente WHERE RG=?");

                        stmt.setInt(1, Integer.parseInt(txRG.getText()));
                        
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Deletado!!");
                        
                        try {
                            stmt.close();
                            conn.close();
                        } catch (SQLException ex) {
                            System.err.println("Erro ao fechar conexoes " + ex);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao Deletar" + ex);

                    }
                }

            }
        });

    }

    public void AlterarDados() {
        setTitle("Alterar dados");
        setSize(320, 300);

        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }

}
