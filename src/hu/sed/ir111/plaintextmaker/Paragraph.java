package hu.sed.ir111.plaintextmaker;

public class Paragraph extends Chunk {
	public Paragraph() {
		super();
	}
	
	public String toString() {
		return EOL + super.toString();
	}
}
