package br.ufmg.dcc.pm.uno.view;

import br.ufmg.dcc.pm.uno.controller.GameController;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class MouseCardEventListener implements EventHandler<MouseEvent>{
	
	private Group card;
	private GameController controller;
	
	private Animation<Node> animation;
	private Thread animationThread;

	public MouseCardEventListener(GameController controller,Group card) {
		this.card = card;
		this.controller = controller;
	}

	@Override
	public void handle(MouseEvent event) {
		if(animation!=null&&animation.isRunning()){
			animation.cancel();
			try {
				animationThread.join();
			} catch (Exception e) {

			}
		}
		if(event.getEventType().equals(MouseEvent.MOUSE_ENTERED)){
			animation = new CardUpAnimation(card);
		} else if(event.getEventType().equals(MouseEvent.MOUSE_EXITED)){
			animation = new CardDownAnimation(card);
		} else if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
			animation = null;
			//TODO Call event on game
			controller.getPlayers().get(0).getChildren().remove(card);
			
			int size = controller.getPlayers().get(0).getChildren().size();
			if(size<2) controller.getPlayers().get(0).setHgap(0);
			else controller.getPlayers().get(0).setHgap((controller.getWidth()-300-size*97)/(size-1));
			
			card.setOnMouseEntered(null);
			card.setOnMouseExited(null);
			controller.addCardToStack(card);
		}
		if(animation!=null){
			animationThread = new Thread(animation);
			animationThread.setDaemon(true);
			animationThread.start();
		}
	}

}
