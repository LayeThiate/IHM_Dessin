package ihm.vue;

import ihm.modele.*;
import ihm.controller.*;
/**
 * permet aux  tools d'accéder à la planche de la fenetre
 * @author abdoulaye
 *
 */

public interface EditorInterface {
	public Board getBoard();
	public Tool getTool();
	public Selection getSelection();
}
