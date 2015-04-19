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
		/*FileWriter fw = new FileWriter("out.txt");
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
		fw.close();*/
		
		String format="undef";
		String inputFile="input.html";
		String outputFile="output.txt";
		if (args.length>0 && (args[0].equals("-h") || args[0].equals("--help"))){
			System.out.println();
			System.out.println("Usage: java -jar ir111.jar [OPTION] [INPUT_FILE] [OUTPUT_FILE]");
			System.out.println();
			System.out.println("Converts the given file from html/wiki/markdown format to text.");
			System.out.println("If format is not defined in OPTION, it will use the file`s extension to determine it (.html/.wiki/.md).");
			System.out.println();
			System.out.println(" -h, --help    Display this help and exit");
			System.out.println(" -f=FORMAT     Convert from the given FORMAT (html/wiki/markdown)");
		} else{
			if (args.length>0 && args[0].startsWith("-f=")) {
				format=args[0].substring(3);
				if (args.length>1){
					inputFile = args[1];
				} else {
					System.err.println("ERROR: No input file is given. Using input.html as default.");
				}
				if (args.length>2){
					outputFile = args[2];
				} else {
					System.err.println("ERROR: No ouput file is given. Using output.txt as default.");
				}
			} else {
				if (args.length>0){
					inputFile = args[0];
				} else {
					System.err.println("ERROR: No input file is given. Using input.html as default.");
				}
				if (args.length>1){
					outputFile = args[1];
				} else {
					System.err.println("ERROR: No ouput file is given. Using output.txt as default.");
				}
				if (inputFile.endsWith(".html")){
					format="html";
				} else if (inputFile.endsWith(".wiki")){
					format="wiki";
				} else if (inputFile.endsWith(".md")){
					format="markdown";
				} else {
					System.err.println("ERROR: Couldn`t determine the file`s extension. Using html as default.");
					format="html";
				}
			}
			FileWriter fw = new FileWriter(outputFile);
			Document doc = null;
			if (format.equals("html")){
				doc = Parser.parseHtml(Paths.get(inputFile));
			} else if (format.equals("wiki")){
				doc = Parser.parseWiki(Paths.get(inputFile));
			} else if (format.equals("markdown")){
				doc = Parser.parseMarkdown(Paths.get(inputFile));
			} else {
				System.err.println("ERROR: Invalid format. Using html as default.");
				doc = Parser.parseHtml(Paths.get(inputFile));
			}
			doc.save(fw);
			fw.flush();
			fw.close();
		}
	}
    
}
