import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;


public class RSA {

	public static String[] generateKeys(String pStr, String qStr) {
		BigInteger p = new BigInteger(pStr);
		BigInteger q = new BigInteger(qStr);
		
		BigInteger N = p.multiply(q);
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		
		String[] keys = new String[3];
		keys[0] = N.toString();
		BigInteger two = BigInteger.ONE.add(BigInteger.ONE);
		BigInteger x = p.divide(two);
		
		while(true) {
			if (N.gcd(x).equals(BigInteger.ONE) && phi.gcd(x).equals(BigInteger.ONE)) {
	            BigInteger e = x;
	            BigInteger d = e.modInverse(phi);
	            keys[1] = e.toString();
	            keys[2] = d.toString();
                break;
	        }
	        x = x.add(BigInteger.ONE);
		}
		
		return keys;
	}
	
	public static String encryptMessage(String message, String eStr, String NStr) {
		BigInteger e = new BigInteger(eStr);
		BigInteger N = new BigInteger(NStr);
		int strLen = message.length();
		BigInteger[] encryptedData = new BigInteger[strLen];
		for(int i = 0; i < strLen; i++) {
			char curChar = message.charAt(i);
			long curLong = (long) curChar;
			BigInteger encrypt = BigInteger.valueOf(curLong);
			BigInteger encrypted = encrypt.modPow(e, N);
			encryptedData[i] = encrypted;
		}
		
		StringBuffer dataBuffer = new StringBuffer();
		for(BigInteger bigInt : encryptedData) {
			dataBuffer.append(bigInt.toString());
			dataBuffer.append(" ");
		}
		
		return dataBuffer.toString();
	}
	
	public static String decryptMessage(String cipherText, String dStr, String NStr) {
		BigInteger d = new BigInteger(dStr);
		BigInteger N = new BigInteger(NStr);
		String[] bigIntStrings = cipherText.split(" ");
		int arrayLen = bigIntStrings.length;
		StringBuffer plainText = new StringBuffer();
		for(int i = 0; i < arrayLen; i++) {
			BigInteger cur = new BigInteger(bigIntStrings[i]);
			BigInteger decrypted = cur.modPow(d, N);
			char curChar = (char)(Integer.parseInt(decrypted.toString()));
			plainText.append(curChar);
		}
		
		return plainText.toString();
	}
	
	public static String[] generatePrimes(int bitLengthInt) {
		Random rnd = new Random();
		String[] primes = new String[2];
		primes[0] = BigInteger.probablePrime(bitLengthInt, rnd).toString();
		primes[1] = BigInteger.probablePrime(bitLengthInt, rnd).toString();
		
		return primes;
	}
	
	public static void main(String[] args) {
		//BigInteger test = 
		System.out.println();
	}
		
	
}
