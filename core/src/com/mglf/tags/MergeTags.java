package com.mglf.tags;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;
import org.springframework.web.servlet.tags.RequestContextAwareTag;
import com.mglf.tags.MergeTags;
import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

/**
 * 
 * @author zhongzhuohan
 * 
 */
public class MergeTags extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;

	private final static Log log = LogFactory.getLog(MergeTags.class);

	private String files;

	private String key;

	private static Map<String, byte[]> merge = new HashMap<String, byte[]>();
	private static Map<String, String> mergeFiles = new HashMap<String, String>();

	public static byte[] getBuf(String key) {
		return merge.get(key);
	}

	public static void reloadBuf(String key, ServletContext servletContext)
			throws Exception {
		synchronized (merge) {
			merge.remove(key);
		}

		loadBuf(key, servletContext, null);
	}

	public static void loadBuf(String key, ServletContext servletContext,
			String files) throws Exception {

		synchronized (merge) {
			byte[] buf = merge.get(key);
			if (buf == null) {
				if (files == null) {
					files = mergeFiles.get(key);
				} else {
					mergeFiles.put(key, files);
				}

				files = files.replaceAll("\\\n", "");
				files = files.replaceAll("\\\r", "");
				files = files.replaceAll("\t", "");

				String[] fs = files.split(",");
				StringBuffer sb = new StringBuffer();

				for (String f : fs) {
					if (f == null || "".equals(f)) {
						continue;
					}

					f = servletContext.getRealPath(f);

					BufferedReader br = new BufferedReader(
							new InputStreamReader(new FileInputStream(f),
									"UTF-8"));

					String strFile = "";

					String line;

					while ((line = br.readLine()) != null) {
						strFile += line;
						strFile += "\n";
					}

					br.close();

					if (f.endsWith(".js") && !f.endsWith(".min.js")) {
						ByteArrayInputStream bais = new ByteArrayInputStream(strFile.getBytes("utf8"));
						
						InputStreamReader in = new InputStreamReader(bais, "utf8");

						JavaScriptCompressor compressor = new JavaScriptCompressor(
								in, new ErrorReporter() {
									public void warning(String message,
											String sourceName, int line,
											String lineSource, int lineOffset) {
										System.out.println(message);
									}

									public void error(String message,
											String sourceName, int line,
											String lineSource, int lineOffset) {
										System.out.println(message);
									}

									public EvaluatorException runtimeError(
											String message, String sourceName,
											int line, String lineSource,
											int lineOffset) {
										error(message, sourceName, line,
												lineSource, lineOffset);
										return new EvaluatorException(message);
									}
								});

						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						OutputStreamWriter writer = new OutputStreamWriter(baos, "utf8");
						compressor.compress(writer, -1, true, false, false, false);
						writer.close();
						baos.close();
						
						strFile = new String(baos.toByteArray(), "utf8");
					}else if (f.endsWith(".css")) {
						ByteArrayInputStream bais = new ByteArrayInputStream(strFile.getBytes("utf8"));
						
						InputStreamReader in = new InputStreamReader(bais, "utf8");

						CssCompressor compressor = new CssCompressor(in);  
						
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						OutputStreamWriter writer = new OutputStreamWriter(baos);
						compressor.compress(writer, -1); 
						writer.close();
						baos.close();
						
						strFile = new String(baos.toByteArray(), "utf8");
					}

					sb.append(strFile);
				}

				merge.put(key, sb.toString().getBytes("utf8"));
			}
		}

	}

	@Override
	protected int doStartTagInternal() throws Exception {
		return 0;
	}

	
	public int doEndTag() throws JspTagException {
		JspWriter out = pageContext.getOut(); // 重要

		try {
			byte[] buf = merge.get(key);

			if (buf == null) {
				loadBuf(key, this.pageContext.getRequest().getServletContext(),
						files);
				buf = merge.get(key);
			}

			String p = this.pageContext.getRequest().getServletContext()
					.getContextPath();
			out.print(p + "/merge/" + key);
		} catch (Exception e) {
			log.error("doEndTag", e);
		}

		return EVAL_PAGE; // 表示处理完标签后继续执行以下的JSP网页

	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
