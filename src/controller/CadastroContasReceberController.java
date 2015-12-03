package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import util.Mascara;
import java.sql.SQLException;
import view.BuscaCadastroContaReceberView;
import view.BuscaCadastroMatriculaView;
import view.CadastroContasReceberView;
import view.PrincipalView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Beans.ContaReceber;
import model.Beans.Matricula;
import model.controllerBean.ControllerAluno;
import model.controllerBean.ControllerContaReceber;
import util.Alerta;
import util.Convert;

public class CadastroContasReceberController implements Initializable {

	public static CadastroContasReceberController tela;
	private Scene scene;
	public static Matricula mat;
	public static ContaReceber con;

	@FXML
	private TextField TextFieldCodigo, TextFieldCodigoMat, TextFieldNomeAluMat, TextFieldValor, TextFieldDia;

	@FXML
	private Button ButtonSalvar, ButtonBuscar, ButtonCancelar, ButtonNovo, ButtonModificar, ButtonExcluir, ButtonFechar,
			ButtonBuscarMat;

	@FXML
	private TextArea TextAreaObs;

	@FXML
	private ComboBox<String> ComboBoxServico, ComboBoxSituacao;

	ObservableList<String> listServico = FXCollections.observableArrayList("Mensalidade", "Consultoria",
			"Venda de Produtos");
	ObservableList<String> listSituacao = FXCollections.observableArrayList("Liquidado", "Em aberto");

	@FXML
	private DatePicker DatePickerVencimento;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		ComboBoxServico.setItems(listServico);
		ComboBoxSituacao.setItems(listSituacao);

		Mascara.colocaBarraData(DatePickerVencimento);

		tela = this;

