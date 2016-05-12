package br.ufmg.dcc.pm.uno.model;

/**
 * A {@link Deck} of a CardGame
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
 *
 */
public interface Deck {

	public void initialize();
	public void shuffle();
	public Card draw();
}
