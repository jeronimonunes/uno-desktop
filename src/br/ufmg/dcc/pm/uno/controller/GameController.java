package br.ufmg.dcc.pm.uno.controller;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import br.ufmg.dcc.pm.uno.model.Card;
import br.ufmg.dcc.pm.uno.model.Card.Color;
import br.ufmg.dcc.pm.uno.view.MouseCardEventListener;
import br.ufmg.dcc.pm.uno.view.PickingCardAnimation;
import br.ufmg.dcc.pm.uno.view.cards.GraphicCardFactory;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.adapter.JavaBeanDoublePropertyBuilder;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

/**
 * Java FX Controller for Game
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
 */
public class GameController implements Initializable,GameClient {

	private static final Random RANDOM_GENERATOR = new Random();

	@FXML private BorderPane page;
	@FXML private StackPane stack;
	@FXML private StackPane deck;
	@FXML private List<FlowPane> players;

	private double width;
	private double height;

	private DoubleProperty widthProperty;
	private DoubleProperty heightProperty;

	private boolean userTurn = true;

	private GameManager game;

	public GameController() {
		try {
			widthProperty = new JavaBeanDoublePropertyBuilder().bean(this).name("width").build();
			heightProperty = new JavaBeanDoublePropertyBuilder().bean(this).name("height").build();
			this.game = new GameManager(this, 4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		widthProperty.bind(page.widthProperty());
		heightProperty.bind(page.heightProperty());
		for(int i = 1; i < getPlayers().size(); i+=2){
			players.get(i).maxWidthProperty().bind(page.heightProperty().subtract(300));
			players.get(i).minWidthProperty().bind(page.heightProperty().subtract(300));
		}
		for(int i = 0; i<5; i++){
			Group card = GraphicCardFactory.getInstance().buildGrahics(Card.Color.NONE,0);
			deck.getChildren().add(card);
			card.setTranslateX(i*2);
			card.setTranslateY(5 - i*2);
		}
		Thread t = new Thread(game);
		t.setDaemon(true);
		t.start();
	}

	public void deckClicked(MouseEvent event){
		game.drawCard();
	}

	@Override
	public void addCardToStack(Card card){
		addCardToStack(GraphicCardFactory.getInstance().buildGrahics(card));
	}

	private void addCardToStack(final Group card){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				card.translateXProperty().unbind();
				card.setTranslateX(RANDOM_GENERATOR.nextInt(96)-48);
				card.translateYProperty().unbind();
				card.setTranslateY(RANDOM_GENERATOR.nextInt(144)-72);
				stack.getChildren().add(card);
			}
		});
	}

	@Override
	public void changeTurn(int i) {
		if(i==0){
			userTurn = true;
		} else {
			userTurn = false;
			new PickingCardAnimation(getPlayers().get(i)).perform();
		}
	}

	@Override
	public void userBuysCard(final int i, final Card c) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				FlowPane ui = getPlayers().get(i);
				final Group card = GraphicCardFactory.getInstance().buildGrahics(c);
				card.setTranslateY(72);
				ui.getChildren().add(card);
				updateHandWidth(ui,i%2==0?getWidth():getHeight());
				if(i==0){
					EventHandler<MouseEvent> mouseEvent = new MouseCardEventListener(GameController.this,c);
					card.setOnMouseEntered(mouseEvent);
					card.setOnMouseExited(mouseEvent);
					card.setOnMouseClicked(mouseEvent);
				}
			}
		});
	}

	@Override
	public void cleanStack() {
		getStack().getChildren().clear();
	}

	@Override
	public void gameOver(int win) {
		// TODO Auto-generated method stub

	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
		for(int i = 0; i<getPlayers().size(); i+=2){
			updateHandWidth(getPlayers().get(i),height);
		}
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
		for(int i = 1; i<getPlayers().size(); i+=2){
			updateHandWidth(getPlayers().get(i),height);
		}
	}

	public static void updateHandWidth(FlowPane pane, double maxSize){
		int size = pane.getChildren().size();
		if(size<2) pane.setHgap(0);
		else pane.setHgap((maxSize-305-size*97)/(size-1));
	}

	public boolean play(Card card) {
		Color color = card.getColor();
		if(Color.NONE.equals(color)){
			color = Color.values()[RANDOM_GENERATOR.nextInt(4)];
		}
		return game.play(card,card.getColor());
	}

	public StackPane getStack() {
		return stack;
	}

	public void setStack(StackPane stack) {
		this.stack = stack;
	}

	public StackPane getDeck() {
		return deck;
	}

	public void setDeck(StackPane deck) {
		this.deck = deck;
	}

	public BorderPane getPage() {
		return page;
	}

	public void setPage(BorderPane page) {
		this.page = page;
	}

	public boolean isUserTurn() {
		return userTurn;
	}

	public void setUserTurn(boolean userTurn) {
		this.userTurn = userTurn;
	}

	public List<FlowPane> getPlayers() {
		return players;
	}

	public void setPlayers(List<FlowPane> players) {
		this.players = players;
	}

}
