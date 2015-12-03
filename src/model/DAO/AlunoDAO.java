package model.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import model.Beans.Aluno;

public class AlunoDAO extends ConexaoDAO {

	public boolean autenticado = false;
	public static int codigo=0;
	private Connection connection = null;

	public AlunoDAO() throws SQLException, ClassNotFoundException {

	}

	public void SalvaCadastro(Aluno objeto) throws SQLException {

		this.connection = getConnection();
		if (objeto == null) {
			JOptionPane.showMessageDialog(null, "objeto nulo");

		} else {

			try {

				String sql;
				sql = "insert into cadastro_aluno (nom_alu,sex_alu,nas_alu,cpf_alu,ide_alu,pro_alu,cod_cid,bai_alu,end_alu,cel_alu,fon_alu,ema_alu,cad_alu,sta_alu,can_alu) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				PreparedStatement pstmt = this.connection.prepareStatement(sql);

				pstmt.setString(1, objeto.getNome());
				pstmt.setString(2, objeto.getSexo());
				pstmt.setDate(3, objeto.getNascimento());
				pstmt.setString(4, objeto.getCpf());
				pstmt.setString(5, objeto.getIdentidade());
				pstmt.setString(6, objeto.getProfissao());
				pstmt.setInt(7, objeto.getCidade());
				pstmt.setString(8, objeto.getBairro());
				pstmt.setString(9, objeto.getEndereco());
				pstmt.setString(10, objeto.getCelular());
				pstmt.setString(11, objeto.getFone());
				pstmt.setString(12, objeto.getEmail());
				pstmt.setDate(13, objeto.getCadastro());
				pstmt.setString(14, objeto.getStatus());
				pstmt.setDate(15, objeto.getCancelamento());

				pstmt.executeUpdate();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Deu pau no insert");
				e.printStackTrace();
			}
		}
		connection.close();
	}

