import java.awt.Point;
import java.text.DecimalFormat;


public class Block {
	
	private int num_blocks; //num blocks

	private int breakpoint;
	private int num_per_dollar; //yellow_per_dollar...etc
	
	/*
	 * yellow_multipliers.x is the money rate to change at
	 * yellow_multipliers.y is the multiplier value
	 */
	private Point[] multiplier = new Point[5]; //Number can change depending on how many we want 
	private int current_mult_index = 0;
	
	public Block(int mult1, int b1,
			int mult2, int b2, 
			int mult3, int b3, 
			int mult4, int b4, 
			int mult5, int b5, 
			int desired_num_per_dollar)
	{
		multiplier[0] = new Point(b1, mult1);
		multiplier[1] = new Point(b2, mult2);
		multiplier[2] = new Point(b3, mult3);
		multiplier[3] = new Point(b4, mult4);
		multiplier[4] = new Point(b5, mult5);

		num_per_dollar = desired_num_per_dollar;
		
		num_blocks = 0;
		breakpoint = 0;
	}
	
	
	public int getNum_blocks() {
		return num_blocks;
	}
	
	public void setNum_blocks(int num_blocks) {
		this.num_blocks = num_blocks;
	}

	public int getBreakpoint() {
		return breakpoint;
	}

	public void setBreakpoint(int breakpoint) {
		this.breakpoint = breakpoint;
	}

	public int getNum_per_dollar() {
		return num_per_dollar;
	}

	public int getCurrent_mult_index() {
		return current_mult_index;
	}

	public void setCurrent_mult_index(int current_mult_index) {
		this.current_mult_index = current_mult_index;
	}

	public Point getMultiplier(int index) {
		return multiplier[index];
	}

	public void setMultiplier(Point p, int index) {
		multiplier[index] = p;
	}



}
