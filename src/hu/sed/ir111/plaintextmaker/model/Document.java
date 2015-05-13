package hu.sed.ir111.plaintextmaker.model;
import java.io.IOException;
import java.util.*;

/**
 * Represents a plain text document we are building
 * @author keletim
 *
 */
public class Document {
	private LinkedList<Chunk> chunks;
	
	public Document() {
		chunks = new LinkedList<Chunk>();
	}
	
	public void addChunk(Chunk chunk) {
		chunks.add(chunk);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(Chunk chunk : chunks) {
			sb.append(chunk);
		}
		
		return sb.toString();
	}
	
	public void save(java.io.FileWriter fw) throws IOException {
		for(Chunk chunk : chunks) {
			fw.write(chunk.toString());
		}
		fw.flush();
	}
}
