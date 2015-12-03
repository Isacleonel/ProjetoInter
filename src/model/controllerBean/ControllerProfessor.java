package model.controllerBean;

import java.sql.SQLException;
import model.Beans.Professor;
import model.DAO.ProfessorDAO;
import util.Alerta;
import view.CadastroProfessorView;

public class ControllerProfessor {

	

	public static boolean salvarProfessor(Professor cad) throws ClassNotFoundException, SQLException {

		ProfessorDAO a = new ProfessorDAO();
		a.VerificaProfessor(cad.getCpf());
		if (a.autenticado == true) {
			Alerta alert = new Alerta(null, "Já existe Professor cadastrado com este CPF!");
			alert.Alertar(CadastroProfessorView.myStage);
			return true;
		} else {
			a.SalvaCadastro(cad);
			return false;
		}
	}

	public static Professor BuscaProfessor(int Codigo) throws ClassNotFoundException, SQLException {

		ProfessorDAO a = new ProfessorDAO();
		return a.BuscarProfessor(Codigo);
	}

	public static int BuscarUltimoID() throws ClassNotFoundException, SQLException {

		ProfessorDAO a = new ProfessorDAO();
		return a.BuscarUltimoID();

	}

	public static void ExcluirProfessor(String codigo) throws ClassNotFoundException, SQLException {

		ProfessorDAO a = new ProfessorDAO();
		a.DeletaProfessor(codigo);

	}

	public static boolean UpdateProfessor(Professor cad) throws ClassNotFoundException, SQLException {

		ProfessorDAO a = new ProfessorDAO();
		a.VerificaProfessor(cad.getCpf());
		if (a.autenticado == true) {
			if (cad.getCodigo() == ProfessorDAO.codigo) {
				a.UpdateProfessor(cad);
				return false;
			} else {
				Alerta alert = new Alerta(null, "Já existe Professor cadastrado com este CPF!");
				alert.Alertar(CadastroProfessorView.myStage);
				return true;

			}

		} else {
			a.UpdateProfessor(cad);
			return false;
		}
	}
	
	
	
	public static boolean verificaTabela() throws ClassNotFoundException, SQLException {

		ProfessorDAO a = new ProfessorDAO();
		a.VerificaNulo();
		if (a.autenticado == true) {
			return true;
		} else {
			return false;
		}
	}
	

}
