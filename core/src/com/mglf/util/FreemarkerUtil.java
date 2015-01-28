/**
 * 
 */
package com.mglf.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import com.mglf.util.ConfigUtil;
import com.mglf.util.FileUtil;
import com.mglf.util.FormatUtil;

import com.mglf.util.ConfigUtil;
import com.mglf.util.FileUtil;
import com.mglf.util.FormatUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author zhongzhuohan
 * 
 */
public class FreemarkerUtil {
	
	/**
	 * 后台模板路径
	 */
	public static String TEMPLET_ADMIN = "WEB-INF/classes/templet/admin";
	
	/**
	 * 企业模板路径
	 */
	public static String TEMPLET_ENT = "WEB-INF/classes/templet/ent";
	
	/**
	 * 个人模板路径
	 */
	public static String TEMPLET_PER = "WEB-INF/classes/templet/per";

	
	private Configuration cfg;
	
	/**
	 * 初始化方法
	 * @param templetPath 模板路径 取值为：TEMPLET_ADMIN、TEMPLET_ENT、TEMPLET_PER
	 * @throws Exception
	 */
	public void init(String templetPath) throws Exception {
		// 初始化FreeMarker配置
		// 创建一个Configuration实例
		cfg = new Configuration();
		// 设置FreeMarker的模版文件位置
		cfg.setEncoding(Locale.getDefault(), "UTF-8");
		cfg.setDirectoryForTemplateLoading(new File(FormatUtil.getBaseFilePath() + templetPath));
	}

	/**
	 * 生成静态页面
	 * 
	 * @param map
	 *            数据
	 * @param templetName
	 *            模板名称，如webNavDetailTemplet
	 * @param fileName
	 *            目标文件名，如game
	 * @param isPreview
	 *            true:预览；false：正式发布
	 * @throws Exception
	 */
	public void processGp(Map<String, Object> map, String templetName, String fileName) throws Exception {
		// 文件地址
		String htmlPath = FormatUtil.getBaseFilePath() + "gp/" + FormatUtil.formatDate(new Date(), "yyyyMMdd");

		// 创建文件夹
		FileUtil.newFolder(htmlPath);

		Template t = cfg.getTemplate(templetName + ".ftl", "UTF-8");
		t.setEncoding("UTF-8");
		File htmlFile = new File(htmlPath + "/" + fileName + ".html");

		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
		// 处理模版
		t.process(map, out);
		out.flush();
		out.close();

	}

	/**
	 * 生成静态页面
	 * 
	 * @param map
	 *            数据
	 * @param templetName
	 *            模板名称，如webNavDetailTemplet
	 * @throws Exception
	 */
	public String process(Map<String, Object> map, String templetName) throws Exception {
		map.put("domain", ConfigUtil.readSysValue("server"));
		
		Template t = cfg.getTemplate(templetName + ".ftl", "UTF-8");
		t.setEncoding("UTF-8");
		StringWriter swriter = new StringWriter();
		t.process(map, swriter);

		return swriter.toString();
	}

}
