package com.fescoadecco.weixin.util.https;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import com.fescoadecco.weixin.web.ApiController;

/**
 * 
 * https工具类
 * 
 */

public class HttpsUtil {
	public final static String POST="POST";
	public final static String GET="GET";
	static Logger logger = Logger.getLogger(ApiController.class);

	/**
	 * 
	 * 发起https请求并获取结果
	 * 
	 * 
	 * 
	 * @param requestUrl
	 *            请求地址
	 * 
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * 
	 * @param outputStr
	 *            提交的数据
	 * 
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */

	public static String httpRequest(String requestUrl, String requestMethod,
			String outputStr) {


		StringBuffer buffer = new StringBuffer();

		try {

			// 创建SSLContext对象，并使用我们指定的信任管理器初始化

			TrustManager[] tm = { new MyX509TrustManager() };

			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");

			sslContext.init(null, tm, new java.security.SecureRandom());

			// 从上述SSLContext对象中得到SSLSocketFactory对象

			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);

			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();

			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);

			httpUrlConn.setDoInput(true);

			httpUrlConn.setUseCaches(false);

			// 设置请求方式（GET/POST）

			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))

				httpUrlConn.connect();

			// 当有数据需要提交时

			if (null != outputStr) {

				OutputStream outputStream = httpUrlConn.getOutputStream();

				// 注意编码格式，防止中文乱码

				outputStream.write(outputStr.getBytes("UTF-8"));

				outputStream.close();

			}

			// 将返回的输入流转换成字符串

			InputStream inputStream = httpUrlConn.getInputStream();

			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");

			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;

			while ((str = bufferedReader.readLine()) != null) {

				buffer.append(str);

			}

			bufferedReader.close();

			inputStreamReader.close();

			// 释放资源

			inputStream.close();

			inputStream = null;

			httpUrlConn.disconnect();

			// jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
			logger.error("Weixin server connection timed out.");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("https request error:{}" + e.getMessage());

		}

		return buffer.toString();

	}

	public static void main(String[] args) {
		 HttpsUtil.httpRequest("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3ec9e57674480a6d&redirect_uri=http%3A%2F%2Fwww.fescoadecco.com%2F&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect",
		 "GET", null);
//		SendHttps
//				.httpRequest(
//						"https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=-Yqy2W0-aWrUH0DVky9t-aYDe6YEvM4e2sEtlfvtk4-xqq2CqG2M1kXNi5C58efQv4BMvYXFuizQoWs5MTh3AlRl4wfipxfOK58dD_F43oD5G2uJq67qypZ1KyfaQApKtiWjQUuhyyNMdyVSs9ellA",
//						SendHttps.POST,
//						"<xml><ToUserName><![CDATA[oXF8HuFPy7L4Q5jXPKtXLAXcauLA]]></ToUserName><FromUserName><![CDATA[gh_10ba5fab0bb9]]></FromUserName><CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content></xml>");
	}

}