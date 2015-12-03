package view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BuscaCadastroCidadeView {
	public static Stage myStage;

	public BuscaCadastroCidadeView() {
	}

	public static FXMLLoader fxmlLoader = null;

	public FXMLLoader getFxmlLoader() {
		return fxmlLoader;
	}

	public void initBuscaCad(Scene parent) {
		try {

			Parent root;
			root = FXMLLoader.load(getClass().getResource("/view/fxml/BuscaCadastroCidade.fxml"));

			Stage stage = new Stage();
			Scene scene = new Scene(root);

			if (parent != null)
				stage.initOwner(parent.getWindow());

			stage.setScene(scene);
			stage.show();

			stage.setResizable(false);

			stage.setY(parent.getWindow().getY() + parent.getWindow().getWidth() / 2.5 - stage.getWidth() / 2.5);

			stage.setTitle("Busca de cadastro de Cidade");

			BuscaCadastroCidadeView.myStage = stage;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}