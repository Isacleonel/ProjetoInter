package controller;

import java.net.URL;

import java.sql.SQLException;
import java.util.ResourceBundle;
import view.BuscaCadastroEstadoView;
import view.CadastroEstadoView;
import view.PrincipalView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Beans.Estado;
import model.controllerBean.ControllerEstado;
import util.Alerta;

public class CadastroEstadoController implements Initializable {

	private Scene scene;
	
	public static CadastroEstadoController tela;
	public static Estado est;

	@FXML
	private TextField TextFieldCodigo, TextFieldNome;

	@FXML
	private Button ButtonSalvar, ButtonBuscar, ButtonCancelar, ButtonNovo, ButtonModificar, ButtonExcluir, ButtonFechar;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	CadastroEstadoController.tela = this;

		try {
			
			if(ControllerEstado.verificaTabela()==true){
			this.carregaInfo(ControllerEstado.BuscarUltimoID());
			}else{
				Alerta alerta = new Alerta(null, "Nenhum Estado cadastrado");
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

				if (alerta.Confirm(CadastroEstadoView.myStage)) {
					if (TextFieldNome.getText() == null || TextFieldNome.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Nome não preenchido!");
						alert.Alertar(CadastroEstadoView.myStage);
						return;
					} else {
						Estado est = new Estado();
						est.setNome(TextFieldNome.getText());

						try {
							if (TextFieldCodigo.getText() == null) {
								if (ControllerEstado.salvarEstado(est) == true) {
									return;
								}
								carregaInfo(ControllerEstado.BuscarUltimoID());
							} else {
								est.setCodigo(Integer.parseInt(TextFieldCodigo.getText()));
							if (ControllerEstado.UpdateEstado(est) == true) {
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
					if(ControllerEstado.verificaTabela()==true){
					carregaInfo(ControllerEstado.BuscarUltimoID());
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

				if (alerta.Confirm(CadastroEstadoView.myStage)) {
					try {
						ControllerEstado.ExcluirEstado(TextFieldCodigo.getText());
						
						if(ControllerEstado.verificaTabela()==true){
						carregaInfo(ControllerEstado.BuscarUltimoID());
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

				BuscaCadastroEstadoView view = new BuscaCadastroEstadoView();
				view.initBuscaCad(scene);
				BuscaCadastroEstadoController.opt = 0;
			}
		});

		ButtonFechar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				CadastroEstadoView.myStage.close();
			}
		});

	}

	
	public void DisableCampos(Boolean statusCampos) {
		TextFieldNome.setDisable(statusCampos);
	};

	public void LimpaCampos() {
		TextFieldCodigo.setText(null);
		TextFieldNome.setText(null);
	};

	public static void setestado(Estado es) throws ClassNotFoundException, SQLException {
		est = es;
	};

	public void carregaInfo() throws ClassNotFoundException, SQLException {

		DisableCampos(true);

		Estado a = CadastroEstadoController.est;
		TextFieldCodigo.setText(a.getCodigo() + "");
		TextFieldNome.setText(a.getNome() + "");
		
	};
	
	public void carregaInfo(int ID) throws ClassNotFoundException, SQLException {
		Estado a = ControllerEstado.BuscaEstado(ID);
		TextFieldCodigo.setText(a.getCodigo() + "");
		TextFieldNome.setText(a.getNome() + "");
	};

	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
}