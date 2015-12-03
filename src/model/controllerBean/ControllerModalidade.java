package model.controllerBean;

import java.sql.SQLException;
import model.Beans.Modalidade;
import model.DAO.ModalidadeDAO;
import util.Alerta;
import view.CadastroModalidadeView;

public class ControllerModalidade {

	

	public static boolean salvarModalidade(Modalidade mod) throws ClassNotFoundException, SQLException {

		ModalidadeDAO a = new ModalidadeDAO();
		a.VerificaModalidade(mod.getNome());
		if (a.autenticado == true) {
			Alerta alert = new Alerta(null, "Já existe esta Modalidade cadastrada!");
			alert.Alertar(CadastroModalidadeView.myStage);
			return true;
		} else {
			a.SalvaCadastro(mod);
			return false;
		}
	}

	
	public static Modalidade BuscaModalidade(int Codigo) throws ClassNotFoundException, SQLException {

		ModalidadeDAO a = new ModalidadeDAO();
		return a.BuscarModalidade(Codigo);
	}

	
	public static int BuscarUltimoID() throws ClassNotFoundException, SQLException {

		ModalidadeDAO a = new ModalidadeDAO();
		return a.BuscarUltimoID();
	}

	
	public static boolean UpdateModalidade(Modalidade mod) throws ClassNotFoundException, SQLException {

		ModalidadeDAO a = new ModalidadeDAO();
		a.VerificaModalidade(mod.getNome());
		if (a.autenticado == true) {
			if (mod.getCodigo() == ModalidadeDAO.codigo) {
				a.UpdateModalidade(mod);
				return false;
			} else {
				Alerta alert = new Alerta(null, "Já existe esta Modalidade cadastrada!");
				alert.Alertar(CadastroModalidadeView.myStage);
				return true;
			}
		} else {
			a.UpdateModalidade(mod);
			return false;
		}
	}

	
	public static void ExcluirModalidade(String codigo) throws ClassNotFoundException, SQLException {

		ModalidadeDAO a = new ModalidadeDAO();
		a.DeletaModalidade(codigo);
	}

	
	
	public static boolean verificaTabela() throws ClassNotFoundException, SQLException {

		ModalidadeDAO a = new ModalidadeDAO();
		a.VerificaNulo();
		if (a.autenticado == true) {
			return true;
		} else {
			return false;
		}
	}
	

}
