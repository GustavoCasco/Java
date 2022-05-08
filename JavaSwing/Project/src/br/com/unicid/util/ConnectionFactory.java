package br.com.unicid.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	
	public static Connection estabeleceConexao()
	{
		try {
			return DriverManager.
					getConnection("jdbc:mysql://localhost:3306/cadastroAluno?useTimezone=true&serverTimezone=UTC", "root", "root");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
