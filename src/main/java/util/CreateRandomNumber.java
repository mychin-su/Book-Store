package util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CreateRandomNumber {
	public static String getSoNgauNhien() {
		Random rd = new Random();
		String s1 = rd.nextInt(10) + "";
		String s2 = rd.nextInt(10) + "";
		String s3 = rd.nextInt(10) + "";
		String s4 = rd.nextInt(10) + "";
		String s5 = rd.nextInt(10) + "";
		String s6 = rd.nextInt(10) + "";
		String s = s1 + s2 + s3 + s4 + s5 + s6;
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		System.out.println(dt);
		return s;

	}

	public static void main(String[] args) {
		System.out.println(getSoNgauNhien());
	}
}
