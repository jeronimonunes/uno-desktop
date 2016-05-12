package br.ufmg.dcc.pm.uno.model;

import br.ufmg.dcc.pm.uno.controller.GameManager;

public class Plus2Card extends Card {

	public Plus2Card(Color color) {
		super(color,10);
	}

	@Override
	public void effect(GameManager game) {
		super.effect(game);
		game.drawCard();
		game.drawCard();
	}
}
