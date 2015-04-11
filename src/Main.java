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
		doc.addChunk(new Header(HeaderKind.h1, "Ez egy c�msor"));
		doc.addChunk(new Paragraph("Ez meg itt egy random p�lda bekezd�s, aminek nincs �rtelme, csak az a l�nyeg, hogy hossz� legyen. H�t, nem biztos, hogy siker�lt. De az�rt pr�b�lkozni szabad, nem? De. Nah�takkormeg. N�zd meg, eg�sz j�l halad. M�r van vagy 200 karakter. Az�rt az m�r nem semmi egy bekezd�s."));
		doc.addChunk(new Paragraph(new Header(HeaderKind.h2, "TODO lista:")));
		List list = new List(ListKind.NUMERAL);
		doc.addChunk(list);
		list.addChild(new PlainText("K�vetelm�nyspecifik�ci�"));
		list.addChild(new PlainText("HTML parser"));
		list.addChild(new PlainText("MarkDown parser"));
		list.addChild(new PlainText("Wikitext parser"));
		list.addChild(new PlainText("GUI"));
		Paragraph par = new Paragraph("Ez meg egy �jabb paragrafus a lista ut�n, amiben a ");
		par.addChild(new Link("https://www.google.hu", "link"));
		par.addChild(new PlainText(" sz�n van egy hivatkoz�s."));
		doc.addChunk(par);
		doc.save(fw);
		fw.close();
	}
    
}
