package br.ufmg.dcc.pm.uno.model;

import br.ufmg.dcc.pm.uno.controller.GameManager;

public class ReverseCard extends Card {

	public ReverseCard(Color color) {
		super(color,12);
	}
	
	@Override
	public void effect(GameManager game) {
		game.getTable().setSpinningDirection(!game.getTable().isSpinningDirection());
		super.effect(game);
	}

}
