package hu.sed.ir111.plaintextmaker;

public class Emphatic extends Chunk {
	public Emphatic() {
		super();
	}
	
	public Emphatic(String text) {
		super(text);
	}
	
	public Emphatic(Chunk child) {
		super(child);
	}
	
	public String toString() {
		return '*' + super.toString() + '*';
	}
}
