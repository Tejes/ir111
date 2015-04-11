package hu.sed.ir111.plaintextmaker;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


//import org.eclipse.mylyn.wikitext.confluence.core.ConfluenceLanguage;
import org.eclipse.mylyn.wikitext.core.parser.MarkupParser;
//import org.eclipse.mylyn.wikitext.html.core.HtmlLanguage;
//import org.eclipse.mylyn.wikitext.markdown.core.MarkdownLanguage;
import org.eclipse.mylyn.wikitext.mediawiki.core.MediaWikiLanguage;
//import org.eclipse.mylyn.wikitext.textile.core.TextileLanguage;
//import org.eclipse.mylyn.wikitext.tracwiki.core.TracWikiLanguage;
//import org.eclipse.mylyn.wikitext.twiki.core.TWikiLanguage;

public class WikiReader extends Reader {
	
	public WikiReader(Document doc, String fileName) throws IOException {
		
		String wikiContent = new String(Files.readAllBytes(Paths.get(fileName)));
		
		MarkupParser markupParser = new MarkupParser();
		
		//markupParser.setMarkupLanguage(new ConfluenceLanguage());
		//markupParser.setMarkupLanguage(new HtmlLanguage());
		//markupParser.setMarkupLanguage(new MarkdownLanguage());
		markupParser.setMarkupLanguage(new MediaWikiLanguage()); //best
		//markupParser.setMarkupLanguage(new TextileLanguage());
		//markupParser.setMarkupLanguage(new TracWikiLanguage()); //good
		//markupParser.setMarkupLanguage(new TWikiLanguage()); //good
		
		String htmlContent = markupParser.parseToHtml(wikiContent);
		
		FileWriter fw = new FileWriter("converted.html");
		fw.write(htmlContent);
		fw.flush();
		
		HtmlReader parser = new HtmlReader(doc, "converted.html");
		
	}
	
}