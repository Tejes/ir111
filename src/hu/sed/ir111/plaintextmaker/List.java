package hu.sed.ir111.plaintextmaker;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

public class List extends Chunk {
	private ListKind kind;
	
	public List(ListKind kind) {
		super();
		this.kind = kind;
	}
	
	public String toString() {
		int i = 1;
		StringBuilder sb = new StringBuilder();
		String roman;
		int maxLength = 0;
		
		if(kind == ListKind.LOWER_ROMAN || kind == ListKind.UPPER_ROMAN) {
			for (Iterator<Chunk> iterator = children.iterator(); iterator.hasNext();) {
				iterator.next();
				roman = makeRomanNumber(i++);
				if(maxLength < roman.length())
					maxLength = roman.length();
			}
			i = 1;
		}
		else if(kind == ListKind.NUMERAL) {
			maxLength = (int)Math.log10(children.size()) + 1;
		}
		
		for(Chunk child : children) {
			switch(kind) {
				case LOWER_ROMAN:
					roman = makeRomanNumber(i).toLowerCase();
					sb.append(StringUtils.repeat(' ', maxLength - roman.length()));
					sb.append(roman);
					sb.append('.');			
					break;
				case UPPER_ROMAN:
					roman = makeRomanNumber(i).toUpperCase();
					sb.append(StringUtils.repeat(' ', maxLength - roman.length()));
					sb.append(roman);
					sb.append('.');	
					break;
				case NUMERAL:
					sb.append(StringUtils.repeat(' ', maxLength - String.valueOf(i).length()));
					sb.append(i);
					sb.append('.');
					break;
				case LOWERCASE:
					sb.append((char)('a' + i - 1));
					sb.append(')');
					break;
				case UPPERCASE:
					sb.append((char)('A' + i - 1));
					sb.append(')');
					break;
				case ASTERISK:
					sb.append('*');
					break;
				case NONE:
					sb.append(' ');
					break;
				default:
					break;
			}
			sb.append(' ' + child.toString() + EOL);
			i++;
		}
		
		return sb.toString();
	}
	
	private static String makeRomanNumber(int number){
		String result = "";
		while (number>=100){
			result+="C";
			number-=100;
		}
		while (number>=90){
			result+="XC";
			number-=90;
		}
		while (number>=50){
			result+="L";
			number-=50;
		}
		while (number>=40){
			result+="XL";
			number-=40;
		}
		while (number>=10){
			result+="X";
			number-=10;
		}
		while (number>=9){
			result+="IX";
			number-=9;
		}
		while (number>=5){
			result+="V";
			number-=5;
		}
		while (number>=4){
			result+="IV";
			number-=4;
		}
		while (number>=1){
			result+="I";
			number-=1;
		}
		return result;
	}

	public void setKind(ListKind kind) {
		this.kind = kind;
	}
}
