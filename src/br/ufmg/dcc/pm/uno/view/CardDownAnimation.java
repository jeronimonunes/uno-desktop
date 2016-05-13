package br.ufmg.dcc.pm.uno.view;

import javafx.application.Platform;
import javafx.scene.Node;

/**
 * {@link Animation} that make a card go down
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @see Animation
 *
 */
public class CardDownAnimation extends Animation<Node> {

	public CardDownAnimation(Node target) {
		super(target);
		try {
			try {
				super.updateValue(super.getTarget().getTranslateY());
			} catch (NullPointerException e){
				super.updateValue(0D);
			}
			super.getTarget().translateYProperty().bind(super.value);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Double call() {
		for(double i = super.value.get()+1; i<=72&&!super.isCancelled(); i++){
			try {
				super.updateValue(i);
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(Platform.isFxApplicationThread()){
			CardDownAnimation.super.getTarget().translateYProperty().unbind();
		} else {
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					CardDownAnimation.super.getTarget().translateYProperty().unbind();
				}
			});
		}
		return null;
	}

}
