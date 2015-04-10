package hu.sed.ir111.plaintextmaker;

public class Paragraph extends Chunk {
	public Paragraph() {
		super();
	}
	
	public Paragraph(String text) {
		super(text);
	}
	
	public Paragraph(Chunk child) {
		super(child);
	}
	
	public String toString() {
		return EOL + super.toString();
	}
}
