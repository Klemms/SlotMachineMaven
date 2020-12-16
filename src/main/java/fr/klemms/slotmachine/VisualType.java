package fr.klemms.slotmachine;

public enum VisualType {

	SLOTMACHINE("Slot Machine", 5, 9),
	CSGOWHEEL("CS-GO Wheel", 6, 9),
	CSGOWHEEL_VERTICAL("CS-GO Wheel (vertical", 5, 9);
	
	public String name;
	public int rows;
	public int columns;
	
	VisualType(String name, int rows, int columns) {
		this.name = name;
		this.rows = rows;
		this.columns = columns;
	}
}
