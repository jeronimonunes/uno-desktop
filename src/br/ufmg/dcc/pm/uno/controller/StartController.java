package br.ufmg.dcc.pm.uno.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Java FX Controller to start a Game
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
 */
public class StartController {
	
	@FXML
	private AnchorPane page;
	
	@FXML
	private Button startBtn;
	
	@FXML
	private Button exitBtn;
	
	/**
	 * Method called when exit button is pressed
	 * @param actionEvent
	 */
	public void exit(ActionEvent actionEvent){
		Stage stage = (Stage) exitBtn.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * Method called when start button is pressed
	 * @param actionEvent
	 */
	public void start(ActionEvent actionEvent){
		Stage stage = (Stage) startBtn.getScene().getWindow();
		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("/br/ufmg/dcc/pm/uno/view/Game.fxml"));
			Scene scene = new Scene(root,page.getWidth(),page.getHeight());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public AnchorPane getPage() {
		return page;
	}

	public void setPage(AnchorPane page) {
		this.page = page;
	}

	public Button getStartBtn() {
		return startBtn;
	}

	public void setStartBtn(Button startBtn) {
		this.startBtn = startBtn;
	}

	public Button getExitBtn() {
		return exitBtn;
	}

	public void setExitBtn(Button exitBtn) {
		this.exitBtn = exitBtn;
	}

}
