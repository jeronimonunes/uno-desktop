package br.ufmg.dcc.pm.uno.view;

import javafx.scene.Node;

/**
 * {@link Animation} that make a card go up and down
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
 * @see Animation
 *
 */
public class CardUpAnimation extends Animation<Node> {
	
	public CardUpAnimation(Node target) {
		super(target);
		try {
			try {
				super.updateValue(super.getTarget().getTranslateY());
			} catch (NullPointerException e){
				super.updateValue(72D);
			}
			super.getTarget().translateYProperty().bind(super.value);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Double call() {
		for(double i = super.value.get()-1; i>=0&&!super.isCancelled(); i--){
			try {
				super.updateValue(i);
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
