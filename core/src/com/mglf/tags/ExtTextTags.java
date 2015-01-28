package com.mglf.tags;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * 
 * @author zhongzhuohan
 * 
 */
public class ExtTextTags extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;

	private String text;

	private String nbsp;
	@Override
	protected int doStartTagInternal() throws Exception {
		return 0;
	}

	
	public int doEndTag() throws JspTagException {
		JspWriter out = pageContext.getOut(); // 重要
		try {

			text = StringEscapeUtils.escapeHtml(text);
//			text = StringEscapeUtils.escapeJavaScript(text); 
			
			text = text.replaceAll("\\n", "</br>");
			if(nbsp==null||(nbsp!=null&&(nbsp.equals("true"))))
				text = text.replaceAll(" ", "&nbsp;"); 
			
			out.print(text);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return EVAL_PAGE; // 表示处理完标签后继续执行以下的JSP网页

	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNbsp() {
		return nbsp;
	}

	public void setNbsp(String nbsp) {
		this.nbsp = nbsp;
	}

}
