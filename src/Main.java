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
		doc.addChunk(new Header(HeaderKind.TITLE, "F�c�m"));
		doc.addChunk(new Header(HeaderKind.H1, "Ez egy c�msor"));
		doc.addChunk(new Paragraph("Ez meg itt egy random p�lda bekezd�s, aminek nincs �rtelme, csak az a l�nyeg, hogy hossz� legyen. H�t, nem biztos, hogy siker�lt. De az�rt pr�b�lkozni szabad, nem? De. Nah�takkormeg. N�zd meg, eg�sz j�l halad. M�r van vagy 200 karakter. Az�rt az m�r nem semmi egy bekezd�s."));
		doc.addChunk(new Paragraph(new Header(HeaderKind.H2, "TODO lista:")));
		List list = new List(ListKind.ASTERISK);
		doc.addChunk(list);
		list.addChild("K�vetelm�nyspecifik�ci�");
		list.addChild("HTML parser");
		list.addChild("MarkDown parser");
		list.addChild("Wikitext parser");
		list.addChild("GUI");
		Paragraph par = new Paragraph("Ez meg egy �jabb paragrafus a lista ut�n, amiben a ");
		par.addChild(new Link("https://www.google.hu", "link"));
		par.addChild(" sz�n van ");
		par.addChild(new Emphatic("egy"));
		par.addChild(" hivatkoz�s.");
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
