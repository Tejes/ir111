import java.io.FileWriter;
import java.io.IOException;

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
		doc.addChunk(new Header(HeaderKind.h1, "Ez egy címsor"));
		doc.addChunk(new Paragraph("Ez meg itt egy random példa bekezdés, aminek nincs értelme, csak az a lényeg, hogy hosszú legyen. Hát, nem biztos, hogy sikerült. De azért próbálkozni szabad, nem? De. Nahátakkormeg. Nézd meg, egész jól halad. Már van vagy 200 karakter. Azért az már nem semmi egy bekezdés."));
		doc.addChunk(new Paragraph(new Header(HeaderKind.h2, "TODO lista:")));
		List list = new List(ListKind.NUMERAL);
		doc.addChunk(list);
		list.addChild(new PlainText("Követelményspecifikáció"));
		list.addChild(new PlainText("HTML parser"));
		list.addChild(new PlainText("MarkDown parser"));
		list.addChild(new PlainText("Wikitext parser"));
		list.addChild(new PlainText("GUI"));
		Paragraph par = new Paragraph("Ez meg egy újabb paragrafus a lista után, amiben a ");
		par.addChild(new Link("https://www.google.hu", "link"));
		par.addChild(new PlainText(" szón van egy hivatkozás."));
		doc.addChunk(par);
		doc.save(fw);
		fw.close();
	}
    
}
