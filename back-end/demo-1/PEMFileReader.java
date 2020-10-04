import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.Key;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.bouncycastle.util.io.pem.PemReader;


public class PEMFileReader {
	
	public static void write(Key key, String description,String filename) throws FileNotFoundException, IOException {
		PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)));
		try {
			PemObject pemObject = new PemObject(description, key.getEncoded());
			pemWriter.writeObject(pemObject);
		} finally {
			pemWriter.close();
		}
	}
	
	public static byte[] read(String filename) throws FileNotFoundException, IOException {
		PemObject pemObject = null;
		byte[] retVal = null;
		PemReader pemReader = new PemReader(new InputStreamReader(new FileInputStream(filename)));
		try {
			pemObject = pemReader.readPemObject();
			pemObject.getContent();
		} finally {
			pemReader.close();
		}
		if(pemObject != null)
			retVal =pemObject.getContent();
		return retVal;
	}
}
