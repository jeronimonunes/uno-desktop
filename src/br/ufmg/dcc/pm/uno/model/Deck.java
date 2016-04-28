package br.ufmg.dcc.pm.uno.model;

public interface Deck {

	public void initialize();
	public void shuffle();
	public Card draw();
	public void deal(int numberOfPlayers);
	
	
}
