package ihm.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import ihm.vue.*;
/**
 * permet l'encatpsulation du comportement de chaque element graphique  en fonction des evenements
 * @author abdoulaye
 *
 */

public interface Tool {
	/**
	 * appui sur le canvas
	 * @param i
	 * @param e
	 */
	public void press(EditorInterface i, MouseEvent e);
	/**
	 * mouvement de la souris sur la canvas
	 * @param i
	 * @param e
	 */
	public void drag(EditorInterface i, MouseEvent e);
	/**
	 * relache du clic
	 * @param i
	 * @param e
	 */
	public void release(EditorInterface i, MouseEvent e);
	/**
	 * dessiner l'element en plein sur le canvas après release
	 * @param i
	 * @param gc
	 */
	public void drawFeedback(EditorInterface i, GraphicsContext gc);
	public String getName();
}
