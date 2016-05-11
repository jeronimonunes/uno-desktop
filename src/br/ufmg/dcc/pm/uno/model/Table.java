package br.ufmg.dcc.pm.uno.model;

import java.util.Stack;

/**
 * Class that represents a UNO Table
 * 
 * @author Jerônimo Nunes Rocha
 *
 */
public class Table {

	private Stack<Card> playedCards;
	private Player[] players;
	private UnoDeck cardDeck;

	private boolean spinningDirection;
	// true clockwise, false inverted

	public Table(int numberOfPlayers, int numberOfHumans) {

		this.playedCards = new Stack<Card>();
		this.players = new Player[numberOfPlayers];

		for (int i = 0; i < numberOfPlayers; i++) {
			players[i] = new Player();
		}

		for (int i = 0; i < numberOfHumans; i++) {
			players[i].setHuman(true);
		}

		this.cardDeck = new UnoDeck();
		this.spinningDirection = true;
		// VIRAR PRIMEIRA CARTA
		playedCards.push(cardDeck.getCards().remove(0));
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

	public Player getPlayer(int index) {
		return players[index];
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public UnoDeck getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(UnoDeck cardDeck) {
		this.cardDeck = cardDeck;
	}

	public boolean isSpinningDirection() {
		return spinningDirection;
	}

	public void setSpinningDirection(boolean spinningDirection) {
		this.spinningDirection = spinningDirection;
	}

}
