package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Funcoes {
	
	/**
	 * Insere dados na tabela.
	 */
	public static void inserir() {
		try {
			Connection conex = DriverManager.getConnection(Interface.url, Interface.user, Interface.senha);
			
			conex.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Busca os dadis da tabela.
	 */
	public static void buscar() {
		try {
			Connection conex = DriverManager.getConnection(Interface.url, Interface.user, Interface.senha);
			
			conex.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Altera os dados da tabela.
	 */
	public static void alterar() {
		try {
			Connection conex = DriverManager.getConnection(Interface.url, Interface.user, Interface.senha);
			
			conex.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Exclui os dados na tabela.
	 */
	public static void excluir() {
		try {
			Connection conex = DriverManager.getConnection(Interface.url, Interface.user, Interface.senha);
			
			conex.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Encerra a aplicação.
	 */
	public static void Sair() {
	}

}
