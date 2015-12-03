package controller;

import java.net.URL;


import java.sql.SQLException;
import java.util.ResourceBundle;
import view.BuscaCadastroCidadeView;
import view.BuscaCadastroEstadoView;
import view.CadastroCidadeView;
import view.PrincipalView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Beans.Cidade;
import model.Beans.Estado;
import model.controllerBean.ControllerCidade;
import util.Alerta;

public class CadastroCidadeController implements Initializable {

	private Scene scene;
	
	public static CadastroCidadeController tela;
	public static Cidade cid;
	public static Estado est;

	@FXML
	private TextField TextFieldCodigo, TextFieldNome, TextFieldCodigoEst, TextFieldNomeEst;

	@FXML
	private Button ButtonSalvar, ButtonBuscar, ButtonCancelar, ButtonNovo, ButtonModificar, ButtonExcluir, ButtonFechar,
			ButtonBuscarEst;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		CadastroCidadeController.tela = this;

		try {
			
			if(ControllerCidade.verificaTabela()==true){
			
			this.carregaInfo(ControllerCidade.BuscarUltimoID());
			
			}else{
				Alerta alerta = new Alerta(null, "Nenhuma Cidade cadastrada");
				alerta.Mensagem(PrincipalView.myStage);
				
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		this.DisableCampos(true);

		TextFieldCodigo.setDisable(true);
		ButtonSalvar.setDisable(true);
		ButtonCancelar.setDisable(true);

		ButtonBuscarEst.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent g) {

				BuscaCadastroEstadoView view = new BuscaCadastroEstadoView();
				view.initBuscaCad(scene);
				BuscaCadastroEstadoController.opt = 1;

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
			public void handle(ActionEvent e) {

				Alerta alerta = new Alerta("Salvar", "Deseja salvar?");

				if (alerta.Confirm(CadastroCidadeView.myStage)) {
					if (TextFieldNome.getText() == null || TextFieldNome.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Nome não preenchido!");
						alert.Alertar(CadastroCidadeView.myStage);
						return;
					} else if (TextFieldCodigoEst.getText() == null || TextFieldCodigoEst.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Codigo do Estado não preenchido!");
						alert.Alertar(CadastroCidadeView.myStage);
						return;
					} else if (TextFieldNomeEst.getText() == null || TextFieldNomeEst.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Nome do Estado não preenchido!");
						alert.Alertar(CadastroCidadeView.myStage);
						return;
					} else {
						try {

							Cidade cid = new Cidade();
							cid.setNome(TextFieldNome.getText());
							cid.setCodigoEstado(Integer.parseInt(TextFieldCodigoEst.getText()));

							if (TextFieldCodigo.getText() == null) {
								if (ControllerCidade.salvarCidade(cid) == true) {
									return;
								}
								carregaInfo(ControllerCidade.BuscarUltimoID());
							} else {
								cid.setCodigo(Integer.parseInt(TextFieldCodigo.getText()));
								if (ControllerCidade.UpdateCidade(cid) == true) {
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
					if(ControllerCidade.verificaTabela()==true){
					carregaInfo(ControllerCidade.BuscarUltimoID());
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

				if (alerta.Confirm(CadastroCidadeView.myStage)) {
					try {
						ControllerCidade.ExcluirCidade(TextFieldCodigo.getText());
						if(ControllerCidade.verificaTabela()==true){
						carregaInfo(ControllerCidade.BuscarUltimoID());
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

				BuscaCadastroCidadeView view = new BuscaCadastroCidadeView();
				view.initBuscaCad(scene);
				BuscaCadastroCidadeController.opt = 0;
			}
		});

		ButtonFechar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				CadastroCidadeView.myStage.close();
			}
		});

	}

	public void DisableCampos(Boolean statusCampos) {
		TextFieldNome.setDisable(statusCampos);
		TextFieldCodigoEst.setDisable(statusCampos);
		ButtonBuscarEst.setDisable(statusCampos);
		TextFieldNomeEst.setDisable(statusCampos);
	};

	public void LimpaCampos() {
		TextFieldCodigo.setText(null);
		TextFieldNome.setText(null);
		TextFieldCodigoEst.setText(null);
		TextFieldNomeEst.setText(null);
	};

	public static void setcidade(Cidade ci) throws ClassNotFoundException, SQLException {
		cid = ci;
	};

	public static void setestado(Estado es) throws ClassNotFoundException, SQLException {
		est = es;
	};

	public void carregaInfo() throws ClassNotFoundException, SQLException {

		DisableCampos(true);

		Cidade a = CadastroCidadeController.cid;
		TextFieldCodigo.setText(a.getCodigo() + "");
		TextFieldNome.setText(a.getNome() + "");
		TextFieldCodigoEst.setText(a.getCodigoEstado() + "");
		TextFieldNomeEst.setText(a.getNomeEstado() + "");

	};

	public void carregaInfo(int ID) throws ClassNotFoundException, SQLException {
		Cidade a = ControllerCidade.BuscaCidade(ID);
		TextFieldCodigo.setText(a.getCodigo() + "");
		TextFieldNome.setText(a.getNome() + "");
		TextFieldCodigoEst.setText(a.getCodigoEstado() + "");
		TextFieldNomeEst.setText(a.getNomeEstado() + "");

	};

	public void carregaInfoEstado() throws ClassNotFoundException, SQLException {

		TextFieldCodigoEst.setText(est.getCodigo() + "");
		TextFieldNomeEst.setText(est.getNome() + "");

	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

}