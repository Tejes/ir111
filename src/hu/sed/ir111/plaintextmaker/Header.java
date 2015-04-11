package hu.sed.ir111.plaintextmaker;

import org.apache.commons.lang3.*;

/**
 * Represents a single line header text. It will be underlined with === or --- depending on the HeaderKind passed in
 * @author keletim
 *
 */
public class Header extends Chunk {
	private HeaderKind kind;
	
	public Header(HeaderKind kind) {
		super();
		this.kind = kind;
	}
	
	public Header(HeaderKind kind, String text) {
		super(text);
		this.kind = kind;
	}
	
	public Header(HeaderKind kind, Chunk child) {
		super(child);
		this.kind = kind;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int len;
		sb.append(super.toString());
		len = sb.length();
		sb.append(EOL);
		sb.append(StringUtils.repeat(kind == HeaderKind.h1 ? "=" : "-", len));
		sb.append(EOL);
		return sb.toString();
	}
}
