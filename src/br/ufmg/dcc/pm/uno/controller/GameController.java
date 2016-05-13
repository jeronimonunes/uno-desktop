package br.ufmg.dcc.pm.uno.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Java FX Controller for Game
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
 */
public class GameController implements Initializable,GameUserInterface {

	private static final Random RANDOM_GENERATOR = new Random();

	@FXML private BorderPane page;
	@FXML private StackPane stack;
	@FXML private StackPane deck;
	@FXML private List<FlowPane> players;

	private List<HashMap<Card, Group>> cards;

	private double width;
	private double height;

	private DoubleProperty widthProperty;
	private DoubleProperty heightProperty;

	private boolean userTurn = true;
	
	private boolean cardDrown = false;

	private Game game;

	public GameController() {
		try {
			widthProperty = new JavaBeanDoublePropertyBuilder().bean(this).name("width").build();
			heightProperty = new JavaBeanDoublePropertyBuilder().bean(this).name("height").build();
			this.game = new GameManager(this, 4);
			cards = Arrays.asList(new HashMap<Card,Group>(),new HashMap<Card,Group>(),new HashMap<Card,Group>(),new HashMap<Card,Group>());
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

	/**
	 * Method called when the deck is clicked. It draws a card at the first and skit the turn at the second
	 * @param event
	 */
	public void deckClicked(MouseEvent event){
		if(cardDrown){
			cardDrown = false;
			game.play(null, null);
		} else {
			cardDrown = true;
			game.drawCard();
		}
	}

	@Override
	public void addCardToStack(final int i, Card card){
		addCardToStack(GraphicCardFactory.getInstance().buildGrahics(card));
		if(i>0){
			//			for(final Card c : cards.get(i).keySet()){
			//				if(c.getNumber()==card.getNumber()&&c.getColor()==card.getColor()){
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					players.get(i).getChildren().remove(0);
					//							players.get(i).getChildren().remove(cards.get(i).get(c));
					//							cards.get(i).remove(c);
				}
			});
			//					break;
			//				}
			//			}
		}
	}

	/**
	 * Adds the given card to the stack
	 * @param card
	 */
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
			cardDrown = false;
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
				Group card = null;
				if(i==0) card = GraphicCardFactory.getInstance().buildGrahics(c);
				else card = GraphicCardFactory.getInstance().buildGrahics(Color.NONE,0);
				cards.get(i).put(c, card);
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
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				List<Node> cards = getStack().getChildren();
				Node card = cards.get(cards.size()-1);
				cards.clear();
				cards.add(card);
			}
		});
	}

	@Override
	public void gameOver(final int win) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Stage stage = (Stage) page.getScene().getWindow();
				Pane root = null;
				try {
					if(win==0){
						root = (Pane)FXMLLoader.load(getClass().getResource("/br/ufmg/dcc/pm/uno/view/Win.fxml"));
					} else {
						root = (Pane)FXMLLoader.load(getClass().getResource("/br/ufmg/dcc/pm/uno/view/Lost.fxml"));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				Scene scene = new Scene(root,page.getWidth(),page.getHeight());
				stage.setScene(scene);
			}
		});
	}

	/**
	 * @return The width of the screen of the game
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Determines the width of the game
	 * @param width
	 */
	public void setWidth(double width) {
		this.width = width;
		for(int i = 0; i<getPlayers().size(); i+=2){
			updateHandWidth(getPlayers().get(i),height);
		}
	}

	/**
	 * 
	 * @return The height of the game screen
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Defines the width of the game screen
	 * @param height
	 */
	public void setHeight(double height) {
		this.height = height;
		for(int i = 1; i<getPlayers().size(); i+=2){
			updateHandWidth(getPlayers().get(i),height);
		}
	}

	/**
	 * Updates the hand to fit in the given size
	 * @param pane
	 * @param maxSize
	 */
	public static void updateHandWidth(FlowPane pane, double maxSize){
		int size = pane.getChildren().size();
		if(size<2) pane.setHgap(0);
		else pane.setHgap((maxSize-305-size*97)/(size-1));
	}

	/**
	 * Plays the given card
	 * @param card
	 * @return
	 */
	public boolean play(Card card) {
		Color color = card.getColor();
		if(Color.NONE.equals(color)){
			color = Color.values()[RANDOM_GENERATOR.nextInt(4)];
		}
		return game.play(card,color);
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

	@Override
	public void changeColor(final Color color) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				switch(color){
				case RED:
					page.setStyle("-fx-background-color:#ff5555;");
					break;
				case GREEN:
					page.setStyle("-fx-background-color:#55aa55;");
					break;
				case BLUE:
					page.setStyle("-fx-background-color:#5555ff;");
					break;
				case YELLOW:
					page.setStyle("-fx-background-color:#ffaa00;");
					break;
				case NONE:
					page.setStyle("-fx-background-color:#000000;");
				}
			}
		});
	}

}
