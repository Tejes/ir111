package hu.sed.ir111.plaintextmaker;

public class Emphatic extends Chunk {
	public Emphatic() {
		super();
	}
	
	public Emphatic(String text) {
		this();
		addChild(new PlainText(text));
	}
	
	public Emphatic(Chunk child) {
		this();
		addChild(child);
	}
	
	public String toString() {
		return '*' + super.toString() + '*';
	}
}
