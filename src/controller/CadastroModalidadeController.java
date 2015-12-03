package controller;

import java.net.URL;


import java.sql.SQLException;
import java.util.ResourceBundle;
import view.BuscaCadastroModalidadeView;
import view.CadastroModalidadeView;
import view.PrincipalView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Beans.Modalidade;
import model.controllerBean.ControllerModalidade;
import util.Alerta;

public class CadastroModalidadeController implements Initializable {

	private Scene scene;
	
	public static CadastroModalidadeController tela;
	public static Modalidade mod;

	@FXML
	private TextField TextFieldCodigo, TextFieldNome;

	@FXML
	private TextArea TextAreaObs;

	@FXML
	private Button ButtonSalvar, ButtonBuscar, ButtonCancelar, ButtonNovo, ButtonModificar, ButtonExcluir, ButtonFechar;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		CadastroModalidadeController.tela = this;

		try {
			
			if(ControllerModalidade.verificaTabela()==true){
			this.carregaInfo(ControllerModalidade.BuscarUltimoID());
			}else{
				Alerta alerta = new Alerta(null, "Nenhuma Modalidade cadastrado");
				alerta.Mensagem(PrincipalView.myStage);
				
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		this.DisableCampos(true);

		TextFieldCodigo.setDisable(true);
		ButtonSalvar.setDisable(true);
		ButtonCancelar.setDisable(true);

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
			public void handle(ActionEvent e) {

				Alerta alerta = new Alerta("Salvar", "Deseja salvar?");

				if (alerta.Confirm(CadastroModalidadeView.myStage)) {
					if (TextFieldNome.getText() == null || TextFieldNome.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Nome não preenchido!");
						alert.Alertar(CadastroModalidadeView.myStage);
						return;
					} else {
						Modalidade mod = new Modalidade();
						mod.setNome(TextFieldNome.getText());
						mod.setObservacao(TextAreaObs.getText());
						try {
							if (TextFieldCodigo.getText() == null) {
								if (ControllerModalidade.salvarModalidade(mod) == true) {
									return;
								}
								carregaInfo(ControllerModalidade.BuscarUltimoID());
							} else {
								mod.setCodigo(Integer.parseInt(TextFieldCodigo.getText()));
								if (ControllerModalidade.UpdateModalidade(mod) == true) {
									return;
								}
								;
							}
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
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
					if(ControllerModalidade.verificaTabela()==true){
					carregaInfo(ControllerModalidade.BuscarUltimoID());
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

				if (alerta.Confirm(CadastroModalidadeView.myStage)) {
					try {
						
						
						ControllerModalidade.ExcluirModalidade(TextFieldCodigo.getText());
						
						if(ControllerModalidade.verificaTabela()==true){
						carregaInfo(ControllerModalidade.BuscarUltimoID());
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

				BuscaCadastroModalidadeView view = new BuscaCadastroModalidadeView();
				view.initBuscaCad(scene);
				BuscaCadastroModalidadeController.opt = 0;
			}
		});

		ButtonFechar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				CadastroModalidadeView.myStage.close();
			}
		});

	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void DisableCampos(Boolean statusCampos) {
		TextFieldNome.setDisable(statusCampos);
		TextAreaObs.setDisable(statusCampos);
	};

	public void LimpaCampos() {
		TextFieldCodigo.setText(null);
		TextFieldNome.setText(null);
		TextAreaObs.setText(null);
	};

	public static void setmodalidade(Modalidade mode) throws ClassNotFoundException, SQLException {
		mod = mode;
	};

	public void carregaInfo() throws ClassNotFoundException, SQLException {

		DisableCampos(true);

		Modalidade a = CadastroModalidadeController.mod;
		TextFieldCodigo.setText(a.getCodigo() + "");
		TextFieldNome.setText(a.getNome() + "");
		TextAreaObs.setText(a.getObservacao() + "");
	};

	public void carregaInfo(int ID) throws ClassNotFoundException, SQLException {
		Modalidade a = ControllerModalidade.BuscaModalidade(ID);
		TextFieldCodigo.setText(a.getCodigo() + "");
		TextFieldNome.setText(a.getNome() + "");
		TextAreaObs.setText(a.getObservacao() + "");
	};

}