package hu.sed.ir111.plaintextmaker.gui;
import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * @author Dinnyés Balázs
 *
 *A cim, infok a szerkesztokrol
 *
 */

@SuppressWarnings("serial")
public class StrCim extends JPanel
{
	private String St;
	private Color color;
	public StrCim(Color color,String St)
	{
		this.color=color;
		this.St=St;
	}
	protected void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		gr.setColor(color);
		gr.setFont(new Font("TimesRoman",Font.ITALIC,50));
		gr.drawString(St,0,100);
	}
}//end class StrCim