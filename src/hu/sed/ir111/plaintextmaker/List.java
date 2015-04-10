package hu.sed.ir111.plaintextmaker;

public class List extends Chunk {
	private ListKind kind;
	
	private String[] romaiak = new String[] {"i", "ii", "iii"};
	
	public List(ListKind kind) {
		super();
		this.kind = kind;
	}
	
	public String toString() {
		int i = 1;
	}
	
	private String getRomai(int number){
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
	
	
}
