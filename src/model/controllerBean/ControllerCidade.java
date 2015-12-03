package model.controllerBean;

import java.sql.SQLException;
import model.Beans.Cidade;
import model.DAO.CidadeDAO;
import util.Alerta;
import view.CadastroCidadeView;

public class ControllerCidade {

	

	public static Cidade BuscaCidade(int Codigo) throws ClassNotFoundException, SQLException {

		CidadeDAO a = new CidadeDAO();
		return a.BuscarCidade(Codigo);
	}

	public static int BuscarUltimoID() throws ClassNotFoundException, SQLException {

		CidadeDAO a = new CidadeDAO();
		return a.BuscarUltimoID();
	}

	public static boolean salvarCidade(Cidade cid) throws ClassNotFoundException, SQLException {

		CidadeDAO a = new CidadeDAO();
		a.VerificaCidade(cid.getNome(), cid.getCodigoEstado());
		if (a.autenticado == true) {
			Alerta alert = new Alerta(null, "Já existe Cidade cadastrada com este Estado!");
			alert.Alertar(CadastroCidadeView.myStage);
			return true;
		} else {
			a.SalvaCadastro(cid);
			return false;
		}

	}

	public static void ExcluirCidade(String codigo) throws ClassNotFoundException, SQLException {

		CidadeDAO a = new CidadeDAO();
		a.DeletaCidade(codigo);
	}

	public static boolean UpdateCidade(Cidade cid) throws ClassNotFoundException, SQLException {

		CidadeDAO a = new CidadeDAO();
		a.VerificaCidade(cid.getNome(), cid.getCodigoEstado());
		if (a.autenticado == true) {
			if (cid.getCodigo() == CidadeDAO.codigo) {
				a.UpdateCidade(cid);
				return false;
			} else {
				Alerta alert = new Alerta(null, "Já existe Cidade cadastrada com este Estado");
				alert.Alertar(CadastroCidadeView.myStage);
				return true;

			}

		} else {
			a.UpdateCidade(cid);
			return false;
		}

	}
	
	
	
	public static boolean verificaTabela() throws ClassNotFoundException, SQLException {

		CidadeDAO a = new CidadeDAO();
		a.VerificaNulo();
		if (a.autenticado == true) {
			return true;
		} else {
			return false;
		}
	}
	
	
	

}
