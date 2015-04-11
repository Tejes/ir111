package hu.sed.ir111.plaintextmaker;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.NodeVisitor;

public class HtmlReader extends Reader {
	
	
	public HtmlReader(Document doc, String fileName) throws IOException {
		document = doc;
		final HashMap<Node, Chunk> map = new HashMap<Node, Chunk>();
		org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(new File(fileName), "UTF-8");
		
		jsoupDoc.traverse(new NodeVisitor() {			
			@Override
			public void tail(Node node, int depth) {
				// TODO Auto-generated method stub
				
			}			
			@Override
			public void head(Node node, int depth) {
				Chunk chunk;
				switch(node.nodeName()) {
					case "b":
					case "i":
					case "u":
						chunk = new Emphatic();
						break;
					default:
						return;
				}
				map.put(node, chunk);
				if(node.parent() == null) {
					
				}
				else if(!map.containsKey(node.parent())) {
					
				}
			}
		});
		
	}
}