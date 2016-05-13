package br.ufmg.dcc.pm.uno.model;

import java.util.LinkedList;
import java.util.List;

import br.ufmg.dcc.pm.uno.controller.Game;

/**
 * Class that represents a CardGame Player
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
 *
 */
public abstract class Player {

	private List<Card> hand = new LinkedList<Card>();
	private Game game;

	public Player(Game game) {
		this.game = game;
	}

	/**
	 * Plays a {@link Card}
	 */
	public abstract void playCard();

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public Game getGame() {
		return game;
	}
	
	public Table getTable(){
		return getGame().getTable();
	}

}
