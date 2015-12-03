package view;

import controller.CadastroAvaliacaoFisicaController;
import controller.CadastroCidadeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastroAvaliacaoFisicaView {

	public static Stage myStage;

	public CadastroAvaliacaoFisicaView() {
	}

	public static FXMLLoader fxmlLoader = null;

	public FXMLLoader getFxmlLoader() {
		return fxmlLoader;
	}

	public void initCadastro(Scene parent) {
		try {

			fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/CadastroAvaliacaoFisica.fxml"));
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			if (parent != null)
				stage.initOwner(parent.getWindow());

			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			stage.setY(parent.getY() + parent.getWidth() / 5.5 - stage.getWidth() / 5.5);
			stage.setTitle("Cadastro de Avaliação Fisica");

			CadastroAvaliacaoFisicaController myController = fxmlLoader.<CadastroAvaliacaoFisicaController> getController();
			myController.setScene(stage.getScene());

			CadastroAvaliacaoFisicaView.myStage = stage;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}