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
				
			}			
			@Override
			public void head(Node node, int depth) {
				Chunk chunk;
				System.out.println(node);
				if(node instanceof TextNode) {
					if(((TextNode) node).text().equals(" "))
						return;
					chunk = new PlainText(((TextNode) node).text());
				}
				else {
					switch(node.nodeName()) {
						case "title":
							chunk = new Header(HeaderKind.TITLE);
							break;
						case "h1":
							chunk = new Header(HeaderKind.H1);
							break;
						case "h2":
						case "h3":
						case "h4":
						case "h5":
						case "h6":
							chunk = new Header(HeaderKind.H2);
							break;
						case "p":
							chunk = new Paragraph();
							break;
						case "ul":
							chunk = new List(ListKind.ASTERISK);
							
							if(!node.attr("style").isEmpty()) {
								((List)chunk).setKind(extractListStyle(node.attr("style")));
							}
							else if(!node.attr("type").isEmpty()) {
								((List)chunk).setKind(extractListStyle(node.attr("type")));
								switch(node.attr("type")) {
									case "1": ((List)chunk).setKind(ListKind.NUMERAL); break;
									case "a": ((List)chunk).setKind(ListKind.LOWERCASE); break;
									case "A": ((List)chunk).setKind(ListKind.UPPERCASE); break;
									case "i": ((List)chunk).setKind(ListKind.LOWER_ROMAN); break;
									case "I": ((List)chunk).setKind(ListKind.UPPER_ROMAN); break;
									default: ((List)chunk).setKind(ListKind.NUMERAL); break;
								}
							}
								
							break;
						case "li":
							chunk = new Chunk();
							break;
						case "a":
							chunk = new Link(node.attr("href"));
							break;
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
				else if(!map.containsKey(node.parent())) {  //TODO
					while(!map.containsKey(node) && node != null) {
						node = node.parent();
					}
					document.addChunk(chunk);
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
	
	private static ListKind extractListStyle(String str) {
		//kicsit favágó, de ez van
		if(str.contains("square") || str.contains("disc") || str.contains("circle"))
			return ListKind.ASTERISK;
		if(str.contains("lower-alpha") || str.contains("lower-greek") || str.contains("lower-latin"))
			return ListKind.LOWERCASE;
		if(str.contains("lower-roman"))
			return ListKind.LOWER_ROMAN;
		if(str.contains("upper-alpha") || str.contains("upper-latin"))
			return ListKind.UPPERCASE;
		if(str.contains("upper-roman"))
			return ListKind.UPPER_ROMAN;
		if(str.contains("list-style-type: none") || str.contains("listy-style: none") || str.contains("list-style:none") || str.contains("list-style-type:none") || str.equals("none"))
			return ListKind.NONE;
		return ListKind.NUMERAL;  //alapeset: minden szám és ha nincs megadva semmi
	}
}
