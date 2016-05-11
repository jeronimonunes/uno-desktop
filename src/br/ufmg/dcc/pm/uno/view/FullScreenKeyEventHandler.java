package br.ufmg.dcc.pm.uno.view;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FullScreenKeyEventHandler implements EventHandler<KeyEvent>{
	
	private Stage primaryStage;

	public FullScreenKeyEventHandler(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void handle(KeyEvent event) {
		if (event.getCode() == KeyCode.F11) {
			primaryStage.setFullScreen(!primaryStage.isFullScreen());
        }
	}

}
