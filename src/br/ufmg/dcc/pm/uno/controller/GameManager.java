package br.ufmg.dcc.pm.uno.controller;

import br.ufmg.dcc.pm.uno.model.Card;
import br.ufmg.dcc.pm.uno.model.Table;

public class GameManager {

	private int numberOfPlayers = 4;
	private int numberOfHumans = 1;
	private int currentPlayerTurn = 0;
	private boolean gameOver = false;
	private Table gameTable;
	

	public void startGame() {
		gameTable = new Table(numberOfPlayers, numberOfHumans);
		Card cardPlayed;

		while (!gameOver) {

			// PLAYER JOGANDO
			cardPlayed = gameTable.getPlayer(currentPlayerTurn).playCard(
					gameTable.getPlayedCards().peek(), gameTable.getCardDeck());

			
			if (cardPlayed != null) {
				// CONSEGUIU JOGAR UMA CARTA
				gameTable.getPlayedCards().push(cardPlayed);
			

				// GANHANDO O JOGO
				if (gameTable.getPlayer(currentPlayerTurn).getHand().isEmpty()
						&& gameTable.getPlayer(currentPlayerTurn).isUnoStatus()) {
					gameOver = true;
					break;
				}

				// RESOLVE EFEITO DA JOGADA E DECIDE PROXIMO JOGADOR
				turnResolution();
			} else {
				// NAO JOGOU CARTA, PASSA VEZ
				nextPlayer(gameTable.isSpinningDirection());
			}
		}
	}

	private void nextPlayer(boolean spinningDirection) {
		if(spinningDirection)
			currentPlayerTurn = (currentPlayerTurn+1) % numberOfPlayers;
		else
			currentPlayerTurn = (currentPlayerTurn-1) % numberOfPlayers;
			
		
	}

	private void turnResolution() {
		// CASE SWITCH REGULAR/ESPECIAL CARD
		// CASE SWITCH DOS EFEITOS ESPECIAIS
		// DEFINE O PROXIMO JOGADOR A JOGAR E O SENTIDO DA MESA

	}

}
