import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;

import com.vcl.util.LogTracer;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSAHelper {
	private static String className = "RSAHelper";
	//static int RSA_KEY_LENGTH = 4096;
	static int RSA_KEY_LENGTH = 2048;
	static String ALGORITHM_NAME = "RSA" ;
	static String PADDING_SCHEME = "OAEPWITHSHA-1ANDMGF1PADDING" ;
	static String MODE_OF_OPERATION = "ECB" ; // This essentially means none behind the scene
	public static boolean isRSAEnabled = false;

	public static void main(String args[]) {
		String shortMessage = "p@ssw0rd";

		/*try {

                byte[] privateKeyBytes = PEMFileReader.read("C:/Users/chandrakanthji/keystore/qfrestrsapriv.pem");
                byte[] publicKeyBytes = PEMFileReader.read("C:/Users/chandrakanthji/keystore/qfrestrsapub.pem");

                String publicKeystring = new BASE64Encoder().encode(publicKeyBytes);
                String privateKeyString = new BASE64Encoder().encode(privateKeyBytes);

                 String encrypted = rsaEncrypt(shortMessage,publicKeystring);
                //String encrypted = rsaEncrypt(shortMessage);
                System.out.println("Encrypted: "+ encrypted.length() + ":  "+ encrypted);

                  //encrypted= "O7RdUqTxV/3u8wxpsUfpPlwBjQwv9yxfKa/w2Wd4nppm/ZIrp5xW3H0GW0wNCQNoSQ9bqNZ7A2NEd28MwBus+Gix8GkmnFOzOL6yBks6duPHKREwG+iKhBjqoy1OXeSi/Qdl6DfEAYWxNgJY+TVdo75hO2u+iGyXakeeWQxE1E65VR1BWfq8fpgWipOpuJ+SmO0ZdLxIHs8aZYSVWISAU9STNeUO4mvxbpSskfrQQizbfxO1spQwlQW4v0tsxEMAzoRPe2ILmhcOpJDSDHorihr2iaJaqdgcUdpOR8Km37CQu94K0twO7GEwzzoGWZ2bckXT7eIe599zkLZ7JHaNafX3FsdISxv/Cj3R/gcni/OLy+9n0xHj0Eq6DksuKcJJrMEFZ4r9BpywJKAI2su/C7eLNxJSpeDESAnJe22VhHhnLgv52fw7oODnGihhsBZShAeCzWA9WoA58nJQBsDs0LQqZrxmkg18Ka64P2JR4m1RwBDXSZm7anKx3yYej48gwhEe7h28ykmG7AkKSQNJFS5CbmqmbO3B2Vlbn6BPAWj4YAD4BWrtxtTiUl42Ns7bC2ZKsSFhZhM9YMiZp1AbMSJ9i0MW2Oscj2aWyQ4mbTjmXbkrRzWOq+Yqamq6OO1y2aYUK8Qju0zDYHI4D6SJTSOEQB7fH+zv3lh7qQCdtuQ=";
                //String encrypted= "mXpaJ2VN29cs88zPpumee6LeaMXrJ7tba+K8v+Zbo4EbgQ84drPSLwKvNkxrV8ycJJXW1TtNpKJ2Y6I+OLsEpCDJzfaJY1fkmKDXujEUR4/pqJ7wxNUK0aZ/FCoNC3v3mUoXM+jFc7VRzyOD3yjJC33IKdIdsSafCqB4tyMFTsryfoEcx2BndLfH0iV/8E54xfKeydfE7lkUYoGmDUg0PiQW62j2ymFYPue8ZKcmGEsUdQrKDzJEIlsXIJe95sfhHc/cMM5ZJTiHZctlbB0A/i1J0jKTefaulxKYcBo/+yC8sz15ycmPGqLMdN4CrkB1BdJpIayvYypxjmei6h8Qnw==";
                  String decrypted =  rsaDecrypt(new BASE64Decoder().decodeBuffer(encrypted),privateKeyString);
                 //String decrypted =  rsaDecrypt(encrypted);
                  System.out.println("Decrypted: " + decrypted);


                } catch(Exception e) {System.out.println("Exception while encryption/decryption") ;e.printStackTrace() ; }*/
		//shortMessage ="W8qAKRPypj/fZq7sYMYOjHEXcnQGJiJ09h1pRTrQOsDGoEtH3cegI/ey63sFvTzAufxmWJRSLlUGSMkXXjcHaMWtM/NcLwiL06g0QtpWvFexTzNnZXw9c0mFy0O3nuDpmfeu7dmpScSULncpRpOsrwZPet2rco0TZw4KksqpYW2vVg2rtM+HJSY6TJLp+L5GTQLx88/pBbbDDzV8iuKxmB0DpEr2uv4bv8rZvpGVS3Jxmzj8O1DfvXRwYlZQic4iUUSKAN02lfKpnCLhNDwqX03lhrSR4S5tpa8VGlGvu0RxAj9jsI92ccYkl4+qxiyOVrSVDbg7TMYBFpJZC2X3bQ==";
		//shortMessage ="0775e1da8c1858f179876bd36a77e0f3::b79b348586c876736d88a8c814fc2ddd::mSXhesY0LaYUbLO1ebDSRQ==";
		try{
			//System.out.println("PlainText---##################--->"+shortMessage);
			//System.out.println("server---##################--->"+System.getProperty("jboss.server.home.dir"));
			String val = getPlainValue(shortMessage);
			//System.out.println("password--######-->"+val);
		}catch(Exception e) {System.out.println("Exception while encryption/decryption") ;e.printStackTrace() ; }
	}

	public static String getPlainValue(String val){
		String decrypted = "";
		String encrypted = "";
		try {

			//byte[] privateKeyBytes = new BASE64Decoder().decodeBuffer(getPrivateKey());
			byte[] privateKeyBytes = PEMFileReader.read("C:/Users/chandrakanthji/keystore/qfrestrsapriv.pem");
			byte[] publicKeyBytes = PEMFileReader.read("C:/Users/chandrakanthji/keystore/qfrestrsapub.pem");
			//byte[] publicKeyBytes = new BASE64Decoder().decodeBuffer(getPublicKey());

			String publicKeystring = new BASE64Encoder().encode(publicKeyBytes);
			String privateKeyString = new BASE64Encoder().encode(privateKeyBytes);

			//encrypted = rsaEncrypt(val,publicKeystring);
			//System.out.println("Encrypted--####length####-->"+ encrypted.length()+"--##String##-->"+ encrypted);

			//encrypted= "O7RdUqTxV/3u8wxpsUfpPlwBjQwv9yxfKa/w2Wd4nppm/ZIrp5xW3H0GW0wNCQNoSQ9bqNZ7A2NEd28MwBus+Gix8GkmnFOzOL6yBks6duPHKREwG+iKhBjqoy1OXeSi/Qdl6DfEAYWxNgJY+TVdo75hO2u+iGyXakeeWQxE1E65VR1BWfq8fpgWipOpuJ+SmO0ZdLxIHs8aZYSVWISAU9STNeUO4mvxbpSskfrQQizbfxO1spQwlQW4v0tsxEMAzoRPe2ILmhcOpJDSDHorihr2iaJaqdgcUdpOR8Km37CQu94K0twO7GEwzzoGWZ2bckXT7eIe599zkLZ7JHaNafX3FsdISxv/Cj3R/gcni/OLy+9n0xHj0Eq6DksuKcJJrMEFZ4r9BpywJKAI2su/C7eLNxJSpeDESAnJe22VhHhnLgv52fw7oODnGihhsBZShAeCzWA9WoA58nJQBsDs0LQqZrxmkg18Ka64P2JR4m1RwBDXSZm7anKx3yYej48gwhEe7h28ykmG7AkKSQNJFS5CbmqmbO3B2Vlbn6BPAWj4YAD4BWrtxtTiUl42Ns7bC2ZKsSFhZhM9YMiZp1AbMSJ9i0MW2Oscj2aWyQ4mbTjmXbkrRzWOq+Yqamq6OO1y2aYUK8Qju0zDYHI4D6SJTSOEQB7fH+zv3lh7qQCdtuQ=";
			//String encrypted= "mXpaJ2VN29cs88zPpumee6LeaMXrJ7tba+K8v+Zbo4EbgQ84drPSLwKvNkxrV8ycJJXW1TtNpKJ2Y6I+OLsEpCDJzfaJY1fkmKDXujEUR4/pqJ7wxNUK0aZ/FCoNC3v3mUoXM+jFc7VRzyOD3yjJC33IKdIdsSafCqB4tyMFTsryfoEcx2BndLfH0iV/8E54xfKeydfE7lkUYoGmDUg0PiQW62j2ymFYPue8ZKcmGEsUdQrKDzJEIlsXIJe95sfhHc/cMM5ZJTiHZctlbB0A/i1J0jKTefaulxKYcBo/+yC8sz15ycmPGqLMdN4CrkB1BdJpIayvYypxjmei6h8Qnw==";
			//anil - encrypted = "jrbN7qfyXZLEFHL0tKzRFrolwPWAusdZd3rywijanE6PpvRlMbpnd5E++qrxGn3pPgSRESANyyV4Eu5bjZqxhlqpJmM3ZLenJEZP6yesUVHxsN/uA3oywzauJJzVeW6n8onIKr6w+uNdxBgWzELlQMc8eLqZ/dZoTEs5mssimQ03lhn6FZFQiwRZdsXkUgC69uzTCRU+qTUQvRVXmVsbgQ/zrMaPY17Vz6lNvowXISEgpVfLG8vUWxGZGtWPDJd1gEucTKkUUoVtQwDGw17ig9N9H+wQecA3FTkg5ke1L/1pEXWfI9Ztf8rkU3UvZcJfsuB8M1LTA2NHpLMXw1ettA==";
			//sandeep - encrypted = "Q1FR9ksfLFvCEnQIL6lGIb8aN6dzuj6NaEb6kDW1uG70PsUqBdRWmXWF7wTjDrwUjstYsxl7lZv/2yZQ7nyYjESVQjY67FYBj5fXqlx2klboRxmLSrC1HcreEHwPzM7+ZAc14SxqtacJj/pPYFXW85VY92S0H8gceMmHZ+FRYTJ3OhkUrHjrJz1EvC/qHjVd4nPAGEL3FgkqUgnkfghcDnSKg1CfNLKdC25hdVfcfN9aZty/AolcSESL8Nuuhhld1aduwE29YCJyP/li8EzsapsbUnXE+0W6sOypGwNdlkiXOZdhU/dBpvHO+6G4iS8mHnR3ThKdRNkb/IiZDAxEjA==";
			//siva - encrypted = "H7gpmSA6JGSAeiTU2+26xZXvQkNtxXWzPSPShIJWwadHbygqwd70pCNMEQbf0pMTN8ILWy9DZ7XYwYXLkdXznQHj3HK8BdidTUVGSRFf8hPBuW2zLcTTpvVJ0BQ+8UYcZPJVwtSD2WPJV2UeJzsYPjim/sWMLqcLf2JHvAIA3DBPUVFXWJBLF0HtVEVU+4eds8tRiHiN0/MFDvjoaAyzuvzpz527CKFXy0KR7MPq/qN1jZXhls2vCNlfK3sS3GuYx+tDTb+BhSkifFUDHVnETMtchPNnQEcXcJQ5MCr9kEHJUO1YcljQl4gYJl7vQTYXcuHegv3yD7AeFxgDUS782A==";
			//encrypted = "L04Uu+cY/ZBn/mR8S+WuO+6Ph6Y4oTZRekTZ1gFzmJ5FtQd6Y8LOGFCwjPY0FFyZBEbSwooMFVl43znnMRYJl6L0txaMbh5LuMHv2Nk5+qSHXD5nPveU3tYbcsqeRWa/zSM+sIzFxgKtYNeh/+20XC209OJvaHPWSGObFkuWKZ2h36xUnYOk9DAda34QGN4hWpKAAa+HmLA0MyJzoP9Utq2tSB8GxwpGcGTxBWDpMkl7y9e0o+EJeR0LEikphVKxApnDinlGc7WyJcJTfvmNtJyhmoZotaOu8HvjvpY/b/RnvXqtZl5gVG/q8f1iKNwLIREYhcIt5YPiY4uGX5b39A==";
			//encrypted = "J2Zeb9KPQrEY8GFJ0Y6fVTk/nR8HnJ7+cgxkQtgxcEyMkwlDevDsnC3v9frQAUScnFbhXObCHXZCR8+jwM1KKXxm+lo42YMx1ZJpOeFiLTYOr0DdMr6yTVKOrDN6nyl+0raZbMyuExP8JjNkI62MFj3I23BFQKx+Jivhb+GLLfA=";
			//encrypted = "VMcw9JQ0DjiPCFIkfFbZVj+SU1MBHaHGLVxXPzBBDiHDaOK+9R3giiOkOT/EgZPZQn5qTfEAXTvvxwAtqzKEa5M2UZbvMuzjZbpFyXGQ1rWfqgJ6BZ84a4v46MWupGlNlzIVUd22yEtcSxkJJ7lw5Q44eeqAl9jZC1YdXsIs3e3vDuJGrbD5YoJ8iL1yq322kJsL+DB3MUh/NPsfJKnorA6fqluDtuslZF+Eqr0/Xge3Q6/n1YgYzVIgIYNxSiNZahNRrk7VU2Y9ul9SW2ILScsSzg+aqvzmgN6oZcDGPse2JXByFrazX4W3oFTZ9yOxrugnsL/wa7Wg9EFobNJY7Q==";
			encrypted = "P6P8FsMFeLa+wAwVvX1Ab9RstVTdNZOxLq1lkSwNn3jsQmjWP+0gcHT7rdGsnKSPrXK9HmOljkdPEdpbwN9EjTUPhmY1v0cbGMK8f/69s4HhLrXAZ3CuUJnC7SqUUnLz/jfDxjH3M/WJ/znAXFw8Hdk0Mxc2ef5C0QzmLZnp9X8FH1S2IhgFrxMGXGrY42nDoGEYjbRlmZeHHh6RK62gUbHvXbaWfCtZo5u1BF+QtvBlpWcyBACVNEQ5oGAcW5LxxjdsiwtrGUWMniLR4RaKMGx2zqOhybGWNhzg+4QdMRyKRS1gKty1SnnRSDbxO4zEn2OTJjguFXXZ+kSILylggA==";


			System.out.println("encrypted string--##########-->"+encrypted);
			decrypted =  rsaDecrypt(new BASE64Decoder().decodeBuffer(encrypted),privateKeyString);
			//String decrypted =  rsaDecrypt(encrypted);
			//System.out.println("Decrypted and plain--##########-->"+decrypted);


		} catch(Exception e) {
			//System.out.println("Exception while encryption/decryption") ;
			//e.printStackTrace() ; 
			LogTracer.writeExceptionLog(className, "getPlainValue", e);
		}  
		return decrypted;
	}
	public static String getEncryptedVal(String val){
		String methodName = "getEncryptedVal";
		LogTracer.writeDebugLog(className, methodName, "Start");
		String encryptedVal = "";
		try{
			byte[] publicKeyBytes = new BASE64Decoder().decodeBuffer(getPublicKey());
			String publicKeystring = new BASE64Encoder().encode(publicKeyBytes);
			encryptedVal = rsaEncrypt(val,publicKeystring);
		}catch(Exception e) {
			//System.out.println("Exception while encryption/decryption") ;
			//e.printStackTrace() ; 
			LogTracer.writeExceptionLog(className, "getEncryptedVal", e);
			
		}  
		LogTracer.writeDebugLog(className, methodName, "End");
		return encryptedVal;
	}
	public static String getDecryptedVal(String val){
		String methodName = "getDecryptedVal";
		LogTracer.writeDebugLog(className, methodName, "Start");
		String decrypted = "";
		try {
			byte[] privateKeyBytes = new BASE64Decoder().decodeBuffer(getPrivateKey());

			String privateKeyString = new BASE64Encoder().encode(privateKeyBytes);
			
			decrypted =  rsaDecrypt(new BASE64Decoder().decodeBuffer(val),privateKeyString);
			//LogTracer.writeSOP("decrypted:--###############-->"+decrypted);
		} catch(BadPaddingException e) {
			//System.out.println("Exception while encryption/decryption") ;
			//e.printStackTrace() ; 
			//LogTracer.writeExceptionLog(className, "getDecryptedVal", e);
		}  catch(Exception e) {
			//System.out.println("Exception while encryption/decryption") ;
			//e.printStackTrace() ; 
			LogTracer.writeExceptionLog(className, "getDecryptedVal", e);
		}  
		LogTracer.writeDebugLog(className, methodName, "End");
		return decrypted;
	}
	private static final String getPrivateKey() throws FileNotFoundException, IOException{

		byte[] privateKeyBytes = PEMFileReader.read(System.getProperty("jboss.server.home.dir")+File.separator+"conf"+File.separator+"qfrestrsapriv.pem");
		//byte[] privateKeyBytes = PEMFileReader.read("C:/Users/chandrakanthji/keystore/qfrestrsapriv.pem");
		return new BASE64Encoder().encode(privateKeyBytes);
	}

	private static final String getPublicKey() throws FileNotFoundException, IOException{

		byte[] publicKeyBytes = PEMFileReader.read(System.getProperty("jboss.server.home.dir")+File.separator+"conf"+File.separator+"qfrestrsapub.pem");
		//byte[] publicKeyBytes = PEMFileReader.read("C:/Users/chandrakanthji/keystore/qfrestrsapub.pem");
		return new BASE64Encoder().encode(publicKeyBytes);
	}

	public static String rsaEncrypt(String message, Key publicKey) throws Exception {

		Cipher c = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME) ;

		c.init(Cipher.ENCRYPT_MODE, publicKey) ;

		byte[] cipherTextArray = c.doFinal(message.getBytes()) ;

		return new BASE64Encoder().encode(cipherTextArray) ;

	}


	public static String rsaDecrypt(byte[] encryptedMessage, Key privateKey) throws Exception {
		Cipher c = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME) ;
		c.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] plainText = c.doFinal(encryptedMessage);

		return new String(plainText) ;

	}


	public static String rsaEncrypt(String message) {

		byte[] cipherTextArray = null;

		try {

			byte[] publicBytes = new BASE64Decoder().decodeBuffer(getPublicKey());
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME) ;
			PublicKey pubKey = keyFactory.generatePublic(keySpec);

			Cipher c = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME) ;

			c.init(Cipher.ENCRYPT_MODE, pubKey) ;

			cipherTextArray = c.doFinal(message.getBytes()) ;

		} catch (Exception e) {
			//e.printStackTrace();
			LogTracer.writeExceptionLog(className, "rsaEncrypt", e);
		}

		return org.apache.commons.codec.binary.Base64.encodeBase64(cipherTextArray).toString() ;
	}

	public static String rsaEncrypt(String message, String pubKeyStr) throws Exception {
		byte[] publicBytes = new BASE64Decoder().decodeBuffer(pubKeyStr);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME) ;
		PublicKey pubKey = keyFactory.generatePublic(keySpec);

		Cipher c = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME) ;

		c.init(Cipher.ENCRYPT_MODE, pubKey) ;

		byte[] cipherTextArray = c.doFinal(message.getBytes()) ;

		return new BASE64Encoder().encode(cipherTextArray) ;            
	}

	public static String rsaDecrypt(String message) {

		byte[] plainText = null;

		try {

			byte[] privateBytes = new BASE64Decoder().decodeBuffer(getPrivateKey());
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privKey = keyFactory.generatePrivate(keySpec);

			Cipher c = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME) ;
			c.init(Cipher.DECRYPT_MODE, privKey);
			plainText = c.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(message.getBytes()));

		} catch (Exception e) {
			//e.printStackTrace();
			LogTracer.writeExceptionLog(className, "rsaDecrypt", e);
		}

		return new String(plainText) ;
	}

	public static String rsaDecrypt(byte[] encryptedMessage, String priKeyStr) throws Exception {
		byte[] privateBytes = new BASE64Decoder().decodeBuffer(priKeyStr);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privKey = keyFactory.generatePrivate(keySpec);

		Cipher c = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME) ;
		c.init(Cipher.DECRYPT_MODE, privKey);
		byte[] plainText = c.doFinal(encryptedMessage);

		return new String(plainText) ;
	}


}
