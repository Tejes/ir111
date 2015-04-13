/**
 * 
 */
package hu.sed.ir111.plaintextmaker;

import java.util.*;

/**
 * Represents a piece of text, can have multiple children
 * @author keletim
 *
 */
public class Chunk {
	public static final String EOL = System.getProperty("line.separator");
	public static final int COLUMN_WIDTH = 80;
	protected LinkedList<Chunk> children;
	
	public Chunk() {
		children = new LinkedList<Chunk>();
	}
	
	public Chunk(String text) {
		this();
		addChild(new PlainText(text));
	}
	
	public Chunk(Chunk child) {
		this();
		addChild(child);
	}
	
	public void addChild(Chunk chunk) {
		children.add(chunk);
	}
	
	public void addChild(String plaintext) {
		addChild(new PlainText(plaintext));
	}
	
	public void addChildren(Collection<Chunk> children) {
		this.children.addAll(children);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Chunk child : children) {
			sb.append(child);
		}
		
		return sb.toString();
	}
}
