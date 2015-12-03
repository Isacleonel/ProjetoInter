package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Beans.ContaReceber;
import oracle.sql.TIMESTAMP;


public class ContaReceberDAO extends ConexaoDAO {

	public boolean autenticado = false;
	public static int codigo = 0;
	private Connection connection = null;

	public ContaReceberDAO() throws SQLException, ClassNotFoundException {
	}

	
	public void SalvaCadastro(ContaReceber objeto) throws SQLException {

		this.connection = getConnection();
		if (objeto == null) {
			JOptionPane.showMessageDialog(null, "objeto nulo");
		} else {
			try {

				String sql;
				sql = "insert into cadastro_conta_receber (cod_mat, ser_con_rec, ven_con_rec, vlr_con_rec, obs_con_rec, sit_con_rec) values (?,?,?,?,?,?)";

				PreparedStatement pstmt = this.connection.prepareStatement(sql);

				pstmt.setInt(1, objeto.getCodigoMatricula());
				pstmt.setString(2, objeto.getServico());
				pstmt.setDate(3, objeto.getVencimento());
				pstmt.setInt(4, objeto.getValor());
				pstmt.setString (5, objeto.getObservacao());
				pstmt.setString (6, objeto.getSituacao());
				

				pstmt.executeUpdate();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Deu pau no insert");
				e.printStackTrace();
			}
		}
		connection.close();
	}
	
	
	
	
	public void UpdateContaReceber(ContaReceber objeto) throws SQLException {
		this.connection = getConnection();

		if (objeto == null) {
			System.out.println("Nulo");
		} else {
			try {

				String sql = "Update cadastro_conta_receber set cod_mat=?, ser_con_rec=?, ven_con_rec=?, vlr_con_rec=?, obs_con_rec=?, sit_con_rec=? where cod_con_rec = ?";

				PreparedStatement prmt = this.connection.prepareStatement(sql);

				prmt.setInt(1, objeto.getCodigoMatricula());
				prmt.setString(2, objeto.getServico());
				prmt.setDate(3, objeto.getVencimento());
				prmt.setInt(4, objeto.getValor());
				prmt.setString (5, objeto.getObservacao());
				prmt.setString (6, objeto.getSituacao());
				prmt.setInt(7, objeto.getCodigo());

				prmt.executeUpdate();
				prmt.close();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Deu pau no update");
			}
		}
		connection.close();
	}
	
	
	
	
	public List<ContaReceber> getListar() throws SQLException {

		this.connection = getConnection();

		String sql = "select a.cod_con_rec, c.nom_alu, a.ser_con_rec, a.vlr_con_rec, a.sit_con_rec from CADASTRO_CONTA_RECEBER a, CADASTRO_MATRICULA b, CADASTRO_ALUNO c where a.cod_mat = b.cod_mat and b.cod_alu = c.cod_alu";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		List<ContaReceber> con = new ArrayList<ContaReceber>();

		while (rs.next()) {
			ContaReceber a = new ContaReceber();
			a.setCodigo(rs.getInt("cod_con_rec"));
			a.setNomeAluno(rs.getString("nom_alu"));
			a.setServico(rs.getString("ser_con_rec"));
			//a.setVencimento(rs.getDate("ven_con_rec"));
			a.setValor(rs.getInt("vlr_con_rec"));
			a.setSituacao(rs.getString("sit_con_rec"));

			con.add(a);
		}
		prmt.close();
		connection.close();
		return con;
	}
	
	
	
	public ContaReceber BuscarContaReceber(int CodigoConta) throws SQLException {

		this.connection = getConnection();

		String sql = "select a.cod_con_rec, a.cod_mat, c.nom_alu, a.ser_con_rec, a.ven_con_rec, b.dia_mat, a.vlr_con_rec, a.obs_con_rec, a.sit_con_rec from CADASTRO_CONTA_RECEBER a, CADASTRO_MATRICULA b, CADASTRO_ALUNO c where a.cod_mat = b.cod_mat and b.cod_alu = c.cod_alu and cod_con_rec = "+ CodigoConta + "";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		ContaReceber a = new ContaReceber();

		while (rs.next()) {
			a = new ContaReceber();
			a.setCodigo(rs.getInt("cod_con_rec"));
			a.setCodigoMatricula(rs.getInt("cod_mat"));
			a.setNomeAluno(rs.getString("nom_alu"));
			a.setServico(rs.getString("ser_con_rec"));
			a.setVencimento(rs.getDate("ven_con_rec"));
			
			a.setDia(rs.getInt("dia_mat"));
			
			a.setValor(rs.getInt("vlr_con_rec"));
			if (rs.getString("obs_con_rec") != null) {
			a.setObservacao(rs.getString("obs_con_rec"));
			}
			a.setSituacao(rs.getString("sit_con_rec"));
			

		}
		prmt.close();
		connection.close();
		return a;
	}
	
	
	public boolean VerificaNulo() throws SQLException {
		this.connection = getConnection();

		try {
			ResultSet rs = null;
			Statement stmt = this.connection.createStatement();
			rs = stmt.executeQuery("select cod_con_rec from cadastro_conta_receber");
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
	
	
	
	public int BuscarUltimoID() throws SQLException {

		this.connection = getConnection();

		String sql = "Select max(cod_con_rec) as cod_con_rec from cadastro_conta_receber";

		PreparedStatement prmt = this.connection.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		int a = 0;

		if(rs.next()) {

			a = rs.getInt("cod_con_rec");
			autenticado = true;
		}else{
			autenticado = false;

		}
		prmt.close();
		connection.close();
		return a;
	}
	
	
	
	public void DeletaContaReceber(String codigo) throws SQLException {
		this.connection = getConnection();
		try {

			Statement stmt = this.connection.createStatement();
			stmt.executeQuery("delete from cadastro_conta_receber where cod_con_rec = '" + codigo + "'");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Deu pau no delete");
		}
		connection.close();
	}
	
	
	
	/*
	public boolean VerificaContaReceber(int CodigoMatricula, String Servico, Date Vencimento) throws SQLException {
		TIMESTAMP ts = new TIMESTAMP(Vencimento);
		
		this.connection = getConnection();

		try {
			System.out.println(ts);
			ResultSet rs = null;
			Statement stmt = this.connection.createStatement();
			rs = stmt.executeQuery("select cod_con_rec from cadastro_conta_receber where cod_mat= '" +CodigoMatricula+"' and ser_con_rec='" +Servico+"' and ven_con_rec= '" +ts+"'");
			if (rs.next()) {

				autenticado = true;
				codigo= (rs.getInt("cod_con_rec"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Deu pau no VerificaContaReceber");
		}
		connection.close();
		return autenticado;
	}
	
	*/
	
	
}
