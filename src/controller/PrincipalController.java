package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import util.Alerta;
import view.CadastroAlunosView;
import view.CadastroAvaliacaoFisicaView;
import view.CadastroCidadeView;
import view.CadastroContasReceberView;
import view.CadastroEstadoView;
import view.CadastroFrequenciaView;
import view.CadastroProfessorView;
import view.PrincipalView;
import view.CadastroMatriculaView;
import view.CadastroModalidadeView;


public class PrincipalController implements Initializable {
	private Scene scene;
	

	@FXML
	private Button ButtonAlunos, ButtonMatricula, ButtonFrequencia, ButtonProfessor, ButtonModalidade, ButtonContasReceber, ButtonAvaliacaoFisica, ButtonSair;

	@FXML 
	private MenuItem MenuItemEstado, MenuItemCidade;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		MenuItemEstado.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent c) {

				CadastroEstadoView view = new CadastroEstadoView();
				view.initCadastro(scene);

			}
		});
		
		
		MenuItemCidade.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent c) {

				CadastroCidadeView view = new CadastroCidadeView();
				view.initCadastro(scene);

			}
		});
		
		
		
		
		ButtonAlunos.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent c) {

				CadastroAlunosView view = new CadastroAlunosView();
				view.initCadastro(scene);

			}
		});
		
		
		ButtonMatricula.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent c) {

				CadastroMatriculaView view = new CadastroMatriculaView();
				view.initMatricula(scene);
				
			}
		});
		
		
		
		ButtonFrequencia.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent c) {

				CadastroFrequenciaView view = new CadastroFrequenciaView();
				view.initCadastro(scene);
				
			}
		});

		
		ButtonProfessor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent c) {

			CadastroProfessorView view = new CadastroProfessorView();
			view.initCadastro(scene);

			}
		});
		
		ButtonModalidade.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				CadastroModalidadeView view = new CadastroModalidadeView();
				view.initCadastro(scene);
				
			}
		});
		
		
		ButtonContasReceber.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				CadastroContasReceberView view = new CadastroContasReceberView();
				view.initCadastro(scene);
				
			}
		});
		
		
		ButtonAvaliacaoFisica.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				CadastroAvaliacaoFisicaView view = new CadastroAvaliacaoFisicaView();
				view.initCadastro(scene);
				
			}
		});
		
		ButtonSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Alerta alerta = new Alerta("Sair do Sistema", "Deseja sair do sistema?");

				if (alerta.Confirm(PrincipalView.myStage)) {
					System.exit(0);
				}

			}
		});

		
		
		
	}

	public void setScene(Scene scene) {
		this.scene = scene;

	}

}
