package br.ufmg.dcc.pm.uno.model;

import java.util.LinkedList;
import java.util.List;

import br.ufmg.dcc.pm.uno.controller.GameManager;

/**
 * Class that represents a CardGame Player
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
 *
 */
public abstract class Player {

	private List<Card> hand = new LinkedList<Card>();
	private GameManager game;

	public Player(GameManager game) {
		this.game = game;
	}

	public abstract void playCard();

	public void notSoSecretMethod() {
		System.out.println("I wish I was more secret =/");
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public GameManager getGame() {
		return game;
	}
	
	public Table getTable(){
		return getGame().getTable();
	}

}
