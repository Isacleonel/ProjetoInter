package model.controllerBean;

import java.sql.SQLException;
import model.Beans.Estado;
import model.DAO.EstadoDAO;
import util.Alerta;
import view.CadastroEstadoView;

public class ControllerEstado {



	public static boolean salvarEstado(Estado est) throws ClassNotFoundException, SQLException {

		EstadoDAO a = new EstadoDAO();
		a.VerificaEstado(est.getNome());
		if (a.autenticado == true) {
			Alerta alert = new Alerta(null, "Este Estado já está cadastrado!");
			alert.Alertar(CadastroEstadoView.myStage);
			return true;
		} else {
			a.SalvaCadastro(est);
			return false;
		}
	}
	
	
	public static Estado BuscaEstado(int Codigo) throws ClassNotFoundException, SQLException {

		EstadoDAO a = new EstadoDAO();
		return a.BuscarEstado(Codigo);
	}
	
	
	public static int BuscarUltimoID() throws ClassNotFoundException, SQLException {

		EstadoDAO a = new EstadoDAO();
		return a.BuscarUltimoID();
	}
	
	
	
	public static boolean UpdateEstado(Estado est) throws ClassNotFoundException, SQLException {

		EstadoDAO a = new EstadoDAO();
		a.VerificaEstado(est.getNome());
		if (a.autenticado == true) {
			if (est.getCodigo() == EstadoDAO.codigo) {
				a.UpdateEstado(est);
				return false;
			} else {
				Alerta alert = new Alerta(null, "Este Estado já está cadastrado!");
				alert.Alertar(CadastroEstadoView.myStage);
				return true;
			}
		} else {
			a.UpdateEstado(est);
			return false;
		}
	}
	
	
	public static void ExcluirEstado(String codigo) throws ClassNotFoundException, SQLException {

		EstadoDAO a = new EstadoDAO();
		a.DeletaEstado(codigo);
	}
	
	
	
	public static boolean verificaTabela() throws ClassNotFoundException, SQLException {

		EstadoDAO a = new EstadoDAO();
		a.VerificaNulo();
		if (a.autenticado == true) {
			return true;
		} else {
			return false;
		}
	}
	
	

}
