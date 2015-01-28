package com.fescoadecco.weixin.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mglf.base.BaseController;
import com.mglf.dto.JsonResult;
import com.mglf.util.CacheUtil;
import com.mglf.util.ConfigUtil;
import com.mglf.util.EmptyUtil;
import com.mglf.util.StringUtil;
import com.fescoadecco.weixin.api.Const;
import com.fescoadecco.weixin.api.UserInfo;
import com.fescoadecco.weixin.dto.UserResponse;
import com.fescoadecco.weixin.util.QRCodeEncoder;

/**
 * 微信api controller
 * 
 * @author xiapengcheng
 * 
 */
@Controller
@RequestMapping("/authorize")
public class AuthorizeController extends BaseController {
	public static Map<String,String> qrmap=new HashMap<String,String>();

	/**
	 * 点击分享的数据授权
	 * @author jinxiaochen
	 * @param code
	 * @param state
	 * @throws IOException 
	 */
	@RequestMapping("/authwxsearchjobs")
	public void authorizeFromWxsearchjobs(String code, String state, HttpServletResponse resp) throws Exception {
		UserResponse userinfo = UserInfo.getUserInfoByCode(code,Const.SNSAPI_BASE);
		String openIdKey = UUID.randomUUID().toString();
		if(!EmptyUtil.isEmpty(userinfo) && !EmptyUtil.isEmpty(userinfo.getOpenid())) {
			CacheUtil.save(CacheUtil.GROUP_WEIXIN_REGLOGIN, openIdKey, userinfo.getOpenid());
		}
				
		String reUrl = ConfigUtil.readSysValue("perWebUrl")
				+ "/" +  ConfigUtil.readSysValue("BluePersonWeb") + "/mobile/weixinclientjoblist/" + openIdKey + "/" + state;
		resp.sendRedirect(reUrl);
	}
	
	
	/**
	 * 点击分享的数据授权
	 * @author jinxiaochen
	 * @param code
	 * @param state
	 * @throws IOException 
	 */
	@RequestMapping("/authshare")
	public void authorizeFromShare(String code, String state, HttpServletResponse resp) throws Exception {
		UserResponse userinfo = UserInfo.getUserInfoByCode(code,Const.SNSAPI_BASE);
		String authResult = "failed";
		String openIdKey = UUID.randomUUID().toString();
		if(!EmptyUtil.isEmpty(userinfo) && !EmptyUtil.isEmpty(userinfo.getOpenid())) {
			CacheUtil.save(CacheUtil.GROUP_WEIXIN_REGLOGIN, openIdKey, userinfo.getOpenid());
			authResult = "success";
		}
		
		String[] states = state.split(",");
		String jobNum = states[0];
		String shortPath = "";
		
		if(states.length > 1){
			shortPath = states[1];
		}		
		
		String reUrl = ConfigUtil.readSysValue("perWebUrl")
				+ "/" +  ConfigUtil.readSysValue("BluePersonWeb") + "/mobile/loginfromshare/" + jobNum + "/" + openIdKey + "/" + authResult + "/" + shortPath;
		resp.sendRedirect(reUrl);
	}
	
	
	
	/**
	 * 授权登陆
	 * @param code
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/")
	public ModelAndView authorize(String code,String state) throws IOException{
		UserResponse userinfo=UserInfo.getUserInfoByCode(code,Const.SNSAPI_BASE);
		ModelMap map=new ModelMap();
		if (userinfo!=null && StringUtil.isNotBlank(userinfo.getOpenid())) {
			System.out.println("Openid:"+userinfo.getOpenid());
			CacheUtil.save(CacheUtil.GROUP_WEIXIN_REGLOGIN, state, userinfo.getOpenid());
			map.put("msg", "授权成功");
			return new ModelAndView("weixin/subscribemsg.jsp",map);
		}else {
			map.put("msg", "授权失败，");
			return new ModelAndView("weixin/subscribemsg.jsp",map);
		}
//		if (userinfo!=null && userinfo.getSubscribe()!=null && userinfo.getSubscribe()==0) {//未关注，重新授权，以snsapi_userinfo方式请求
//			String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Const.APPID+"&redirect_uri=http://115.47.49.80/weixin/weixin/authorize/&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
//			map.put("url", url);
//			//return new ModelAndView("weixin/subscribe.jsp",map);
//		}else {//已关注
//			/**
//			 * userinfo 用户信息处理
//			 */
//			
//			//return new ModelAndView("weixin/index.jsp");
//		}
		
	}
	
	@RequestMapping("/changeAuthorize/{token}")
	public void changeAuthorize(@PathVariable("token") String token, HttpServletResponse resp) throws IOException{
		
		String reurl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Const.APPID+"&redirect_uri="+ConfigUtil.readWeixinValue("redirect_uri")+"&response_type=code&scope=snsapi_base&state="+token+"#wechat_redirect";
		resp.sendRedirect(reurl);
	}
	/**
	 * 随机生成uuid作二维码唯一参数
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/qruuid")
	public ModelAndView qruuid(HttpServletRequest request)
			throws ServletException, IOException {
		UUID u = UUID.randomUUID();
		ModelMap map=new ModelMap();
		map.put("uuid",u);
		return new ModelAndView("weixin/qrcode.jsp",map);
	}
	
	/**
	 * 获取二维码图片
	 * @param uuid
	 * @param response
	 * @return
	 */
	@RequestMapping("/qrImage")
	@ResponseBody
	public String qrImage(String uuid,HttpServletResponse response) {
        String content ="http://115.47.49.80/weixin/weixin/authorize/changeAuthorize?uuid="+uuid;
        QRCodeEncoder encoder = new QRCodeEncoder();  
        encoder.encoderQRCoder(content, response);  
        return null;
	}
	/**
	 * 用户使用手机扫描
	 * @param uuid
	 * @return
	 */
	@RequestMapping("/phoneSetQrCode")
	@ResponseBody
	public String phoneSetQrCode(String state,String code){
		UserResponse u=UserInfo.getUserInfoByCode(code,Const.SNSAPI_BASE);
		qrmap.put(state, u.getOpenid());
		return null;
	}
	/**
	 * 根据cache中uuid判断用户是否扫描二维码
	 * @param uuid
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping("/qrlogin")
	@ResponseBody
	public JsonResult<String> qrlogin(String uuid,String code) throws InterruptedException {
		JsonResult<String> json = new JsonResult<String>();
		String status=JsonResult.FAIL;
		for (int i = 0; i < 5; i++) {
			if (qrmap.containsKey(uuid)) {
				json.setStatus(JsonResult.SUCCESS);
				break;
			}
			Thread.sleep(5000);
		}
		json.setStatus(status);
		return json;
	}
	
	
}
