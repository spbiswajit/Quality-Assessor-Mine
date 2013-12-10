package com.qaitdevlabs.qualityassessor.util;


import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
public class Testpasswordencoder {

	public static void main(String arr[]) {
		PasswordEncoder encoder = new ShaPasswordEncoder();
		String result = encoder.encodePassword("viv123", "vivkumar@gab.com");
		System.out.println(result);
	}
}
