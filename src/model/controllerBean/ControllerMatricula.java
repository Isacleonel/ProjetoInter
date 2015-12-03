package model.controllerBean;

import java.sql.SQLException;
import model.Beans.Matricula;
import model.DAO.MatriculaDAO;
import util.Alerta;
import view.CadastroMatriculaView;

public class ControllerMatricula {

	

	public static boolean salvarMatricula(Matricula mat) throws ClassNotFoundException, SQLException {

		MatriculaDAO a = new MatriculaDAO();
		a.VerificaMatricula(mat.getCodigoAluno());
		if (a.autenticado == true) {
			Alerta alert = new Alerta(null, "Este Aluno já está matriculado");
			alert.Alertar(CadastroMatriculaView.myStage);
			return true;
		} else {
			a.SalvaCadastro(mat);
			return false;
		}
	}
	
	
	
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
	

}
