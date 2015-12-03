package view;

import controller.CadastroMatriculaController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastroMatriculaView {

	public static Stage myStage;
	public CadastroMatriculaView() {
	}

	public static FXMLLoader fxmlLoader = null;

	public FXMLLoader getFxmlLoader() {
		return fxmlLoader;
	}

	public void initMatricula(Scene parent) {
		try {
			
			fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/CadastroMatricula.fxml"));
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			if (parent != null)
				stage.initOwner(parent.getWindow());

			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			stage.setY(parent.getY() + parent.getWidth() / 5.5 - stage.getWidth() / 5.5);
			stage.setTitle("Cadastro de Matricula");

			CadastroMatriculaController myController = fxmlLoader.<CadastroMatriculaController> getController();
			myController.setScene(stage.getScene());
			
			CadastroMatriculaView.myStage = stage;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}