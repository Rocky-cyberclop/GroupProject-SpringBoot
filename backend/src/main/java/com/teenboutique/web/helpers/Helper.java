package com.teenboutique.web.helpers;

public class Helper {
	public static String generateRandomString(int length) {
	    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < length; i++) {
	        sb.append(characters.charAt((int) (Math.random() * characters.length())));
	    }
	    return sb.toString();
	}
	
	public static Boolean checkImg(String s) {
		if(s.contains("http"))return true;
		return false;
	}
}
