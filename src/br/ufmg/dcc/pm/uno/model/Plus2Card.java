package br.ufmg.dcc.pm.uno.model;

import br.ufmg.dcc.pm.uno.game.Game;

/**
 * A card with the effect of adding two cards to the hand of the next user
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 *
 */
public class Plus2Card extends Card {

	public Plus2Card(Color color) {
		super(color,10);
	}

	@Override
	public void effect(Game game) {
		super.effect(game);
		game.drawCard();
		game.drawCard();
	}
}
