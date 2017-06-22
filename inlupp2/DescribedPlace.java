package inlupp2;

public class DescribedPlace extends Plats {

	String description;
	
	DescribedPlace(int x, int y, String name, String category, String desc){
		super(x, y, name, category);
		this.description = desc;
		
		
	}
	
	String getString() {
		return name + "\n" + description;
	}

}
