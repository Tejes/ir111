package hu.sed.ir111.plaintextmaker.gui;

import hu.sed.ir111.plaintextmaker.Parser;
import hu.sed.ir111.plaintextmaker.model.Document;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.*;

/**
 * 
 * @author Dinnyes Balazs
 * 
 *         A konvertert indito panel a programhoz
 * 
 */

@SuppressWarnings("serial")
public class FoPanel extends JPanel implements ActionListener {
	ButtonGroup group = new ButtonGroup();
	static JDialog dialog;
	private final String wiki = "wiki";
	private final String html = "html";
	private final String md = "md";
	Logok lo;
	final static int convNum = 3;
	JButton button;
	JRadioButton[] r;
	public static String command;
	private JFileChooser fcs1;
	String defIn = "input.html";
	String defOut = "output.txt";
	JLabel jl;
	public File f;

	public FoPanel() {

		JPanel panel = createPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
		add(panel, BorderLayout.CENTER);
		fcs1 = new JFileChooser();
		fcs1.setCurrentDirectory(new File("."));
		fcs1.setFileFilter(new JavaFilter());
	}

	private JPanel createPanel() {
		jl = new JLabel("Start converting");
		// jl.setForeground(Color.GREEN);
		r = new JRadioButton[convNum];
		for (int i = 0; i < convNum; i++) {
			r[i] = new JRadioButton("");
		}
		curr();
		r[0].setActionCommand(wiki);
		r[1].setActionCommand(html);
		r[2].setActionCommand(md);

		for (int i = 0; i < r.length; i++) {
			group.add(r[i]);
		}
		r[0].setSelected(true);
		button = new JButton("Convert open");
		button.addActionListener(this);
		JPanel box = new JPanel();
		lo = new Logok("src/fold2.gif", 1);
		lo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
		// box.add(lo, BorderLayout.NORTH);
		for (int i = 0; i < r.length; i++) {
			box.add(r[i]);
		}
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(box, BorderLayout.NORTH);
		pane.add(button, BorderLayout.SOUTH);
		pane.add(jl);
		return pane;
	}

	private void curr() {
		r[0].setText("Convert from wiki ");
		// r[0].setForeground(new Color(0,40, 120));
		r[1].setText("Convert from html ");
		// r[1].setForeground(new Color(120,0,40));
		r[2].setText("Convert from markdown ");
		// r[2].setForeground(new Color(0,120,40));
	}

	private void open(String command) {
		boolean bool = true;
		File sfs1 = fcs1.getSelectedFile();

		jl.setForeground(Color.BLUE);
		if (sfs1 != null) {
			try {
				String format = "undef";
				String inputFile = sfs1.getAbsolutePath();
				System.out.println(inputFile);

				String outputFile = sfs1.getName();
				int pont = outputFile.lastIndexOf(".");
				outputFile = outputFile.substring(0, pont);
				outputFile = "out_" + outputFile + ".txt";
				sfs1.getName().toLowerCase().endsWith("." + command);
				if (inputFile.endsWith(".html")) {
					format = "html";
				} else if (inputFile.endsWith(".wiki")) {
					format = "wiki";
				} else if (inputFile.endsWith(".md")) {
					format = "markdown";
				} else {
					bool = false;
					JOptionPane
							.showMessageDialog(
									this,
									"File megnyitási hiba: Nem engedelyezett file formatum. Az input.html hasznalva.");
					format = "html";
					inputFile = defIn;
					outputFile = defOut;
				}
				System.out.println(outputFile);
				FileWriter fw = new FileWriter(outputFile);
				Document doc = null;
				if (format.equals("html")) {
					doc = Parser.parseHtml(Paths.get(inputFile));
				} else if (format.equals("wiki")) {
					doc = Parser.parseWiki(Paths.get(inputFile));
				} else if (format.equals("markdown")) {
					doc = Parser.parseMarkdown(Paths.get(inputFile));
				}
				doc.save(fw);
				fw.flush();
				fw.close();
				if (bool)
					jl.setText("done, in:[" + sfs1.getName() + "] out:["
							+ outputFile + "]");
				else
					jl.setText("done, in:[" + defIn + "] out:[" + defOut + "]");
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this,	"File megnyitási hiba, az input.html mint alapértelmezett file, legyen mindig meg!");
			}
		}
	}// end open

	public void actionPerformed(ActionEvent e) {
		command = group.getSelection().getActionCommand();
		if (fcs1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			open(command);
		}
	}
}// end foPanel