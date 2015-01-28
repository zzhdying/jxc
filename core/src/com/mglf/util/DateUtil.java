package com.mglf.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mglf.util.DateUtil;
import com.mglf.util.EmptyUtil;

import com.mglf.util.DateUtil;
import com.mglf.util.EmptyUtil;

public class DateUtil {

	protected static final Log LOG = LogFactory.getLog(DateUtil.class);

	public static final int RESULT_FROM_DATE_ERROR = 1;

	public static final int RESULT_TO_DATE_ERROR = 2;

	public static final int RESULT_FROM_DATE_AFTER_TO_DATE = 3;

	public static final int RESULT_SUCCESS = 0;

	
	private static final SimpleDateFormat DF_YMDHMS = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

	

	/**
	 * 计算两个时间差多少个月年
	 * @param startDate 开始时间
	 * @param endDate	接受时间
	 * @return
	 */
	public static String getBetween(Date startDate, Date endDate) {
		String str = "";
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(endDate);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(startDate);
		int c = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
		if (c < 12) {
			str = c + "月";
		} else {
			if (c % 12 == 0) {
				str = Integer.valueOf((c / 12)) + "年";
			} else {
				str = Integer.valueOf((c / 12)) + "年" + (c % 12) + "月";
			}
		}

		return str;
	}


	/**
	 * 计算两个日期之间相差的月数 格式为：2011-02-02 或2011/02/01
	 * 
	 * @param startDate
	 *            :开始时间
	 * @param endDate
	 *            :结束时间
	 * @return int 两个时间之间的月数
	 */
	public static int getMonthsBetween(String startDate, String endDate) {
		String lastYear = startDate.substring(0, 4);
		String lastMonth = startDate.substring(5, 7);
		String registYear = endDate.substring(0, 4);
		String registMonth = endDate.substring(5, 7);
		int year2 = Integer.parseInt(lastYear);
		int year1 = Integer.parseInt(registYear);
		int month2 = Integer.parseInt(lastMonth);
		int month1 = Integer.parseInt(registMonth);
		int months;
		if (month2 < month1) {
			months = month2 + 10 - month1 + (year2 - year1) * 12;
		} else {
			months = month2 - month1 + (year2 - year1) * 12;
		}
		return months;

	}


	public static String getSelTime(String year, String month, String day,
			String hour, String minute) {

		StringBuffer sb = new StringBuffer();
		if (!StringUtils.isBlank(year))
			sb.append(year);
		else
			sb.append("9999");
		if (!StringUtils.isBlank(month))
			sb.append(month);
		else
			sb.append("99");
		if (!StringUtils.isBlank(day))
			sb.append(day);
		else
			sb.append("99");
		if (!StringUtils.isBlank(hour))
			sb.append(hour);
		else
			sb.append("99");
		if (!StringUtils.isBlank(minute))
			sb.append(minute);
		else
			sb.append("99");
		return sb.toString();
	}

	public static String getSelDate(String year, String month, String day) {

		StringBuffer sb = new StringBuffer();

		if (!StringUtils.isBlank(year))
			sb.append(year);
		else
			sb.append("9999");
		if (!StringUtils.isBlank(month))
			sb.append(month);
		else
			sb.append("99");
		if (!StringUtils.isBlank(day))
			sb.append(day);
		else
			sb.append("99");
		return sb.toString();
	}

	public static String getPassTime(long timeGap) {
		if (timeGap < 0)
			return "00:00:00";

		DecimalFormat df = new DecimalFormat("00");
		long hour = timeGap / 60 / 60;

		long minute = timeGap / 60 - hour * 60;

		long second = timeGap - hour * 60 * 60 - minute * 60;

		StringBuffer sb = new StringBuffer();
		sb.append(df.format(hour));
		sb.append(":");
		sb.append(df.format(minute));
		sb.append(":");
		sb.append(df.format(second));

		System.out.println(sb.toString());
		return sb.toString();
	}

	

