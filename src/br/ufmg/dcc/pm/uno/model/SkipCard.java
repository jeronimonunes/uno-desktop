package br.ufmg.dcc.pm.uno.model;

import br.ufmg.dcc.pm.uno.game.Game;

/**
 * A card with the effect of skipping the next player turn
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 *
 */
public class SkipCard extends Card {

	public SkipCard(Color color) {
		super(color,11);
	}
	
	@Override
	public void effect(Game game) {
		super.effect(game);
		super.effect(game);
	}

}