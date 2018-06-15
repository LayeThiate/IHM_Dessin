package ihm.modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip{
	
	
	public ClipEllipse(double left,double top,double right,double bottom,Color color){
		super(left, top, right, bottom, color);
	}

	@Override
	public Clip copy() {
		Clip c = new ClipEllipse(getLeft(), getTop(), getRight(), getBottom(), getColor());
		c.move(50, 40);
		return c;
	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(getColor());
		ctx.fillOval(getLeft(), getTop(), getRight()-getLeft(), getBottom()-getTop());
	}
	@Override
	public boolean isSelected(double x, double y){
		double cx = (getLeft()+getRight())/2;
		double cy = (getBottom()+getTop())/2;
		double rx = (getRight()-getLeft())/2;
		double ry = (getBottom()-getTop())/2;
		if(((((x-cx)/rx)*((x-cx)/rx)) + (((y-cy)/ry)*((y-cy)/ry))) <= 1)
			return true;
		return false;
	}
}
