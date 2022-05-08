package br.com.unicid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection criaConexao() {
		try {
			return DriverManager.
					getConnection("jdbc:mysql://localhost:3306/aula27?useTimezone=true&serverTimezone=UTC", "root", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
