package inlupp2;

import javax.swing.JComponent;

public abstract class Plats extends JComponent {

	int xPos, yPos;
	String name;
	String category;
	
	Plats(int x, int y, String name, String category){
		
		this.xPos = x;
		this.yPos = y;
		this.name = name;
		this.category = category;
		
	}
	
	public String getName() {
		return name;
	}
	
	abstract String getString();
	
	public String toString() {
		return category + "," + xPos + "," + yPos + "," + name;
	}
}
