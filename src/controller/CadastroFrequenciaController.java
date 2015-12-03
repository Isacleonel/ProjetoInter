package controller;

import java.net.URL;

import java.sql.SQLException;
import java.util.ResourceBundle;
import view.BuscaCadastroAlunosView;
import view.BuscaCadastroMatriculaView;
import view.BuscaCadastroModalidadeView;
import view.BuscaCadastroProfessorView;
import view.CadastroFrequenciaView;
import view.CadastroMatriculaView;
import view.PrincipalView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import model.Beans.Aluno;
import model.Beans.Matricula;
import model.Beans.Modalidade;
import model.Beans.Professor;
import model.controllerBean.ControllerMatricula;
import util.Alerta;

public class CadastroFrequenciaController implements Initializable {
	
	private Scene scene;
	//public static Aluno alu;
	//public static CadastroMatriculaController tela;
	//public static Modalidade mod;
	//public static Professor pro;
	//public static Matricula mat;

	@FXML
	private TextField TextFieldCodigo, TextFieldCodigoAlu, TextFieldNomeAlu;

	@FXML
	private Button ButtonSalvar, ButtonBuscar, ButtonCancelar, ButtonNovo, ButtonModificar, ButtonExcluir, ButtonFechar,
			ButtonBuscarAlu;

	
	//@FXML
	//private ToggleGroup Plano;

	@FXML
	private CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21,c22,c23,c24,c25,c26,c27,c28,c29,c30;

	@FXML
	private ComboBox ComboBoxMes;
	
	@FXML
	private HBox HBoxFrequencia;
	
	@FXML
	private ComboBox<String> ComboBoxFrequencia;

