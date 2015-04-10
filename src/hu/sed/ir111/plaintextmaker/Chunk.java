/**
 * 
 */
package hu.sed.ir111.plaintextmaker;

import java.util.*;

/**
 * @author keletim
 *
 */
public abstract class Chunk {
	public static final String EOL = System.getProperty("line.separator");
	protected ArrayList<Chunk> children;
	
	public Chunk() {
		children = new ArrayList<Chunk>();
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
