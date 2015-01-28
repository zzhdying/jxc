package com.mglf.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mglf.tags.MergeTags;

public class MergeServlet extends HttpServlet {

	private final static Log log = LogFactory.getLog(MergeServlet.class);
	
	private Date lastDate;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		lastDate = new Date();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream os = null;
		
		try {
			String url = request.getRequestURI();
			url = url.substring(url.lastIndexOf('/')+1);
			
			String reload = request.getParameter("reload");
			String ip = request.getRemoteAddr();
			
			if("true".equals(reload) && (ip.startsWith("10.") || ip.startsWith("127.") )){
				lastDate = new Date();
				MergeTags.reloadBuf(url, request.getServletContext());
				return;
			}
			
			String type = "text/plain";
			
			if(url.endsWith(".css")){
				type = "text/css";
			}else if(url.endsWith(".js")){
				type = "application/javascript";
			}
			
			response.setContentType(type+"; charset=UTF-8");
			response.addHeader("Cache-Control", "max-age=3600");
	 		response.addHeader("Expires", "120");
			response.addHeader("last-modified", new Date().getTime()+"");
			
			if(request.getHeader("If-Modified-Since")!=null){
				Long timeStamp=NumberUtils.toLong(request.getHeader("If-Modified-Since"));
				Date lastVisitTime=new Date(timeStamp);
				if(lastVisitTime.after(lastDate)){
					response.setStatus(HttpServletResponse.SC_NOT_MODIFIED); 
					return;
				}
			}
			
			byte[] buf = MergeTags.getBuf(url);
			if(buf == null){
				return;
			}
			
			os = response.getOutputStream();
		
			os.write(buf);
			
			os.flush();
			
		} catch (Exception e) {
			
			
			log.error("doService:", e);
			
		} finally{
			if(os != null){
				os.close();
			}
		}
	}
}
