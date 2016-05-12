package br.ufmg.dcc.pm.uno.controller;

import br.ufmg.dcc.pm.uno.model.Card;

/**
 * Interface that any user of the {@link GameManager} must implement
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
 */
public interface GameClient {
	
	/**
	 * Informs that it's other player turn
	 * @param i The index of the player, 0 is user, 1,2 and 3 are CPU's
	 */
	public void changeTurn(int i);
	
	/**
	 * Informs that a Player has brought a card
	 * @param i The index of the player
	 * @param card which card was bought or null if the {@link GameClient} shoudn't know
	 */
	public void userBuysCard(int i, Card card);
	
	/**
	 * Adds a new card to the stack
	 * @param c
	 */
	public void addCardToStack(Card c);
	
	/**
	 * Clear the stack
	 */
	public void cleanStack();
	
	/**
	 * Tells that the game is over
	 * @param winner If the {@link GameClient}
	 */
	public void gameOver(int winner);

}