	public static boolean judgeTime(String str) {
		try {
			String[] array = StringUtils.split(":");
			int hour = Integer.parseInt(array[0]);
			int minute = Integer.parseInt(array[1]);
			int second = Integer.parseInt(array[2]);
			if (hour > 24 || hour < 0 || minute >= 60 || minute < 0
					|| second >= 60 || second < 0) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// /////////////////////////////////////////

	public static Date parseDateTime(String date, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			return df.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}


	public static String formatYMDHMS(Date date) {
		return ((SimpleDateFormat) DF_YMDHMS.clone()).format(date);
	}

	

	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }  
    
    /**
     * 计算开始时间+N天
     * @param startDate
     * @param days
     * @return Date
     * @throws ParseException 
     */
    public static Date dateAddDays(Date startDate,int days) throws ParseException{
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
    	 startDate=sdf.parse(sdf.format(startDate));  
         Calendar cal = Calendar.getInstance();    
         cal.setTime(startDate);  
   
         int Year = cal.get(Calendar.YEAR);  
         int Month = cal.get(Calendar.MONTH);  
         int Day = cal.get(Calendar.DAY_OF_MONTH);  
   
         int NewDay = Day + days;  
   
         cal.set(Calendar.YEAR, Year);  
         cal.set(Calendar.MONTH, Month);  
         cal.set(Calendar.DAY_OF_MONTH, NewDay);  
   
         return new Date(cal.getTimeInMillis()); 
    }
    
    /**
     * 计算开始时间+N小时
     * @param startDate
     * @param days
     * @return Date
     * @throws ParseException 
     */
    public static Date dateAddHour(Date startDate,int h) throws ParseException{
    	Calendar cal = Calendar.getInstance();    
    	cal.setTime(startDate);  
    	cal.add(Calendar.HOUR,h);
    	return cal.getTime();
    }
	/**
	 * 申请时间距离当前时间过去了多少时间
	 * @param applyDate
	 * @return string
	 * 			多少秒前
	 * 			多少分钟前
	 * 			多少小时前
	 * 			昨天几点
	 * 			前天几点
	 * 			几月几日几时几分几秒
	 * @throws ParseException 
	 */
	public static String applyDayFromNow(Date applyDate) throws ParseException {
		if (null == applyDate) {
			return "";
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(applyDate);
		Date now = new Date();
		long time1 = now.getTime();
		long time2 = cal.getTimeInMillis();
		
		// 秒数
		long seconds = (time1 - time2) / 1000;
		if (seconds < 60) {
			if(seconds < 0){
				return "0秒前";
			}
			return seconds + "秒前";
		}
		// 分钟
		long minutes = seconds / 60;
		if (minutes < 60) {
			return minutes + "分钟前";
		}

		// 小时
		long hours = minutes / 60;
		if (hours < 24) {
			return hours + "小时前";
		}

		// 天数
		int dys = DateUtil.daysBetween(applyDate, now);
		// 小时
		int dayhours = cal.get(Calendar.HOUR_OF_DAY);
		if (1 <= dys && dys < 2) {
			return "昨天" + dayhours + "点";
		}
		if (2 <= dys && dys < 3) {
			return "前天" + dayhours + "点";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(applyDate);
	}


	
	/**
	 * 申请时间距离当前时间还有多少时间
	 * @param applyDate
	 * @return string
	 * 			多少秒后
	 * 			多少分钟后
	 * 			多少小时后
	 * 			明天天几点
	 * 			后天天几点
	 * 			几月几日几时几分几秒
	 * @throws ParseException 
	 */
	public static String applyDayAfterNow(Date applyDate) throws ParseException {
		if (null == applyDate) {
			return "";
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(applyDate);
		Date now = new Date();
		long time1 = now.getTime();
		long time2 = cal.getTimeInMillis();
		
		// 秒数
		long seconds = (time2 - time1) / 1000;
		if (seconds < 60) {
			if(seconds < 0){
				//applyDayFromNow
			 return	applyDayFromNow(applyDate);
				//return "0秒后";
			}
			return seconds + "秒后";
		}
		// 分钟
		long minutes = seconds / 60;
		if (minutes < 60) {
			return minutes + "分钟后";
		}

		// 小时
		long hours = minutes / 60;
		if (hours < 24) {
			return hours + "小时后";
		}

		// 天数
		int dys = DateUtil.daysBetween(applyDate, now);
		// 小时
		int dayhours = cal.get(Calendar.HOUR_OF_DAY);
		if (1 <= dys && dys < 2) {
			return "明天" + dayhours + "点";
		}
		if (2 <= dys && dys < 3) {
			return "后天" + dayhours + "点";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(applyDate);
	}
	

	/**
	 * 面试时间距离当前时间还有多少时间
	 * @param applyDate
	 * @return string
	 * 			多少秒后
	 * 			多少分钟后
	 * 			多少小时后
	 * 			明天天几点
	 * 			后天天几点
	 * 			几月几日几时几分几秒
	 * @throws ParseException 
	 */
	public static String interviewDayAfterNow(Date applyDate) throws ParseException {
		if (null == applyDate) {
			return "";
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(applyDate);
		Date now = new Date();
		long time1 = now.getTime();
		long time2 = cal.getTimeInMillis();
		
		// 秒数
		long seconds = (time2 - time1) / 1000;
		if (seconds < 60) {
			if(seconds < 0){
				return "0秒后";
			}
			return seconds + "秒后";
		}
		// 分钟
		long minutes = seconds / 60;
		if (minutes < 60) {
			return minutes + "分钟后";
		}

		// 小时
		long hours = minutes / 60;
		if (hours < 24) {
			return hours + "小时后";
		}

		// 天数
		int dys = DateUtil.daysBetween(applyDate, now);
		// 小时
		int dayhours = cal.get(Calendar.HOUR_OF_DAY);
		if (1 <= dys && dys < 2) {
			return "明天" + dayhours + "点";
		}
		if (2 <= dys && dys < 3) {
			return "后天" + dayhours + "点";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(applyDate);
	}

	

	/**
	 * 根据生日计算年龄
	 * @param brithDate
	 * @return int
	 * 			多少岁
	 * @throws ParseException 
	 */
	public static int getAge(Date brithDate) throws ParseException {
		if (null == brithDate) {
			return 0;
		}
		
		Calendar brithCal = Calendar.getInstance();
		brithCal.setTime(brithDate);
		Calendar nowCal = Calendar.getInstance();
		nowCal.setTime(new Date());
		nowCal.add(Calendar.DATE, -brithCal.get(Calendar.DATE));
		nowCal.add(Calendar.MONTH, -brithCal.get(Calendar.MONTH));
		nowCal.add(Calendar.YEAR, -brithCal.get(Calendar.YEAR));
		
		return nowCal.get(Calendar.YEAR);
	}
	
	
	/**
	 * 相关距离当前时间过去了多少时间
	 * @param refDate
	 * @return string
	 * 			多少秒前
	 * 			多少分钟前
	 * 			多少小时前
	 * 			多少天前
	 * @throws ParseException 
	 */
	public static String refDayFromNow(Date refDate) throws ParseException {
		if (null == refDate) {
			return "";
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(refDate);
		Date now = new Date();
		long time1 = now.getTime();
		long time2 = cal.getTimeInMillis();
		
		// 秒数
		long seconds = (time1 - time2) / 1000;
		if (seconds < 60) {
			if(seconds < 0){
				return "0秒前";
			}
			return seconds + "秒前";
		}
		// 分钟
		long minutes = seconds / 60;
		if (minutes < 60) {
			return minutes + "分钟前";
		}

		// 小时
		long hours = minutes / 60;
		if (hours < 24) {
			return hours + "小时前";
		}

		// 天数
		int dys = DateUtil.daysBetween(refDate, now);
		
		return dys + "天前";

	}
	/**
	 * 现在到相关时间还有多少时间
	 * @param applyDate
	 * @return string
	 * 			多少秒后
	 * 			多少分钟后
	 * 			多少小时后
	 * 			多少天后
	 * @throws ParseException 
	 */
	public static String refDayAfterNow(Date applyDate) throws ParseException {
		if (null == applyDate) {
			return "";
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(applyDate);
		Date now = new Date();
		long time1 = now.getTime();
		long time2 = cal.getTimeInMillis();
		
		// 秒数
		long seconds = (time2 - time1) / 1000;
		if (seconds < 60) {
			if(seconds < 0){
				return "0秒后";
			}
			return seconds + "秒后";
		}
		// 分钟
		long minutes = seconds / 60;
		if (minutes < 60) {
			return minutes + "分钟后";
		}

		// 小时
		long hours = minutes / 60;
		if (hours < 24) {
			return hours + "小时后";
		}

		// 天数
		int dys = DateUtil.daysBetween(now,applyDate);
		return dys + "天后";
	}
	
	/**
	 * 多少天前
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String overdueDaysFromNow(Date date) throws Exception{
		if(EmptyUtil.isEmpty(date)){
			return "";
		}
		Date now=new Date();
		int days=daysBetween(date, now);
		if(0==days){
			return "今天";
		}
		return days+"天前";
	}
	
	/**
	 * 多少天后
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String soonOverdueDayFromNow(Date date) throws Exception{
		Date now=new Date();
		int days=daysBetween(now, date);
		if(0==days){
			return "今天";
		}
		return days+"天后";
	}
	
	public static Date addDay(Date date, int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE,calendar.get(Calendar.DATE) + days);
		return calendar.getTime();
	}
	
	public static Date addWeek(Date date, int weeks){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.WEEK_OF_YEAR,calendar.get(Calendar.WEEK_OF_YEAR) + weeks);
		return calendar.getTime();
	}
	
	public static Date addMonth(Date date, int months){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH) + months);
		return calendar.getTime();
	}
	
	/**
	 * 计算参数时间1年后的日期
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date addYear(Date date, int years){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR) + years);
		return calendar.getTime();
	}
	
	/**
	 * 格式化日期<br>
	 * yyyy/MM/dd HH:mm:ss -> 2014/09/09 15:34:39<br>
	 * yyyy-MM-dd -> 2014-09-09<br>
	 * @param date 日期对象
	 * @param fmtStr 格式字符串，如：yyyy/MM/dd HH:mm:ss
	 * @return 格式化日期字符串
	 * @author Gordon
	 * @since 2014-09-09
	 */
	public static String formatDate(Date date, String fmtStr){
		SimpleDateFormat format = new SimpleDateFormat(fmtStr);
		return format.format(date);
	}
	
	public static void main(String[] args) throws ParseException {
		//System.out.println(addDay(new Date(),1));
		//System.out.println(addWeek(new Date(),1));
		//System.out.println(addMonth(new Date(),1));
		//System.out.println(addYear(new Date(),1));
		//System.out.println(getBetween(addYear(new Date(),1), addYear(new Date(),2)));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(dateAddHour(new Date(),16)));
	}
	
}
