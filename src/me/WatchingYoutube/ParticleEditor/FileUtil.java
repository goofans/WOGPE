package me.WatchingYoutube.ParticleEditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtil {
	
	private static final Logger log = Logger.getLogger(FileUtil.class.getName());
	
	public static String expandEnvVars(String s)
	  {
	    int first, second;

	    while ((first = s.indexOf('%')) != -1) {
	      second = s.indexOf('%', first + 1);
	      if (second == -1) return s;

	      String var = s.substring(first + 1, second);
	      StringBuilder sb = new StringBuilder(s.substring(0, first));
	      String envVar = System.getenv(var);
	      if (envVar == null) return null;
	      sb.append(envVar);

	      sb.append(s.substring(second + 1));
	      s = sb.toString();
	    }
	    return s;
	  }
	
	public static byte[] readFile(File file) throws IOException {
		int fileSize = (int) file.length();
		InputStream is = new FileInputStream(file);
		
		try {
			byte[] inputBytes = new byte[fileSize];

		      int offset = 0;
		      int numRead;

		      while (offset < fileSize && ((numRead = is.read(inputBytes, offset, fileSize - offset)) != -1)) {
		        offset += numRead;
		        log.log(Level.FINER, "got " + numRead + " bytes from " + file.getName());
		      }

		      if (offset < fileSize) {
		        throw new IOException("Short read of " + file + ", expected " + fileSize + " but got " + offset);
		      }

		      return inputBytes;
		    }
		    finally {
		      is.close();
		}
		
	}
	
	public static void copyInputStreamToFile(InputStream inputStream, File file) 
			throws IOException {

	        try (FileOutputStream outputStream = new FileOutputStream(file)) {

	            int read;
	            byte[] bytes = new byte[1024];

	            while ((read = inputStream.read(bytes)) != -1) {
	                outputStream.write(bytes, 0, read);
	            }

				// commons-io
	            //IOUtils.copy(inputStream, outputStream);

	        }

	    }

}
