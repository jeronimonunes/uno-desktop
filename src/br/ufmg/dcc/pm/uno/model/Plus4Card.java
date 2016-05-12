package br.ufmg.dcc.pm.uno.model;

import br.ufmg.dcc.pm.uno.controller.GameManager;

public class Plus4Card extends Card {

	public Plus4Card() {
		super(Color.NONE,4);
	}
	
	@Override
	public void effect(GameManager game) {
		super.effect(game);
		game.drawCard();
		game.drawCard();
		game.drawCard();
		game.drawCard();
	}
	
	@Override
	public boolean isCompactible(GameManager game) {
		return true;
	}

}
