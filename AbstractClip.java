package ihm.modele;

import javafx.scene.paint.Color;

/**
 * factorisation de l'implementation des elements graphique
 * @author abdoulaye
 *
 */
public abstract class AbstractClip implements Clip{
	protected Color color;
	protected double left,top,right,bottom;
	
	public AbstractClip(double left,double top,double right,double bottom,Color color){
		this.left=left;
		this.right=right;
		this.top=top;
		this.bottom=bottom;
		this.color=color;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getLeft() {
		return left;
	}
	public void setLeft(double left) {
		this.left = left;
	}
	public double getTop() {
		return top;
	}
	public void setTop(double top) {
		this.top = top;
	}
	public double getRight() {
		return right;
	}
	public void setRight(double right) {
		this.right = right;
	}
	public double getBottom() {
		return bottom;
	}
	public void setBottom(double bottom) {
		this.bottom = bottom;
	}
	
	public double getWidth(){
		return right -left;
	}
	
	public double getHeigth(){
		return bottom - top;
	}
	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		this.left=left;
		this.right=right;
		this.top=top;
		this.bottom=bottom;
	}

	/**
	 * deplace l'element
	 */
	@Override
	public void move(double x, double y) {
		left+=x;
		right+=x;
		top+=y;
		bottom+=y;	
	}
	/**
	 * verifie si l'element est selectionné
	 */
	@Override
	public boolean isSelected(double x, double y) {
		if((x>=left && y>=top) && (x<=right && y<=bottom))
			return true;
		return false;
	}

}
