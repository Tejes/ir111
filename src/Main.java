import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import hu.sed.ir111.plaintextmaker.*;


/**
 * @author keletim
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("out.txt");
		Document doc = new Document();
		doc.addChunk(new Header(HeaderKind.TITLE, "Fõcím"));
		doc.addChunk(new Header(HeaderKind.H1, "Ez egy címsor"));
		doc.addChunk(new Paragraph("Ez meg itt egy random példa bekezdés, aminek nincs értelme, csak az a lényeg, hogy hosszú legyen. Hát, nem biztos, hogy sikerült. De azért próbálkozni szabad, nem? De. Nahátakkormeg. Nézd meg, egész jól halad. Már van vagy 200 karakter. Azért az már nem semmi egy bekezdés."));
		doc.addChunk(new Paragraph(new Header(HeaderKind.H2, "TODO lista:")));
		List list = new List(ListKind.ASTERISK);
		doc.addChunk(list);
		list.addChild("Követelményspecifikáció");
		list.addChild("HTML parser");
		list.addChild("MarkDown parser");
		list.addChild("Wikitext parser");
		list.addChild("GUI");
		Paragraph par = new Paragraph("Ez meg egy újabb paragrafus a lista után, amiben a ");
		par.addChild(new Link("https://www.google.hu", "link"));
		par.addChild(" szón van ");
		par.addChild(new Emphatic("egy"));
		par.addChild(" hivatkozás.");
		doc.addChunk(par);
		doc.save(fw);
		fw.close();
		
		fw = new FileWriter("out2.txt");
		doc = Parser.parseHtml(Paths.get("test.html"));
		doc.save(fw);
		fw.flush();
		fw.close();
	}
    
}
