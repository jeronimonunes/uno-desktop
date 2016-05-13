package br.ufmg.dcc.pm.uno.model;

import br.ufmg.dcc.pm.uno.game.Game;

/**
 * A {@link Card} with the effect of adding 4 cards to the hand of the next user
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 *
 */
public class Plus4Card extends Card {

	public Plus4Card() {
		super(Color.NONE,4);
	}
	
	@Override
	public void effect(Game game) {
		super.effect(game);
		game.drawCard();
		game.drawCard();
		game.drawCard();
		game.drawCard();
	}
	
	@Override
	public boolean isCompactible(Game game) {
		return true;
	}

}
