package ihm.modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip{

	public ClipRect(double left,double top,double right,double bottom,Color color){
		super(left, top, right, bottom, color);
	}
	
	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(getColor());
		ctx.fillRect(getLeft(), getTop(), getRight()-getLeft(), getBottom()-getTop());
		ctx.setFill(getColor());
	}

	@Override
	public Clip copy() {
		Clip co = new ClipRect(getLeft(), getTop(), getRight(), getBottom(), getColor());	
		co.move(25, 50);
		return co;
	}

}
