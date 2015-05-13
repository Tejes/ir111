package hu.sed.ir111.plaintextmaker.gui;
import java.io.File;

/**
 * A file valaszto ablakhoz a filter class 
 * 
 */

import javax.swing.filechooser.FileFilter;

   	class JavaFilter extends FileFilter{
		public boolean accept(File f)
		{
			return f.getName().toLowerCase().endsWith("."+FoPanel.command) || f.isDirectory();
		}

		public String getDescription()
		{
			return FoPanel.command+"-allomanyok";
		}
	}//end JavaFilter