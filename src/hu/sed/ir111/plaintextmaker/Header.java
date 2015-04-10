package hu.sed.ir111.plaintextmaker;

import org.apache.commons.lang3.*;

public class Header extends Chunk {
	private HeaderKind kind;
	
	public Header(HeaderKind kind) {
		super();
		this.kind = kind;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int len;
		sb.append(super.toString());
		len = sb.length();
		sb.append(System.getProperty("line.separator"));
		sb.append(StringUtils.repeat(kind == HeaderKind.h1 ? "=" : "-", len));
		
		return sb.toString();
	}
}
