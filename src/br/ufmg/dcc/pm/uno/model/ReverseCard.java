package br.ufmg.dcc.pm.uno.model;

import br.ufmg.dcc.pm.uno.game.Game;

/**
 * A card with the effect of reverting the game rotation
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 *
 */
public class ReverseCard extends Card {

	public ReverseCard(Color color) {
		super(color,12);
	}
	
	@Override
	public void effect(Game game) {
		game.getTable().setSpinningDirection(!game.getTable().isSpinningDirection());
		super.effect(game);
	}

}
