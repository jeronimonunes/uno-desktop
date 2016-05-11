package br.ufmg.dcc.pm.uno.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.ufmg.dcc.pm.uno.model.Card.Color;
import br.ufmg.dcc.pm.uno.model.EspecialCard.EspecialEffect;

/**
 * Class that represents a UNO Deck
 * 
 * @author Jerônimo Nunes Rocha
 * 
 */
public class UnoDeck implements Deck {

	private List<Card> cards = new LinkedList<Card>();

	@Override
	public void initialize() {

		//ADDING REGULAR CARDS TO THE DECK
		
		for (int j = 0; j < 2; j++) {
			for (int i = 0+j; i < 10; i++) {
				cards.add(new RegularCard(i, Color.RED));
			}

			for (int i = 0+j; i < 10; i++) {
				cards.add(new RegularCard(i, Color.BLUE));
			}

			for (int i = 0+j; i < 10; i++) {
				cards.add(new RegularCard(i, Color.GREEN));
			}

			for (int i = 0+j; i < 10; i++) {
				cards.add(new RegularCard(i, Color.YELLOW));
			}
		}
		
		//ADDING ESPECIAL CARDS TO THE DECK
		
		for (int i=0;i<2;i++){
			cards.add(new EspecialCard(Color.RED,EspecialEffect.SKIP));
			cards.add(new EspecialCard(Color.RED,EspecialEffect.REVERSE));
			cards.add(new EspecialCard(Color.RED,EspecialEffect.PLUS2));
			
			cards.add(new EspecialCard(Color.BLUE,EspecialEffect.SKIP));
			cards.add(new EspecialCard(Color.BLUE,EspecialEffect.REVERSE));
			cards.add(new EspecialCard(Color.BLUE,EspecialEffect.PLUS2));
			
			cards.add(new EspecialCard(Color.GREEN,EspecialEffect.SKIP));
			cards.add(new EspecialCard(Color.GREEN,EspecialEffect.REVERSE));
			cards.add(new EspecialCard(Color.GREEN,EspecialEffect.PLUS2));
			
			cards.add(new EspecialCard(Color.YELLOW,EspecialEffect.SKIP));
			cards.add(new EspecialCard(Color.YELLOW,EspecialEffect.REVERSE));
			cards.add(new EspecialCard(Color.YELLOW,EspecialEffect.PLUS2));
		}
		
		for (int i=0;i<4;i++){
			cards.add(new EspecialCard(Color.RED,EspecialEffect.CHANGECOLOR));
			cards.add(new EspecialCard(Color.RED,EspecialEffect.PLUS4));
		}

	}

	@Override
	public void shuffle() {
		Collections.shuffle(cards);

	}

	public UnoDeck() {
		initialize();
		shuffle();
	}

	@Override
	public Card draw() {
		//System.out.println("REMOVEU DA LISTA");
		return cards.remove(0);
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

}
