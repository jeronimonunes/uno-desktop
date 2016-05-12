package br.ufmg.dcc.pm.uno.model;

import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import br.ufmg.dcc.pm.uno.model.Card.Color;

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

		for(Color c : EnumSet.complementOf(EnumSet.of(Color.NONE))){
			//ADDING ESPECIAL CARDS TO THE DECK
			cards.add(new SkipCard(c));
			cards.add(new SkipCard(c));
			cards.add(new ReverseCard(c));
			cards.add(new ReverseCard(c));
			cards.add(new Plus2Card(c));
			cards.add(new Plus2Card(c));
			
			//ADDING REGULAR CARDS TO THE DECK
			for(int i = 0; i<10; i++){
				cards.add(new Card(c,i));
				cards.add(new Card(c,i));
			}
		}
		
		for (int i=0;i<4;i++){
			cards.add(new ChangeColor());
			cards.add(new Plus4Card());
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
		return cards.size()==0?null:cards.remove(0);
	}

	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	public List<Card> getCards() {
		return cards;
	}

}