	public boolean VerificaAluno(String CpfAluno) throws SQLException {
		this.connection = getConnection();

		try {
			ResultSet rs = null;
			Statement stmt = this.connection.createStatement();
			rs = stmt.executeQuery("select cod_alu from cadastro_aluno where cpf_alu='" +CpfAluno+"'");
			if (rs.next()) {

				autenticado = true;
				codigo= (rs.getInt("cod_alu"));
				
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Deu pau no VerificaAluno");
		}
		connection.close();
		return autenticado;
	}
	
	

	public List<Aluno> getListar() throws SQLException {

		this.connection = getConnection();

		String sql = "Select cod_alu, nom_alu, sta_alu from cadastro_aluno";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		List<Aluno> Alunos = new ArrayList<Aluno>();

		while (rs.next()) {
			Aluno a = new Aluno();
			a.setCodigo(rs.getInt("cod_alu"));
			a.setNome(rs.getString("nom_alu"));
			a.setStatus(rs.getString("sta_alu"));

			Alunos.add(a);
		}
		prmt.close();
		connection.close();
		return Alunos;
	}

	public Aluno BuscarAluno(int CodigoAluno) throws SQLException {

		this.connection = getConnection();

		String sql = "select a.cod_alu, a.nom_alu, a.sex_alu, a.nas_alu, a.cpf_alu, a.ide_alu, a.pro_alu, a.cod_cid, b.nom_cid, c.nom_est, a.bai_alu, a.end_alu, a.cel_alu, a.fon_alu, a.ema_alu, a.cad_alu, a.sta_alu, a.can_alu from cadastro_aluno a, cadastro_cidade b, cadastro_estado c where a.cod_cid = b.cod_cid and b.cod_est = c.cod_est and cod_alu = "+ CodigoAluno + "";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		Aluno a = new Aluno();

		while (rs.next()) {
			a = new Aluno();
			a.setCodigo(rs.getInt("cod_alu"));
			a.setNome(rs.getString("nom_alu"));
			a.setSexo(rs.getString("sex_alu"));
			a.setNascimento(rs.getDate("nas_alu"));

			if (rs.getString("cpf_alu") != null) {
				a.setCpf(rs.getString("cpf_alu"));
			}
			if (rs.getString("ide_alu") != null) {
				a.setIdentidade(rs.getString("ide_alu"));
			}
			if (rs.getString("pro_alu") != null) {
				a.setProfissao(rs.getString("pro_alu"));
			}
			
			a.setCidade(rs.getInt("cod_cid"));
			a.setNomeCidade(rs.getString("nom_cid"));
			a.setEstado(rs.getString("nom_est"));
			
		
			if (rs.getString("bai_alu") != null) {
				a.setBairro(rs.getString("bai_alu"));
			}
			if (rs.getString("end_alu") != null) {
				a.setEndereco(rs.getString("end_alu"));
			}
			if (rs.getString("cel_alu") != null) {
				a.setCelular(rs.getString("cel_alu"));
			}
			if (rs.getString("fon_alu") != null) {
				a.setFone(rs.getString("fon_alu"));
			}
			if (rs.getString("ema_alu") != null) {
				a.setEmail(rs.getString("ema_alu"));
			}

			a.setCadastro(rs.getDate("cad_alu"));
			a.setStatus(rs.getString("sta_alu"));
			
			if (rs.getDate("can_alu") != null) {
			a.setCancelamento(rs.getDate("can_alu"));
			}
		}
		prmt.close();
		connection.close();
		return a;
	}

	public int BuscarUltimoID() throws SQLException {

		this.connection = getConnection();

		String sql = "Select max(cod_alu) as cod_alu from cadastro_aluno";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		int a = 0;

		if(rs.next()) {

			a = rs.getInt("cod_alu");
			autenticado = true;
		}else{
			autenticado = false;
			
		}
		
		
		prmt.close();
		connection.close();
		return a;
	}

	public void DeletaAluno(String codigo) throws SQLException {
		this.connection = getConnection();

		try {

			Statement stmt = this.connection.createStatement();
			stmt.executeQuery("delete from cadastro_aluno where cod_alu = '" + codigo + "'");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Deu pau no delete");

		}
		connection.close();

	}

	public void UpdateAluno(Aluno objeto) throws SQLException {
		this.connection = getConnection();

		if (objeto == null) {
			System.out.println("Nulo");
		} else {

			try {

				String sql = "Update cadastro_aluno set nom_alu=?, sex_alu=?, nas_alu=?, cpf_alu=?, ide_alu=?, pro_alu=?, cod_cid=?, bai_alu=?, end_alu=?, cel_alu=?, fon_alu=?, ema_alu=?, cad_alu=?, sta_alu=?, can_alu=? where cod_alu = ?";

				PreparedStatement prmt = this.connection.prepareStatement(sql);

				prmt.setString(1, objeto.getNome());
				prmt.setString(2, objeto.getSexo());
				prmt.setDate(3, objeto.getNascimento());
				prmt.setString(4, objeto.getCpf());
				prmt.setString(5, objeto.getIdentidade());
				prmt.setString(6, objeto.getProfissao());
				prmt.setInt(7, objeto.getCidade());
				prmt.setString(8, objeto.getBairro());
				prmt.setString(9, objeto.getEndereco());
				prmt.setString(10, objeto.getCelular());
				prmt.setString(11, objeto.getFone());
				prmt.setString(12, objeto.getEmail());
				prmt.setDate(13, objeto.getCadastro());
				prmt.setString(14, objeto.getStatus());
				prmt.setDate(15, objeto.getCancelamento());
				prmt.setInt(16, objeto.getCodigo());

				prmt.executeUpdate();
				prmt.close();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Deu pau no update");
			}
		}
		connection.close();
	}

	
	
	
	public boolean verificaAlunoMatricula(int codigoAluno) throws SQLException {
		this.connection = getConnection();

		try {
			ResultSet rs = null;
			Statement stmt = this.connection.createStatement();
			rs = stmt.executeQuery("select cod_alu from cadastro_matricula where cod_alu='" +codigoAluno+"'");
			if (rs.next()) {

				autenticado = true;
				//codigo= (rs.getInt("cod_alu"));
				
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Deu pau no VerificaAluno");
		}
		connection.close();
		return autenticado;
	}
	
	
	public boolean VerificaNulo() throws SQLException {
		this.connection = getConnection();

		try {
			ResultSet rs = null;
			Statement stmt = this.connection.createStatement();
			rs = stmt.executeQuery("select cod_alu from cadastro_aluno");
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
