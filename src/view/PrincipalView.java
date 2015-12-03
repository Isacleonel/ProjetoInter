package view;

import controller.PrincipalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PrincipalView {

	public static Stage myStage;
	public PrincipalView() {
	}

	public static FXMLLoader fxmlLoader = null;

	public FXMLLoader getFxmlLoader() {
		return fxmlLoader;
	}

	public void initPrincipal(Stage window) {
		try {

			
			fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/Principal.fxml"));
			Parent root = fxmlLoader.load();
			root.autosize();
			window.setTitle("Sistema para academia");
			Scene scene = new Scene(root);
			window.setScene(scene);
			window.setMaximized(true);
			PrincipalController myController = fxmlLoader.<PrincipalController> getController();

			myController.setScene(window.getScene());
			window.show();;
			
			PrincipalView.myStage = window;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}