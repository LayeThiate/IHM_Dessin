package ihm.modele;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * conteneur d'un ensemble d'elements graphiques (Clip)
 * @author abdoulaye
 *
 */

public class Board {
	
	private List<Clip> planche;
	
	public Board(){
		planche=new ArrayList<>();
	}
	public List<Clip> getContents(){
		return planche;
	}
	public void addClip(Clip clip){
		planche.add(clip);
	}
	public void addClip(List<Clip> clip){
		planche.addAll(clip);
	}
	public void removeClip(Clip clip){
		planche.remove(clip);
	}
	public void removeClip(List<Clip> clip){
		planche.removeAll(clip);
	}
	/**
	 * dessine tous les elements contenus
	 * @param gc
	 */
	public void draw(GraphicsContext gc){
		gc.setFill(Color.WHITE); // effacer la zone d'affichage
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight()); 
		for(Clip clip : planche ){
			clip.draw(gc);
		}
	}
}
