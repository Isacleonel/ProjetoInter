package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Beans.Modalidade;

public class ModalidadeDAO extends ConexaoDAO {

	public boolean autenticado = false;
	public static int codigo = 0;
	private Connection connection = null;

	public ModalidadeDAO() throws SQLException, ClassNotFoundException {
	}

	public void SalvaCadastro(Modalidade objeto) throws SQLException {

		this.connection = getConnection();
		if (objeto == null) {
			JOptionPane.showMessageDialog(null, "objeto nulo");
		} else {
			try {

				String sql;
				sql = "insert into cadastro_modalidade (nom_mod,obs_mod) values (?,?)";

				PreparedStatement pstmt = this.connection.prepareStatement(sql);

				pstmt.setString(1, objeto.getNome());
				pstmt.setString(2, objeto.getObservacao());

				pstmt.executeUpdate();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Deu pau no insert");
				e.printStackTrace();
			}
		}
		connection.close();
	}

	public boolean VerificaModalidade(String NomeModalidade) throws SQLException {
		this.connection = getConnection();
		try {

			ResultSet rs = null;
			Statement stmt = this.connection.createStatement();
			rs = stmt.executeQuery("select cod_mod from cadastro_modalidade where nom_mod = '" + NomeModalidade + "'");

			if (rs.next()) {

				autenticado = true;
				codigo = (rs.getInt("cod_mod"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Deu pau no VerificaModalidade");
		}
		connection.close();
		return autenticado;
	}

	public List<Modalidade> getListar() throws SQLException {

		this.connection = getConnection();

		String sql = "Select cod_mod, nom_mod from cadastro_modalidade";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		List<Modalidade> mod = new ArrayList<Modalidade>();

		while (rs.next()) {
			Modalidade a = new Modalidade();
			a.setCodigo(rs.getInt("cod_mod"));
			a.setNome(rs.getString("nom_mod"));

			mod.add(a);
		}
		prmt.close();
		connection.close();
		return mod;
	}

	public Modalidade BuscarModalidade(int CodigoModalidade) throws SQLException {

		this.connection = getConnection();

		String sql = "Select cod_mod, nom_mod, obs_mod from cadastro_modalidade where cod_mod = " + CodigoModalidade
				+ "";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		Modalidade a = new Modalidade();

		while (rs.next()) {
			a = new Modalidade();
			a.setCodigo(rs.getInt("cod_mod"));
			a.setNome(rs.getString("nom_mod"));

			if (rs.getString("obs_mod") != null) {
				a.setObservacao(rs.getString("obs_mod"));
			}
		}
		prmt.close();
		connection.close();
		return a;
	}

	public int BuscarUltimoID() throws SQLException {

		this.connection = getConnection();

		String sql = "Select max(cod_mod) as cod_mod from cadastro_modalidade";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		int a = 0;

		while (rs.next()) {

			a = rs.getInt("cod_mod");
		}
		prmt.close();
		connection.close();
		return a;
	}

	public void UpdateModalidade(Modalidade objeto) throws SQLException {
		this.connection = getConnection();

		if (objeto == null) {
			System.out.println("Nulo");
		} else {
			try {

				String sql = "Update cadastro_modalidade set nom_mod=?, obs_mod=? where cod_mod = ?";

				PreparedStatement prmt = this.connection.prepareStatement(sql);

				prmt.setString(1, objeto.getNome());
				prmt.setString(2, objeto.getObservacao());
				prmt.setInt(3, objeto.getCodigo());

				prmt.executeUpdate();
				prmt.close();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Deu pau no update");
			}
		}
		connection.close();
	}

	public void DeletaModalidade(String codigo) throws SQLException {
		this.connection = getConnection();
		try {

			Statement stmt = this.connection.createStatement();
			stmt.executeQuery("delete from cadastro_modalidade where cod_mod = '" + codigo + "'");

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
			rs = stmt.executeQuery("select cod_mod from cadastro_modalidade");
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
