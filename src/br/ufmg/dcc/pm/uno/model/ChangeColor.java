package br.ufmg.dcc.pm.uno.model;

import br.ufmg.dcc.pm.uno.game.Game;

/**
 * Especial type of {@link Card} that can be played any time
 * and allows the player to choose the next {@link Color}
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 *
 */
public class ChangeColor extends Card {

	public ChangeColor() {
		super(Color.NONE,1);
	}
	
	@Override
	public void effect(Game game) {
		super.effect(game);
		//TODO ask for color
	}
	
	@Override
	public boolean isCompactible(Game game) {
		return true;
	}

}