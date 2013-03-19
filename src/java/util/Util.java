package util;

import bean.UploadType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;

public class Util {
	public static String MD5(String md5) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
        
        public static void copyFile(String filename, InputStream in, UploadType type) {
            String destination = "";
            
            switch(type) {
                case ASSIGNMENT_GROUP:
                    destination = "C:/AssignmentGroup/";
                    break;
                case MODELCHANGE:
                    destination = "C:/Modelchange/";
                    break;
            }
            try {
                OutputStream out = new FileOutputStream(new File(destination + filename));
                
                int read = 0;
                byte[] bytes = new byte[1024];
                
                while((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                
                in.close();
                out.flush();
                out.close();
                
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
            
        }
}
