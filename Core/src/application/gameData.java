package application;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class gameData {

    private final static gameData instance = new gameData();
    
    public static gameData getInstance() {
        return instance;
    }
    
    //for common use
    gameController gameController1;
    Stage stage = new Stage();
    
    public Stage getStage() {
    	return stage;
    }
    
    public void setNamesWindow() throws IOException {
		BorderPane names = (BorderPane)FXMLLoader.load(getClass().getResource("playersNames.fxml"));
		Stage stage = getStage();
		stage.setTitle("Players names");
		Scene firstScene = new Scene(names,350,200);
		stage.setScene(firstScene);
		stage.show();
	} //for common use
    
    
    private Player player1 = new Player();
    private Player player2 = new Player();

    public Player getPlayer1() {
        return player1;
    }
    
    public Player getPlayer2() {
        return player2;
    }
}