package hu.sed.ir111.plaintextmaker;

public class Emphatic extends Chunk {
	public Emphatic() {
		super();
	}
	
	public String toString() {
		return '*' + super.toString() + '*';
	}
}
