package model.controllerBean;

import java.sql.SQLException;
import model.Beans.Aluno;
import model.DAO.AlunoDAO;
import util.Alerta;
import view.CadastroAlunosView;

public class ControllerAluno {

	



	public static boolean salvarAluno(Aluno cad) throws ClassNotFoundException, SQLException {

		AlunoDAO a = new AlunoDAO();
		a.VerificaAluno(cad.getCpf());
		if (a.autenticado == true) {
			Alerta alert = new Alerta(null, "Já existe Aluno cadastrado com este CPF!");
			alert.Alertar(CadastroAlunosView.myStage);
			return true;
		} else {
			a.SalvaCadastro(cad);
			return false;
		}

	}
	

public static boolean UpdateAluno(Aluno cad) throws ClassNotFoundException, SQLException {

		AlunoDAO a = new AlunoDAO();
		a.VerificaAluno(cad.getCpf());
		if (a.autenticado == true) {
			if (cad.getCodigo() == AlunoDAO.codigo){
				a.UpdateAluno(cad);
				return false;
			}else {
			Alerta alert = new Alerta(null, "Já existe Aluno cadastrado com este CPF!");
			alert.Alertar(CadastroAlunosView.myStage);
			return true;
			
		}
		
	}else{
		a.UpdateAluno(cad);
		return false;
	}	
}
	
	
public static Aluno BuscaAluno(int Codigo) throws ClassNotFoundException, SQLException {

		AlunoDAO a = new AlunoDAO();
		return a.BuscarAluno(Codigo);

	}

	public static int BuscarUltimoID() throws ClassNotFoundException, SQLException {

		AlunoDAO a = new AlunoDAO();
		return a.BuscarUltimoID();

	}
	
	
	
	public static void ExcluirAluno(String codigo) throws ClassNotFoundException, SQLException {
		
		
		
		AlunoDAO a = new AlunoDAO();
		a.DeletaAluno(codigo);
		
		
	}
	
	
	
	
	public static boolean verificaAlunoMatricula(Aluno alu) throws ClassNotFoundException, SQLException {

		AlunoDAO a = new AlunoDAO();
		a.verificaAlunoMatricula(alu.getCodigo());
		if (a.autenticado == true) {
			Alerta alert = new Alerta(null, "Este Aluno não pode ser excluído enquanto possuir Matricula");
			alert.Erro(CadastroAlunosView.myStage);
			return true;
		} else {
			return false;
		}

	}
	
	
	
	public static boolean verificaTabela() throws ClassNotFoundException, SQLException {

		AlunoDAO a = new AlunoDAO();
		a.VerificaNulo();
		if (a.autenticado == true) {
			return true;
		} else {
			return false;
		}
	}
	
	
	

}
