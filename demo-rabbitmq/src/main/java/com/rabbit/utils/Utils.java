package com.rabbit.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class Utils {

	static private ObjectMapper objectMapper = new ObjectMapper();

	private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);
	/**
	 * 读取json的值
	 * 
	 * @param key
	 * @param valueType
	 * @return
	 */
	static public <T> T readValue(String key, Class<T> valueType) {
		try {
			return objectMapper.readValue(key, valueType);
		} catch (IOException e) {
			LOGGER.error("error",e);
		}

		return null;
	}

	/**
	 * 把对象转为json
	 * 
	 * @param object
	 * @return
	 */
	static public String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			LOGGER.error(Utils.throwAbleToString(e));

		}
		return null;
	}

	/**
	 * 产生一个时间戳yyyyMMddHHmmssSSS
	 * 
	 * @return
	 */
	static public String generateReqId() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String reqId = simpleDateFormat.format(new Date());
		return reqId;
	}

	/**
	 * 产生一个时间戳yyyyMMddHHmmssSSS
	 * 
	 * @return
	 */
	static public String yyyyMMddHHmmss() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String reqId = simpleDateFormat.format(new Date());
		return reqId;
	}
	static public String yyyyMMdd() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String reqId = simpleDateFormat.format(new Date());
		return reqId;
	}
	static public Date parseyyyyMMdd(String value) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		
		return simpleDateFormat.parse(value);
	}
	static public Date parseyyyyMMddHHmmss(String value) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return simpleDateFormat.parse(value);
	}
	static public Date parseyyyy_MM_ddHHmmss(String value) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.parse(value);
	}
	static public String MM() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
		String reqId = simpleDateFormat.format(new Date());
		return reqId;
	}
	static public String MM(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
		return simpleDateFormat.format(date);
	}
	static public String yyyyMMdd(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat.format(date);
	}
	static public String yyyyMMddHHmmss(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return simpleDateFormat.format(date);
	}
	static public String throwAbleToString(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

	static public String transToSqlCondition(ArrayList<String> data) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < data.size(); ++i) {
			String item = "'" + data.get(i) + "'";
			if (i == 0) {
				builder.append(item);
			} else {
				builder.append("," + item);
			}
		}
		return builder.toString();
	}

	static public String transDotToSqlCondition(String data) {
		StringBuilder builder = new StringBuilder();
		String[] split = data.split(",");
		for (int i = 0; i < split.length; ++i) {
			String item = "'" + split[i] + "'";
			if (i == 0) {
				builder.append(item);
			} else {
				builder.append("," + item);
			}
		}
		return builder.toString();
	}
	/**
	 * 通过出生年月日，来计算年龄
	 * 
	 * @param birthYear
	 * @return
	 */
	static public int calAge(Date birthYear){
		Calendar instance = Calendar.getInstance();
		instance.setTime(birthYear);
		Calendar nowCalendar = Calendar.getInstance();
		return nowCalendar.get(Calendar.YEAR)-instance.get(Calendar.YEAR);
	}
	/**
	 * 计算 上一个月
	 * @param date
	 * @return
	 */
	static public Date getBeforeOneMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		return new Date(calendar.getTimeInMillis());
	}
	public static long get24ExpireTime() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		
		long expireTime = (calendar.getTime().getTime()-date.getTime())/1000;
		return expireTime;
	}
	static public <T> T readMapValue(String key, TypeReference<T> valueTypeRef) {
		try {
			return objectMapper.readValue(key, valueTypeRef);
		} catch (IOException e) {
			LOGGER.error("error",e);
		}

		return null;
	}
	/**
	 * 获取当年的第一天
	 */
	public static Date getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}
	/**
	 * 获取某年第一天日期
	 */
	private static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}
	
	/** 
	* 判断给定日期是否为月末的一天 
	* 
	* @param date 
	* @return true:是|false:不是 
	*/ 
	public static boolean isLastDayOfMonth(Date date) { 
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date); 
		calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1)); 
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1) { 
		return true; 
		} 
		return false; 
	} 
	/**
	 * 加减日期
	 * @param dateTime
	 * @param n
	 * @return
	 */
	static public Date addAndSubtractDaysByCalendar(Date dateTime/*待处理的日期*/,int n/*加减天数*/){ 
	     //日期格式 
	     Calendar calstart = Calendar.getInstance();
	     calstart.setTime(dateTime);

		 calstart.add(Calendar.DAY_OF_WEEK, n);
		 return calstart.getTime(); 
	}
	static public String yyyy(Date date){//获取月份
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		String yyyy = simpleDateFormat.format(date);
		return yyyy;
	}
	//求两个时间差值月分
	public static int getMonthDelta(Date startDate, Date endDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        return diffMonth;
    }
	/*
     * 获取当前月第一天
     */
    public static Date getMonthFirstDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		// 时
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		// 分
		calendar.set(Calendar.MINUTE, 0);
		// 秒
		calendar.set(Calendar.SECOND, 0);
		// 毫秒
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
    /**** 
     * 传入具体日期 ，返回具体日期增加一个月。 
     */  
    public static Date getAfterOneMonth(Date date) {  
        Calendar rightNow = Calendar.getInstance();  
        rightNow.setTime(date);  
        rightNow.add(Calendar.MONTH, 1);  
        return rightNow.getTime();  
    }
    
    static public String HHmm(Date date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
		return simpleDateFormat.format(date);
	}
    
    //求取 相差天数
	static public int getDateSpace(String startTime, String endTime) throws ParseException {
		Calendar calst = Calendar.getInstance();
		Calendar caled = Calendar.getInstance();

		calst.setTime(Utils.parseyyyyMMdd(startTime));
		caled.setTime(Utils.parseyyyyMMdd(endTime));
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;

		return days;
	}
    
    //解决fdfs问题
    public static Object getProperty(Object bean, String fieldName) {  

	    Field[] fields = bean.getClass().getDeclaredFields();  
	      
	    Object obj = null;  
	    for (int i = 0; i < fields.length; i++) {  
	        Field field = fields[i];  
	          
	        if (fieldName.equals(field.getName())) {  
	            try {  
	            	field.setAccessible(true);
	                obj = field.get(bean);  
	                break;
	            } catch (IllegalArgumentException e) {  
	               e.printStackTrace();
	            } catch (IllegalAccessException e) {  
	                e.printStackTrace();
	            }  
	        }  
	    }  
	    return obj;   
	}
    
    static public <T> ArrayList<T> readJsonListValue(String key, Class<T> valueType) {
        try {
            CollectionType constructCollectionType = objectMapper.getTypeFactory()
                    .constructCollectionType(ArrayList.class, valueType);
            return objectMapper.readValue(key, constructCollectionType);
        } catch (IOException e) {
            LOGGER.error("readJsonList error", e);
        }
        return null;
    }
	static public void freePointer(Pointer pointer) {
		if (pointer == null) {
			return;
		}
		long peer = Pointer.nativeValue(pointer);
		Native.free(peer);
		Pointer.nativeValue(pointer, 0);
		pointer = null;
	}

	static public String generateUUid(){
    	return UUID.randomUUID().toString().replaceAll("-","");
	}


}
