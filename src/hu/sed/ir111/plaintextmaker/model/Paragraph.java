package hu.sed.ir111.plaintextmaker.model;

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
		return super.toString() + EOL + EOL;
	}
}
