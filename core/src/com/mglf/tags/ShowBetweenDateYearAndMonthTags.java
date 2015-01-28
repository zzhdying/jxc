package com.mglf.tags;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;


@SuppressWarnings("serial")
public class ShowBetweenDateYearAndMonthTags extends RequestContextAwareTag {
	private String startTime;
	private String endTime;

	@Override
	protected int doStartTagInternal() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int doEndTag()  throws JspException{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Date sTime = null;
		Date eTime = null;
		try {
			if(StringUtils.isNotBlank(startTime)){
				sTime=format.parse(startTime);
			}
			if (StringUtils.isNotBlank(endTime)) {
				eTime=format.parse(endTime);
			}
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JspWriter out = pageContext.getOut();
		Calendar cal = Calendar.getInstance();
		StringBuilder sBuilder = new StringBuilder();
		if (null!=sTime && null!=eTime) {
			cal.setTime(sTime);
			int startY = cal.get(Calendar.YEAR);
			int startM = cal.get(Calendar.MONTH);
			int startD = cal.get(Calendar.DATE);
			int startDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		

			cal.setTime(eTime);
			int endY = cal.get(Calendar.YEAR);
			int endM = cal.get(Calendar.MONTH);
			// 处理2011-01-10到2011-01-10，认为服务为一天
			int endD = cal.get(Calendar.DATE) + 1;
			int endDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	

		
		int lday = endD - startD;
		if (lday < 0) {
			endM = endM - 1;
			lday = startDayOfMonth + lday;
		}
		// 处理天数问题，如：2011-01-01 到 2013-12-31 2年11个月31天 实际上就是3年
		if (lday == endDayOfMonth) {
			endM = endM + 1;
			lday = 0;
		}
		int mos = (endY - startY) * 12 + (endM - startM);
		int lyear = mos / 12;
		int lmonth = mos % 12;
		if (lyear > 0) {
			sBuilder.append(lyear + "年");
		}
		if (lmonth > 0) {
			sBuilder.append(lmonth + "个月");
		}
		}
		try {
			out.print(sBuilder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doEndTag();
		
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	
	

}
