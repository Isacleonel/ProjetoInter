package model.DAO;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoDAO {

	private String driver = "oracle.jdbc.OracleDriver";
	private String caminho = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private String usuario = "academia";
	private String senha = "academia102030";

	protected Connection getConnection() throws SQLException {
		System.setProperty("jdbc.driver", driver);
		Connection connection = DriverManager.getConnection(caminho, usuario, senha);
		return connection;
	}

	protected void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}