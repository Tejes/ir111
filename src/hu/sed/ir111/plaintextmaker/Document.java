package hu.sed.ir111.plaintextmaker;
import java.util.*;

public class Document {
	private ArrayList<Chunk> chunks;
	
	public Document() {
		chunks = new ArrayList<Chunk>();
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
}
