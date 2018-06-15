package ihm.modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipLine extends AbstractClip {

public ClipLine(double left, double top, double right, double bottom, Color color) {
		super(left, top, right, bottom, color);
	}

//	public ClipLine(double left, double rigth, Color color) {
//		super(left, 2, left+1, 2, color);
//	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(getColor());
		ctx.fillRect(getLeft(), getTop(), getRight()-getLeft(), 5);
		ctx.setFill(getColor());		
	}

	@Override
	public Clip copy() {
		Clip l = new ClipLine(getLeft(), getTop(), getRight(), getBottom(), getColor());
		l.move(50, 30);
		return l;
	}

}
