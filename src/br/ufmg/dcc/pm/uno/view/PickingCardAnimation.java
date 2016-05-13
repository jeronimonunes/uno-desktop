package br.ufmg.dcc.pm.uno.view;

import java.util.Random;

import br.ufmg.dcc.pm.uno.model.Card;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

/**
 * {@link Animation} that simulates the CPU trying to choose a {@link Card}
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
 *
 */
public class PickingCardAnimation extends Animation<FlowPane> {

	private static final Random RANDOM_GENERATOR = new Random();

	public PickingCardAnimation(FlowPane cards) {
		super(cards);
	}

	@Override
	public Double call() {
		for(int k = 0; k < 2 && !super.isCancelled(); k++){
			Node n = super.getTarget().getChildren().get(RANDOM_GENERATOR.nextInt(super.getTarget().getChildren().size()));
			new CardAnimation(n).perform();
			super.updateProgress(k, 2);
		}
		return null;
	}

}
