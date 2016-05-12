package br.ufmg.dcc.pm.uno.model;

import br.ufmg.dcc.pm.uno.controller.GameClient;
import br.ufmg.dcc.pm.uno.controller.GameManager;

/**
 * A player that {@link Object#wait()} on it's turn for some {@link GameClient} to make the
 * play for theirself and call {@link Object#notify()} on the game
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jerônimo Nunes Rocha
 *
 */
public class ClientPlayer extends Player {

	public ClientPlayer(GameManager game) {
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
