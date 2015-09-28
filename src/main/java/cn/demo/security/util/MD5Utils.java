package cn.demo.security.util;



import java.security.MessageDigest;


public class MD5Utils {
	private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
		'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
		'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v'};
	/***
	 * 
	 * @param b
	 * @return
	 */
	private static String byteArrayToHexString(byte[] b) { 
		StringBuffer resultSb = new StringBuffer(); 
		for (int i = 0; i < b.length; i++) { 
			resultSb.append(byteToHexString(b[i])); 
		} 
		return resultSb.toString(); 
	} 

	/***
	 * 
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) { 
		int n = b; 
		if (n < 0) n = 256 + n; 
		int d1 = n / 16; 
		int d2 = n % 16; 
		return hexDigits[d1] + hexDigits[d2]; 
	} 

	/***
	 * 
	 * @param origin
	 * @return
	 */
	public static String MD5Encode(String origin) { 
		String resultString = null; 
		try { 
			resultString=new String(origin); 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			resultString=byteArrayToHexString(md.digest(resultString.getBytes())); 
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		} 
		return resultString; 
	}
	public static String MD5EncodeUnsigned(String origin){
		return toUnsignedString(MD5Encode(origin));
	}
	private static String toUnsignedString(String base) {
		String dest="";
		for(int i=0;i<base.length();i=i+2){
			int ten=Integer.parseInt(base.substring(i,i+2),16);
			int index=ten%32;
			dest=dest+digits[index];
		}
		return dest;
	}
}

