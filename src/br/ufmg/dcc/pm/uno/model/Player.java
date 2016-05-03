package br.ufmg.dcc.pm.uno.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that represents a UNO Player
 * @author Jerônimo Nunes Rocha
 *
 */
public class Player {
	
	private List<Card> hand = new LinkedList<Card>();
	private boolean unoStatus = false;
	private boolean isHuman = true;

	public void drawCard(Deck cardDeck){
		hand.add(0,cardDeck.draw());
	}
	
	public Card playCard(Card lastCardPlayed,Deck cardDeck){
		Card toBePlayed = chooseCard(lastCardPlayed,hand);
		
		if(isValidPlay(toBePlayed,lastCardPlayed) && toBePlayed != null){
			hand.remove(toBePlayed);
			return toBePlayed;
			
		}
		else{
			drawCard(cardDeck);
		}
		
		if(isValidPlay(hand.get(0),lastCardPlayed)){
			return hand.remove(0);
		}
		else{
			return null;
		}	
		
		
	}
	
	private boolean isValidPlay(Card toBePlayed, Card lastCardPlayed) {
		// TODO Auto-generated method stub
		//VERICA SE ESTÁ OK COM AS REGRAS
		return false;
	}


	private Card chooseCard(Card lastCardPlayed, List<Card> hand2) {
		// TODO Auto-generated method stub
		// 2 METODOS, PARA HUMANO E PARA MAQUINA
		//MAQUINA: PERCORRE A MÃO PROCURANDO A PRIMEIRA VALIDA, SE NAO ENCONTRAR RETORNA NULL
		//HUMANO: LOOP INFINITO ATÉ ESCOLHER A CERTA, SE CLICAR NO BARALHO RETORNA NULL
		
		return null;
	}

	public void callUno(){
		unoStatus = true;
	}

	public boolean isUnoStatus() {
		return unoStatus;
	}

	public void setUnoStatus(boolean unoStatus) {
		this.unoStatus = unoStatus;
	}

	public boolean isHuman() {
		return isHuman;
	}

	public void setHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	
	
	
	
}
