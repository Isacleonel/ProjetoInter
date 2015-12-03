
package view;


import controller.CadastroProfessorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastroProfessorView {

	public static Stage myStage;
	public CadastroProfessorView() {
	}

	public static FXMLLoader fxmlLoader = null;

	public FXMLLoader getFxmlLoader() {
		return fxmlLoader;
	}

	public void initCadastro(Scene parent) {
		try {
			
			fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/CadastroProfessor.fxml"));
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			if (parent != null)
				stage.initOwner(parent.getWindow());

			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			stage.setY(parent.getY() + parent.getWidth() / 5.5 - stage.getWidth() / 5.5);
			stage.setTitle("Cadastro de Professores");

			CadastroProfessorController myController = fxmlLoader.<CadastroProfessorController> getController();
			myController.setScene(stage.getScene());
			
			CadastroProfessorView.myStage = stage;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}