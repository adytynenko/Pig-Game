package application;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class gameController {
	
	@FXML
	private ImageView diceImg;
	@FXML
	private Label playerTurn;
	@FXML
	private Label turnNumber;
	@FXML
	private Label pointsNumber;
	@FXML
	private Label player1Score;
	@FXML
	private Label player2Score;
	@FXML
	private Label if_1_point;
	@FXML
	private Button rollButton;
	@FXML
	private Button holdButton;
	@FXML
	private Button startNewGame;
	@FXML
	private Button historyRecords;
	
	gameData gameObj = gameData.getInstance(); //global class instance
	
	private Player player1 = gameObj.getPlayer1();
	private Player player2 = gameObj.getPlayer2();
	private Player listOfPlayers[] = {player1, player2};
	private int currentPlayerId = 0;
	private Player currentPlayer = listOfPlayers[currentPlayerId];
	private boolean gameIsFinished = false;
	private ObjectOutputStream playersRecords;
	
	@FXML
	public void initialize() {
		System.out.println("Game is started:");
	}
	
	private String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		String todayDate = dtf.format(now);
		return todayDate;
	}
	
	public void startNewGame() throws IOException {  //"Start new game" button
		System.out.println("New game is started:");
		gameIsFinished = false;
		diceImg.setImage(null);
		player1.reset();
		player2.reset();
		resetText();
		gameObj.setNamesWindow();
	}
	
	private void resetText() {
		playerTurn.setText("Select names");
		turnNumber.setText("Rolls this turn: " + player1.numberOfTurn);
		pointsNumber.setText("Points this turn: " + player1.turnScore);
		player1Score.setText("Player 1's score: " + player1.score);
		player2Score.setText("Player 2's score: " + player2.score);
	}
	
	public void namesDone(String player1name, String player2name) { //set names from names window
		playerTurn.setText(player1name + "'s turn");
		player1Score.setText(player1name + "'s score: 0");
		player2Score.setText(player2name + "'s score: 0");
	}
	
	private void switchPlayer() {
		currentPlayerId = (currentPlayerId + 1) % listOfPlayers.length;
		currentPlayer = listOfPlayers[currentPlayerId];
	}
	
	private String filePath = "file:dice/";
	private int numb;
	private Random rand = new Random();
	
		
	public void rollButton() throws IOException {
		
		if (gameIsFinished == false) {
			currentPlayer.setDate(getCurrentDate());
			if_1_point.setText("");
			playerTurn.setText(currentPlayer.name + "'s turn");
			numb = rand.nextInt(6)+1;
			diceImg.setImage(new Image(filePath + numb + ".png"));  //randomize dice image
			
			int turnResult = currentPlayer.getDice(numb);
			turnNumber.setText("Rolls this turn: " + currentPlayer.numberOfTurn);
			pointsNumber.setText("Points this turn: " + currentPlayer.turnScore);
			player1Score.setText(player1.name + "'s score: " + player1.score);
			player2Score.setText(player2.name + "'s score: " + player2.score);
			System.out.println(currentPlayer);
			
			if (turnResult == 1) { //someone gets 100 points --> win
				player1.setDate(getCurrentDate());
				player2.setDate(getCurrentDate());
				currentPlayer.setWin(true);
				playerTurn.setText(currentPlayer.name + " wins!!!"); //writes who win
				System.out.println(currentPlayer.name + " wins!!!\n");
				gameIsFinished = true;
				try {
					playersRecords = new ObjectOutputStream(new FileOutputStream("file:playersRecords.data", true)) {
						protected void writeStreamHeader() throws IOException {
							reset();
							}
						};
				} catch (IOException e) {
					System.out.println("Error. Game will not be saved.");
					return;
				}
				playersRecords.writeObject(player1);
				playersRecords.writeObject(player2);
				playersRecords.close();
				
			} else if (turnResult == -1) { //player gets 1 point --> score=0
				if_1_point.setText("Player switches turn.");
				switchPlayer();
				currentPlayer.numberOfTurn = 0;
				
			} //if turnResult == 0 -> do nothing/continue
		}
	}
	
	public void holdButton() {

		if (gameIsFinished == false) {
			currentPlayer.setDate(getCurrentDate());
			System.out.println("Player " + currentPlayer.name + " ends his/her turn.");
			switchPlayer();
			currentPlayer.numberOfTurn = 0;
			currentPlayer.turnScore = 0;
			turnNumber.setText("Rolls this turn: " + currentPlayer.numberOfTurn);
			pointsNumber.setText("Points this turn: " + currentPlayer.turnScore);
			playerTurn.setText(currentPlayer.name + "'s turn");	
		}
	}
	
	public void historyRecords() throws IOException {
		
		GridPane history = (GridPane)FXMLLoader.load(getClass().getResource("historyRecords.fxml"));
		Stage stage2 = new Stage();
		stage2.setTitle("Game History");
		Scene scene2 = new Scene(history,500,500);
		stage2.setScene(scene2);
		stage2.show();
	}	
}
