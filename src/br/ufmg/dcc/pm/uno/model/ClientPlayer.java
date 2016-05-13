package br.ufmg.dcc.pm.uno.model;

import br.ufmg.dcc.pm.uno.game.GameUserInterface;
import br.ufmg.dcc.pm.uno.game.UnoGame;

/**
 * A player that {@link Object#wait()} on it's turn for some {@link GameUserInterface} to make the
 * play for theirself and call {@link Object#notify()} on the game
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 *
 */
public class ClientPlayer extends Player {

	public ClientPlayer(UnoGame game) {
		super(game);
	}

	@Override
	public void playCard() {
		try {
			synchronized(getGame()){
				getGame().wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
