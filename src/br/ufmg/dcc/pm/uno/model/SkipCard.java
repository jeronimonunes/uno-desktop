package br.ufmg.dcc.pm.uno.model;

import br.ufmg.dcc.pm.uno.controller.GameManager;

public class SkipCard extends Card {

	public SkipCard(Color color) {
		super(color,11);
	}
	
	@Override
	public void effect(GameManager game) {
		super.effect(game);
		super.effect(game);
	}

}