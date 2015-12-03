package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Beans.Matricula;

public class MatriculaDAO extends ConexaoDAO {

	public boolean autenticado = false;
	public static int codigo = 0;
	private Connection connection = null;

	public MatriculaDAO() throws SQLException, ClassNotFoundException {
	}

	public void SalvaCadastro(Matricula objeto) throws SQLException {

		this.connection = getConnection();
		if (objeto == null) {
			JOptionPane.showMessageDialog(null, "objeto nulo");
		} else {
			try {

				String sql;
				sql = "insert into cadastro_matricula (cod_alu, cod_mod, pla_mod, hor_ini, hor_fim, cod_pro, dia_mat, vlr_men, vlr_per) values (?,?,?,?,?,?,?,?,?)";

				PreparedStatement pstmt = this.connection.prepareStatement(sql);

				pstmt.setInt(1, objeto.getCodigoAluno());
				pstmt.setInt(2, objeto.getCodigoModalidade());
				pstmt.setString(3, objeto.getPlanoMatricula());
				pstmt.setString(4, objeto.getHorarioIni());
				pstmt.setString(5, objeto.getHorarioFim());
				pstmt.setInt(6, objeto.getCodigoProfessor());
				pstmt.setInt(7, objeto.getDiaPagamento());
				pstmt.setInt(8, objeto.getValorMensal());
				pstmt.setInt(9, objeto.getValorPersonal());

				pstmt.executeUpdate();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Deu pau no insert");
				e.printStackTrace();
			}
		}
		connection.close();
	}

	public boolean VerificaMatricula(int codigoAluno) throws SQLException {
		this.connection = getConnection();
		try {

			ResultSet rs = null;
			Statement stmt = this.connection.createStatement();
			rs = stmt.executeQuery("select cod_mat from cadastro_matricula where cod_alu = '" + codigoAluno + "'");

			if (rs.next()) {

				autenticado = true;
				codigo = (rs.getInt("cod_mat"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Deu pau no VerificaMatricula");
		}
		connection.close();
		return autenticado;
	}

	public void UpdateMatricula(Matricula objeto) throws SQLException {
		this.connection = getConnection();

		if (objeto == null) {
			System.out.println("Nulo");
		} else {
			try {

				String sql = "Update cadastro_matricula set cod_alu=?, cod_mod=?, pla_mod=?, hor_ini=?, hor_fim=?, cod_pro=?, dia_mat=?, vlr_men=?, vlr_per=? where cod_mat = ?";

				PreparedStatement prmt = this.connection.prepareStatement(sql);

				prmt.setInt(1, objeto.getCodigoAluno());
				prmt.setInt(2, objeto.getCodigoModalidade());
				prmt.setString(3, objeto.getPlanoMatricula());
				prmt.setString(4, objeto.getHorarioIni());
				prmt.setString(5, objeto.getHorarioFim());
				prmt.setInt(6, objeto.getCodigoProfessor());
				prmt.setInt(7, objeto.getDiaPagamento());
				prmt.setInt(8, objeto.getValorMensal());
				prmt.setInt(9, objeto.getValorPersonal());
				prmt.setInt(10, objeto.getCodigo());

				prmt.executeUpdate();
				prmt.close();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Deu pau no update");
			}
		}
		connection.close();
	}

	public List<Matricula> getListar() throws SQLException {

		this.connection = getConnection();

		String sql = "Select a.cod_mat, b.nom_alu from cadastro_matricula a, cadastro_aluno b where a.cod_alu = b.cod_alu";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		List<Matricula> mat = new ArrayList<Matricula>();

		while (rs.next()) {
			Matricula a = new Matricula();
			a.setCodigo(rs.getInt("cod_mat"));
			a.setNomeAluno(rs.getString("nom_alu"));

			mat.add(a);
		}
		prmt.close();
		connection.close();
		return mat;
	}

	public Matricula BuscarMatricula(int CodigoMatricula) throws SQLException {

		this.connection = getConnection();

		String sql = "Select  a.cod_mat, a.cod_alu, b.nom_alu, a.cod_mod, c.nom_mod, a.pla_mod, a.hor_ini, a.hor_fim, a.cod_pro, d.nom_pro, a.dia_mat, a.vlr_men, a.vlr_per from cadastro_matricula a, cadastro_aluno b, cadastro_modalidade c, cadastro_professor d where a.cod_alu = b.cod_alu and a.cod_mod = c.cod_mod and a.cod_pro = d.cod_pro and cod_mat = "
				+ CodigoMatricula + "";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		Matricula a = new Matricula();

		while (rs.next()) {
			a = new Matricula();
			a.setCodigo(rs.getInt("cod_mat"));
			a.setCodigoAluno(rs.getInt("cod_alu"));
			a.setNomeAluno(rs.getString("nom_alu"));
			a.setCodigoModalidade(rs.getInt("cod_mod"));
			a.setNomeModalidade(rs.getString("nom_mod"));
			a.setPlanoMatricula(rs.getString("pla_mod"));
			a.setHorarioIni(rs.getString("hor_ini"));
			a.setHorarioFim(rs.getString("hor_fim"));
			if (rs.getInt("cod_pro") != 0) {
				a.setCodigoProfessor(rs.getInt("cod_pro"));
			}
			a.setNomeProfessor(rs.getString("nom_pro"));
			a.setDiaPagamento(rs.getInt("dia_mat"));
			a.setValorMensal(rs.getInt("vlr_men"));
			if (rs.getInt("vlr_per") != 0) {
				a.setValorPersonal(rs.getInt("vlr_per"));
			}

		}
		prmt.close();
		connection.close();
		return a;
	}

	public int BuscarUltimoID() throws SQLException {

		this.connection = getConnection();

		String sql = "Select max(cod_mat) as cod_mat from cadastro_matricula";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		int a = 0;

		while (rs.next()) {

			a = rs.getInt("cod_mat");
		}
		prmt.close();
		connection.close();
		return a;
	}

	public void DeletaMatricula(String codigo) throws SQLException {
		this.connection = getConnection();
		try {

			Statement stmt = this.connection.createStatement();
			stmt.executeQuery("delete from cadastro_matricula where cod_mat = '" + codigo + "'");

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
			rs = stmt.executeQuery("select cod_mat from cadastro_matricula");
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
