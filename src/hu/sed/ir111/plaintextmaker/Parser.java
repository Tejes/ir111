package hu.sed.ir111.plaintextmaker;

import hu.sed.ir111.plaintextmaker.model.Chunk;
import hu.sed.ir111.plaintextmaker.model.Document;
import hu.sed.ir111.plaintextmaker.model.Emphatic;
import hu.sed.ir111.plaintextmaker.model.Header;
import hu.sed.ir111.plaintextmaker.model.HeaderKind;
import hu.sed.ir111.plaintextmaker.model.Link;
import hu.sed.ir111.plaintextmaker.model.List;
import hu.sed.ir111.plaintextmaker.model.ListKind;
import hu.sed.ir111.plaintextmaker.model.Paragraph;
import hu.sed.ir111.plaintextmaker.model.PlainText;
import hu.sed.ir111.plaintextmaker.model.Table;
import hu.sed.ir111.plaintextmaker.model.TableCell;
import hu.sed.ir111.plaintextmaker.model.TableRow;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

import org.eclipse.mylyn.wikitext.core.parser.MarkupParser;
import org.eclipse.mylyn.wikitext.mediawiki.core.MediaWikiLanguage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeVisitor;
import org.markdown4j.*;

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
				//System.out.println(node);
				if(node instanceof TextNode) {
					String text = ((TextNode)node).text();
					if(text.equals(" "))
						return;
					chunk = new PlainText(Pattern.compile("</?ref[^>]*>").matcher(text).replaceAll(""));
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
						case "ol":
							chunk = new List(node.nodeName().equals("ol") ? ListKind.NUMERAL : ListKind.ASTERISK);
							
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
						case "table":
							chunk = new Table();
							break;
						case "tr":
							chunk = new TableRow();
							break;
						case "th":
							chunk = new TableCell(true);
							break;
						case "td":
							chunk = new TableCell();
							break;
						default:
							return;
					}
				}
				map.put(node, chunk);
				if(node.parent() == null) {
					document.addChunk(chunk);
				}
				else if(!map.containsKey(node.parent())) {  //az �snek nincs chunkja -> vagy gy�k�r, vagy nem kezelt tag (span, div, stb)
					while(node != null && !map.containsKey(node.parent())) { //megkeress�k azt az �st aminek van chunkja, vagy null-t kapunk ha gy�k�rhez �rt�nk
						node = node.parent();
					}
					if(node == null)  //gy�k�rn�l vagyunk, a dokumentum lesz az �s
						document.addChunk(chunk);
					else   //megtal�ltuk a legk�zelebbi �s�t amihez van chunk
						map.get(node.parent()).addChild(chunk);
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
			FileWriter rw=new FileWriter("KoztesWiki.html");
			MarkupParser markupParser = new MarkupParser();
			markupParser.setMarkupLanguage(new MediaWikiLanguage());
			rw.write(markupParser.parseToHtml(wikiContent));
			rw.flush();
			rw.close();
			return parseHtml(markupParser.parseToHtml(wikiContent));
		}
	
		public static Document parseMarkdown(Path filePath) throws IOException {
			//String mdContent = new String(Files.readAllBytes(filePath));
			FileWriter rw=new FileWriter("KoztesMarkdn.html");
			//System.out.println(mdContent);
			//MarkupParser markupParser = new MarkupParser();
			//markupParser.setMarkupLanguage(new MarkdownLanguage());
			//rw.write(markupParser.parseToHtml(mdContent));
			String html = new Markdown4jProcessor().process(new String(Files.readAllBytes(filePath)));
			rw.write(html);
			//System.out.println(markupParser.parseToHtml(mdContent));
			rw.flush();
			rw.close();
			return parseHtml(html);

		}
	
	private static ListKind extractListStyle(String str) {
		//kicsit fav�g�, de ez van
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
		return ListKind.NUMERAL;  //alapeset: minden sz�m �s ha nincs megadva semmi
	}
}
