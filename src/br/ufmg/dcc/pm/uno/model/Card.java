package br.ufmg.dcc.pm.uno.model;

/**
 * Class that represents a UNO Card
 * @author Jerônimo Nunes Rocha
 *
 */
public abstract class Card {
	public enum Color {
		RED,YELLOW,BLUE,GREEN,NONE
	}
	
	private int number = 0;
	private Color color = Color.NONE;
	
	public abstract void printCard();
	
	public Card(int number,Color color){
		this.number = number;
		this.color = color;
	}
	
	public Card(Color color){
		this.color = color;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	

}
