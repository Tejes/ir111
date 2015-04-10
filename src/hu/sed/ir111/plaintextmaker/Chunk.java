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
	protected ArrayList<Chunk> children;
	
	public Chunk() {
		children = new ArrayList<Chunk>();
	}
	
	public void addChild(Chunk chunk) {
		children.add(chunk);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Chunk child : children) {
			sb.append(child);
		}
		
		return sb.toString();
	}
}
