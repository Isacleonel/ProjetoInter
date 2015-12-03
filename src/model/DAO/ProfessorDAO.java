package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Beans.Professor;

public class ProfessorDAO extends ConexaoDAO {

	public boolean autenticado = false;
	public static int codigo = 0;
	private Connection connection = null;

	public ProfessorDAO() throws SQLException, ClassNotFoundException {

	}

	public void SalvaCadastro(Professor objeto) throws SQLException {

		this.connection = getConnection();
		if (objeto == null) {
			JOptionPane.showMessageDialog(null, "objeto nulo");

		} else {

			try {

				String sql;
				sql = "insert into cadastro_professor (nom_pro,sex_pro,cpf_pro,ide_pro,nas_pro,for_pro,ano_pro,cod_mod,tur_pro,cod_cid,bai_pro,end_pro,cel_pro,fon_pro,cad_pro,ema_pro) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				PreparedStatement pstmt = this.connection.prepareStatement(sql);

				pstmt.setString(1, objeto.getNome());
				pstmt.setString(2, objeto.getSexo());
				pstmt.setString(3, objeto.getCpf());
				pstmt.setString(4, objeto.getIdentidade());
				pstmt.setDate(5, objeto.getNascimento());
				pstmt.setString(6, objeto.getFormacao());
				pstmt.setInt(7, objeto.getAno());
				pstmt.setInt(8, objeto.getCodigoModalidade());
				pstmt.setString(9, objeto.getTurno());
				pstmt.setInt(10, objeto.getCodigoCidade());
				pstmt.setString(11, objeto.getBairro());
				pstmt.setString(12, objeto.getEndereco());
				pstmt.setString(13, objeto.getCelular());
				pstmt.setString(14, objeto.getFone());
				pstmt.setDate(15, objeto.getCadastro());
				pstmt.setString(16, objeto.getEmail());

				pstmt.executeUpdate();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Deu pau no insert");
				e.printStackTrace();
			}
		}
		connection.close();
	}

