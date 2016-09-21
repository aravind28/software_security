import java.math.BigInteger;
import java.util.Random;
import java.util.StringTokenizer;

public class elGamalMath {
	private BigInteger p,g,a,y, y1,y2;
	private Random r;

	public elGamalMath(){
		r = new Random();
		p = new BigInteger("14893003337626352152463254152616458181260144281");
		g = new BigInteger("4893003337626352152463254152616458181260144281");
		a = new BigInteger("843900337326351225463254152616458181260144281");
		y = g.modPow(a,p);
		y1 = new BigInteger("0");
		y2 = new BigInteger("0");
	}
	
	public BigInteger randomPrime(int i){
		return y1;
		//randomPrime(i,30);
	}
	//encrypts with built in public key
	public void encrypt(BigInteger m){
		//System.out.println("m: " + m.toString());
		//constructs a random bit integer k with any number of bits from 3 to 1 less than p
		BigInteger k = 
				randomPrime(p.bitCount()-(int)(3 + Math.random()*p.bitCount()));
		
		y1 = g.modPow(k,p);
		y2 = m.xor(y.modPow(k,p));
 	   //System.out.println("y1: " + y1);
		//System.out.println("y2: " + y2);
	}
	public BigInteger decrypt(BigInteger[] b){
		return b[1].xor(b[0].modPow(a,p));
	}

	//version of encrypt which takes a new public key
	/*public String bigEncrypt(String message,BigInteger g, BigInteger y, BigInteger p){
		this.g = g;
		this.y = y;
		this.p = p;
		return bigEncrypt(message);
	}*/

	public String bigEncrypt(String message,String key){
		StringTokenizer st = new StringTokenizer(key,"(),");
		p = new BigInteger(st.nextToken());
		g = new BigInteger(st.nextToken());
		y = new BigInteger(st.nextToken());
		return bigEncrypt(message);
	}


	//version of encrypt which uses the default public key   
	public String bigEncrypt(String message){
		byte[]b = message.getBytes();
		BigInteger[][] cipher = new BigInteger[b.length][2];
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<b.length;i++){
			encrypt(new BigInteger(""+b[i]+"")); 
			cipher[i][0]= y1;
			cipher[i][1]= y2;
		}
		for(int i=0;i<b.length;i++){
			sb.append("(");
			sb.append(cipher[i][0]);
			sb.append(",");
			sb.append(cipher[i][1]);
			sb.append(")");
		}
		return (new String(sb));
		
	}
	public String bigDecrypt(String c){
		StringTokenizer st = new StringTokenizer(c,"(),");
		BigInteger[] temp = new BigInteger[2];
		StringBuffer plain= new StringBuffer();
		while (st.hasMoreTokens()){
			temp[0]=new BigInteger(st.nextToken());
			temp[1]=new BigInteger(st.nextToken());
			plain.append((char)(decrypt(temp)).intValue());
		}
		return new String(plain);   
	}
											  
											  
	public static void main(String args[]){
		elGamalMath e = new elGamalMath();
		String k = "14893003337626352152463254152616458181260144281,"
				+ "4893003337626352152463254152616458181260144281,"
				+ "5260810279682188795512623296546807031696158558";
		String temp = e.bigEncrypt("my message",k);
 	   System.out.println(temp);
		//System.out.println(e.bigDecrypt(temp));
		//BigInteger m = new BigInteger("180");
		//String mess="this is my message";
		//System.out.println("Encrypting: " + mess );
		//System.out.println("ciphertext: " + e.bigEncrypt(mess));
		//BigInteger[] temp = e.encrypt(m,k);
		//BigInteger i = e.decrypt(temp);
		//BigInteger g = new BigInteger("4893003337626352152463254152616458181260144281");
		//BigInteger a = new BigInteger("843900337326351225463254152616458181260144281");
		//BigInteger p = new BigInteger("14893003337626352152463254152616458181260144281");
		//System.out.println("y: " + (g.modPow(a,p)).toString());
	}
	
	
}
