package br.ufmg.dcc.pm.uno.view;

import javafx.application.Platform;
import javafx.scene.Node;

/**
 * {@link Animation} that make a card go up and down
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
 * @see Animation
 *
 */
public class CardAnimation extends Animation<Node> {

	public CardAnimation(Node target) {
		super(target);
		try {
			super.getTarget().translateYProperty().bind(super.value);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public Double call() {
		
		if(Platform.isFxApplicationThread()){
			CardAnimation.super.getTarget().translateYProperty().unbind();
		} else {
			double i = 71;
			boolean topReached = false;
			while(i!=72 && !super.isCancelled()){
				try {
					super.updateValue(i);
					if(i==0){
						topReached = true;
					}
					i += topReached?1:-1;
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					CardAnimation.super.getTarget().translateYProperty().unbind();
				}
			});
		}
		return null;
	}

}
