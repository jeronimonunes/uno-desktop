package br.ufmg.dcc.pm.uno.controller;

import br.ufmg.dcc.pm.uno.model.Card;

/**
 * Interface that any {@link GameManager} must implement
 * @author Jerônimo Nunes Rocha
 *
 */
public interface GameServer {
	
	public Card drawCard();
	
	public boolean play(Card card);
	
}