	ObservableList<String> listFre = FXCollections.observableArrayList("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		//tela = this;

		ComboBoxMes.setItems(listFre);


		this.DisableCampos(true);

		TextFieldCodigo.setDisable(true);
		ButtonSalvar.setDisable(true);
		ButtonCancelar.setDisable(true);
		TextFieldCodigoAlu.setEditable(false);
		TextFieldNomeAlu.setEditable(false);
		

		ButtonNovo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent g) {

				LimpaCampos();

				DisableCampos(false);

				ButtonNovo.setDisable(true);
				ButtonSalvar.setDisable(false);
				ButtonModificar.setDisable(true);
				ButtonCancelar.setDisable(false);
				ButtonExcluir.setDisable(true);
				ButtonBuscar.setDisable(true);
				

			}

		});

		ButtonBuscarAlu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent g) {

			/*	BuscaCadastroAlunosView view = new BuscaCadastroAlunosView();
				view.initBuscaCad(scene);
				BuscaCadastroAlunosController.opt = 1;

			}
		});

		ButtonBuscarMod.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent g) {

				BuscaCadastroModalidadeView view = new BuscaCadastroModalidadeView();
				view.initBuscaCad(scene);
				BuscaCadastroModalidadeController.opt = 2;

			}
		});

		ButtonBuscarPro.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent g) {

				BuscaCadastroProfessorView view = new BuscaCadastroProfessorView();
				view.initBuscaCad(scene);
				BuscaCadastroProfessorController.opt = 1;
*/
			}
		});

		ButtonSalvar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent d) {

			/*	Alerta alerta = new Alerta("Salvar", "Deseja salvar?");

				if (alerta.Confirm(CadastroMatriculaView.myStage)) {

					if (TextFieldNomeAlu.getText() == null || TextFieldNomeAlu.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Aluno não preenchido!");
						alert.Alertar(CadastroMatriculaView.myStage);
						return;
					} else if (TextFieldNomeMod.getText() == null || TextFieldNomeMod.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Modalidade não preenchido!");
						alert.Alertar(CadastroMatriculaView.myStage);
						return;
					} else if (Plano.getSelectedToggle() == null) {
						Alerta alert = new Alerta(null, "Campo Plano não preenchido!");
						alert.Alertar(CadastroMatriculaView.myStage);
						return;
					} else if (TextFieldHoraIni.getText() == null || TextFieldHoraIni.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Horario (início das atividades) não preenchido!");
						alert.Alertar(CadastroMatriculaView.myStage);
						return;
					} else if (TextFieldHoraFim.getText() == null || TextFieldHoraFim.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Horario (final das atividades) não preenchido!");
						alert.Alertar(CadastroMatriculaView.myStage);
						return;
					} else if (TextFieldPagamento.getText() == null || TextFieldPagamento.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Dia de Pagamento não preenchido!");
						alert.Alertar(CadastroMatriculaView.myStage);
						return;
					} else if (TextFieldMensal.getText() == null || TextFieldMensal.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Valor Mensal não preenchido!");
						alert.Alertar(CadastroMatriculaView.myStage);
						return;

					} else {

						try {
							Matricula mat = new Matricula();

							mat.setCodigoAluno(Integer.parseInt(TextFieldCodigoAlu.getText()));
							mat.setNomeAluno(TextFieldNomeAlu.getText());
							mat.setCodigoModalidade(Integer.parseInt(TextFieldCodigoMod.getText()));
							mat.setNomeModalidade(TextFieldNomeMod.getText());
							escolha = (RadioButton) Plano.getSelectedToggle();
							mat.setPlanoMatricula(escolha.getText());
							mat.setHorarioIni(TextFieldHoraIni.getText());
							mat.setHorarioFim(TextFieldHoraFim.getText());
							if (TextFieldCodigoPro.getText() != null) {
								mat.setCodigoProfessor(Integer.parseInt(TextFieldCodigoPro.getText()));
							}
							mat.setNomeProfessor(TextFieldNomePro.getText());
							mat.setDiaPagamento(Integer.parseInt(TextFieldPagamento.getText()));
							mat.setValorMensal(Integer.parseInt(TextFieldMensal.getText()));
							if (TextFieldPersonal.getText() != null) {
								mat.setValorPersonal(Integer.parseInt(TextFieldPersonal.getText()));
							}

							if (TextFieldCodigo.getText() == null) {

								if (ControllerMatricula.salvarMatricula(mat) == true) {
									return;
								}
								carregaInfo(ControllerMatricula.BuscarUltimoID());

							} else {

								mat.setCodigo(Integer.parseInt(TextFieldCodigo.getText()));
								if (ControllerMatricula.UpdateMatricula(mat) == true) {
									return;
								}
								;

							}

						} catch (SQLException | ClassNotFoundException e) {
							e.printStackTrace();
						}
					}

					DisableCampos(true);
					ButtonNovo.setDisable(false);
					ButtonSalvar.setDisable(true);
					ButtonModificar.setDisable(false);
					ButtonCancelar.setDisable(true);
					ButtonExcluir.setDisable(false);
					ButtonBuscar.setDisable(false);

				}*/
			}
		});

		ButtonCancelar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				/*try {
					if(ControllerMatricula.verificaTabela()==true){
					carregaInfo(ControllerMatricula.BuscarUltimoID());
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}*/

				DisableCampos(true);

				ButtonNovo.setDisable(false);
				ButtonSalvar.setDisable(true);
				ButtonModificar.setDisable(false);
				ButtonCancelar.setDisable(true);
				ButtonExcluir.setDisable(false);
				ButtonBuscar.setDisable(false);

			}

		});

		ButtonBuscar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

			//	BuscaCadastroMatriculaView view = new BuscaCadastroMatriculaView();
				//view.initBuscaCad(scene);
				//BuscaCadastroMatriculaController.opt = 0;
			}

		});

		ButtonModificar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent g) {

				DisableCampos(false);

				ButtonNovo.setDisable(true);
				ButtonSalvar.setDisable(false);
				ButtonModificar.setDisable(true);
				ButtonCancelar.setDisable(false);
				ButtonExcluir.setDisable(true);
				ButtonBuscar.setDisable(true);

			}
		});

		ButtonExcluir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
/*
				Alerta alerta = new Alerta("Excluir", "Confirma exclusão?");

				if (alerta.Confirm(CadastroMatriculaView.myStage)) {
					try {
						ControllerMatricula.ExcluirMatricula(TextFieldCodigo.getText());
						
						if(ControllerMatricula.verificaTabela()==true){
						carregaInfo(ControllerMatricula.BuscarUltimoID());
						}
						
						ButtonNovo.setDisable(false);
						ButtonSalvar.setDisable(true);
						ButtonModificar.setDisable(false);
						ButtonCancelar.setDisable(true);
						ButtonExcluir.setDisable(false);
						ButtonBuscar.setDisable(false);

					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
*/
			}

		});

		ButtonFechar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				CadastroFrequenciaView.myStage.close();
			}

		});

	}

	public void setScene(Scene scene) {
		this.scene = scene;

	}

	public void DisableCampos(Boolean statusCampos) {

		TextFieldCodigoAlu.setDisable(statusCampos);
		ButtonBuscarAlu.setDisable(statusCampos);
		TextFieldNomeAlu.setDisable(statusCampos);
		ComboBoxMes.setDisable(statusCampos);
		HBoxFrequencia.setDisable(statusCampos);

	};

	public void LimpaCampos() {
		TextFieldCodigo.setText(null);
		TextFieldCodigoAlu.setText(null);
		TextFieldNomeAlu.setText(null);
		ComboBoxMes.setValue(null);
		
	};

	

	

}