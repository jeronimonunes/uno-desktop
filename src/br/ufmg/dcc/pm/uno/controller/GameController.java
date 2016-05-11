package br.ufmg.dcc.pm.uno.controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import br.ufmg.dcc.pm.uno.model.Card;
import br.ufmg.dcc.pm.uno.model.UnoDeck;
import br.ufmg.dcc.pm.uno.view.MouseCardEventListener;
import br.ufmg.dcc.pm.uno.view.PickingCardAnimation;
import br.ufmg.dcc.pm.uno.view.cards.GraphicCardFactory;
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

public class GameController implements Initializable {

	private static final Random RANDOM_GENERATOR = new Random();
	
	@FXML
	private BorderPane page;
	
	@FXML
	private StackPane stack;

	@FXML
	private StackPane deck;

	@FXML
	private FlowPane playerCards;
	
	@FXML
	private FlowPane cpu0cards;
	
	@FXML
	private FlowPane cpu1cards;
	
	@FXML
	private FlowPane cpu2cards;
	
	private double width;
	
	private double height;
	
	private DoubleProperty widthProperty;
	
	private DoubleProperty heightProperty;
	
	public GameController() {
		try {
			widthProperty = new JavaBeanDoublePropertyBuilder().bean(this).name("width").build();
			heightProperty = new JavaBeanDoublePropertyBuilder().bean(this).name("height").build();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		widthProperty.bind(page.widthProperty());
		heightProperty.bind(page.heightProperty());
		cpu0cards.maxWidthProperty().bind(page.heightProperty().subtract(300));
		cpu2cards.maxWidthProperty().bind(page.heightProperty().subtract(300));
		cpu0cards.minWidthProperty().bind(page.heightProperty().subtract(300));
		cpu2cards.minWidthProperty().bind(page.heightProperty().subtract(300));
		for(int i = 0; i<5; i++){
			Group card = GraphicCardFactory.getInstance().buildGrahics(Card.Color.NONE,0);
			deck.getChildren().add(card);
			card.setTranslateX(i*2);
			card.setTranslateY(5 - i*2);
		}
		UnoDeck deck = new UnoDeck();
		for(int i = 20; i<30; i++){
			addPlayerCard(deck.getCards().get(i));
			addCpu0Card(deck.getCards().get(i));
			addCpu1Card(deck.getCards().get(i));
			addCpu2Card(deck.getCards().get(i));
		}
	}

	public void deckClicked(MouseEvent event){
		//TODO Call event on game
		new Thread(new PickingCardAnimation(cpu0cards)).start();
		UnoDeck deck = new UnoDeck();
		int i = RANDOM_GENERATOR.nextInt(deck.getCards().size());
		addPlayerCard(deck.getCards().get(i));
	}

	public void addCardToStack(Card card){
		addCardToStack(GraphicCardFactory.getInstance().buildGrahics(card));
	}

	public void addCardToStack(Group card){
		card.translateXProperty().unbind();
		card.setTranslateX(RANDOM_GENERATOR.nextInt(96)-48);
		card.translateYProperty().unbind();
		card.setTranslateY(RANDOM_GENERATOR.nextInt(144)-72);
		stack.getChildren().add(card);
	}

	public void addPlayerCard(Card c){
		final Group card = GraphicCardFactory.getInstance().buildGrahics(c);
		card.setTranslateY(72);
		playerCards.getChildren().add(card);
		int size = playerCards.getChildren().size();
		if(size<2) playerCards.setHgap(0);
		else playerCards.setHgap((getWidth()-300-size*97)/(size-1));
		EventHandler<MouseEvent> event = new MouseCardEventListener(this,card);
		card.setOnMouseEntered(event);
		card.setOnMouseExited(event);
		card.setOnMouseClicked(event);
	}
	
	public void addCpu0Card(Card c){
		final Group card = GraphicCardFactory.getInstance().buildGrahics(Card.Color.NONE,0);
		card.setTranslateY(72);
		cpu0cards.getChildren().add(card);
		int size = cpu0cards.getChildren().size();
		if(size<2) cpu0cards.setHgap(0);
		else cpu0cards.setHgap((getHeight()-300-size*97)/(size-1));
	}
	
	public void addCpu1Card(Card c){
		final Group card = GraphicCardFactory.getInstance().buildGrahics(Card.Color.NONE,0);
		card.setTranslateY(72);
		cpu1cards.getChildren().add(card);
		int size = cpu1cards.getChildren().size();
		if(size<2) cpu1cards.setHgap(0);
		else cpu1cards.setHgap((getWidth()-300-size*97)/(size-1));
	}
	
	public void addCpu2Card(Card c){
		final Group card = GraphicCardFactory.getInstance().buildGrahics(Card.Color.NONE,0);
		card.setTranslateY(72);
		cpu2cards.getChildren().add(card);
		int size = cpu2cards.getChildren().size();
		if(size<2) cpu2cards.setHgap(0);
		else cpu2cards.setHgap((getHeight()-size*97)/(size-1));
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

	public FlowPane getPlayerCards() {
		return playerCards;
	}

	public void setPlayerCards(FlowPane playerCards) {
		this.playerCards = playerCards;
	}

	public FlowPane getCpu0cards() {
		return cpu0cards;
	}

	public void setCpu0cards(FlowPane cpu0cards) {
		this.cpu0cards = cpu0cards;
	}

	public FlowPane getCpu1cards() {
		return cpu1cards;
	}

	public void setCpu1cards(FlowPane cpu1cards) {
		this.cpu1cards = cpu1cards;
	}

	public FlowPane getCpu2cards() {
		return cpu2cards;
	}

	public void setCpu2cards(FlowPane cpu2cards) {
		this.cpu2cards = cpu2cards;
	}

	public BorderPane getPage() {
		return page;
	}

	public void setPage(BorderPane page) {
		this.page = page;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		int size = playerCards.getChildren().size();
		if(size<2) playerCards.setHgap(0);
		else playerCards.setHgap((width-300-size*97)/(size-1));
		size = cpu1cards.getChildren().size();
		if(size<2) cpu1cards.setHgap(0);
		else cpu1cards.setHgap((width-300-size*97)/(size-1));
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		int size = cpu2cards.getChildren().size();
		if(size<2) cpu2cards.setHgap(0);
		else cpu2cards.setHgap((height-305-size*97)/(size-1));
		size = cpu0cards.getChildren().size();
		if(size<2) cpu0cards.setHgap(0);
		else cpu0cards.setHgap((height-305-size*97)/(size-1));
		this.height = height;
	}

}
