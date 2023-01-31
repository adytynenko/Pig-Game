package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class namesController {
	
	@FXML
	private TextField player1Name;
	@FXML
	private TextField player2Name;
	@FXML
	private Button setNames;
	
	gameData gameObj;

	public void initialize() {
		gameObj = gameData.getInstance();
	}
	
	public void setNames() throws IOException {
		
		gameController gameController1 = gameObj.gameController1;
		Stage stage = gameObj.getStage();
		
		String player1name = player1Name.getText();
		String player2name = player2Name.getText();
		gameObj.getPlayer1().setName(player1name);
		gameObj.getPlayer2().setName(player2name);
				
		gameController1.namesDone(player1name, player2name);
		
		stage.close();
		
	}
	
}
