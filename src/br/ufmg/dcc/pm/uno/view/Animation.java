package br.ufmg.dcc.pm.uno.view;


import java.util.concurrent.atomic.AtomicReference;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.stage.Screen;

/**
 * Class to represent a animation on {@link Screen}
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 *
 * @param <T> The {@link Node} type on {@link Screen} to the {@link Animation} to occur
 */
public abstract class Animation<T extends Node> extends Task<Double> {

	private T target;

	protected final ObjectProperty<Double> value = new SimpleObjectProperty<>(this, "value", 72D);
	
	/**
     * Used to send value updates in a thread-safe manner from the subclass
     * to the FX application thread. AtomicReference is used so as to coalesce
     * updates such that we don't flood the event queue.
     */
	private AtomicReference<Double> valueUpdate = new AtomicReference<Double>();

	/**
	 * (constructor)
	 * Constructs a {@link Animation} with the given target
	 * @param target
	 */
	public Animation(T target) {
		this.target = target;
	}

	/**
	 * Starts the {@link Animation} and waits for it to finish
	 * To perform the {@link Animation} but don't wait for it, call {@link #start()}
	 */
	public void perform(){
		Thread t = new Thread(this);
		t.setDaemon(true);
		try {
			t.start();
			t.join();
		} catch (InterruptedException e) {

		}
	}

	/**
	 * 
	 * @return The {@link Node} where the {@link Animation} is running
	 */
	public T getTarget() {
		return target;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return super.cancel(false);
	}

	/**
	 * Updates the <code>value</code> property. Calls to updateValue
	 * are coalesced and run later on the FX application thread, so calls
	 * to updateValue, even from the FX Application thread, may not
	 * necessarily result in immediate updates to this property, and
	 * intermediate values may be coalesced to save on event
	 * notifications.
	 * <p>
	 *     <em>This method is safe to be called from any thread.</em>
	 * </p>
	 *
	 * @param value the new value
	 * @since JavaFX 8.0
	 */
	protected void updateValue(Double value) {
		super.updateValue(value);
		if (Platform.isFxApplicationThread()) {
			this.value.set(value);
		} else {
			// As with the workDone, it might be that the background thread
			// will update this value quite frequently, and we need
			// to throttle the updates so as not to completely clobber
			// the event dispatching system.
			if (valueUpdate.getAndSet(value) == null) {
				Platform.runLater(() -> Animation.this.value.set(valueUpdate.getAndSet(null)));
			}
		}
	}

}
