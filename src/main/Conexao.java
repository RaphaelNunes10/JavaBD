package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {

	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3311/Pousada";
	static String user = "root";
	static String senha = "root";
	public static Connection conex;
	
	public static void abrirConexao() {
		try {
			conex = DriverManager.getConnection(url,user,senha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void fecharConexao() {
		try {
			conex.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        public static Connection getConnection(){
        try{
            Class.forName(driver);
            return DriverManager.getConnection(url, user, senha);
        }catch(ClassNotFoundException | SQLException e){
            System.err.println("Erro ao conectar Banco de dados");
            return null;
        }
    }

    public static void closeConnection(Connection conn){
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException ex){
                System.err.println("NÃ£o foi possivel fechar a conxÃ£o");

            }
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt){
        if(stmt != null){
            try{
                stmt.close();
            }catch(SQLException ex){
                System.err.println("Erro ao fechar stmt");
            }
        }
        closeConnection(conn);
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException ex){
                System.err.println("Erro ao fechar rs");
            }
        }
        closeConnection(conn, stmt);
    }

}
