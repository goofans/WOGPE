package me.WatchingYoutube.ParticleEditor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.TransformerException;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.w3c.dom.Document;

public class AESBin {
	
	private static final Logger log = Logger.getLogger(AESBin.class.getName());
	
	private static final byte[] KEY = {0x0D, 0x06, 0x07, 0x07, 0x0C, 0x01, 0x08, 0x05,
	          0x06, 0x09, 0x09, 0x04, 0x06, 0x0D, 0x03, 0x0F,
	          0x03, 0x06, 0x0E, 0x01, 0x0E, 0x02, 0x07, 0x0B};
	
	private static final byte EOF_MARKER = (byte) 0xFD;

	public static byte[] decryptFile(File file) throws IOException {
		byte[] inputBytes = FileUtil.readFile(file);
		return decrypt(inputBytes);
	}
	
	private static byte[] decrypt(byte[] inputBytes) throws IOException
	  {
	    int inputSize = inputBytes.length;

	    BufferedBlockCipher cipher = getCipher(false);

	    byte[] outputBytes = new byte[cipher.getOutputSize(inputSize)];

	    int outputLen = cipher.processBytes(inputBytes, 0, inputSize, outputBytes, 0);

	    try {
	      outputLen += cipher.doFinal(outputBytes, outputLen);
	    }
	    catch (InvalidCipherTextException e) {
	      log.log(Level.SEVERE, "Can't decrypt file", e);
	      throw new IOException("Can't decrypt file: " + e.getLocalizedMessage());
	    }

	    
	    for (int i = 0; i < outputLen; ++i) {
	      byte b = outputBytes[i];
	      if (b == EOF_MARKER) {
	        log.finer("Skipped " + (outputLen - i) + " bytes at the end (old size " + outputLen + ", new size " + i + ")");
	        StringBuilder sb = new StringBuilder("[");
	        for (int j = i; j < outputLen; ++j) {
	          sb.append(' ').append(byteToHex(outputBytes[j]));
	        }
	        sb.append(" ]");
	        log.finer("Skipped bytes: " + sb);
	        outputLen = i;
	        break;
	      }
	    }
		
	    int start = 0;


//	    return new String(outputBytes, start, outputLen - start, CHARSET);
	    byte[] finalBytes = new byte[outputLen - start];
	    System.arraycopy(outputBytes, start, finalBytes, 0, outputLen);

	    return finalBytes;
	  }
	
	public static byte[] encryptFile(File file) throws IOException
	  {
	    byte[] inputBytes = encrypt(FileUtil.readFile(file));
	    return inputBytes;
	  }
	
	private static byte[] encrypt(byte[] inputBytes) throws IOException
	  {

	    /* If input was multiple of 16, NO padding. Example: res\levels\BulletinBoardSystem\BulletinBoardSystem.level.bin */
	    /* Otherwise pad to next 16 byte boundary */

	    int origSize = inputBytes.length;
	    if (origSize % 16 != 0) {
	      int padding = 16 - origSize % 16;

	      int newSize = origSize + padding;

	      log.finer("Size " + origSize + " padded with " + padding + " bytes to make " + newSize);

	      byte[] newInputBytes = new byte[newSize];
	      System.arraycopy(inputBytes, 0, newInputBytes, 0, origSize);
	      inputBytes = newInputBytes;
//	      inputBytes = Arrays.copyOf(inputBytes, newSize);

	      /* Write up to 4 0xFD bytes immediately after the original file. The remainder can stay as the 0x00 provided by Arrays.copyOf. */
	      for (int i = origSize; i < origSize + 4 && i < newSize; ++i) {
	        inputBytes[i] = EOF_MARKER;
	      }
	    }
	    else {
	      log.finer("Size " + origSize + " already multiple of 16, no padding");
	    }

	    int inputSize = inputBytes.length;

	    BufferedBlockCipher cipher = getCipher(true);

	    byte[] outputBytes = new byte[cipher.getOutputSize(inputSize)];

	    int outputLen = cipher.processBytes(inputBytes, 0, inputSize, outputBytes, 0);

	    try {
	      outputLen += cipher.doFinal(outputBytes, outputLen);
	    }
	    catch (InvalidCipherTextException e) {
	      log.log(Level.SEVERE, "Can't encrypt file", e);
	      throw new IOException("Can't encrypt file: " + e.getLocalizedMessage());
	    }
	    
	    int start = 0;

//	    return Arrays.copyOf(outputBytes, outputLen);
	    byte[] outputBytes2 = new byte[outputLen - start];
	    System.arraycopy(outputBytes, start, outputBytes2, 0, outputLen);

	    return outputBytes2;
	  }
	
	private static String byteToHex(byte b)
	{
		String s = Integer.toHexString(b).toUpperCase();

	    if (s.length() < 2) return "0x0" + s;
	    return "0x" + s.substring(s.length() - 2, s.length());
	}
	private static BufferedBlockCipher getCipher(boolean forEncryption)
	  {
	    BlockCipher engine = new AESEngine();
	    BufferedBlockCipher cipher = new BufferedBlockCipher(new CBCBlockCipher(engine)); //new PaddedBufferedBlockCipher(

	    cipher.init(forEncryption, new KeyParameter(KEY));
	    return cipher;
	  }
}
