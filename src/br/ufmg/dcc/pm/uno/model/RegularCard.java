package br.ufmg.dcc.pm.uno.model;

public class RegularCard extends Card {
	public RegularCard(int number,Type color){
		super(number,color);
	}

	@Override
	public void printCard() {
		System.out.println(this.getNumber()+" "+this.getColor());
	}
}
