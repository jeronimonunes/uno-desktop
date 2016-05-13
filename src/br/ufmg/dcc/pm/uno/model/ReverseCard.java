package br.ufmg.dcc.pm.uno.model;

import br.ufmg.dcc.pm.uno.controller.Game;

/**
 * A card with the effect of reverting the game rotation
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
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
