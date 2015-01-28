package com.mglf.biz.tags;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

@SuppressWarnings("serial")
public class TimeshowTags extends RequestContextAwareTag {
	
	@Override
	protected int doStartTagInternal() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int doEndTag() throws JspException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String show = "";
		try {
			if (time == null || time.trim().length() == 0) {
				if (var != null) {
					pageContext.setAttribute(var, show);
				} else
					pageContext.getOut().print("系统自动");
				return super.doEndTag();
			}
			Date date = format.parse(time);
			Long s = (new Date().getTime() - date.getTime());
			if(s>0){
				Calendar curDate = new GregorianCalendar();
				curDate.setTime(new Date());
				curDate.set(Calendar.HOUR_OF_DAY, 23);
			    curDate.set(Calendar.MINUTE, 59);
			    curDate.set(Calendar.SECOND, 59);
			    curDate.set(Calendar.MILLISECOND, 999);
			    Long cur = curDate.getTimeInMillis() - date.getTime();
				Long day = cur / 1000 / 60 / 60 / 24;
				if (day < 1) {
					Long h = s / 1000 / 60 / 60;
					if (h < 1) {
						Long m = s / 1000 / 60;
						if (m < 1) {
							show = "刚刚";
						} else {
							show = m + "分钟前";
						}
					} else {
						show = h + "小时前";
					}
				} else if (day >= 1 &&day<2) {
					show = "昨天";
				} else if (day >= 2 && day<3) {
					show = "前天";
				} else {
					SimpleDateFormat format1 = new SimpleDateFormat(
							"yyyy-MM-dd");
					SimpleDateFormat format2 = new SimpleDateFormat(
							"HH:mm");
					
					if("dynamic".equals(param)){
						show = format1.format(date);
					}else{
						show = format1.format(date);
						if(hasBr!=null&&hasBr.equals("br")){
							show +=("<br/>"+format2.format(date));
						}else{
							show +=(" "+format2.format(date));
						}
					}
				}
			}else{
				Calendar curDate = new GregorianCalendar();
				curDate.setTime(new Date());
				curDate.set(Calendar.HOUR_OF_DAY, 0);
			    curDate.set(Calendar.MINUTE, 0);
			    curDate.set(Calendar.SECOND, 0);
			    curDate.set(Calendar.MILLISECOND, 0);
				s = date.getTime()-curDate.getTimeInMillis();
				Long day = s / 1000 / 60 / 60 / 24;
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				int ap= calendar.get(Calendar.AM_PM);
				SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
				String time = format2.format(date);
				if (day < 1) {
					if(ap==0){
						show = "今天上午"+time;
					}else{
						show = "今天下午"+time;
					}
				}else if (day >= 1 &&day<2) {
					if(ap==0){
						show = "明天上午"+time;
					}else{
						show = "明天下午"+time;
					}
				}else{
					SimpleDateFormat format1 = new SimpleDateFormat("MM.dd");
					if(ap==0){
						show = format1.format(date)+"上午"+time;
					}else{
						show = format1.format(date)+"下午"+time;
					}
				}
			}
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (var != null) {
			pageContext.setAttribute(var, show);
		} else {
			try {
				pageContext.getOut().print(show);
			} catch (IOException ioe) {
				throw new JspTagException(ioe.toString(), ioe);
			}
		}

		// try {
		// out.print(show);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return super.doEndTag();

	}
	private String var;
	private String hasBr;
	
	public String getHasBr() {
		return hasBr;
	}

	public void setHasBr(String hasBr) {
		this.hasBr = hasBr;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	private String param;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

}
