package hu.sed.ir111.plaintextmaker.gui;
import javax.swing.*;  
import java.awt.*;

/**
 * 
 * @author Dinnyés Balázs
 * a Designer elemek a foPanelhez(kepek, amimaciok)
 * 
 */

@SuppressWarnings("serial")
public class Logok extends JPanel
{
	int bool;
	private Image img;
	private MediaTracker tr;
	public Logok(String fileName,int bool)
	{
		this.bool=bool;
		img= Toolkit.getDefaultToolkit().createImage(fileName);
		tr=new MediaTracker(this);
		tr.addImage(img,0);
		try
		{
			tr.waitForID(0);
		}
		catch (InterruptedException ex)
		{
		}
		finally
		{
			tr.removeImage(img,0);
		}
	}
	protected void paintComponent(Graphics gr)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		super.paintComponent(gr);
		if(bool==0)
			gr.drawImage(img,0,0,screenSize.width/3-60,screenSize.height/3-60,this);
		else
			gr.drawImage(img,0,0,screenSize.width/20,screenSize.height/15,this);
	}
}//end class Logok