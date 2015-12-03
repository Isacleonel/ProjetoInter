package model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class LoginDAO extends ConexaoDAO {

	public boolean autenticado = false;

	private Connection connection = null;

	public LoginDAO() throws SQLException, ClassNotFoundException {
		this.connection = getConnection();
	}

	public boolean autenticar(String nom_usu, String sen_usu) {
		try {
			ResultSet rs = null;
			Statement stmt = this.connection.createStatement();
			rs = stmt.executeQuery("select nom_usu, sen_usu from cadastro_usuario where nom_usu='" + nom_usu + "' and sen_usu='"
					+ sen_usu + "'");
			if (rs.next()) {
				// JOptionPane.showMessageDialog(null, "autenticado");
				autenticado = true;
			}
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Exception LoginDAO");
		}
		return autenticado;
	}
}
