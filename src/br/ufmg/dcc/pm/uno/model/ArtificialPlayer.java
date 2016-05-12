package br.ufmg.dcc.pm.uno.model;

import java.util.Collections;
import java.util.Random;

import br.ufmg.dcc.pm.uno.controller.GameManager;
import br.ufmg.dcc.pm.uno.model.Card.Color;

/**
 * A automated {@link Player} that plays by itself
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
 *
 */
public class ArtificialPlayer extends Player {

	private static final Random RANDOM_GENERATOR = new Random();

	public ArtificialPlayer(GameManager game) {
		super(game);
	}

	@Override
	public void playCard() {
		Card card = chooseCard();
		Color color = null;;
		if(card==null) card = getGame().drawCard();

		if (card != null && card.isCompactible(getGame())) {
			getHand().remove(card);
			color = card.getColor();
			if(Color.NONE.equals(color)){
				color = Color.values()[RANDOM_GENERATOR.nextInt(4)];
			}
		} else {
			card = null;
		}
		getGame().play(card,color);
	}

	private Card chooseCard() {
		Collections.shuffle(getHand());
		for (Card card : getHand()) {
			if (card.isCompactible(getGame())) {
				return card;
			}
		}
		return null;
	}

}
