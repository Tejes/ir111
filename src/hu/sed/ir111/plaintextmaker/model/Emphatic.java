package hu.sed.ir111.plaintextmaker.model;

/**
 * Represents an emphatised piece of text, like bold, italic or underlined. Prints out *text* in default  
 * @author keletim
 *
 */
public class Emphatic extends Chunk {
	private String left = "*", right = "*";
	public Emphatic() {
		super();
	}
	
	public Emphatic(String text) {
		super(text);
	}
	
	public Emphatic(Chunk child) {
		super(child);
	}
	
	/**
	 * Change the left and right delimiters from the default *
	 * @param left
	 * @param right
	 */
	public void setDelimiters(String left, String right) {
		this.left = left;
		this.right = right;
	}
	
	public String toString() {
		return left + super.toString() + right;
	}
}
