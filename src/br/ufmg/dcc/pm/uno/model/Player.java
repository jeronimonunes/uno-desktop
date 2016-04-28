package br.ufmg.dcc.pm.uno.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that represents a UNO Player
 * @author Jerônimo Nunes Rocha
 *
 */
public class Player {
	
	List<Card> hand = new LinkedList<Card>();
	private boolean unoStatus = false;

	public void drawCard(Deck cardDeck){
		hand.add(cardDeck.draw());
	}
	
	public void playCard(){
		//TODO
	}
	
	public void callUno(){
		unoStatus = true;
	}

	public boolean isUnoStatus() {
		return unoStatus;
	}

	public void setUnoStatus(boolean unoStatus) {
		this.unoStatus = unoStatus;
	}
	
	
}
