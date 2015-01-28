package com.mglf.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * http代理
 * 
 */
public class ProxyHostUtil {

	public static void setProxyHost(HttpClient httpClient){
		GetMethod getMethod = null;
		try{
			
			String resp = "";
			
			String url = "http://letushide.com/filter/http,all,cn/list_of_free_HTTP_CN_China_proxy_servers";
			
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
			client.getHttpConnectionManager().getParams().setSoTimeout(3000);
			getMethod = new GetMethod(url);
			
			int statusCode = client.executeMethod(getMethod);
				
			if (statusCode == HttpStatus.SC_OK) {
				resp = getMethod.getResponseBodyAsString();
			}

			Document doc = Jsoup.parse(resp);

			String maxSpeed = "";
			String maxHost = null;
			String maxPort = null;
			Elements eles = doc.getElementsByAttributeValue("id", "data");
			for(int i=0; i<eles.size(); i++){
				Element ele = eles.get(i);
				
				Element linkEle = ele.getElementById("link");
				Element portEle = linkEle.nextElementSibling();
				Element speedEle = ele.getElementById("speed");
				
				String host = linkEle.getElementsByTag("a").get(0).text();
				String port = portEle.text();
				String speed = speedEle.attr("class");

				if(speed.compareTo(maxSpeed)>0){
					try{
						String testUrl = "http://"+host+":"+port;
						getMethod = new GetMethod(testUrl);
						statusCode = client.executeMethod(getMethod);
					}catch(Exception e){
						continue;
					}
										
					maxSpeed = speed;
					
					maxHost = host;
					maxPort = port;
				}
			}
			
			System.out.println("proxy "+maxHost+":"+maxPort);
			httpClient.getHostConfiguration().setProxy(maxHost, Integer.parseInt(maxPort));
		}catch(Exception e){			
		}finally{
			if(getMethod != null){
				try{
					getMethod.releaseConnection();
				}catch(Exception e){
				}
			}
		}
	}
	
	public static void main(String[] argvs){
		setProxyHost(null);
	}
}