		try {

			if (ControllerContaReceber.verificaTabela() == true) {

				this.carregaInfo(ControllerContaReceber.BuscarUltimoID());

			} else {

				Alerta alerta = new Alerta(null, "Nenhuma Conta cadastrado");
				alerta.Mensagem(PrincipalView.myStage);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		this.DisableCampos(true);

		TextFieldCodigo.setDisable(true);
		TextFieldDia.setDisable(true);
		ButtonSalvar.setDisable(true);
		ButtonCancelar.setDisable(true);
		TextFieldCodigoMat.setEditable(false);
		TextFieldNomeAluMat.setEditable(false);

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
				ButtonBuscarMat.setDisable(false);

			}
		});

		ButtonBuscarMat.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent g) {

				BuscaCadastroMatriculaView view = new BuscaCadastroMatriculaView();
				view.initBuscaCad(scene);
				BuscaCadastroMatriculaController.opt = 1;

			}
		});

		ButtonSalvar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent d) {

				Alerta alerta = new Alerta("Salvar", "Deseja salvar?");

				if (alerta.Confirm(CadastroContasReceberView.myStage)) {

					if (TextFieldNomeAluMat.getText() == null || TextFieldNomeAluMat.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Matricula não preenchido!");
						alert.Alertar(CadastroContasReceberView.myStage);
						return;
					} else if (ComboBoxServico.getValue() == null || ComboBoxServico.getValue().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Serviço não preenchido!");
						alert.Alertar(CadastroContasReceberView.myStage);
						return;
					} else if (DatePickerVencimento.getValue() == null) {
						Alerta alert = new Alerta(null, "Campo Data de Vencimento não preenchido!");
						alert.Alertar(CadastroContasReceberView.myStage);
						return;
					} else if (TextFieldValor.getText() == null || TextFieldValor.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Valor não preenchido!");
						alert.Alertar(CadastroContasReceberView.myStage);
						return;
					} else if (ComboBoxSituacao.getValue() == null || ComboBoxSituacao.getValue().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Situação não preenchido!");
						alert.Alertar(CadastroContasReceberView.myStage);
						return;

					} else {

						try {
							ContaReceber con = new ContaReceber();

							con.setCodigoMatricula(Integer.parseInt(TextFieldCodigoMat.getText()));
							con.setServico(ComboBoxServico.getValue().toString());
							con.setVencimento(Convert.toDate(DatePickerVencimento.getValue()));
							con.setValor(Integer.parseInt(TextFieldValor.getText()));
							con.setObservacao(TextAreaObs.getText());
							con.setSituacao(ComboBoxSituacao.getValue().toString());

							if (TextFieldCodigo.getText() == null) {


								ControllerContaReceber.salvarContaReceber(con);
								carregaInfo(ControllerContaReceber.BuscarUltimoID());

							} else {

								con.setCodigo(Integer.parseInt(TextFieldCodigo.getText()));
								ControllerContaReceber.UpdateContaReceber(con);

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
					if (ControllerContaReceber.verificaTabela() == true) {
						carregaInfo(ControllerContaReceber.BuscarUltimoID());
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

				BuscaCadastroContaReceberView view = new BuscaCadastroContaReceberView();
				view.initBuscaCad(scene);
				// BuscaCadastroMatriculaController.opt = 0;
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

				if (alerta.Confirm(CadastroContasReceberView.myStage)) {
					try {

						ControllerContaReceber.ExcluirContaReceber(TextFieldCodigo.getText());

						if (ControllerContaReceber.verificaTabela() == true) {
							carregaInfo(ControllerContaReceber.BuscarUltimoID());
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
				CadastroContasReceberView.myStage.close();
			}

		});

	}

	public void setScene(Scene scene) {
		this.scene = scene;

	}

	public void DisableCampos(Boolean statusCampos) {

		TextFieldCodigoMat.setDisable(statusCampos);
		ButtonBuscarMat.setDisable(statusCampos);
		TextFieldNomeAluMat.setDisable(statusCampos);
		ComboBoxServico.setDisable(statusCampos);
		DatePickerVencimento.setDisable(statusCampos);
		TextFieldValor.setDisable(statusCampos);
		TextAreaObs.setDisable(statusCampos);
		ComboBoxSituacao.setDisable(statusCampos);

	};

	public void LimpaCampos() {
		TextFieldCodigo.setText(null);
		TextFieldCodigoMat.setText(null);
		TextFieldNomeAluMat.setText(null);
		ComboBoxServico.setValue(null);
		DatePickerVencimento.setValue(null);
		TextFieldDia.setText(null);
		TextFieldValor.setText(null);
		TextAreaObs.setText(null);
		ComboBoxSituacao.setValue(null);
	};

	public static void setmatricula(Matricula matr) throws ClassNotFoundException, SQLException {
		mat = matr;
	}

	public void carregaInfoMatricula() throws ClassNotFoundException, SQLException {

		TextFieldCodigoMat.setText(mat.getCodigo() + "");
		TextFieldNomeAluMat.setText(mat.getNomeAluno() + "");

	}

	public void carregaInfoMatriculaMensal() throws ClassNotFoundException, SQLException {

		TextFieldCodigoMat.setText(mat.getCodigo() + "");
		TextFieldNomeAluMat.setText(mat.getNomeAluno() + "");
		ComboBoxServico.setValue("Mensalidade");
		TextFieldDia.setText(mat.getDiaPagamento() + "");
		TextFieldValor.setText(mat.getValorMensal() + "");

	}

	public static void setcontareceber(ContaReceber conta) throws ClassNotFoundException, SQLException {
		con = conta;
	};

	public void carregaInfo() throws ClassNotFoundException, SQLException {

		DisableCampos(true);
		

		ContaReceber a = CadastroContasReceberController.con;

		TextFieldCodigo.setText(a.getCodigo() + "");
		TextFieldCodigoMat.setText(a.getCodigoMatricula() + "");
		TextFieldNomeAluMat.setText(a.getNomeAluno() + "");
		ComboBoxServico.setValue(a.getServico() + "");
		DatePickerVencimento.setValue(Convert.toLocalDate(a.getVencimento()));
		
		if(a.getServico().equals("Mensalidade")){
			TextFieldDia.setText(a.getDia() + "");
			}else{
				TextFieldDia.setText("");
			}
		
		TextFieldValor.setText(a.getValor() + "");
		TextAreaObs.setText(a.getObservacao() + "");
		ComboBoxSituacao.setValue(a.getSituacao() + "");

	};

	public void carregaInfo(int ID) throws ClassNotFoundException, SQLException {
		ContaReceber a = ControllerContaReceber.BuscaContaReceber(ID);
		TextFieldCodigo.setText(a.getCodigo() + "");
		TextFieldCodigoMat.setText(a.getCodigoMatricula() + "");
		TextFieldNomeAluMat.setText(a.getNomeAluno() + "");
		ComboBoxServico.setValue(a.getServico() + "");
		DatePickerVencimento.setValue(Convert.toLocalDate(a.getVencimento()));
		
		if(a.getServico().equals("Mensalidade")){
		TextFieldDia.setText(a.getDia() + "");
		}else{
			TextFieldDia.setText("");
		}
		
		TextFieldValor.setText(a.getValor() + "");
		TextAreaObs.setText(a.getObservacao() + "");
		ComboBoxSituacao.setValue(a.getSituacao() + "");
	};

}