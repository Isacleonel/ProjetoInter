package model.controllerBean;

import java.sql.SQLException;

import model.Beans.Aluno;
import model.Beans.ContaReceber;
import model.DAO.AlunoDAO;
import model.DAO.ContaReceberDAO;
import oracle.sql.TIMESTAMP;
import util.Alerta;
import util.Convert;
import view.CadastroAlunosView;
import view.CadastroContasReceberView;


public class ControllerContaReceber {

	
	public static void salvarContaReceber(ContaReceber con) throws ClassNotFoundException, SQLException {

		ContaReceberDAO a = new ContaReceberDAO();
		a.SalvaCadastro(con);
			
		}
	/*
	public static void salvarCOntaReceber(ContaReceber con) throws ClassNotFoundException, SQLException {

		ContaReceberDAO a = new ContaReceberDAO();
		TIMESTAMP ts = new TIMESTAMP(con.getVencimento());
		a.VerificaContaReceber(con.getCodigoMatricula(), con.getServico(), con.getVencimento());
		if (a.autenticado == true) {
			
			Alerta alerta = new Alerta(null, "Já existe uma conta cadastrada com esta Matricula, Serviço e Data de Vencimento, deseja criar uma nova conta?");
			if (alerta.Confirm(CadastroContasReceberView.myStage)) {
				a.SalvaCadastro(con);
				return;
			}
			
		} else {
			a.SalvaCadastro(con);

		}

	}
	
	*/
	
	public static void UpdateContaReceber(ContaReceber con) throws ClassNotFoundException, SQLException {

		ContaReceberDAO a = new ContaReceberDAO();
		a.UpdateContaReceber(con);
				
	}
	
	
	
	public static ContaReceber BuscaContaReceber(int Codigo) throws ClassNotFoundException, SQLException {

		ContaReceberDAO a = new ContaReceberDAO();
		return a.BuscarContaReceber(Codigo);
	}
	
	
	
	public static boolean verificaTabela() throws ClassNotFoundException, SQLException {

		ContaReceberDAO a = new ContaReceberDAO();
		a.VerificaNulo();
		if (a.autenticado == true) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static int BuscarUltimoID() throws ClassNotFoundException, SQLException {

		ContaReceberDAO a = new ContaReceberDAO();
		return a.BuscarUltimoID();

	}
	
	
	
	
public static void ExcluirContaReceber(String codigo) throws ClassNotFoundException, SQLException {
		
		ContaReceberDAO a = new ContaReceberDAO();
		a.DeletaContaReceber(codigo);
	}
	


/*
	
	
	
	public static boolean UpdateMatricula(Matricula mat) throws ClassNotFoundException, SQLException {

		MatriculaDAO a = new MatriculaDAO();
		a.VerificaMatricula(mat.getCodigoAluno());
		if (a.autenticado == true) {
			if (mat.getCodigo() == MatriculaDAO.codigo) {
				a.UpdateMatricula(mat);
				return false;
			} else {
				Alerta alert = new Alerta(null, "Este Aluno já está matriculado");
				alert.Alertar(CadastroMatriculaView.myStage);
				return true;
			}
		} else {
			a.UpdateMatricula(mat);
			return false;
		}
	}
	
	
	public static Matricula BuscaMatricula(int Codigo) throws ClassNotFoundException, SQLException {

		MatriculaDAO a = new MatriculaDAO();
		return a.BuscarMatricula(Codigo);
	}
	
	
	
	public static int BuscarUltimoID() throws ClassNotFoundException, SQLException {

		MatriculaDAO a = new MatriculaDAO();
		return a.BuscarUltimoID();
	}
	
	
	public static void ExcluirMatricula(String codigo) throws ClassNotFoundException, SQLException {

		MatriculaDAO a = new MatriculaDAO();
		a.DeletaMatricula(codigo);
	}
	
	
	
	public static boolean verificaTabela() throws ClassNotFoundException, SQLException {

		MatriculaDAO a = new MatriculaDAO();
		a.VerificaNulo();
		if (a.autenticado == true) {
			return true;
		} else {
			return false;
		}
	}
	
*/
}
