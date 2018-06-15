package ihm.vue;

import ihm.modele.*;
import ihm.controller.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * l'encapsulation de la fenetre et le dessin édité
 * @author abdoulaye
 *
 */

public class EditorWindow implements EditorInterface{
	private Board planche;
	private Tool outil; // permet de déléguer les actions de la souris à la classe outil en cours
	private Canvas canvas;
	private Selection selection = new Selection();
	Color color;

	@Override
	public Board getBoard() {
		return planche;
	}

	@Override
	public Selection getSelection() {
		return selection;
	}

	public Tool getTool() {
		return outil;
	}

	public EditorWindow(Stage s) {
		
		HBox hbox = new HBox();
		
		Pane pane = new Pane();
		
		RadioButton butSelectMove = new RadioButton("select/move");
		RadioButton butEllipse = new RadioButton("ellipse");
		RadioButton butRectangle = new RadioButton("rectangle");
		RadioButton butLine = new RadioButton("line");
		ToggleGroup toGroupe = new ToggleGroup();
		butSelectMove.setToggleGroup(toGroupe);
		butEllipse.setToggleGroup(toGroupe);
		butRectangle.setToggleGroup(toGroupe);
		butLine.setToggleGroup(toGroupe);
		butSelectMove.setFocusTraversable(false);
		butRectangle.setFocusTraversable(false);
		butLine.setFocusTraversable(false);
		butEllipse.setFocusTraversable(false); // selectionné  par défaut
		
		VBox vBoxBut = new VBox();
		vBoxBut.getChildren().addAll(butSelectMove, butEllipse, butRectangle, butLine);
		vBoxBut.setSpacing(5);
		
		ColorPicker colorP = new ColorPicker(); // choix des couleurs
		color = colorP.getValue();
		
		Button clone = new Button("Clone");
		Button delete = new Button("Delete");
		clone.setDisable(true);
		delete.setDisable(true);
		VBox vboxCD = new VBox(3);
		vboxCD.getChildren().addAll(clone, delete);
		
		pane.setMinWidth(180);
		pane.setMinHeight(600);
		pane.getChildren().addAll(vBoxBut, colorP, vboxCD);
		pane.getChildren().get(0).setLayoutX(25);
		pane.getChildren().get(0).setLayoutY(15);
		pane.getChildren().get(1).setLayoutX(25);
		pane.getChildren().get(1).setLayoutY(175);
		pane.getChildren().get(2).setLayoutX(80);
		pane.getChildren().get(2).setLayoutY(220);
		vboxCD.setAlignment(Pos.CENTER);
		
		canvas = new Canvas(600, 600);

		planche = new Board();
		/* Label */
		Label l = new Label("Dessin");
		
		/* evenement de SELECT */
		butSelectMove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				selection = new Selection();
				outil = new ToolSelection();
				l.textProperty().set(outil.getName());
			}
		});

		/* gestion des evenements */
		/* evenement de copy */
		clone.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//ClipBoard.getInstance().copyToClipBoard(selection.getContents());
				planche.getContents().addAll(selection.listCopy());
				draw();
			}
		});

		/* evenement de paste */
//		paste.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				planche.getContents().addAll(
//						ClipBoard.getInstance().copyFromClipBoard());
//				draw();
//			}
//		});

		/* evenement de delete */
		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				planche.removeClip(selection.getContents());
				// selection.clear();
				//ClipBoard.getInstance().clear();
				draw();
			}
		});
		

		/* Evenement des boutons */
		/* evenement de rectangle */
		butRectangle.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				color = colorP.getValue();
				outil = new ToolRect(color);
				l.textProperty().set(outil.getName());
			}
		});
		/* evenement de ellipse */
		butEllipse.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				color = colorP.getValue();
				outil = new ToolEllipse(color);
				l.textProperty().set(outil.getName());
			}
		});
	
		/* evenement de ellipse */
		butLine.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				color = colorP.getValue();
				outil = new ToolLine(color);
				l.textProperty().set(outil.getName());
			}
		});
		
		/* evenement de SELECT */
		butSelectMove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				clone.setDisable(false);
				delete.setDisable(false);
				selection = new Selection();
				outil = new ToolSelection();
				l.textProperty().set(outil.getName());
			}
		});
		
		 //ajout d'un ChangeListener au groupe de boutons radio
        toGroupe.selectedToggleProperty().addListener(new ChangeListener<Object>(){
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
                if(newValue.equals(butSelectMove)) {
                	clone.setDisable(false);
    				delete.setDisable(false);
                }
                else {
                	clone.setDisable(true);
    				delete.setDisable(true);
                }
            }
        });

		/* evenement sur le canevas */
		canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				outil.press(EditorWindow.this, e);
				outil.drawFeedback(EditorWindow.this,
						canvas.getGraphicsContext2D());

			}
		});

		canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				outil.drag(EditorWindow.this, e);
				draw();
				outil.drawFeedback(EditorWindow.this,
						canvas.getGraphicsContext2D());
			}
		});

		canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				outil.release(EditorWindow.this, e);
				draw();
				outil.drawFeedback(EditorWindow.this,
						canvas.getGraphicsContext2D());
			}
		});

		hbox.getChildren().addAll(pane, new Separator(), canvas);
		VBox bv = new VBox();
		bv.getChildren().addAll(hbox,new Separator(), l);
		Scene sc = new Scene(bv);
		s.setScene(sc);
		s.show();
	}

	public void draw() {
		planche.draw(canvas.getGraphicsContext2D());
	}

	

}
