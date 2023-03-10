package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	gameController gameController1;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			gameData gameObj = gameData.getInstance();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("pigGame.fxml"));
			BorderPane root = (BorderPane)loader.load();
			
			gameObj.gameController1 = loader.getController();
			
			primaryStage.setTitle("Pig game");
			Scene scene = new Scene(root,700,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show(); 
			
			gameObj.setNamesWindow(); // Names Window

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}