package ihm.controller;

import ihm.modele.ClipLine;
import ihm.vue.EditorInterface;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ToolLine implements Tool{
	private ClipLine l ;
	private Color color;
	
	public ToolLine(Color color) {
		this.color = color;
	}
	
	public void setColor(Color color) {
		l.setColor(color); 
	}

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		l = new ClipLine(e.getX(), e.getY(), e.getX(), e.getY(), color);
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		l.setGeometry(l.getLeft(), l.getTop(), e.getX(), l.getBottom());
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		i.getBoard().addClip(l);		
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.strokeRect(l.getLeft(), l.getTop(),l.getWidth(), l.getHeigth());
	}

	@Override
	public String getName() {
		return "Line";
	}

}
