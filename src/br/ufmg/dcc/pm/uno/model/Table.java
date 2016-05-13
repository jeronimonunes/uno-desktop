package br.ufmg.dcc.pm.uno.model;

import java.util.Stack;

import br.ufmg.dcc.pm.uno.controller.GameManager;

/**
 * Class that represents a UNO Table
 * 
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
 */
public class Table {

	private Stack<Card> playedCards;
	private Player[] players;
	private Deck cardDeck;

	private boolean spinningDirection;
	private GameManager game;
	// true clockwise, false inverted
	
	public Table(GameManager game, int numberOfPlayers) {

		this.game = game;
		this.playedCards = new Stack<Card>();
		this.players = new Player[numberOfPlayers];

		players[0] = new ClientPlayer(game);
		for (int i = 1; i < numberOfPlayers; i++) {
			players[i] = new ArtificialPlayer(game);
		}

		this.cardDeck = new DeckBuilder().buildUnoDeck().toDeck();
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

	public Player getPlayer(int index) {
		return players[index];
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
	
	public GameManager getGame() {
		return game;
	}

}
