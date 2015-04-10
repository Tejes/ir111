package hu.sed.ir111.plaintextmaker;
import java.io.IOException;
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
	
	public void save(java.io.FileWriter fw) throws IOException {
		for(Chunk chunk : chunks) {
			fw.write(chunk.toString());
		}
	}
}
