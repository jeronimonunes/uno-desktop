package br.ufmg.dcc.pm.uno.model;

import java.util.Stack;

/**
 * Class that represents a UNO Table
 * @author Jerônimo Nunes Rocha
 *
 */
public class Table {
	
	private Stack<Card> playedCards;
	private Player[] players;
	private Deck cardDeck;
	
	private boolean spinningDirection;
	//true clockwise, false inverted
	
	public Table(int numberOfPlayers){
		
		this.playedCards = new Stack<Card>();
		this.players = new Player[numberOfPlayers];
		this.cardDeck = new UnoDeck();
		this.spinningDirection = true;
	}

	public Stack<Card> getPlayedCards() {
		return playedCards;
	}

	public void setPlayedCards(Stack<Card> playedCards) {
		this.playedCards = playedCards;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public Deck getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(Deck cardDeck) {
		this.cardDeck = cardDeck;
	}

	public boolean isSpinningDirection() {
		return spinningDirection;
	}

	public void setSpinningDirection(boolean spinningDirection) {
		this.spinningDirection = spinningDirection;
	}
	
	
	
}
