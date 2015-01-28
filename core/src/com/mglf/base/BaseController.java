package com.mglf.base;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import com.mglf.dto.AttachmentDto;

/**
 * 
 * @author liuchuanhong
 * @since 2014/03/07
 * 
 */
@Controller
public class BaseController {

	/**
	 * 获取客户端真实IP地址
	 * 
	 * @param request
	 * @return IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		String ip = request.getRemoteAddr();

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		
		String xRealIp = request.getHeader("X-Real-IP"); 
		if(xRealIp != null){
			ip = xRealIp;
		}
		
		return ip;
	}
	
	/**
	 * 文件下载
	 * @throws Exception
	 */
	public void dumpFileToClient(AttachmentDto attachmentDto,HttpServletResponse response)
			throws Exception {
		String defaultFileCharset = "UTF-8";

		String fileName=attachmentDto.getFileName();
		response.reset();
		response.setHeader("Content-Length", String.valueOf(attachmentDto.getFileSize().toString()));
		response.setHeader("Content-Disposition", "inline; filename=\""
				+fileName + "\"");
		response.setContentType("application/octet-stream;charset="+defaultFileCharset);
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(attachmentDto.getData());
			out.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

}
