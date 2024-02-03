package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection c = null;
		try {
			// Load SQL Server JDBC driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// Update the connection string with correct syntax for database name.
			String url = "jdbc:sqlserver://Vuong-Duc-Thoai\\SQLEXPRESS:1433;databaseName=BookStores;encrypt=true;trustServerCertificate=true";
			String userName = "sa";
			String password = "Thoai12309@";

			// Establish connection.
			c = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// Handle the error for the driver class not found
			e.printStackTrace();
		}
		return c;
	}	

	public static void closeConnection(Connection c) {
		if (c != null) {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void printInfo(Connection c) {
		try {
			if (c != null) {
				DatabaseMetaData mtdt = c.getMetaData();
				System.out.println(mtdt.getDatabaseProductName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection connection = JDBCUtil.getConnection();
		JDBCUtil.printInfo(connection);

		JDBCUtil.closeConnection(connection);
	}
}
