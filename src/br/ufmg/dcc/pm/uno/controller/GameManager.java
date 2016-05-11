package br.ufmg.dcc.pm.uno.controller;

import java.util.Collections;

import br.ufmg.dcc.pm.uno.model.Card;
import br.ufmg.dcc.pm.uno.model.EspecialCard;
import br.ufmg.dcc.pm.uno.model.RegularCard;
import br.ufmg.dcc.pm.uno.model.Table;

public class GameManager implements GameServer {

	private int numberOfPlayers = 10;
	private int numberOfHumans = 0;
	private int currentPlayerTurn = 0;
	private boolean gameOver = false;
	private Table gameTable;

	public void startGame() {
		Card cardPlayed = null;

		gameTable = new Table(numberOfPlayers, numberOfHumans);
		dealCards(numberOfPlayers);

		while (!gameOver) {
			// EMBARALHA MAO DOS COMPUTADORES PARA NAO ENTRAR EM LOOP
			// NAO APAGAR
			Collections.shuffle(gameTable.getPlayer(currentPlayerTurn)
					.getHand());

			// CHECA SE O BARALHO ACABOU E O REMONTA A PARTIR DA PILHA DA CARTAS
			// JOGADAS
			isCardDeckEmpty();

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
				System.out.println("Jogador" + currentPlayerTurn + "comprou");
				nextPlayer(gameTable.isSpinningDirection());

			}

		}
		System.out.println("Jogador" + currentPlayerTurn + "Ganhou!");

	}

	private void isCardDeckEmpty() {
		if (gameTable.getCardDeck().getCards().isEmpty()) {
			if (gameTable.getPlayedCards().size() == 1)
				System.out.println("ACABARAM AS CARTAS JOGADAS");
			remountDeck();
			System.out.println("ACABOU O BARALHO");
		}
	}

	private void remountDeck() {
		Card top;
		top = gameTable.getPlayedCards().pop();
		while (!gameTable.getPlayedCards().isEmpty()) {
			gameTable.getCardDeck().getCards()
			.add(gameTable.getPlayedCards().pop());
		}
		Collections.shuffle(gameTable.getCardDeck().getCards());
		gameTable.getPlayedCards().push(top);

	}

	private void nextPlayer(boolean spinningDirection) {
		if (spinningDirection){

			currentPlayerTurn = ((currentPlayerTurn + 1) % numberOfPlayers);
		}
		else{
			currentPlayerTurn = ((((currentPlayerTurn - 1) % numberOfPlayers) + numberOfPlayers) % numberOfPlayers);
		}
	}

	private void turnResolution() {
		System.out.println("Jogador" + currentPlayerTurn +
				"jogou:");

		if (gameTable.getPlayedCards().peek() instanceof RegularCard) {
			nextPlayer(gameTable.isSpinningDirection());
			gameTable.getPlayedCards().peek().printCard();

		} else if (gameTable.getPlayedCards().peek() instanceof EspecialCard) {
			switch (((EspecialCard) gameTable.getPlayedCards().peek())
					.getEffect().toString()) {

					case "CHANGECOLOR":
						gameTable.getPlayedCards().peek().printCard();
						nextPlayer(gameTable.isSpinningDirection());
						break;
					case "PLUS4":
						gameTable.getPlayedCards().peek().printCard();

						for (int i = 0; i < 4; i++) {
							isCardDeckEmpty();
							gameTable.getPlayer(
									(currentPlayerTurn + 1) % numberOfPlayers)
							.drawCard(gameTable.getCardDeck());
						}
						nextPlayer(gameTable.isSpinningDirection());
						nextPlayer(gameTable.isSpinningDirection());
						break;

					case "REVERSE":
						gameTable.getPlayedCards().peek().printCard();

						gameTable
						.setSpinningDirection(!gameTable.isSpinningDirection());
						nextPlayer(gameTable.isSpinningDirection());
						break;

					case "SKIP":
						gameTable.getPlayedCards().peek().printCard();

						nextPlayer(gameTable.isSpinningDirection());
						nextPlayer(gameTable.isSpinningDirection());
						break;

					case "PLUS2":
						gameTable.getPlayedCards().peek().printCard();

						for (int i = 0; i < 2; i++) {
							isCardDeckEmpty();
							gameTable.getPlayer(
									(currentPlayerTurn + 1) % numberOfPlayers)
							.drawCard(gameTable.getCardDeck());
						}
						nextPlayer(gameTable.isSpinningDirection());
						nextPlayer(gameTable.isSpinningDirection());
						break;
			}
		}



		//REGULAR/ESPECIAL CARD
		// CASE SWITCH DOS EFEITOS ESPECIAIS

	}

	private void dealCards(int numberOfPlayers) {

		for (int i = 0; i < numberOfPlayers; i++) {
			for (int j = 0; j < 7; j++) {
				gameTable.getPlayer(i).drawCard(gameTable.getCardDeck());

			}
		}
	}

}
