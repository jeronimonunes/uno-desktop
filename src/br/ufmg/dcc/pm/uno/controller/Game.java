package br.ufmg.dcc.pm.uno.controller;

import br.ufmg.dcc.pm.uno.model.Card;
import br.ufmg.dcc.pm.uno.model.Card.Color;
import br.ufmg.dcc.pm.uno.model.Table;

/**
 * Interface that any Game must implement to communicate with a UI
 * @author Jerônimo Nunes Rocha
 *
 */
public interface Game extends Runnable {

	/**
	 * Plays the given card to the game
	 * @param card The card to be played
	 * @param color The next color of table
	 * @return true if the play were valid
	 */
	public boolean play(Card card, Color color);

	/**
	 * Draws a card from the deck and put it in the hand of the current player
	 * @return The card drawn
	 */
	public Card drawCard();

	/**
	 * @return The Game Table
	 */
	public Table getTable();

	/**
	 * @return The current color of the table
	 */
	public Object getColor();

	/**
	 * Rotates the table by one
	 */
	public void nextPlayer();
	
}
