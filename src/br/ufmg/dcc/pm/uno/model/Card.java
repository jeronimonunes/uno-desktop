package br.ufmg.dcc.pm.uno.model;

/**
 * Class that represents a UNO Card
 * @author Jerônimo Nunes Rocha
 *
 */
public abstract class Card {
	public enum Type {
		RED,YELLOW,BLUE,GREEN,NONE
	}
	
	private int number = 0;
	private Type color = Type.NONE;
	
	public abstract void printCard();
	
	public Card(int number,Type color){
		this.number = number;
		this.color = color;
	}
	
	public Card(Type color){
		this.color = color;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setColor(Type color) {
		this.color = color;
	}
	
	public Type getColor() {
		return color;
	}
	

}