	public boolean VerificaProfessor(String CpfProfessor) throws SQLException {
		this.connection = getConnection();

		try {
			ResultSet rs = null;
			Statement stmt = this.connection.createStatement();
			rs = stmt.executeQuery("select cod_pro from cadastro_professor where cpf_pro='" + CpfProfessor + "'");
			if (rs.next()) {

				autenticado = true;
				codigo = (rs.getInt("cod_pro"));

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Deu pau no VerificaProfessor");
		}
		connection.close();
		return autenticado;
	}

	public List<Professor> getListar() throws SQLException {

		this.connection = getConnection();

		String sql = "Select cod_pro, nom_pro from cadastro_professor";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		List<Professor> prof = new ArrayList<Professor>();

		while (rs.next()) {
			Professor a = new Professor();
			a.setCodigo(rs.getInt("cod_pro"));
			a.setNome(rs.getString("nom_pro"));

			prof.add(a);
		}
		prmt.close();
		connection.close();
		return prof;
	}

	public Professor BuscarProfessor(int CodigoProfessor) throws SQLException {

		this.connection = getConnection();

		String sql = "select a.cod_pro, a.nom_pro, a.sex_pro, a.cpf_pro, a.ide_pro, a.nas_pro, a.for_pro, a.ano_pro, a.cod_mod, b.nom_mod, a.tur_pro, a.cod_cid, c.nom_cid, c.cod_est, d.nom_est, a.bai_pro, a.end_pro, a.cel_pro, a.fon_pro, a.cad_pro, a.ema_pro from cadastro_professor a, cadastro_modalidade b, cadastro_cidade c, cadastro_estado d where a.cod_mod = b.cod_mod and a.cod_cid = c.cod_cid and c.cod_est = d.cod_est and a.cod_pro = "
				+ CodigoProfessor + "";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		Professor a = new Professor();

		while (rs.next()) {
			a = new Professor();
			a.setCodigo(rs.getInt("cod_pro"));
			a.setNome(rs.getString("nom_pro"));
			a.setSexo(rs.getString("sex_pro"));
			a.setCpf(rs.getString("cpf_pro"));
			if (rs.getString("ide_pro") != null) {
				a.setIdentidade(rs.getString("ide_pro"));
			}
			a.setNascimento(rs.getDate("nas_pro"));
			a.setFormacao(rs.getString("for_pro"));
			a.setAno(rs.getInt("ano_pro"));
			a.setCodigoModalidade(rs.getInt("cod_mod"));
			a.setNomeModalidade(rs.getString("nom_mod"));
			a.setTurno(rs.getString("tur_pro"));
			a.setCodigoCidade(rs.getInt("cod_cid"));
			a.setNomeCidade(rs.getString("nom_cid"));
			a.setCodigoEstado(rs.getInt("cod_est"));
			a.setNomeEstado(rs.getString("nom_est"));
			if (rs.getString("bai_pro") != null) {
				a.setBairro(rs.getString("bai_pro"));
			}
			if (rs.getString("end_pro") != null) {
				a.setEndereco(rs.getString("end_pro"));
			}
			if (rs.getString("cel_pro") != null) {
				a.setCelular(rs.getString("cel_pro"));
			}
			if (rs.getString("fon_pro") != null) {
				a.setFone(rs.getString("fon_pro"));
			}
			a.setCadastro(rs.getDate("cad_pro"));
			if (rs.getString("ema_pro") != null) {
				a.setEmail(rs.getString("ema_pro"));
			}

		}
		prmt.close();
		connection.close();
		return a;
	}

	public int BuscarUltimoID() throws SQLException {

		this.connection = getConnection();

		String sql = "Select max(cod_pro) as cod_pro from cadastro_professor";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		int a = 0;

		while (rs.next()) {

			a = rs.getInt("cod_pro");

		}
		prmt.close();
		connection.close();
		return a;
	}

	public void DeletaProfessor(String codigo) throws SQLException {
		this.connection = getConnection();

		try {

			Statement stmt = this.connection.createStatement();
			stmt.executeQuery("delete from cadastro_professor where cod_pro = '" + codigo + "'");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Deu pau no delete");

		}
		connection.close();

	}

	public void UpdateProfessor(Professor objeto) throws SQLException {
		this.connection = getConnection();

		if (objeto == null) {
			System.out.println("Nulo");
		} else {

			try {

				String sql = "Update cadastro_professor set nom_pro=?,sex_pro=?,cpf_pro=?,ide_pro=?,nas_pro=?,for_pro=?,ano_pro=?,cod_mod=?,tur_pro=?,cod_cid=?,bai_pro=?,end_pro=?,cel_pro=?,fon_pro=?,cad_pro=?,ema_pro=? where cod_pro = ?";

				PreparedStatement prmt = this.connection.prepareStatement(sql);

				prmt.setString(1, objeto.getNome());
				prmt.setString(2, objeto.getSexo());
				prmt.setString(3, objeto.getCpf());
				prmt.setString(4, objeto.getIdentidade());
				prmt.setDate(5, objeto.getNascimento());
				prmt.setString(6, objeto.getFormacao());
				prmt.setInt(7, objeto.getAno());
				prmt.setInt(8, objeto.getCodigoModalidade());
				prmt.setString(9, objeto.getTurno());
				prmt.setInt(10, objeto.getCodigoCidade());
				prmt.setString(11, objeto.getBairro());
				prmt.setString(12, objeto.getEndereco());
				prmt.setString(13, objeto.getCelular());
				prmt.setString(14, objeto.getFone());
				prmt.setDate(15, objeto.getCadastro());
				prmt.setString(16, objeto.getEmail());
				prmt.setInt(17, objeto.getCodigo());

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
			rs = stmt.executeQuery("select cod_pro from cadastro_professor");
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
