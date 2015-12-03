package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Beans.Cidade;

public class CidadeDAO extends ConexaoDAO {

	public boolean autenticado = false;
	public static int codigo = 0;
	private Connection connection = null;

	public CidadeDAO() throws SQLException, ClassNotFoundException {
	}

	public List<Cidade> getListar() throws SQLException {

		this.connection = getConnection();

		String sql = "Select a.cod_cid, a.nom_cid, b.nom_est from cadastro_cidade a, cadastro_estado b where a.cod_est = b.cod_est";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		List<Cidade> cid = new ArrayList<Cidade>();

		while (rs.next()) {
			Cidade a = new Cidade();
			a.setCodigo(rs.getInt("cod_cid"));
			a.setNome(rs.getString("nom_cid"));
			a.setNomeEstado(rs.getString("nom_est"));

			cid.add(a);
		}
		prmt.close();
		connection.close();
		return cid;
	}

	public Cidade BuscarCidade(int CodigoCidade) throws SQLException {

		this.connection = getConnection();

		String sql = "select a.cod_cid, a.nom_cid, a.cod_est, b.nom_est from cadastro_cidade a, cadastro_estado b where a.cod_est = b.cod_est and a.cod_cid = "
				+ CodigoCidade + "";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		Cidade a = new Cidade();

		while (rs.next()) {
			a = new Cidade();
			a.setCodigo(rs.getInt("cod_cid"));
			a.setNome(rs.getString("nom_cid"));
			a.setCodigoEstado(rs.getInt("cod_est"));
			a.setNomeEstado(rs.getString("nom_est"));

		}
		prmt.close();
		connection.close();
		return a;
	}

	public int BuscarUltimoID() throws SQLException {

		this.connection = getConnection();

		String sql = "Select max(cod_cid) as cod_cid from cadastro_cidade";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		int a = 0;

		while (rs.next()) {

			a = rs.getInt("cod_cid");
		}
		prmt.close();
		connection.close();
		return a;
	}

	public boolean VerificaCidade(String NomeCidade, int CodigoEstado) throws SQLException {
		this.connection = getConnection();

		try {
			ResultSet rs = null;
			Statement stmt = this.connection.createStatement();
			rs = stmt.executeQuery("select cod_cid from cadastro_cidade where nom_cid = '" + NomeCidade
					+ "' and cod_est='" + CodigoEstado + "'");
			if (rs.next()) {

				autenticado = true;
				codigo = (rs.getInt("cod_cid"));

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Deu pau no VerificaCidade");
		}
		connection.close();
		return autenticado;
	}

	public void SalvaCadastro(Cidade objeto) throws SQLException {

		this.connection = getConnection();
		if (objeto == null) {
			JOptionPane.showMessageDialog(null, "objeto nulo");
		} else {
			try {

				String sql;
				sql = "insert into cadastro_cidade (nom_cid,cod_est) values (?,?)";

				PreparedStatement pstmt = this.connection.prepareStatement(sql);

				pstmt.setString(1, objeto.getNome());
				pstmt.setInt(2, objeto.getCodigoEstado());

				pstmt.executeUpdate();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Deu pau no insert");
				e.printStackTrace();
			}
		}
		connection.close();
	}

	public void DeletaCidade(String codigo) throws SQLException {
		this.connection = getConnection();
		try {

			Statement stmt = this.connection.createStatement();
			stmt.executeQuery("delete from cadastro_cidade where cod_cid = '" + codigo + "'");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Deu pau no delete");
		}
		connection.close();
	}

	public void UpdateCidade(Cidade objeto) throws SQLException {
		this.connection = getConnection();

		if (objeto == null) {
			System.out.println("Nulo");
		} else {

			try {

				String sql = "Update cadastro_cidade set nom_cid=?, cod_est=? where cod_cid = ?";

				PreparedStatement prmt = this.connection.prepareStatement(sql);

				prmt.setString(1, objeto.getNome());
				prmt.setInt(2, objeto.getCodigoEstado());
				prmt.setInt(3, objeto.getCodigo());

				prmt.executeUpdate();
				prmt.close();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Deu pau no update");
			}
		}
		connection.close();
	}
	
	
	
	
	public boolean VerificaNulo() throws SQLException {
		this.connection = getConnection();

		try {
			ResultSet rs = null;
			Statement stmt = this.connection.createStatement();
			rs = stmt.executeQuery("select cod_cid from cadastro_cidade");
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
