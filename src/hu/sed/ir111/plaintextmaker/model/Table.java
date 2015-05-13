package hu.sed.ir111.plaintextmaker.model;

import java.util.*;
import hu.sed.ir111.plaintextmaker.*;

public class Table extends Chunk {
	private ArrayList<Integer> columnWidths = new ArrayList<Integer>();
	//private int numColumns = 0;
	
	public String toString() {
		for(Chunk child : children) {
			int[] widths = ((TableRow)child).getColumnWidths();
			for(int i = 0; i < widths.length; i++) {
				if(columnWidths.size() < i + 1) {
					columnWidths.add(widths[i]);
					//numColumns++;
				}
				else if(columnWidths.get(i) < widths[i]) {
					columnWidths.set(i, widths[i]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(EOL);
		for(Chunk child : children) {
			TableRow row = (TableRow)child;
			String str = row.toString(columnWidths);
			sb.append(str);
			sb.append(EOL);
			if(row.getIsHeader()) {
				sb.append(StringUtils.repeat('-', str.length()));
				sb.append(EOL);
			}
		}
		sb.append(EOL);
		return sb.toString();
	}
}
