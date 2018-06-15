package ihm.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ihm.modele.*;
import ihm.vue.*;

public class ToolRect implements Tool{
	private ClipRect c;
	private Color color ;
	
	public ToolRect(Color c){
		color = c;
	}
	
	public void setColor(Color color){
		c.setColor(color);
	}

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		c = new ClipRect(e.getX(), e.getY(), e.getX(), e.getY(), color);
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		c.setGeometry(c.getLeft(), c.getTop(), e.getX(), e.getY());
		
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		i.getBoard().addClip(c);
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.strokeRect(c.getLeft(), c.getTop(),c.getWidth(), c.getHeigth());
	}

	@Override
	public String getName() {
		return "Rectangle";
	}

}
