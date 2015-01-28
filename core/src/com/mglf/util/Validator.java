package com.mglf.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class Validator {

	public interface Re {

		
		String HALF = "^[\uFF00-\uFFFF]*$";

		
		String URL = "((((http|https)\\:\\/\\/)((www\\.[a-zA-Z0-9\\-\\.]+)|[a-zA-Z0-9\\-\\.]+)))[a-zA-Z0-9\\-\\.](\\:[0-9]+)*(/($|[a-zA-Z0-9\\.\\,\\;\\?\\'\\\\\\+&%\\$#\\[\\]\\=~_\\-]+))*";
		String URL_NO_PROTOCOL = "(/($|[a-zA-Z0-9\\.\\,\\;\\?\\'\\\\\\+&%\\$#\\[\\]\\=~_\\-]+))*";

		
		String ALPHABET = "[a-zA-Z]+";

		
		String ALPHA_NUMBER = "[a-zA-Z0-9]+";

		
		String NUMBER = "[0-9]+";

		
		String NUMERIC = "-?[0-9]+(\\.?[0-9]*)?";

		String DOUBLE = "^[0-9]+(\\.([0-9]+))?$";

		String TEL = "[0-9\\-]+";

		String PHONE = "[0-9\\-\\+]+";

		String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";


		String SLASH_YYYY_MM_DD = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
	}

	private static final Pattern RE_HALF = Pattern.compile(Re.HALF);

	private static final Pattern RE_ALPHANUMBER = Pattern
			.compile(Re.ALPHA_NUMBER);

	private static final Pattern RE_ALPHABET = Pattern.compile(Re.ALPHABET);

	private static final Pattern RE_NUMBER = Pattern.compile(Re.NUMBER);

	private static final Pattern RE_NUMERIC = Pattern.compile(Re.NUMERIC);

	private static final Pattern RE_DOUBLE = Pattern.compile(Re.DOUBLE);



	private static final Pattern TEL = Pattern.compile(Re.TEL);
	private static final Pattern PHONE = Pattern.compile(Re.PHONE);

	private static final Pattern EMAIL = Pattern.compile(Re.EMAIL);

	private static final Pattern URL = Pattern.compile(Re.URL);
	private static final Pattern URL_NO_PROTOCOL = Pattern
			.compile(Re.URL_NO_PROTOCOL);


	private static final Pattern RE_SLASH_YYYY_MM_DD = Pattern
			.compile(Re.SLASH_YYYY_MM_DD);

	public static boolean isAlphaNumber(String str) {
		return (str != null) && RE_ALPHANUMBER.matcher(str).matches();
	}

	public static boolean isAlphabet(String str) {
		return (str != null) && RE_ALPHABET.matcher(str).matches();
	}

	public static boolean isTel(String str) {
		return (str != null) && TEL.matcher(str).matches();
	}

	public static boolean isPhone(String str) {
		return (str != null) && PHONE.matcher(str).matches();
	}

	public static boolean isNumber(String str) {
		return !StringUtils.isBlank(str) && RE_NUMBER.matcher(str).matches();
	}

	public static boolean isNumeric(String str) {
		return !StringUtils.isBlank(str) && RE_NUMERIC.matcher(str).matches();
	}

	public static boolean isDouble(String str) {
		return !StringUtils.isBlank(str) && RE_DOUBLE.matcher(str).matches();
	}

	public static boolean isMailAddr(String mailAddr) {

		Matcher matcher = EMAIL.matcher(mailAddr);
		return matcher.matches();
	}
	
	/**
	 * String is email address format
	 * @param str
	 * @return true: yes, false: no or null or "" 
	 */
	public static boolean isEmail(String str){
		return StringUtils.isNotBlank(str) && EMAIL.matcher(str).matches();
	}



	public static boolean isHalf(String str) {
		return (str != null) && RE_HALF.matcher(str).matches();
	}

	public static boolean isFormatedDouble(String str, int plusCount,
			int minusCount) {
		String regex;
		if (plusCount <= 0) {
			if (minusCount <= 0) {
				regex = "[\\+|\\-]?0";
			} else {
				regex = "[\\+|\\-]?0+(.[0-9]{0," + minusCount + "})?";
			}
		} else {
			if (minusCount <= 0) {
				regex = "[\\+|\\-]?0*[0-9]{1," + plusCount + "}";
			} else {
				regex = "[\\+|\\-]?0*[0-9]{1," + plusCount + "}(.[0-9]{0,"
						+ minusCount + "})?";
			}
		}
		Pattern double_pat = Pattern.compile(regex);
		return !StringUtils.isBlank(str) && double_pat.matcher(str).matches();
	}

	public static boolean isURL(String str) {
		return URL.matcher(str).matches();
	}

	public static boolean isURLNoProtocol(String str) {
		return URL_NO_PROTOCOL.matcher(str).matches();
	}


	public static boolean isSlashyyyyMMdd(String str) {
		return RE_SLASH_YYYY_MM_DD.matcher(str).matches();
	}
}
