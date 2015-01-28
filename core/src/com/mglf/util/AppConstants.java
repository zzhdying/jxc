/**
 * 
 */
package com.mglf.util;

/**
 * 常量类
 * 
 * @author zhongzhuohan
 */
public class AppConstants {

	/**
	 * 手机号码regex
	 */
	public static String PHONE_REGEX = "^[1][3578]\\d{9}$";
	/**
	 * 邮箱regex
	 */
	public static String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

	/**
	 * 邮箱激活成功
	 */
	public static final int EMAIL_ACTIVE_SUCCESS = 0;
	
	/**
	 * 邮箱激活失败
	 */
	public static final int EMAIL_ACTIVE_FAILURE = 1;
	
	public enum GENDER {
		MAN("男", "MAN"),

		WOMAN("女", "WOMAN");
		private String name;

		private String value;

		private GENDER(String displayName, String value) {
			this.name = displayName;
			this.value = value;
		}

		public static String getDisPlayName(String index) {

			for (GENDER item : GENDER.values()) {
				if (item.value.equals(index)) {
					return item.name;
				}
			}
			return "";
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

	

	public enum USER_TYPE {
		COMPANY("企业", 1),

		PERSONAL("个人", 2);

		private String name;

		private Integer value;

		private USER_TYPE(String displayName, Integer value) {
			this.name = displayName;
			this.value = value;
		}

		public static String getDisPlayName(Integer index) {

			for (USER_TYPE item : USER_TYPE.values()) {
				if (item.value.equals(index)) {
					return item.name;
				}
			}
			return "";
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

	}

}
