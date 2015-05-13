package hu.sed.ir111.plaintextmaker.model;
import hu.sed.ir111.plaintextmaker.StringUtils;

import java.util.*;


public class TableRow extends Chunk {
	
	public TableRow() {
		super();
	}
	
	public String toString()   {
		return "Egy táblázat sorának kiíratásához szükség van az oszlopok szélességére";
	}
	
	public String toString(ArrayList<Integer> columnWidths) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for(Iterator<Chunk> it = children.iterator(); it.hasNext(); i++) {
			String col = it.next().toString();
			sb.append(" ");
			sb.append(col);
			sb.append(StringUtils.repeat(" ", columnWidths.get(i) - col.length() + 1));
		}
		return sb.toString();
	}
	
	public boolean getIsHeader() {
		for(Chunk child : children) {
			if(((TableCell)child).getIsHeader())
				return true;
		}
		return false;
	}
	
	public int[] getColumnWidths() {
		int[] ret = new int[children.size()];
		int i = 0;
		for(Iterator<Chunk> it = children.iterator(); it.hasNext(); i++) {
			ret[i] = it.next().toString().length();
		}
		return ret;
	}
}
