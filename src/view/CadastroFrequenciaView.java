package view;

import controller.CadastroEstadoController;
import controller.CadastroFrequenciaController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastroFrequenciaView {

	public static Stage myStage;

	public CadastroFrequenciaView() {
	}

	public static FXMLLoader fxmlLoader = null;

	public FXMLLoader getFxmlLoader() {
		return fxmlLoader;
	}

	public void initCadastro(Scene parent) {
		try {

			fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/CadastroFrequencia.fxml"));
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			if (parent != null)
				stage.initOwner(parent.getWindow());

			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			stage.setY(parent.getY() + parent.getWidth() / 5.5 - stage.getWidth() / 5.5);
			stage.setTitle("Cadastro de Frequência");

			CadastroFrequenciaController myController = fxmlLoader.<CadastroFrequenciaController> getController();
			myController.setScene(stage.getScene());

			CadastroFrequenciaView.myStage = stage;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}