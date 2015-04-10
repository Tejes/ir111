package hu.sed.ir111.plaintextmaker;

public class PlainText extends Chunk {
	private String text;
	
	public PlainText(String text) {
		super();
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
	
	public void addChild() {
		throw new IllegalStateException("PlainText objektum nem tartalmazhat gyerekeket");
	}
}
