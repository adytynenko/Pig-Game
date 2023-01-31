package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class History {
	
	private SimpleStringProperty name;
	private SimpleStringProperty date;
	private SimpleStringProperty score;
	private SimpleStringProperty gameResult;
	
	public History(String name, String date, String score, String gameResult) {
		this.name = new SimpleStringProperty(name);
		this.date = new SimpleStringProperty(date);
		this.score = new SimpleStringProperty(score);
		this.gameResult = new SimpleStringProperty(gameResult);
	}

	public String getName() {
		return name.get();
	}
	
	public SimpleStringProperty NameProperty() {
		return name;
	}
	
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	
	public String getDate() {
		return date.get();
	}
	
	public SimpleStringProperty DateProperty() {
		return date;
	}
	
	public void setDate(String date) {
		this.date= new SimpleStringProperty(date);
	}
	
	public String getScore() {
		return score.get();
	}
	
	public SimpleStringProperty ScoreProperty() {
		return score;
	}
	
	public void setScore(String score) {
		this.score = new SimpleStringProperty(score);
	}
	
	public String getGameResult() {
		return gameResult.get();
	}
	
	public SimpleStringProperty GameResProperty() {
		return gameResult;
	}
	
	public void setGameResult(String gameResult) {
		this.gameResult = new SimpleStringProperty(gameResult);
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public void setDate(SimpleStringProperty date) {
		this.date = date;
	}

	public void setScore(SimpleStringProperty score) {
		this.score = score;
	}

	public void setGameResult(SimpleStringProperty gameResult) {
		this.gameResult = gameResult;
	}
	
	@Override
	public String toString() {
		return "Player [name = " + name.get() + ", date = " + date.get() + ", score = " + score.get() + ", gameResult = " + gameResult.get() + "]";
	}
	
}
 
