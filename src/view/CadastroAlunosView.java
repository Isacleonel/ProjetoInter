package view;

import controller.CadastroAlunosController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastroAlunosView {

	public static Stage myStage;
	public CadastroAlunosView() {
		
	}
	
	
	
	

	public static FXMLLoader fxmlLoader = null;

	public FXMLLoader getFxmlLoader() {
		return fxmlLoader;
	}

	public void initCadastro(Scene parent) {
		try {
			
			fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/CadastroAlunos.fxml"));
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			if (parent != null)
				stage.initOwner(parent.getWindow());

			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			stage.setY(parent.getY() + parent.getWidth() / 5.5 - stage.getWidth() / 5.5);
			stage.setTitle("Cadastro de alunos");

			CadastroAlunosController myController = fxmlLoader.<CadastroAlunosController> getController();
			myController.setScene(stage.getScene());
			
			CadastroAlunosView.myStage = stage;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}