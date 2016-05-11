package br.ufmg.dcc.pm.uno.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import br.ufmg.dcc.pm.uno.model.Card.Color;
import br.ufmg.dcc.pm.uno.model.EspecialCard.EspecialEffect;

/**
 * Class that represents a UNO Player
 * 
 * @author Jerônimo Nunes Rocha
 *
 */
public class Player {

	private List<Card> hand = new LinkedList<Card>();
	private boolean unoStatus = false;
	private boolean isHuman = false;

	public void drawCard(UnoDeck cardDeck) {
		hand.add(0, cardDeck.draw());

		// System.out.println("adicionou a mão");
	}

	public Card playCard(Card lastCardPlayed, UnoDeck cardDeck) {
		Card toBePlayed = chooseCard(lastCardPlayed);

		if (toBePlayed != null) {
			if (isValidPlay(toBePlayed, lastCardPlayed)) {
				hand.remove(toBePlayed);
				System.out.println("Ultima carta jogada:");
				lastCardPlayed.printCard();
				return toBePlayed;
			}

		} else {
			drawCard(cardDeck);
		}
		//SE A CARTA QUE COMPROU É VALIDA JOGA ELA
		if (isValidPlay(hand.get(0), lastCardPlayed)) {
			if (hand.get(0) instanceof EspecialCard) {
				if (((EspecialCard) hand.get(0)).getEffect() == EspecialEffect.PLUS4
						|| ((EspecialCard) hand.get(0)).getEffect() == EspecialEffect.CHANGECOLOR) {

					
					switch (new Random().nextInt() % 4) {
					case 0:
						hand.get(0).setColor(Color.RED);
						break;
					case 1:
						hand.get(0).setColor(Color.BLUE);
						break;
					case 2:
						hand.get(0).setColor(Color.GREEN);
						break;
					case 3:
						hand.get(0).setColor(Color.BLUE);
						break;
					}
				}
			}

			return hand.remove(0);
			
		} else {
			return null;
		}

	}

	private boolean isValidPlay(Card toBePlayed, Card lastCardPlayed) {
		// +4 E UNO SEMPRE VALIDAS
		if (toBePlayed instanceof EspecialCard) {
			if (((EspecialCard) toBePlayed).getEffect() == EspecialEffect.PLUS4
					|| ((EspecialCard) toBePlayed).getEffect() == EspecialEffect.CHANGECOLOR) {
				return true;
			}
		}

		// OUTRAS CARTAS
		if (toBePlayed.getColor() == lastCardPlayed.getColor()) {
			return true;
		} else if (lastCardPlayed instanceof RegularCard
				&& toBePlayed instanceof RegularCard) {
			return lastCardPlayed.getNumber() == toBePlayed.getNumber();
		} else if (lastCardPlayed instanceof EspecialCard
				&& toBePlayed instanceof EspecialCard) {
			return ((EspecialCard) lastCardPlayed).getEffect() == ((EspecialCard) toBePlayed)
					.getEffect();
		} else
			return false;

	}

	private Card chooseCard(Card lastCardPlayed) {
		Card chosenCard = null;
		// 2 METODOS, PARA HUMANO E PARA MAQUINA

		// HUMANO: LOOP INFINITO ATÉ ESCOLHER A CERTA, SE CLICAR NO BARALHO
		// RETORNA NULL

		// MAQUINA: PERCORRE A MÃO PROCURANDO A PRIMEIRA VALIDA, SE NAO
		// ENCONTRAR RETORNA NULL

		if (isHuman) {
			// ENTRADA
			return chosenCard;
		} else {
			for (Card cardIterator : hand) {
				if (isValidPlay(cardIterator, lastCardPlayed)) {
					chosenCard = cardIterator;
					hand.remove(cardIterator);
					if (hand.size() > 1) {
						unoStatus = false;
					} else {
						unoStatus = true;
					}

					// SETAR COR QUANDO FOR +4 OU UNO
					if (chosenCard instanceof EspecialCard) {
						if (((EspecialCard) chosenCard).getEffect() == EspecialEffect.PLUS4
								|| ((EspecialCard) chosenCard).getEffect() == EspecialEffect.CHANGECOLOR) {

							switch (new Random().nextInt() % 4) {
							case 0:
								chosenCard.setColor(Color.RED);
								break;
							case 1:
								chosenCard.setColor(Color.BLUE);
								break;
							case 2:
								chosenCard.setColor(Color.GREEN);
								break;
							case 3:
								chosenCard.setColor(Color.BLUE);
								break;
							}
							// ESCOLHE ALEATORIAMENTE A COR, CAN BE IMPROVED
						}
					}
					return chosenCard;
				}
			}
			return chosenCard;
		}

	}

	public void notSoSecretMethod() {
		System.out.println("I wish I was more secret =/");
	}

	public void callUno() {
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
