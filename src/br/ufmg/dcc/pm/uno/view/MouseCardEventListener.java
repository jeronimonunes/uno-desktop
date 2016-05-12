package br.ufmg.dcc.pm.uno.view;

import br.ufmg.dcc.pm.uno.controller.GameController;
import br.ufmg.dcc.pm.uno.model.Card;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class MouseCardEventListener implements EventHandler<MouseEvent>{

	private GameController controller;

	private Animation<Node> animation;
	private Thread animationThread;
	private Card card;

	public MouseCardEventListener(GameController controller,Card card) {
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
			animation = new CardUpAnimation(findCardUI(event.getTarget()));
		} else if(event.getEventType().equals(MouseEvent.MOUSE_EXITED)){
			animation = new CardDownAnimation(findCardUI(event.getTarget()));
		} else if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
			if(controller.isUserTurn()){
				if(controller.play(card)){
					controller.getPlayers().get(0).getChildren().remove(findCardUI(event.getTarget()));
					animation = null;
					GameController.updateHandWidth(controller.getPlayers().get(0), controller.getWidth());
				}
			}
		}
		if(animation!=null){
			animationThread = new Thread(animation);
			animationThread.setDaemon(true);
			animationThread.start();
		}
	}

	private static Node findCardUI(EventTarget target){
		Node g = (Node) target;
		while(g!=null&&!"_root".equals(g.getId())){
			g=g.getParent();
		}
		return g;
	}

}
