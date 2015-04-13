package hu.sed.ir111.plaintextmaker;

/**
 * Represents hiperlinks. The link is printed in <> brackets after the text
 * @author keletim
 *
 */
public class Link extends Chunk {
	private String href;
	
	public Link(String href) {
		super();
		this.href = href;
	}
	
	public Link(String href, String text) {
		super(text);
		this.href = href;
	}
	
	public Link(String href, Chunk child) {
		super(child);
		this.href = href;
	}
	
	public String toString() {
		return super.toString() + " <" + href + ">";
	}
}
