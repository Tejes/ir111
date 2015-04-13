package hu.sed.ir111.plaintextmaker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import org.eclipse.mylyn.wikitext.core.parser.MarkupParser;
import org.eclipse.mylyn.wikitext.markdown.core.MarkdownLanguage;
import org.eclipse.mylyn.wikitext.mediawiki.core.MediaWikiLanguage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeVisitor;

public abstract class Parser {
	
	public static Document parseHtml(String html) throws IOException {
		final Document document = new Document();
		final HashMap<Node, Chunk> map = new HashMap<Node, Chunk>();
		org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(html, "UTF-8");
		
		jsoupDoc.traverse(new NodeVisitor() {			
			@Override
			public void tail(Node node, int depth) {
				// TODO Auto-generated method stub
				
			}			
			@Override
			public void head(Node node, int depth) {
				Chunk chunk;
				System.out.println(node);
				if(node instanceof TextNode) {
					chunk = new PlainText(((TextNode) node).text());
				}
				else {
					switch(node.nodeName()) {
						case "b":
						case "i":
						case "u":
							chunk = new Emphatic();
							break;
						default:
							return;
					}
				}
				map.put(node, chunk);
				if(node.parent() == null) {
					document.addChunk(chunk);
				}
				else if(!map.containsKey(node.parent())) {
					
				}
				else {
					map.get(node.parent()).addChild(chunk);
				}
			}
		});
		return document;
	}
	
	public static Document parseHtml(Path filePath) throws IOException {
		return parseHtml(new String(Files.readAllBytes(filePath)));
	}
	
	public static Document parseWiki(Path filePath) throws IOException {
		String wikiContent = new String(Files.readAllBytes(filePath));		
		
		MarkupParser markupParser = new MarkupParser();
		
		//markupParser.setMarkupLanguage(new ConfluenceLanguage());
		//markupParser.setMarkupLanguage(new HtmlLanguage());
		//markupParser.setMarkupLanguage(new MarkdownLanguage());
		markupParser.setMarkupLanguage(new MediaWikiLanguage()); //best
		//markupParser.setMarkupLanguage(new TextileLanguage());
		//markupParser.setMarkupLanguage(new TracWikiLanguage()); //good
		//markupParser.setMarkupLanguage(new TWikiLanguage()); //good
		
//		String htmlContent = markupParser.parseToHtml(wikiContent);
//		
//		FileWriter fw = new FileWriter("converted.html");
//		fw.write(htmlContent);
//		fw.flush();
//		fw.close();
		return parseHtml(markupParser.parseToHtml(wikiContent));
		
	}
	
	public static Document parseMarkdown(Path filePath) throws IOException {
		String mdContent = new String(Files.readAllBytes(filePath));
		
		MarkupParser markupParser = new MarkupParser();
		markupParser.setMarkupLanguage(new MarkdownLanguage());
		
		return parseHtml(markupParser.parseToHtml(mdContent));
	}
}
