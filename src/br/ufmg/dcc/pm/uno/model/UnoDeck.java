package br.ufmg.dcc.pm.uno.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.ufmg.dcc.pm.uno.model.Card.Type;
import br.ufmg.dcc.pm.uno.model.EspecialCard.EspecialEffect;

/**
 * Class that represents a UNO Deck
 * 
 * @author Jerônimo Nunes Rocha
 * 
 */
public class UnoDeck implements Deck {

	List<Card> cards = new LinkedList<Card>();

	@Override
	public void initialize() {

		//ADDING REGULAR CARDS TO THE DECK
		
		for (int j = 0; j < 2; j++) {
			for (int i = 0+j; i < 10; i++) {
				cards.add(new RegularCard(i, Type.RED));
			}

			for (int i = 0+j; i < 10; i++) {
				cards.add(new RegularCard(i, Type.BLUE));
			}

			for (int i = 0+j; i < 10; i++) {
				cards.add(new RegularCard(i, Type.GREEN));
			}

			for (int i = 0+j; i < 10; i++) {
				cards.add(new RegularCard(i, Type.YELLOW));
			}
		}
		
		//ADDING ESPECIAL CARDS TO THE DECK
		
		for (int i=0;i<2;i++){
			cards.add(new EspecialCard(Type.RED,EspecialEffect.SKIP));
			cards.add(new EspecialCard(Type.RED,EspecialEffect.REVERSE));
			cards.add(new EspecialCard(Type.RED,EspecialEffect.PLUS2));
			
			cards.add(new EspecialCard(Type.BLUE,EspecialEffect.SKIP));
			cards.add(new EspecialCard(Type.BLUE,EspecialEffect.REVERSE));
			cards.add(new EspecialCard(Type.BLUE,EspecialEffect.PLUS2));
			
			cards.add(new EspecialCard(Type.GREEN,EspecialEffect.SKIP));
			cards.add(new EspecialCard(Type.GREEN,EspecialEffect.REVERSE));
			cards.add(new EspecialCard(Type.GREEN,EspecialEffect.PLUS2));
			
			cards.add(new EspecialCard(Type.YELLOW,EspecialEffect.SKIP));
			cards.add(new EspecialCard(Type.YELLOW,EspecialEffect.REVERSE));
			cards.add(new EspecialCard(Type.YELLOW,EspecialEffect.PLUS2));
		}
		
		for (int i=0;i<4;i++){
			cards.add(new EspecialCard(Type.NONE,EspecialEffect.CHANGECOLOR));
			cards.add(new EspecialCard(Type.NONE,EspecialEffect.PLUS4));
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
		return cards.remove(0);
	}

	@Override
	public void deal(int numberOfPlayers) {
		// TODO Auto-generated method stub
		// TALVEZ NÃO AQUI
	}

}
