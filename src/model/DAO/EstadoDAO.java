package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Beans.Estado;

public class EstadoDAO extends ConexaoDAO {

	public boolean autenticado = false;
	public static int codigo = 0;
	private Connection connection = null;

	public EstadoDAO() throws SQLException, ClassNotFoundException {
	}

	public void SalvaCadastro(Estado objeto) throws SQLException {

		this.connection = getConnection();
		if (objeto == null) {
			JOptionPane.showMessageDialog(null, "objeto nulo");
		} else {
			try {

				String sql;
				sql = "insert into cadastro_estado (nom_est) values (?)";

				PreparedStatement pstmt = this.connection.prepareStatement(sql);

				pstmt.setString(1, objeto.getNome());

				pstmt.executeUpdate();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Deu pau no insert");
				e.printStackTrace();
			}
		}
		connection.close();
	}

	public boolean VerificaEstado(String NomeEstado) throws SQLException {
		this.connection = getConnection();
		try {

			ResultSet rs = null;
			Statement stmt = this.connection.createStatement();
			rs = stmt.executeQuery("select cod_est from cadastro_estado where nom_est = '" + NomeEstado + "'");

			if (rs.next()) {

				autenticado = true;
				codigo = (rs.getInt("cod_est"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Deu pau no VerificaEstado");
		}
		connection.close();
		return autenticado;
	}

	public Estado BuscarEstado(int CodigoEstado) throws SQLException {

		this.connection = getConnection();

		String sql = "Select cod_est, nom_est from cadastro_estado where cod_est = " + CodigoEstado + "";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		Estado a = new Estado();

		while (rs.next()) {
			a = new Estado();
			a.setCodigo(rs.getInt("cod_est"));
			a.setNome(rs.getString("nom_est"));

		}
		prmt.close();
		connection.close();
		return a;
	}

	public List<Estado> getListar() throws SQLException {

		this.connection = getConnection();

		String sql = "Select cod_est, nom_est from cadastro_estado";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		List<Estado> est = new ArrayList<Estado>();

		while (rs.next()) {
			Estado a = new Estado();
			a.setCodigo(rs.getInt("cod_est"));
			a.setNome(rs.getString("nom_est"));

			est.add(a);
		}
		prmt.close();
		connection.close();
		return est;
	}

	public int BuscarUltimoID() throws SQLException {

		this.connection = getConnection();

		String sql = "Select max(cod_est) as cod_est from cadastro_estado";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		int a = 0;

		while (rs.next()) {

			a = rs.getInt("cod_est");
		}
		prmt.close();
		connection.close();
		return a;
	}

	public void UpdateEstado(Estado objeto) throws SQLException {
		this.connection = getConnection();

		if (objeto == null) {
			System.out.println("Nulo");
		} else {
			try {

				String sql = "Update cadastro_estado set nom_est=? where cod_est = ?";

				PreparedStatement prmt = this.connection.prepareStatement(sql);

				prmt.setString(1, objeto.getNome());
				prmt.setInt(2, objeto.getCodigo());

				prmt.executeUpdate();
				prmt.close();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Deu pau no update");
			}
		}
		connection.close();
	}

	public void DeletaEstado(String codigo) throws SQLException {
		this.connection = getConnection();
		try {

			Statement stmt = this.connection.createStatement();
			stmt.executeQuery("delete from cadastro_estado where cod_est = '" + codigo + "'");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Deu pau no delete");
		}
		connection.close();
	}
	
	
	
	public boolean VerificaNulo() throws SQLException {
		this.connection = getConnection();

		try {
			ResultSet rs = null;
			Statement stmt = this.connection.createStatement();
			rs = stmt.executeQuery("select cod_est from cadastro_estado");
			if (rs.next()) {
				autenticado = true;
			}else{
				autenticado=false;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Deu pau no VerificaNulo");
		}
		connection.close();
		return autenticado;
	}
	

}
