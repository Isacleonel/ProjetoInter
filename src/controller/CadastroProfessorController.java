package controller;

import java.net.URL;


import java.sql.SQLException;
import java.util.ResourceBundle;
import model.Beans.Cidade;
import model.controllerBean.ControllerProfessor;
import util.Alerta;
import util.Convert;
import util.Mascara;
import view.BuscaCadastroCidadeView;
import view.BuscaCadastroModalidadeView;
import view.BuscaCadastroProfessorView;
import view.CadastroProfessorView;
import view.PrincipalView;
import model.Beans.Modalidade;
import model.Beans.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


public class CadastroProfessorController implements Initializable {
	

	private Scene scene;

	public static Modalidade mod;
	public static Cidade cid;
	public static Professor pro;

	public static CadastroProfessorController tela;

	@FXML
	private TextField TextFieldCodigo, TextFieldNome, TextFieldCpf, TextFieldIdentidade, TextFieldFormacao,
			TextFieldAno, TextFieldCodigoMod, TextFieldNomeMod, TextFieldCodigoCid, TextFieldNomeCid,
			TextFieldCodigoEst, TextFieldNomeEst, TextFieldBairro, TextFieldEndereco, TextFieldCelular, TextFieldFone,
			TextFieldEmail;

	@FXML
	private ComboBox<String> ComboBoxSexo, ComboBoxTurno;

	ObservableList<String> listSexo = FXCollections.observableArrayList("Masculino", "Feminino");
	ObservableList<String> listTurno = FXCollections.observableArrayList("Manhã", "Tarde", "Noite", "Manhã e Tarde",
			"Manhã e Noite", "Tarde e Noite", "Manhã, Tarde e Noite");

	@FXML
	private DatePicker DatePickerNascimento, DatePickerCadastro;

	@FXML
	private Button ButtonSalvar, ButtonBuscar, ButtonCancelar, ButtonNovo, ButtonModificar, ButtonExcluir, ButtonFechar,
			ButtonBuscarMod, ButtonBuscarCid;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		CadastroProfessorController.tela = this;

		ComboBoxSexo.setItems(listSexo);
		ComboBoxTurno.setItems(listTurno);
		
		Mascara.colocaBarraData(DatePickerNascimento);
		Mascara.colocaBarraData(DatePickerCadastro);

