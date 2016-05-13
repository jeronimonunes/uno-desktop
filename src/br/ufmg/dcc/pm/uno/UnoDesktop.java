package br.ufmg.dcc.pm.uno;

import br.ufmg.dcc.pm.uno.controller.GameController;
import br.ufmg.dcc.pm.uno.controller.StartController;
import br.ufmg.dcc.pm.uno.view.FullScreenKeyEventHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Uno Desktop {@link Application} Game
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 *
 */
@SuppressWarnings("unused")
public class UnoDesktop extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("view/Start.fxml"));
			Scene scene = new Scene(root,800,450);
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.show();
			primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, new FullScreenKeyEventHandler(primaryStage));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
