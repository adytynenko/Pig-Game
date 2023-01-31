package application;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class historyRecordsController {
	
	@FXML
	private TableView<History> records;
	@FXML
	private TableColumn<History,String> nameCol;
	@FXML
	private TableColumn<History,String> dateCol;
	@FXML
	private TableColumn<History,String> scoreCol;
	@FXML
	private TableColumn<History,String> gameResultCol;
	@FXML
	private Label winners;
	
	private ObjectInputStream playersRecords;
	private ArrayList <History> playersList;
	
	@FXML
	public void initialize() throws IOException {
			
		nameCol.setCellValueFactory(cellData -> cellData.getValue().NameProperty());
		dateCol.setCellValueFactory(cellData -> cellData.getValue().DateProperty());
		scoreCol.setCellValueFactory(cellData -> cellData.getValue().ScoreProperty());
		gameResultCol.setCellValueFactory(cellData -> cellData.getValue().GameResProperty());
				
		playersList = getRecords();
		
		System.out.println("History records:");
		Iterator<History> iter = playersList.iterator();
		while (iter.hasNext()) {
            History record = iter.next();
            System.out.println(record);
        }
		
		records.setItems(FXCollections.observableArrayList(playersList));
		
		ArrayList<String> uniquePlayers = getUniquePlayers();
		ArrayList<Integer> numbOfWin = numbersOfWin(uniquePlayers);
		String winnersList = "";
		 
		for (int i=0; i < uniquePlayers.size(); i++) {
			String winner = uniquePlayers.get(i) + " has " + numbOfWin.get(i) + " wins.\n";
			winnersList += winner;
		} System.out.println("\n" + winnersList);
		
		winners.setText(winnersList);
		
	}
	
	private ArrayList<History> getRecords() throws IOException {
		playersList = new ArrayList<>();
		
		String name;
		String date;
		String score;
		String gameResult;
		
		try {
			playersRecords = new ObjectInputStream(new FileInputStream("file:playersRecords.data"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			boolean eof = false;
			while(!eof) {
				try {
					
					Player player = (Player)playersRecords.readObject();
					name = player.name;
					date = player.date;
					score = Integer.toString(player.score);
					if (player.win==true) {
						gameResult = "win";
					} else {
						gameResult = "lose";
					}
					History record = new History(name, date, score, gameResult);
					playersList.add(record);
				
				} catch(EOFException e) { 
					eof = true;
				} 
			}	
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} playersRecords.close();
		return playersList;

	}
	
	private ArrayList<String> getUniquePlayers() throws IOException {
		ArrayList<String> uniquePlayers = new ArrayList<>();
		getRecords().stream().map(e -> e.getName()).distinct().forEach(e -> uniquePlayers.add(e));
		return uniquePlayers;
	}
	
	private ArrayList<Integer> numbersOfWin(ArrayList<String> uniquePlayers) throws IOException {
		ArrayList<Integer> numbOfWin = new ArrayList<>();
		for(String player : uniquePlayers) {
			numbOfWin.add((int) getRecords().stream().filter(e -> e.getName().equals(player))
					.filter(e -> e.getGameResult().equals("win")).count());
		}
		return numbOfWin;
	}
} 



