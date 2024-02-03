package util;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;

public class Encode {
	// sha - 1 => thuong duoc su dung

	public static String toSHA1(String toEncrypt) {
		String salt = "";// @#$%%&^jeiwodjaoisddsewriojedasdas';dá'dokdasdkoaspkdas
		String result = null;
		toEncrypt += salt;
		try {
			byte[] dataBytes = toEncrypt.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			result = Base64.encodeBase64String(md.digest(dataBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(toSHA1("123456"));
	}
}
