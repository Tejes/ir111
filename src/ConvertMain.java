import hu.sed.ir111.plaintextmaker.gui.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;

@SuppressWarnings("serial")
public class ConvertMain extends JFrame implements MouseListener
{
	JWindow win;
	public ConvertMain()
	{
		Logok lo;
		Logok lo2;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(300,200,screenSize.width, screenSize.height);
		setSize(300,250);
		setTitle("FileConverter 2.1");
		FoPanel FoP= new FoPanel();
		getContentPane().add(FoP);
		//FoP.setVisible(true);
		JPanel JPkoz=new JPanel();
		JPkoz.setLayout(new GridLayout(1,1,10,10));
		JPkoz.add(lo=new Logok("src/wikilogo.JPG",0));
		JPkoz.add(new Logok("src/htmllogo.jpg",0));
		JPkoz.add(lo2 = new Logok("src/marklogo.JPG",0));
		lo.setVisible(false);
		lo2.setVisible(false);
		setVisible(true);
		}

	public void mouseClicked(MouseEvent ev)
	{
		if(ev.getSource()==win)
		{

		win.setVisible(false);
		}
	}

 	public static void main(String args[])
 	{
		new ConvertMain();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}// class ConvertMain