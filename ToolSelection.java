package ihm.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import ihm.modele.*;
import ihm.vue.*;

public class ToolSelection implements Tool{

	private double oldX;
	private double oldY;

	/**
	 * selection de plusieurs figure en appuyant sur la touche shift
	 */
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		if(e.isShiftDown())
			i.getSelection().toogleSelect(i.getBoard(), e.getX(), e.getY());
		else
			i.getSelection().select(i.getBoard(), e.getX(), e.getY());
		oldX = e.getX();
		oldY = e.getY();
				
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		for(Clip c : i.getSelection().getContents()){
			c.move(e.getX() - oldX, e.getY() - oldY);
		}
		oldX = e.getX();
		oldY = e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getSelection().drawFeedback(gc);
	}

	@Override
	public String getName() {
		return "Selection";
	}

}
