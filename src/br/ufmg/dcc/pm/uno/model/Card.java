package br.ufmg.dcc.pm.uno.model;

import java.text.MessageFormat;

import br.ufmg.dcc.pm.uno.game.Game;

/**
 * Class that represents a Card
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 *
 */
public class Card {
	
	public enum Color {
		RED,YELLOW,BLUE,GREEN,NONE
	}
	
	private int number = 0;
	private Color color = Color.NONE;
	
	public Card(Color color, int number){
		this.number = number;
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
	
	/**
	 * Makes the needed changes in game when this card is played
	 * @param game The game where this Card is to be applied
	 */
	public void effect(Game game){
		game.nextPlayer();
	}
	
	/**
	 * Decides if this Card can be played based on the last play
	 * @param game The game running to get the last card played and the last color picked
	 * @return true if this Card can be played, false otherwise
	 */
	public boolean isCompactible(Game game){
		Card card = game.getTable().getPlayedCards().peek();
		return this.getNumber() == card.getNumber() || this.getColor().equals(game.getColor());
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("Card[{0},{1}]", getColor(), getNumber());
	}

}
