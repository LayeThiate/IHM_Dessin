package ihm.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ihm.modele.*;
import ihm.vue.*;

public class ToolEllipse implements Tool{
	private ClipEllipse ce ;
	private Color color; 
	
	public ToolEllipse(Color c){
		color = c;
	}
	
	public void setColor(Color c){
		ce.setColor(c);
	}
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		ce = new ClipEllipse(e.getX(), e.getY(), e.getX(), e.getY(), color);
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		ce.setGeometry(ce.getLeft(), ce.getTop(), e.getX(), e.getY());
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		i.getBoard().addClip(ce);
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.strokeOval(ce.getLeft(), ce.getTop(), ce.getWidth(), ce.getHeigth());
	}

	@Override
	public String getName() {
		return "Ellipse";
	}

}
