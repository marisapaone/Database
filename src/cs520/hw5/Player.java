
//author: Marisa Paone


package cs520.hw5;

public class Player {
	
	public Player ( ) {
		
	}
	
	//initializing variables
	private int number;
	private String name;
	private String position;
	private String year;
	
	//mutator and accessor methods
	public void setNumber(int num) {
		this.number = num;
	}
	public void setName(String n) {
		this.name = n;
	}
	public void setPosition(String p) {
		this.position = p;
	}
	public void setYear(String y) {
		this.year = y;
	}
	public int getNumber () {
		return number;
	}
	public String getName () {
		return name;
	}
	public String getPosition() {
		return position;
	}
	public String getYear() {
		return year;
	}
	
	//single constructor that accepts string as its argument
	public Player(String name) {
		this.name = name;
	}
		
	public String toString() {
		//this method prints the string with number, name, position and year
		String str = ("#"+ number +": "+name+" ("+position+", "+ year+")");
		//System.out.println(str);
		return str;
		
	}
}


