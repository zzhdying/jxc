package com.fescoadecco.weixin.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mglf.base.BaseController;
import com.mglf.util.ConfigUtil;
import com.fescoadecco.weixin.session.BlueMessageHandle;
import com.fescoadecco.weixin.session.DefaultSession;
import com.fescoadecco.weixin.util.https.MySecurity;

/**
 * 微信api controller
 * 
 * @author xiapengcheng
 * 
 */
@Controller
@RequestMapping("/")
public class WeixinController extends BaseController {

	private static Logger logger = Logger.getLogger(WeixinController.class);

	public static Map<String, String> qrmap = new HashMap<String, String>();


	@RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE, value = "/", method = RequestMethod.GET)
	public void indexGet(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String reValue = "";
	
			String signature = request.getParameter("signature");// 微信加密签名
			String timestamp = request.getParameter("timestamp");// 时间戳
			String nonce = request.getParameter("nonce");// 随机数
			String echostr = request.getParameter("echostr");// 随机字符串
			
			if(signature == null){
				return;
			}
			if(timestamp == null){
				return;
			}
			if(nonce == null){
				return;
			}
			if(echostr == null){
				return;
			}
			
			// 重写tostring方法，得到三个参数的拼接字符串
			List<String> list = new ArrayList<String>(3) {
				private static final long serialVersionUID = 2621444383666420433L;
	
				public String toString() {
					return this.get(0) + this.get(1) + this.get(2);
				}
			};
			list.add(ConfigUtil.readWeixinValue("token"));
			list.add(timestamp);
			list.add(nonce);
			Collections.sort(list);// 排序
			String tmpStr = new MySecurity().encode(list.toString(),
					MySecurity.SHA_1);// SHA-1加密
			if (signature.equals(tmpStr)) {
				reValue = echostr;
			}
			PrintWriter pw = response.getWriter();
			pw.write(reValue);
		} catch (Exception e) {
			logger.error("indexGet:", e);
		}
	}

	/**
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(produces = MediaType.TEXT_XML_VALUE, value = "/", method = RequestMethod.POST)
	public void indexPost(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			InputStream is = request.getInputStream();
			response.setContentType("text/xml;charset=UTF-8");
			OutputStream os = response.getOutputStream();

			DefaultSession session = DefaultSession.newInstance();

			BlueMessageHandle blueMessageHandle = new BlueMessageHandle(
					session);
			session.addOnHandleMessageListener(blueMessageHandle);

			// 必须调用这两个方法
			// 如果不调用close方法，将会出现响应数据串到其它Servlet中。
			session.process(is, os);// 处理微信消息

			session.close();// 关闭Session
		} catch (Exception e) {
			logger.error("indexPost:", e);
		}
	}
}
