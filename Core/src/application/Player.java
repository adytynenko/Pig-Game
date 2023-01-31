package application;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Player implements Serializable {
	
	String name = "";
	Integer score = 0;
	int numberOfTurn = 0;
	int turnScore = 0;
	boolean win = false;
	String date = ""; 
	
	public Player() {
	}

	public Player(String name) {
		this.name = name;
		this.score = score;
		this.numberOfTurn = numberOfTurn;
		this.turnScore = turnScore;
		this.win = win;
		this.date = date;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	

	public int getTurnNumb() {
		return numberOfTurn;
	}

	public void setTurnNumb(int turnNumb) {
		this.numberOfTurn = turnNumb;
	}

	public int getTurnScore() {
		return turnScore;
	}

	public void setTurnScore(int turnScore) {
		this.turnScore = turnScore;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Player [name = " + name + ", numberOfTurn = " + numberOfTurn + ", turnScore = " + 
				turnScore + ", score = " + score + ", isWin = " + win + ", date = " + date + "]";
	}

	public void reset() {
		name = "";
		score = 0;
		numberOfTurn = 0;
		turnScore = 0;
		win = false;
		date = "";
		
	}
	
	public int getDice(int numb) { 	 //get points from rolling dice
		
		if (numb == 1) {
			turnScore = numb;
			numberOfTurn += 1;
			score = 0;
			return -1; 	//player get score=0 and switch turn
		
		} else {
			turnScore = numb;
			numberOfTurn += 1;
			score += turnScore;
			if (score<100)
				return 0;	//player continue
			else
				return 1; 	//player wins
		}
	}
}
