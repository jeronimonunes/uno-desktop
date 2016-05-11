package br.ufmg.dcc.pm.uno.model;

public class EspecialCard extends Card{
	public enum EspecialEffect {
		SKIP,REVERSE,PLUS2,CHANGECOLOR,PLUS4
	}
	
	private EspecialEffect effect;
	
	public EspecialCard (Color color,EspecialEffect effect){
		super(color);
		this.effect = effect;
	}
	
	public void setEffect(EspecialEffect effect) {
		this.effect = effect;
	}
	
	public EspecialEffect getEffect() {
		return effect;
	}

	@Override
	public void printCard() {
		System.out.println(((EspecialCard) this).getEffect().toString()+" "+this.getColor());
		
	}

}
