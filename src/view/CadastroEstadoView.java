package view;

import controller.CadastroEstadoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastroEstadoView {

	public static Stage myStage;

	public CadastroEstadoView() {
	}

	public static FXMLLoader fxmlLoader = null;

	public FXMLLoader getFxmlLoader() {
		return fxmlLoader;
	}

	public void initCadastro(Scene parent) {
		try {

			fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/CadastroEstado.fxml"));
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			if (parent != null)
				stage.initOwner(parent.getWindow());

			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			stage.setY(parent.getY() + parent.getWidth() / 5.5 - stage.getWidth() / 5.5);
			stage.setTitle("Cadastro de Estado");

			CadastroEstadoController myController = fxmlLoader.<CadastroEstadoController> getController();
			myController.setScene(stage.getScene());

			CadastroEstadoView.myStage = stage;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}