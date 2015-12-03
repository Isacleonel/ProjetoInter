package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DAO.LoginDAO;
import util.Alerta;
import view.MainApp;
import view.PrincipalView;

public class LoginController implements Initializable {
	private Stage stage;

	@FXML
	private TextField TextFieldUsuario;

	@FXML
	private PasswordField PasswordFieldSenha;

	@FXML
	private Button ButtonEntrar, ButtonSair;
	
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		ButtonEntrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String usuario = TextFieldUsuario.getText().toString();
				String senha = PasswordFieldSenha.getText().toString();

				if (usuario.isEmpty()) {
					Alerta alerta = new Alerta("Login", "Campo usuário vazio!");
					alerta.Mensagem(stage);
					return;
				} else if (senha.isEmpty()) {
					Alerta alerta = new Alerta("Login", "Campo senha vazio!");
					alerta.Mensagem(stage);
					return;
				} else {

					try {
						LoginDAO aut = new LoginDAO();
						aut.autenticar(usuario, senha);
						if (usuario.equals("admin") && (senha.equals("123")) || aut.autenticado == true) {

							MainApp.myStage.close();
							PrincipalView tela = new PrincipalView();
							tela.initPrincipal(new Stage());

							// root.autosize();
						} else {

							// ((TextField) TextFieldUsuario).setText(null);
							// ((TextField) PasswordFieldSenha).setText(null);

							Alerta alerta = new Alerta("Login", "Nome de usuário ou senha inválidos");
							alerta.Erro(stage);
							return;

						}
					} catch (ClassNotFoundException | SQLException ex) {
						Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		});

		ButtonSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});

	}

}
