package br.ufmg.dcc.pm.uno.model;

import java.util.EnumSet;

import br.ufmg.dcc.pm.uno.model.Card.Color;

/**
 * Builder to construct a {@link Deck}
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
 */
public class DeckBuilder {

	private Deck deck;

	/**
	 * (constructor)
	 */
	public DeckBuilder() {
		this.deck = new Deck();
	}

	/**
	 * Adds the {@link SkipCard} to the Deck
	 * @return this
	 */
	public DeckBuilder withSkipCards(){
		for(Color c : EnumSet.complementOf(EnumSet.of(Color.NONE))){
			deck.getCards().add(new SkipCard(c));
			deck.getCards().add(new SkipCard(c));
		}
		return this;
	}

	/**
	 * Adds the {@link ReverseCard} to the Deck
	 * @return this
	 */
	public DeckBuilder withReverseCards(){
		for(Color c : EnumSet.complementOf(EnumSet.of(Color.NONE))){
			deck.getCards().add(new ReverseCard(c));
			deck.getCards().add(new ReverseCard(c));
		}
		return this;
	}

	/**
	 * Adds the {@link Plus2Card} to the Deck
	 * @return this
	 */
	public DeckBuilder withPlus2Cards(){
		for(Color c : EnumSet.complementOf(EnumSet.of(Color.NONE))){
			deck.getCards().add(new Plus2Card(c));
			deck.getCards().add(new Plus2Card(c));
		}
		return this;
	}

	/**
	 * Adds the {@link Plus4Card} to the Deck
	 * @return this
	 */
	public DeckBuilder withPlus4Cards(){
		deck.getCards().add(new Plus4Card());
		deck.getCards().add(new Plus4Card());
		deck.getCards().add(new Plus4Card());
		deck.getCards().add(new Plus4Card());
		return this;
	}

	/**
	 * Adds the {@link ChangeColor} to the Deck
	 * @return this
	 */
	public DeckBuilder withChangeColorCards(){
		deck.getCards().add(new ChangeColor());
		deck.getCards().add(new ChangeColor());
		deck.getCards().add(new ChangeColor());
		deck.getCards().add(new ChangeColor());
		return this;
	}

	/**
	 * Adds regular {@link Card} to the Deck
	 * @return this
	 */
	public DeckBuilder withRegularCards(){
		for(Color c : EnumSet.complementOf(EnumSet.of(Color.NONE))){
			for(int i = 0; i<10; i++){
				deck.getCards().add(new Card(c,i));
				deck.getCards().add(new Card(c,i));
			}
		}
		return this;
	}

	/**
	 * Builds a UNO {@link Deck}
	 * @return this
	 */
	public DeckBuilder buildUnoDeck(){
		return this.withChangeColorCards()
				.withPlus2Cards()
				.withPlus4Cards()
				.withRegularCards()
				.withReverseCards()
				.withSkipCards();
	}

	/**
	 * @return A {@link Deck} containing the added cards
	 */
	public Deck toDeck(){
		this.deck.shuffle();
		return this.deck;
	}

}
