package br.ufmg.dcc.pm.uno.controller;

import br.ufmg.dcc.pm.uno.model.Card;

public interface GameActionListener {
	
	/**
	 * Informs that it's other player turn
	 * This method will block until the user has choose their card
	 * @param i The index of the player, 0 is user, 1,2 and 3 are CPU's
	 */
	public Card changeTurn(int i);
	
	/**
	 * Informs that a CPU has brought a card
	 * @param i The index of the player
	 */
	public void userBuysCard(int i, Card card);

}
