package br.ufmg.dcc.pm.uno.view.cards;

import java.io.IOException;
import java.util.logging.Logger;

import br.ufmg.dcc.pm.uno.model.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

public class GraphicCardFactory {
	
	private static final GraphicCardFactory INSTANCE = new GraphicCardFactory();
	
	private final static Logger LOGGER = Logger.getLogger(GraphicCardFactory.class.getName());
	
	public static GraphicCardFactory getInstance() {
		return INSTANCE;
	}

	private GraphicCardFactory() {
		
	}
	
	public Group buildGrahics(Card card) {
		return buildGrahics(card.getColor(),card.getNumber());
	}

	public Group buildGrahics(Card.Color color, int number) {
		StringBuilder fileName = new StringBuilder();
		switch (color) {
			case BLUE:
				fileName.append("b");
				break;
			case GREEN:
				fileName.append("g");
				break;
			case NONE:
				fileName.append("n");
				break;
			case RED:
				fileName.append("r");
				break;
			case YELLOW:
				fileName.append("y");
				break;
			default:
				break;
		}
		fileName.append(number).append(".fxml");
		try {
			return (Group)FXMLLoader.load(getClass().getResource(fileName.toString()));
		} catch (IOException e) {
			LOGGER.severe("It was not possible to open Graphic representation of a card");
			e.printStackTrace();
			return null;
		}
	}

}
