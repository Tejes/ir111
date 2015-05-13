package hu.sed.ir111.plaintextmaker.model;

public class TableCell extends Chunk {
	private boolean isHeader = false;
	
	public TableCell() {
		super();
	}
	
	public TableCell(boolean header) {
		super();
		isHeader = header;
	}
	
	public TableCell(String text) {
		super(text);
	}
	
	public boolean getIsHeader()  {
		return isHeader;
	}
}
