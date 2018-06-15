package ihm.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import ihm.modele.*;


public class Selection {
	
	private List<Clip> list;
	
	public Selection(){
		list = new ArrayList<Clip>();
	}
	
	public void select(Board board, double x, double y){
		list.clear();
		for(Clip c : board.getContents()){
			if(c.isSelected(x, y)){
				list.add(c);
				System.out.println("contains");
				break;
			}
		}
	}
		
	public void toogleSelect(Board board,double x,double y){
		for(Clip c : board.getContents()){
			if(c.isSelected(x, y)){
				if(!list.contains(c)){
					list.add(c);
					System.out.println("add");
				}
				else{
					list.remove(c);
					System.out.println("remove");
				}
			}
		}
	}
	
	public List<Clip> listCopy(){
		List<Clip> copy = new ArrayList<Clip>();
		for(Clip c : list){
			copy.add(c.copy());
			
		}
		return copy;
	}
	
	public void clear(){
		list.clear();
	}
	public List<Clip> getContents(){
		return list;
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	public void drawFeedback(GraphicsContext gc){
		double left = Double.MAX_VALUE; 
		double top = Double.MAX_VALUE; 
		double rigth = Double.MIN_VALUE;
		double bottom = Double.MIN_VALUE;

		for(Clip c : list){
			if(left > c.getLeft())
				left = c.getLeft();
			if(top > c.getTop())
				top = c.getTop();
			if(rigth < c.getRight())
				rigth = c.getRight();
			if(bottom < c.getBottom())
				bottom = c.getBottom();
		}
		
		gc.strokeRect(left, top, rigth-left, bottom-top);
	
	}

}
