package hu.sed.ir111.plaintextmaker;

/**
 * String utils. Not instantiable
 * @author keletim
 *
 */
public abstract class StringUtils {	
	public static String repeat(String str, int n) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			sb.append(str);
		}
		return sb.toString();
	}
	
	public static String repeat(char chr, int n) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			sb.append(chr);
		}
		return sb.toString();
	}
}
