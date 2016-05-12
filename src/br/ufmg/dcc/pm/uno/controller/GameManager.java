package br.ufmg.dcc.pm.uno.controller;

import java.util.Collections;
import java.util.Random;
import java.util.logging.Logger;

import br.ufmg.dcc.pm.uno.model.Card;
import br.ufmg.dcc.pm.uno.model.Card.Color;
import br.ufmg.dcc.pm.uno.model.Table;

/**
 * The Uno Game implementation
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
 *
 */
public class GameManager implements Runnable {

	private final static Logger LOGGER = Logger.getLogger(GameManager.class.getName());
	private static final Random RANDOM_GENERATOR = new Random();

	private int currentPlayerTurn = 0;
	private boolean gameOver = false;
	private Table table;
	private GameClient client;

	private Color color = Color.NONE;

	public GameManager(GameClient client, int numberOfPlayers) {
		table = new Table(this,numberOfPlayers);
		this.client = client;
	}

	public void gameOver(){
		gameOver = true;
		int winner = 0;
		for(int i = 1; i<table.getPlayers().length;i++){
			if(table.getPlayer(i).getHand().size()<table.getPlayer(winner).getHand().size()){
				winner = i;
			}
		}
		LOGGER.info("Player "+winner+" won");
		client.gameOver(winner);
	}

	/**
	 * The player defines the card only when it 
	 * @param card
	 * @param color
	 * @return true if the play were valid
	 */
	public boolean play(Card card,Color color) {
		if(card==null||color==null){
			LOGGER.info("The player "+currentPlayerTurn+" has skipped his turn");
			if(table.getCardDeck().isEmpty()) gameOver();
			nextPlayer();
			synchronized (this) {
				this.notify();
			}
			return true;
		} else {
			if(card.isCompactible(this)){
				LOGGER.info("The player "+currentPlayerTurn+" has played "+card+" "+color);
				this.color = color;
				client.addCardToStack(card);

				// GANHANDO O JOGO
				if (table.getPlayer(currentPlayerTurn).getHand().isEmpty()) {
					gameOver();
				} else {
					card.effect(this);
				}
				synchronized (this) {
					this.notify();					
				}
				return true;
			} else {
				return false;
			}
		}
	}

	public Card drawCard(){
		return drawCard(currentPlayerTurn);
	}

	private Card drawCard(int player){
		if (getTable().getCardDeck().isEmpty()) {
			if (getTable().getPlayedCards().size() == 1)
				LOGGER.info("No more possible moves");
			Card top;
			top = getTable().getPlayedCards().pop();
			while (!getTable().getPlayedCards().isEmpty()) {
				getTable().getCardDeck().getCards()
				.add(getTable().getPlayedCards().pop());
			}
			Collections.shuffle(getTable().getCardDeck().getCards());
			getTable().getPlayedCards().push(top);
			LOGGER.info("The deck is over");
		}

		Card card = getTable().getCardDeck().draw();
		if(card!=null) {
			getTable().getPlayer(player).getHand().add(0,card);
			client.userBuysCard(player, card);
		}
		return card;
	}

	@Override
	public void run() {
		dealCards();
		currentPlayerTurn = RANDOM_GENERATOR.nextInt(4);
		for(Card c:'')
		getTable().getPlayedCards().add(getTable().getCardDeck().draw());
		client.addCardToStack(getTable().getPlayedCards().peek());
		// VIRAR PRIMEIRA CARTA
		playedCards.push(cardDeck.draw());
		while(!gameOver){
			client.changeTurn(currentPlayerTurn);
			table.getPlayer(currentPlayerTurn).playCard();
		}
	}

	public void nextPlayer() {
		if (table.isSpinningDirection()){
			currentPlayerTurn = ((currentPlayerTurn + 1) % getNumberOfPlayers());
		} else {
			currentPlayerTurn = ((((currentPlayerTurn - 1) % getNumberOfPlayers()) + getNumberOfPlayers()) % getNumberOfPlayers());
		}
	}

	private void dealCards() {
		for (int i = 0; i < getNumberOfPlayers(); i++) {
			for (int j = 0; j < 7; j++) {
				drawCard(i);
			}
		}
	}

	public int getNumberOfPlayers(){
		return table.getPlayers().length;
	}

	public Color getColor() {
		return color;
	}

	public Table getTable() {
		return table;
	}

}
