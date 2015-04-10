package hu.sed.ir111.plaintextmaker;

public class Link extends Chunk {
	private String href;
	
	public Link(String href) {
		super();
		this.href = href;
	}
	
	public String toString() {
		return super.toString() + " [" + href + "]";
	}
}
