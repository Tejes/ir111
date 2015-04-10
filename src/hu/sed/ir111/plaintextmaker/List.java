package hu.sed.ir111.plaintextmaker;

public class List extends Chunk {
	private ListKind kind;
	
	private String[] romaiak = new String[] {"i", "ii", "iii"};
	
	public List(ListKind kind) {
		super();
		this.kind = kind;
	}
	
	public String toString() {
		int i = 1;
	}
}
