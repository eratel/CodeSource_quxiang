/**
 * Copyright 2002-2008 BULL SAS, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of BULL SAS, Inc.
 * Use is subject to license terms.
 */
package org.etoak.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

/**
 * Here is Interface or Class Internal helper general description.
 * 
 * <p>
 * Here is other notice point or description
 * 
 * @author LJY
 * @since 1.0
 * 
 */
public class DateTimeUtil {

	private static Logger logger = Logger.getLogger(DateTimeUtil.class);

	public static Timestamp nowTimestamp() {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		return stamp;
	}

	public static Date nowDate() {
		return new Date();
	}

	public static String toDateString(Date theDate, String pattern) {
		Assert.notNull(theDate);
		String result = "";

		try {

			SimpleDateFormat b = new SimpleDateFormat(pattern);
			b.setLenient(false);
			result = b.format(theDate);

		} catch (Exception e) {

			logger.error("toDateString error: " + e.getMessage());

			Calendar calc = GregorianCalendar.getInstance();
			calc.setTime(theDate);

			int tmpYear = calc.get(Calendar.YEAR);
			int tmpMonth = (calc.get(Calendar.MONTH) + 1);
			int tmpDay = calc.get(Calendar.DAY_OF_MONTH);
			result = tmpDay + "/" + tmpMonth + "/" + tmpYear;

		}

		if (logger.isDebugEnabled())
			logger.debug("toDateString result:" + result);

		return result;

	}
	
	public static String getCurrentYear()
	{
		Calendar calc = GregorianCalendar.getInstance();
		int year = calc.get(Calendar.YEAR);
		return Integer.toString(year);
	}

	// yyyyMMddHHmm
	public static boolean validateDate(String sDate, String pattern) {
		try {
			SimpleDateFormat b = new SimpleDateFormat(pattern);
			b.setLenient(false);
			Date a = b.parse(sDate);
			if (a.getTime() > 0)
				return true;
		} catch (ParseException e) {

			return false;
		}

		return true;
	}

	public static Date toDate(String sDate, String pattern) {
		Date a = null;

		try {
			SimpleDateFormat b = new SimpleDateFormat(pattern);
			b.setLenient(false);
			a = b.parse(sDate);

		} catch (ParseException e) {
			logger.error("toDate function error: " + e.getMessage());
			return new Date();
		}

		return a;
	}

	public static String getFixedImpDate() {
		Calendar calc = GregorianCalendar.getInstance();
		int tmpYear = calc.get(Calendar.YEAR);
		int tmpMonth = (calc.get(Calendar.MONTH) + 1);
		int tmpDay = calc.get(Calendar.DAY_OF_MONTH);
		String strImpDate = tmpMonth + "/" + tmpDay + "/" + tmpYear;
		return strImpDate;
	}

	public static String shortSDate(String strDate) {
		SimpleDateFormat b = new SimpleDateFormat("MM/dd/yyyy");
		Date d = toDate(strDate, "MM/dd/yyyy");
		String sDate = null;
		sDate = b.format(d);
		return sDate;
	}

	
    public static String timestampToString(Timestamp ts,String foramt){
    	
    	java.util.Date date=new java.util.Date();
    	date.setTime(ts.getTime());
    	SimpleDateFormat sdf=new SimpleDateFormat(foramt);
    	return sdf.format(date);
    }
    
    public static Timestamp stringToTimestamp(String str,String foramtOri){
        SimpleDateFormat sdf1=new SimpleDateFormat(foramtOri);
        
        
        java.util.Date date=new java.util.Date();
        try {
            date=sdf1.parse(str);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        java.sql.Timestamp time=new java.sql.Timestamp(date.getTime());
        
        return time;
    }
    
    public static java.sql.Date stringToSqlDate(String str,String foramtOri){
    	SimpleDateFormat sdf1=new SimpleDateFormat(foramtOri);
    	
    	
    	java.util.Date date=new java.util.Date();
    	try {
			date=sdf1.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	java.sql.Date time=new java.sql.Date(date.getTime());
    	
    	return time;
    }
    
    public static int compareTimestamp(Timestamp t1,Timestamp t2){
    	if(t1.getTime()>t2.getTime()){
    		return 1;
    	}else if(t1.getTime()==t2.getTime()){
    	    return 0;
    	}else {
    		return -1;
    	}
    	
    } 
    
    public static int compareDate(Date t1,Date t2){
    	if(t1.getTime()>t2.getTime()){
    		return 1;
    	}else if(t1.getTime()==t2.getTime()){
    	    return 0;
    	}else {
    		return -1;
    	}
    	
    } 
    
    
	public static void main(String args[]) {
		// System.out.println(toDateString(new Date(), "dd/MM/yyyy"));
		// System.out.println(toDateString(new Date(), "MM/dd/yyyy"));
		// System.out.println(toDateString(new Date(), "MM-dd-yyyy"));
		// System.out.println(toDateString(new Date(), "yyyy-MM-dd"));
		// System.out.println(toDateString(new Date(), "yyyy/MM/dd"));
		// System.out.println(shortSDate("01/08/2008 00:00:00"));
/*		System.out.println(validateDate("10/25/2008", "dd/MM/yyyy"));
		
		for(int i=0;i<5;i++){
			for (int j=10;j<15;j++){
				System.out.println(i+"--i--"+j+"--j");
				if(j==12){
					continue;
				}
			}
		}*/

	}

}
