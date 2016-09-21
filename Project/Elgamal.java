import java.math.BigInteger;
import java.util.Random;


public class ElGamal {
	private static BigInteger p = new BigInteger("14893003337626352152463254152616458181260144281");
	private static BigInteger alpha = new BigInteger("4893003337626352152463254152616458181260144281");
	//private static BigInteger a = new BigInteger("843900337326351225463254152616458181260144281");
	//private static BigInteger beta = alpha.modPow(a,p);

	public static String[][] encryptData(String message, String alphaStr, String betaStr, String pStr) {
		BigInteger alpha = new BigInteger(alphaStr);
		BigInteger beta = new BigInteger(betaStr);
		BigInteger p = new BigInteger(pStr);

		int randomBitLength = p.bitCount() / 2;
		int strLen = message.length();
		String[][] encryptedData = new String[2][strLen];
		Random r = new Random();
		for(int i = 0; i < strLen; i++) {
			char curChar = message.charAt(i);
			long curLong = (long) curChar;
			BigInteger encrypt = BigInteger.valueOf(curLong);
			BigInteger k = new BigInteger(randomBitLength, r);
			BigInteger y1 = alpha.modPow(k, p);
			BigInteger y2 = (encrypt.multiply(beta.modPow(k, p))).mod(p);
			encryptedData[0][i] = y1.toString();
			encryptedData[1][i] = y2.toString();
		}

		return encryptedData;
	}
	
	public static String[] generateKeys() {
		String[] keys = new String[4];
		keys[0] = alpha.toString();
		int aBitCount = p.bitCount() - 10;
		BigInteger a = BigInteger.probablePrime(aBitCount, new Random());
		keys[1] = alpha.modPow(a,p).toString();
		keys[2] = p.toString();
		keys[3] = a.toString();
		
		return keys;
	}
	
	public static String decryptData(String[][] encryptedData, String aStr, String pStr) {
		BigInteger p = new BigInteger(pStr);
		BigInteger a = new BigInteger(aStr);
		StringBuffer plainText = new StringBuffer();
		int strLen = encryptedData[0].length;
		for(int i = 0; i < strLen; i++) {
			BigInteger y1 = new BigInteger(encryptedData[0][i]);
			BigInteger y2 = new BigInteger(encryptedData[1][i]);
			BigInteger message = (y2.multiply(y1.modPow(a, p).modInverse(p))).mod(p);
			char curChar = (char)(Integer.parseInt(message.toString()));
			plainText.append(curChar);
		}
		
		return plainText.toString();
	}
	
}
