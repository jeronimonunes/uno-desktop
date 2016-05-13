package br.ufmg.dcc.pm.uno.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A {@link Deck} of a CardGame
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 *
 */
public class Deck {

	private List<Card> cards = new LinkedList<Card>();

	/**
	 * Shuffles the {@link Deck}
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	/**
	 * Draw a card from the deck;
	 * @return The card drawn
	 */
	public Card draw() {
		return cards.size()==0?null:cards.remove(0);
	}

	/**
	 * @return true if the {@link Deck} is empty, false otherwise
	 */
	public boolean isEmpty() {
		return cards.isEmpty();
	}

	/**
	 * @return The cards of the deck
	 */
	public List<Card> getCards() {
		return cards;
	}
}
