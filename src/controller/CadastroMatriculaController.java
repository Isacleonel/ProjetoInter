package controller;

import java.net.URL;

import java.sql.SQLException;
import java.util.ResourceBundle;
import view.BuscaCadastroAlunosView;
import view.BuscaCadastroMatriculaView;
import view.BuscaCadastroModalidadeView;
import view.BuscaCadastroProfessorView;
import view.CadastroMatriculaView;
import view.PrincipalView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class CadastroMatriculaController implements Initializable {
	
	private Scene scene;
	public static Aluno alu;
	public static CadastroMatriculaController tela;
	public static Modalidade mod;
	public static Professor pro;
	public static Matricula mat;

	@FXML
	private TextField TextFieldCodigo, TextFieldCodigoAlu, TextFieldNomeAlu, TextFieldCodigoMod, TextFieldNomeMod,
			TextFieldHoraIni, TextFieldHoraFim, TextFieldCodigoPro, TextFieldNomePro, TextFieldMensal,
			TextFieldPersonal, TextFieldPagamento;

	@FXML
	private Button ButtonSalvar, ButtonBuscar, ButtonCancelar, ButtonNovo, ButtonModificar, ButtonExcluir, ButtonFechar,
			ButtonBuscarAlu, ButtonBuscarPro, ButtonBuscarMod, ButtonLimparPro;

	@FXML
	private HBox HBoxPlano, HBoxHorario;

	@FXML
	private ToggleGroup Plano;

	@FXML
	private RadioButton RadioButtonTodos, RadioButton3, escolha;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		tela = this;

		try {
			
			if(ControllerMatricula.verificaTabela()==true){
			
			this.carregaInfo(ControllerMatricula.BuscarUltimoID());
			
			}else{
				
				Alerta alerta = new Alerta(null, "Nenhuma Matricula cadastrado");
				alerta.Mensagem(PrincipalView.myStage);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		RadioButtonTodos.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (!newPropertyValue) {
					escolha = (RadioButton) Plano.getSelectedToggle();
				}
			}
		});

		RadioButton3.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (!newPropertyValue) {
					escolha = (RadioButton) Plano.getSelectedToggle();
				}
			}
		});

		this.DisableCampos(true);

		TextFieldCodigo.setDisable(true);
		ButtonSalvar.setDisable(true);
		ButtonCancelar.setDisable(true);
		TextFieldCodigoAlu.setEditable(false);
		TextFieldNomeAlu.setEditable(false);
		TextFieldCodigoMod.setEditable(false);
		TextFieldNomeMod.setEditable(false);
		TextFieldCodigoPro.setEditable(false);
		TextFieldNomePro.setEditable(false);

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

				BuscaCadastroAlunosView view = new BuscaCadastroAlunosView();
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

			}
		});

		ButtonLimparPro.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent g) {

				TextFieldCodigoPro.setText(null);
				TextFieldNomePro.setText(null);

			}
		});

		ButtonSalvar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent d) {

				Alerta alerta = new Alerta("Salvar", "Deseja salvar?");

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

				}
			}
		});

		ButtonCancelar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				try {
					if(ControllerMatricula.verificaTabela()==true){
					carregaInfo(ControllerMatricula.BuscarUltimoID());
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

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

				BuscaCadastroMatriculaView view = new BuscaCadastroMatriculaView();
				view.initBuscaCad(scene);
				BuscaCadastroMatriculaController.opt = 0;
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

			}

		});

		ButtonFechar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				CadastroMatriculaView.myStage.close();
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
		TextFieldCodigoMod.setDisable(statusCampos);
		ButtonBuscarMod.setDisable(statusCampos);
		TextFieldNomeMod.setDisable(statusCampos);
		HBoxPlano.setDisable(statusCampos);
		HBoxHorario.setDisable(statusCampos);
		TextFieldCodigoPro.setDisable(statusCampos);
		ButtonBuscarPro.setDisable(statusCampos);
		TextFieldNomePro.setDisable(statusCampos);
		ButtonLimparPro.setDisable(statusCampos);
		TextFieldPagamento.setDisable(statusCampos);
		TextFieldMensal.setDisable(statusCampos);
		TextFieldPersonal.setDisable(statusCampos);

	};

	public void LimpaCampos() {
		TextFieldCodigo.setText(null);
		TextFieldCodigoAlu.setText(null);
		TextFieldNomeAlu.setText(null);
		TextFieldCodigoMod.setText(null);
		TextFieldNomeMod.setText(null);
		RadioButtonTodos.setSelected(false);
		RadioButton3.setSelected(false);
		TextFieldHoraIni.setText(null);
		TextFieldHoraFim.setText(null);
		TextFieldCodigoPro.setText(null);
		TextFieldNomePro.setText(null);
		TextFieldMensal.setText(null);
		TextFieldPagamento.setText(null);
		TextFieldMensal.setText(null);
		TextFieldPersonal.setText(null);
	};

	public void carregaInfo() throws ClassNotFoundException, SQLException {

		DisableCampos(true);

		Matricula a = CadastroMatriculaController.mat;
		TextFieldCodigo.setText(a.getCodigo() + "");
		TextFieldCodigoAlu.setText(a.getCodigoAluno() + "");
		TextFieldNomeAlu.setText(a.getNomeAluno() + "");
		TextFieldCodigoMod.setText(a.getCodigoModalidade() + "");
		TextFieldNomeMod.setText(a.getNomeModalidade() + "");

		if (a.getPlanoMatricula().equals("Todos dias")) {
			RadioButtonTodos.setSelected(true);
		} else if (a.getPlanoMatricula().equals("3 dias")) {
			RadioButton3.setSelected(true);
		}

		TextFieldHoraIni.setText(a.getHorarioIni() + "");
		TextFieldHoraFim.setText(a.getHorarioFim() + "");
		TextFieldCodigoPro.setText(a.getCodigoProfessor() + "");
		TextFieldNomePro.setText(a.getNomeProfessor() + "");
		TextFieldPagamento.setText(a.getDiaPagamento() + "");
		TextFieldMensal.setText(a.getValorMensal() + "");
		TextFieldPersonal.setText(a.getValorPersonal() + "");

	};

	public void carregaInfo(int ID) throws ClassNotFoundException, SQLException {

		Matricula a = ControllerMatricula.BuscaMatricula(ID);

		TextFieldCodigo.setText(a.getCodigo() + "");
		TextFieldCodigoAlu.setText(a.getCodigoAluno() + "");
		TextFieldNomeAlu.setText(a.getNomeAluno() + "");
		TextFieldCodigoMod.setText(a.getCodigoModalidade() + "");
		TextFieldNomeMod.setText(a.getNomeModalidade() + "");

		if (a.getPlanoMatricula().equals("Todos dias")) {
			RadioButtonTodos.setSelected(true);
		} else if (a.getPlanoMatricula().equals("3 dias")) {
			RadioButton3.setSelected(true);
		}

		TextFieldHoraIni.setText(a.getHorarioIni() + "");
		TextFieldHoraFim.setText(a.getHorarioFim() + "");
		TextFieldCodigoPro.setText(a.getCodigoProfessor() + "");
		TextFieldNomePro.setText(a.getNomeProfessor() + "");
		TextFieldPagamento.setText(a.getDiaPagamento() + "");
		TextFieldMensal.setText(a.getValorMensal() + "");
		TextFieldPersonal.setText(a.getValorPersonal() + "");

	};

	public void carregaInfoAluno() throws ClassNotFoundException, SQLException {

		TextFieldCodigoAlu.setText(alu.getCodigo() + "");
		TextFieldNomeAlu.setText(alu.getNome() + "");

	}

	public void carregaInfoModalidade() throws ClassNotFoundException, SQLException {

		TextFieldCodigoMod.setText(mod.getCodigo() + "");
		TextFieldNomeMod.setText(mod.getNome() + "");

	}

	public void carregaInfoProfessor() throws ClassNotFoundException, SQLException {

		TextFieldCodigoPro.setText(pro.getCodigo() + "");
		TextFieldNomePro.setText(pro.getNome() + "");

	}

	public static void setaluno(Aluno aluno) throws ClassNotFoundException, SQLException {

		alu = aluno;

	};

	public static void setmodalidade(Modalidade mode) throws ClassNotFoundException, SQLException {
		mod = mode;
	};

	public static void setprofessor(Professor prof) throws ClassNotFoundException, SQLException {
		pro = prof;
	};

	public static void setmatricula(Matricula matr) throws ClassNotFoundException, SQLException {
		mat = matr;
	};

}