		try {
			
			if(ControllerProfessor.verificaTabela()==true){
			
			this.carregaInfo(ControllerProfessor.BuscarUltimoID());
			
			}else{
				Alerta alerta = new Alerta(null, "Nenhum Professor cadastrado");
				alerta.Mensagem(PrincipalView.myStage);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		this.DisableCampos(true);

		TextFieldCodigo.setDisable(true);
		ButtonSalvar.setDisable(true);
		ButtonCancelar.setDisable(true);
		TextFieldCodigoMod.setEditable(false);
		TextFieldNomeMod.setEditable(false);
		TextFieldCodigoCid.setEditable(false);
		TextFieldNomeCid.setEditable(false);
		TextFieldCodigoEst.setEditable(false);
		TextFieldNomeEst.setEditable(false);

		ButtonBuscarMod.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent g) {
				BuscaCadastroModalidadeView view = new BuscaCadastroModalidadeView();
				view.initBuscaCad(scene);
				BuscaCadastroModalidadeController.opt = 1;

			}
		});

		ButtonBuscarCid.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent g) {
				BuscaCadastroCidadeView view = new BuscaCadastroCidadeView();
				view.initBuscaCad(scene);
				BuscaCadastroCidadeController.opt = 1;

			}
		});

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

		ButtonSalvar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent d) {

				Alerta alerta = new Alerta("Salvar", "Deseja salvar?");

				if (alerta.Confirm(CadastroProfessorView.myStage)) {

					if (TextFieldNome.getText() == null || TextFieldNome.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Nome não preenchido!");
						alert.Alertar(CadastroProfessorView.myStage);
						return;
					} else if (ComboBoxSexo.getValue() == null || ComboBoxSexo.getValue().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Sexo não preenchido!");
						alert.Alertar(CadastroProfessorView.myStage);
						return;
					} else if (TextFieldCpf.getText() == null || TextFieldCpf.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo CPF não preenchido!");
						alert.Alertar(CadastroProfessorView.myStage);
						return;
					} else if (DatePickerNascimento.getValue() == null) {
						Alerta alert = new Alerta(null, "Campo Data de Nascimento não preenchido!");
						alert.Alertar(CadastroProfessorView.myStage);
						return;
					} else if (TextFieldFormacao.getText() == null || TextFieldFormacao.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Formação não preenchido!");
						alert.Alertar(CadastroProfessorView.myStage);
						return;
					} else if (TextFieldNomeMod.getText() == null || TextFieldNomeMod.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Modalidade não preenchido!");
						alert.Alertar(CadastroProfessorView.myStage);
						return;
					} else if (ComboBoxTurno.getValue() == null || ComboBoxTurno.getValue().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Turno não preenchido!");
						alert.Alertar(CadastroProfessorView.myStage);
						return;
					} else if (TextFieldNomeCid.getText() == null || TextFieldNomeCid.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Cidade não preenchido!");
						alert.Alertar(CadastroProfessorView.myStage);
						return;
					} else if (DatePickerCadastro.getValue() == null) {
						Alerta alert = new Alerta(null, "Campo Data de Cadastro não preenchido!");
						alert.Alertar(CadastroProfessorView.myStage);
						return;
					} else {

						try {
							Professor pro = new Professor();

							pro.setNome(TextFieldNome.getText());
							pro.setSexo(ComboBoxSexo.getValue().toString());
							pro.setCpf(TextFieldCpf.getText());
							pro.setIdentidade(TextFieldIdentidade.getText());
							pro.setNascimento(Convert.toDate(DatePickerNascimento.getValue()));
							pro.setFormacao(TextFieldFormacao.getText());
							if (TextFieldAno.getText() != null) {
								pro.setAno(Integer.parseInt(TextFieldAno.getText()));
							}
							pro.setCodigoModalidade(Integer.parseInt(TextFieldCodigoMod.getText()));
							pro.setTurno(ComboBoxTurno.getValue().toString());
							pro.setCodigoCidade(Integer.parseInt(TextFieldCodigoCid.getText()));
							pro.setBairro(TextFieldBairro.getText());
							pro.setEndereco(TextFieldEndereco.getText());
							pro.setCelular(TextFieldCelular.getText());
							pro.setFone(TextFieldFone.getText());
							pro.setCadastro(Convert.toDate(DatePickerCadastro.getValue()));
							pro.setEmail(TextFieldEmail.getText());

							if (TextFieldCodigo.getText() == null) {
								if (ControllerProfessor.salvarProfessor(pro) == true) {
									return;
								}
								carregaInfo(ControllerProfessor.BuscarUltimoID());

							} else {

								pro.setCodigo(Integer.parseInt(TextFieldCodigo.getText()));
								if (ControllerProfessor.UpdateProfessor(pro) == true) {
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

		ButtonModificar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				DisableCampos(false);

				ButtonNovo.setDisable(true);
				ButtonSalvar.setDisable(false);
				ButtonModificar.setDisable(true);
				ButtonCancelar.setDisable(false);
				ButtonExcluir.setDisable(true);
				ButtonBuscar.setDisable(true);

			}
		});

		ButtonCancelar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				try {
					if(ControllerProfessor.verificaTabela()==true){
					carregaInfo(ControllerProfessor.BuscarUltimoID());
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

		ButtonExcluir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				Alerta alerta = new Alerta("Excluir", "Confirma exclusão?");

				if (alerta.Confirm(CadastroProfessorView.myStage)) {

					try {

						ControllerProfessor.ExcluirProfessor(TextFieldCodigo.getText());

						if(ControllerProfessor.verificaTabela()==true){
						carregaInfo(ControllerProfessor.BuscarUltimoID());
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

		ButtonBuscar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				BuscaCadastroProfessorView view = new BuscaCadastroProfessorView();
				view.initBuscaCad(scene);
				// BuscaCadastroProfessorController.opt = 0;

			}
		});

		ButtonFechar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				CadastroProfessorView.myStage.close();
			}
		});

	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void carregaInfo() throws ClassNotFoundException, SQLException {

		DisableCampos(true);

		Professor a = CadastroProfessorController.pro;
		TextFieldCodigo.setText(a.getCodigo() + "");
		TextFieldNome.setText(a.getNome() + "");
		TextFieldCodigoEst.setText(a.getCodigoEstado() + "");
		TextFieldNomeEst.setText(a.getNomeEstado() + "");
		ComboBoxSexo.setValue(a.getSexo() + "");
		TextFieldCpf.setText(a.getCpf() + "");
		TextFieldIdentidade.setText(a.getIdentidade() + "");
		DatePickerNascimento.setValue(Convert.toLocalDate(a.getNascimento()));
		TextFieldFormacao.setText(a.getFormacao() + "");
		TextFieldAno.setText(a.getAno() + "");
		TextFieldCodigoMod.setText(a.getCodigoModalidade() + "");
		TextFieldNomeMod.setText(a.getNomeModalidade() + "");
		ComboBoxTurno.setValue(a.getTurno() + "");
		TextFieldCodigoCid.setText(a.getCodigoCidade() + "");
		TextFieldNomeCid.setText(a.getNomeCidade() + "");
		TextFieldCodigoEst.setText(a.getCodigoEstado() + "");
		TextFieldNomeEst.setText(a.getNomeEstado() + "");
		TextFieldBairro.setText(a.getBairro() + "");
		TextFieldEndereco.setText(a.getEndereco() + "");
		TextFieldCelular.setText(a.getCelular() + "");
		TextFieldFone.setText(a.getFone() + "");
		DatePickerCadastro.setValue(Convert.toLocalDate(a.getCadastro()));
		TextFieldEmail.setText(a.getEmail() + "");
	};

	public void carregaInfo(int ID) throws ClassNotFoundException, SQLException {

		Professor a = ControllerProfessor.BuscaProfessor(ID);
		TextFieldCodigo.setText(a.getCodigo() + "");
		TextFieldNome.setText(a.getNome() + "");
		TextFieldCodigoEst.setText(a.getCodigoEstado() + "");
		TextFieldNomeEst.setText(a.getNomeEstado() + "");
		ComboBoxSexo.setValue(a.getSexo() + "");
		TextFieldCpf.setText(a.getCpf() + "");
		TextFieldIdentidade.setText(a.getIdentidade() + "");
		DatePickerNascimento.setValue(Convert.toLocalDate(a.getNascimento()));
		TextFieldFormacao.setText(a.getFormacao() + "");
		TextFieldAno.setText(a.getAno() + "");
		TextFieldCodigoMod.setText(a.getCodigoModalidade() + "");
		TextFieldNomeMod.setText(a.getNomeModalidade() + "");
		ComboBoxTurno.setValue(a.getTurno() + "");
		TextFieldCodigoCid.setText(a.getCodigoCidade() + "");
		TextFieldNomeCid.setText(a.getNomeCidade() + "");
		TextFieldCodigoEst.setText(a.getCodigoEstado() + "");
		TextFieldNomeEst.setText(a.getNomeEstado() + "");
		TextFieldBairro.setText(a.getBairro() + "");
		TextFieldEndereco.setText(a.getEndereco() + "");
		TextFieldCelular.setText(a.getCelular() + "");
		TextFieldFone.setText(a.getFone() + "");
		DatePickerCadastro.setValue(Convert.toLocalDate(a.getCadastro()));
		TextFieldEmail.setText(a.getEmail() + "");
	};

	public void carregaInfoModalidade() throws ClassNotFoundException, SQLException {

		TextFieldCodigoMod.setText(mod.getCodigo() + "");
		TextFieldNomeMod.setText(mod.getNome() + "");

	}

	public void carregaInfoCidade() throws ClassNotFoundException, SQLException {

		TextFieldCodigoCid.setText(cid.getCodigo() + "");
		TextFieldNomeCid.setText(cid.getNome() + "");
		TextFieldCodigoEst.setText(cid.getCodigoEstado() + "");
		TextFieldNomeEst.setText(cid.getNomeEstado() + "");

	}

	public static void setprofessor(Professor prof) throws ClassNotFoundException, SQLException {
		pro = prof;
	};

	public static void setmodalidade(Modalidade mode) throws ClassNotFoundException, SQLException {
		mod = mode;
	};

	public static void setcidade(Cidade ci) throws ClassNotFoundException, SQLException {
		cid = ci;
	};

	public void DisableCampos(Boolean statusCampos) {

		TextFieldNome.setDisable(statusCampos);
		ComboBoxSexo.setDisable(statusCampos);
		TextFieldCpf.setDisable(statusCampos);
		TextFieldIdentidade.setDisable(statusCampos);
		DatePickerNascimento.setDisable(statusCampos);
		TextFieldFormacao.setDisable(statusCampos);
		TextFieldAno.setDisable(statusCampos);
		TextFieldCodigoMod.setDisable(statusCampos);
		TextFieldNomeMod.setDisable(statusCampos);
		ButtonBuscarMod.setDisable(statusCampos);
		ComboBoxTurno.setDisable(statusCampos);
		TextFieldCodigoCid.setDisable(statusCampos);
		ButtonBuscarCid.setDisable(statusCampos);
		TextFieldNomeCid.setDisable(statusCampos);
		TextFieldCodigoEst.setDisable(statusCampos);
		TextFieldNomeEst.setDisable(statusCampos);
		TextFieldBairro.setDisable(statusCampos);
		TextFieldEndereco.setDisable(statusCampos);
		TextFieldCelular.setDisable(statusCampos);
		TextFieldFone.setDisable(statusCampos);
		DatePickerCadastro.setDisable(statusCampos);
		TextFieldEmail.setDisable(statusCampos);
	};

	public void LimpaCampos() {
		TextFieldCodigo.setText(null);
		TextFieldNome.setText(null);
		ComboBoxSexo.setValue(null);
		TextFieldCpf.setText(null);
		TextFieldIdentidade.setText(null);
		DatePickerNascimento.setValue(null);
		TextFieldFormacao.setText(null);
		TextFieldAno.setText(null);
		TextFieldCodigoMod.setText(null);
		TextFieldNomeMod.setText(null);
		ComboBoxTurno.setValue(null);
		TextFieldCodigoCid.setText(null);
		TextFieldNomeCid.setText(null);
		TextFieldCodigoEst.setText(null);
		TextFieldNomeEst.setText(null);
		TextFieldBairro.setText(null);
		TextFieldEndereco.setText(null);
		TextFieldCelular.setText(null);
		TextFieldFone.setText(null);
		DatePickerCadastro.setValue(null);
		TextFieldEmail.setText(null);
	};

	

}