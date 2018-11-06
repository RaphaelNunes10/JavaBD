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

import main.Conexao;

public class InsereColab extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField txCNPJ, txNomeAG, txNomeCT, txDesc, txCEP, txRua, txBairro, txNum;
    private JLabel lbCNPJ, lbNomeAG, lbNomeCT, lbDesc, lbCEP, lbRua, lbBairro, lbNum;
    private JButton btBuscar, btProx, btAnt, btAlterar, btInserir, btDeleta;

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
//   
//    private Connection conex;
//    private PreparedStatement stmt;
//    private ResultSet rs;

    public InsereColab() {
        InicializaComponentes();
        DefineEventos();
        Colaboradores();
        carregaDAO();
    }

    private void InicializaComponentes() {
        new Conexao();
		conn = Conexao.getConnection();

        ImageIcon icon = new ImageIcon("search.png");
        ImageIcon icon1 = new ImageIcon("back.png");
        ImageIcon icon2 = new ImageIcon("prox.png");
        ImageIcon icon3 = new ImageIcon("salvar.png");
        ImageIcon icon4 = new ImageIcon("pencil.png");

        lbCNPJ = new JLabel("CNPJ");
        txCNPJ = new JTextField(150);
        lbNomeAG = new JLabel("Agencia");
        txNomeAG = new JTextField(150);
        lbNomeCT = new JLabel("Responsavel");
        txNomeCT = new JTextField(150);
        lbDesc = new JLabel("Desconto");
        txDesc = new JTextField(20);
        lbCEP = new JLabel("CEP");
        txCEP = new JTextField(20);

        lbRua = new JLabel("Rua");
        txRua = new JTextField(20);
        lbNum = new JLabel("Numero");
        txNum = new JTextField(20);
        lbBairro = new JLabel("Bairro");
        txBairro = new JTextField(20);

        btBuscar = new JButton("Buscar", icon);
        btProx = new JButton("Proximo", icon2);
        btAnt = new JButton("Anterior", icon1);
        btAlterar = new JButton("Alterar", icon3);
        btDeleta = new JButton("Deleta", icon4);
        btInserir = new JButton("Inserir", icon4);

        lbCNPJ.setBounds(20, 20, 35, 20);
        txCNPJ.setBounds(60, 20, 165, 20);
        lbNomeAG.setBounds(20, 47, 70, 20);
        txNomeAG.setBounds(75, 47, 150, 20);
        lbNomeCT.setBounds(20, 75, 78, 20);
        txNomeCT.setBounds(100, 75, 125, 20);
        lbDesc.setBounds(20, 105, 70, 20);
        txDesc.setBounds(80, 105, 145, 20);
        lbCEP.setBounds(20, 135, 55, 20);
        txCEP.setBounds(50, 135, 175, 20);
        lbRua.setBounds(20, 165, 175, 20);
        txRua.setBounds(45, 165, 180, 20);
        lbNum.setBounds(20, 190, 175, 20);
        txNum.setBounds(70, 190, 155, 20);
        lbBairro.setBounds(20, 220, 175, 20);
        txBairro.setBounds(60, 220, 165, 20);

        btBuscar.setBounds(235, 20, 95, 20);
        btBuscar.setHorizontalTextPosition(JButton.LEFT);
//        btBuscar.setIcon(new ImageIcon("main.teste/im.png"));
        btAnt.setBounds(7, 255, 83, 20);
        btAlterar.setBounds(125, 285, 95, 20);
        btInserir.setBounds(20, 285, 95, 20);
        btDeleta.setBounds(230, 285, 95, 20);
        btProx.setBounds(250, 255, 90, 20);

        add(lbCNPJ);
        add(txCNPJ);
        add(lbNomeCT);
        add(lbNomeAG);
        add(txNomeCT);
        add(txNomeAG);
        add(lbDesc);
        add(txDesc);
        add(lbCEP);
        add(txCEP);

        add(lbRua);
        add(txRua);

        add(lbNum);
        add(txNum);

        add(lbBairro);
        add(txBairro);

        btBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("search.png")));
        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("pencil.png")));
        btProx.setIcon(new javax.swing.ImageIcon(getClass().getResource("Prox.png")));
        btAnt.setIcon(new javax.swing.ImageIcon(getClass().getResource("back.png")));
        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("alterar.png")));
        btDeleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("excluir.png")));
        btInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("pencil.png")));
        
        add(btBuscar);
        add(btAlterar);
        add(btDeleta);
        add(btInserir);
        add(btProx);
        add(btAnt);
        add(btAlterar);
    }

    public void carregaDAO() {
        try {
            stmt = conn.prepareStatement("select colaborador.CNPJ, colaborador.NomeAgencia, colaborador.NomeContato, colaborador.Desconto, endereco.CEP, endereco.Rua, endereco.Bairro, endereco.numero from endereco inner join colaborador on colaborador.CNPJ = endereco.cnpj_colaborador ORDER BY Colaborador.NomeAgencia ASC");
            rs = stmt.executeQuery();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro..." + erro.toString());
        }
    }

    private void DefineEventos() {
        btInserir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    new Conexao();
					Connection conn = Conexao.getConnection(); //Minha conexÃ£o estÃ¡ OK

                    PreparedStatement stmt = conn.prepareStatement("select * from endereco inner join colaborador on colaborador.CNPJ = endereco.cnpj_colaborador WHERE COLABORADOR.CNPJ = ?");
                    stmt.setString(1, txCNPJ.getText());
                     ResultSet rs = stmt.executeQuery();
                     
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "CNPJ ja cadastrado");
                        Conexao.closeConnection(conn, stmt);

                    } else {
                        try {
                            PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO colaborador(CNPJ, NomeAgencia, NomeContato, Desconto) VALUES(?,?,?,?)");
                            PreparedStatement stmts = conn.prepareStatement("INSERT INTO endereco(CEP, Rua, Bairro, numero, CNPJ_Colaborador) values(?,?,?,?,?)");

                            stmt1.setString(1, txCNPJ.getText());
                            stmt1.setString(2, txNomeAG.getText());
                            stmt1.setString(3, txNomeCT.getText());
                            stmt1.setInt(4, Integer.parseInt(txDesc.getText()));

                            stmts.setString(1, txCEP.getText());
                            stmts.setString(2, txRua.getText());
                            stmts.setString(3, txBairro.getText());
                            stmts.setInt(4, Integer.parseInt(txNum.getText()));
                            stmts.setString(5, txCNPJ.getText());

                            stmts.executeUpdate();
                            stmt1.executeUpdate();
                                                    JOptionPane.showMessageDialog(null, "Cadastrador!!");

                                                        
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Erro ao Inserir " + ex);
                        }finally{
                            Conexao.closeConnection(conn, stmt);
                        }
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao Inserir " + ex);
                }finally{
                            Conexao.closeConnection(conn, stmt);
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

                        txCNPJ.setText(rs.getString("Colaborador.CNPJ"));
                        txNomeAG.setText(rs.getString("Colaborador.NomeAgencia"));
                        txNomeCT.setText(rs.getString("Colaborador.NomeContato"));
                        txDesc.setText(rs.getString("Colaborador.Desconto"));
                        txCEP.setText(rs.getString("endereco.CEP"));
                        txRua.setText(rs.getString("endereco.Rua"));
                        txNum.setText(rs.getString("endereco.numero"));
                        txBairro.setText(rs.getString("endereco.Bairro"));

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
                        txCNPJ.setText(rs.getString("Colaborador.CNPJ"));
                        txNomeAG.setText(rs.getString("Colaborador.NomeAgencia"));
                        txNomeCT.setText(rs.getString("Colaborador.NomeContato"));
                        txDesc.setText(rs.getString("Colaborador.Desconto"));
                        txCEP.setText(rs.getString("endereco.CEP"));
                        txRua.setText(rs.getString("endereco.Rua"));
                        txNum.setText(rs.getString("endereco.numero"));
                        txBairro.setText(rs.getString("endereco.Bairro"));

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

                String sql = "select colaborador.CNPJ, colaborador.NomeAgencia, colaborador.NomeContato, colaborador.Desconto, endereco.CEP, endereco.Rua, endereco.Bairro, endereco.numero from endereco inner join colaborador on colaborador.CNPJ = endereco.cnpj_colaborador where Colaborador.CNPJ = ?";
                if (txCNPJ.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Digite o CNPJ");
                } else {
                    try {
                        new Conexao();
						Connection conn = Conexao.getConnection(); //Minha conexÃ£o estÃ¡ OK
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, Integer.parseInt(txCNPJ.getText())); //O campo Ã© nÃºmerico
                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {
                            try {
                                txCNPJ.setText(rs.getString("Colaborador.CNPJ"));
                                txNomeAG.setText(rs.getString("Colaborador.NomeAgencia"));
                                txNomeCT.setText(rs.getString("Colaborador.NomeContato"));
                                txDesc.setText(rs.getString("Colaborador.Desconto"));
                                txCEP.setText(rs.getString("endereco.CEP"));
                                txRua.setText(rs.getString("endereco.Rua"));
                                txNum.setText(rs.getString("endereco.numero"));
                                txBairro.setText(rs.getString("endereco.Bairro"));
                            } catch (SQLException ex) {
                                System.err.print("Erro ao inserir no JTextField " + ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Verifique o CNPJ ");
                        }
                        try {
                            stmt.close();
                            conn.close();
                        } catch (SQLException ex) {
                            System.err.println("Erro ao fechar conexÃµes " + ex);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na consulta " + ex);

                    } finally {
                        Conexao.closeConnection(conn, stmt, rs);
                    }
                }
            }
        });

        btAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (txCNPJ.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Digite o CNPJ");
                } else {
                    try {
                        new Conexao();
						Connection conn = Conexao.getConnection(); //Minha conexÃ£o estÃ¡ OK
                        PreparedStatement stmt = conn.prepareStatement("UPDATE Colaborador INNER JOIN Endereco  on Colaborador.CNPJ = Endereco.CNPJ_Colaborador SET colaborador.NomeAgencia=?, colaborador.NomeContato=?, colaborador.Desconto=?, endereco.CEP=?, endereco.Rua=?, endereco.Bairro=?, endereco.numero =? where colaborador.CNPJ=?");

                        try {
                            stmt.setString(1, txNomeAG.getText());
                            stmt.setString(2, txNomeCT.getText());
                            stmt.setInt(3, Integer.parseInt(txDesc.getText()));
                            stmt.setString(4, txCEP.getText());
                            stmt.setString(5, txRua.getText());
                            stmt.setString(6, txBairro.getText());
                            stmt.setInt(7, Integer.parseInt(txNum.getText()));
                            stmt.setString(8, txCNPJ.getText());
                            stmt.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Alterado!!");


                        } catch (SQLException ex) {
                            System.err.print("Confira os dados" + ex);
                        }
                        try {
                            stmt.close();
                            conn.close();
                        } catch (SQLException ex) {
                            System.err.println("Erro ao fechar conexÃµes " + ex);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na consulta " + ex);

                    }
                }
            }
        });

    
    btDeleta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (txCNPJ.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Digite o CNPJ");
                } else {
                    try {
                        new Conexao();
						Connection conn = Conexao.getConnection(); //Minha conexÃ£o estÃ¡ OK
                        PreparedStatement stmt = conn.prepareStatement("DELETE  Colaborador, endereco from Colaborador INNER JOIN Endereco  on Colaborador.CNPJ = Endereco.CNPJ_Colaborador where colaborador.CNPJ=? and Endereco.CNPJ_Colaborador = ?");
                        
                        stmt.setString(1, txCNPJ.getText());
                        stmt.setString(2, txCNPJ.getText());
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Deletado!!");
                       
                        try {
                            stmt.close();
                            conn.close();
                        } catch (SQLException ex) {
                            System.err.println("Erro ao fechar conexÃµes " + ex);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao Deletar" + ex);

                    }
                }

            }
        });

    }

    public void Colaboradores() {
        setTitle("Colaborador");
        setSize(370, 350);
// select * from endereco inner join colaborador on colaborador.CNPJ = endereco.cnpj_colaborador ;
        setLocationRelativeTo(null);
        setLayout(null);
    }
}
