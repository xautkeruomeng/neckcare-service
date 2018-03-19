package Service;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.util.Calendar; 

import DBManage.DBManage;

public class GetTimeService {

	public static String timeStart;
	public static String timeEnd;
	public static String dateTimeS;
	public static String dateTimeE;
	public static String cDateStringS;
	public static String cDateStringE;
	public static String DayTimeS;
	public static String DayTimeE;
	public static Date date1;
	public static Date date2;
	public static String sql;
	public static int sumUsephonetime = 0;
	public static int sumUsephonetimeD = 0;
	public static int sumUsephonetimeY = 0;
	public static int Year =0;
	public static int Month = 0;
	
	public List<Map<String,Object>> getTime(String userName) {
		
		
        Calendar cale = null;  
        cale = Calendar.getInstance();  
		List<Map<String,Object>> listA= new ArrayList<Map<String,Object>>();
			
		SimpleDateFormat sdDateFormat  = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String cDateString = sdDateFormat.format(date);
		dateTimeS = cDateString+" 06:00:00";
		dateTimeE = cDateString+" 09:00:00";
		//时间段
		for(int i = 0;i<8;)
		{
		//累加时间
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    Date one;  
	    Date two;  
	    long days=0;  	        
	    try {
			one = df.parse(dateTimeS);
			long time1 = one.getTime(); 
			long timeS = time1+3*60*60*1000*i;
		    Date d1 = new Date(timeS);
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    timeStart = sdf.format(d1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
	    try {
			two = df.parse(dateTimeE);
			long time2 = two.getTime();
			long timeE = time2+3*60*60*1000*i;
			Date d2 = new Date(timeE);
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    timeEnd = sdf.format(d2);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		
		String sql = "select * from  dayneckstate  where username = '"+userName+"' And datetime  between  '"+timeStart+"'  and  '"+timeEnd+"'";
		//获取DB对象
		DBManage sqlDbManage = DBManage.creatInstance();
		sqlDbManage.connectDB(); 
		try {
			//操作DB对象 
			ResultSet resultSet = sqlDbManage.excuteQuery(sql);
			int count = 0;
			
			while(resultSet.next()){
				count++;
				Map<String,Object> map = new HashMap<String, Object>();
				int usephonetime = resultSet.getInt("usephonetime");
				sumUsephonetime = usephonetime + sumUsephonetime;
				map.put("usephonetime", sumUsephonetime);		
				listA.add(map);	
			}
			if(count == 0){
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("usephonetime", sumUsephonetime);		
				listA.add(map);	
			}
			sumUsephonetime = 0;
					
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		i++;
		}
		
		
		
		//每日
        // 获取当月第一天 
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");    
        // 获取前月的第一天  
        cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, 0);  
        cale.set(Calendar.DAY_OF_MONTH, 1);  
        cDateStringS = format.format(cale.getTime()); 
        //获取第五天
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
	    Date one1;  	  
	    long days1=0;  	        
	    try {
			one1 = df.parse(cDateStringS);
			long time11 = one1.getTime(); 
			long timeS1 = time11+24*60*60*1000*4;
		    Date d1 = new Date(timeS1);
		    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		    cDateStringE = sdf1.format(d1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
	    
		try {
			date1 = df.parse(cDateStringS);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    
		try {
			date2 = df.parse(cDateStringE);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		for(int j = 0;j <6;)
		{
			//累加时间
			  Calendar calenS = Calendar.getInstance();
			  Calendar calenE = Calendar.getInstance();
			  //日历对象默认的日期为当前日期，调用setTime设置该日历对象的日期为程序中指定的日期
			  calenS.setTime(date1);
			  calenE.setTime(date2);
			  //将日历的"天"增加5
			  calenS.add(Calendar.DAY_OF_YEAR,5*j);
			  calenE.add(Calendar.DAY_OF_YEAR,5*j);
			  //获取日历对象的时间，并赋给日期对象c
			  Date cS=calenS.getTime();
			  Date cE=calenE.getTime();
			  //用f格式化c并输出
			  DayTimeS = df.format(cS);
			  DayTimeE = df.format(cE);
		    
		String sqlD = "select SUM(usephonetime) AS usephonetime from  dayneckstate  where username = '"+userName+"' And datetime  between  '"+DayTimeS+" 00:00:00'  and  '"+DayTimeE+" 23:59:59'";
		//获取DB对象
		DBManage sqlDbManage = DBManage.creatInstance();
		sqlDbManage.connectDB(); 
		try {
			//操作DB对象 
			ResultSet resultSet = sqlDbManage.excuteQuery(sqlD);
			int count = 0;		
			while(resultSet.next()){
				count++;
				Map<String,Object> map = new HashMap<String, Object>();
				int usephonetime = resultSet.getInt("usephonetime");
				sumUsephonetimeD = usephonetime + sumUsephonetimeD;
				map.put("usephonetime", sumUsephonetimeD);		
				listA.add(map);	
			}
			sumUsephonetimeD = 0;
					
		
		} catch (Exception e) {
			// TODO: handle exception
		}
j++;	
}
		
		
		//每年
		
		for(int k =0;k<12;k++){
			Calendar a=Calendar.getInstance();
			
			Year = a.get(Calendar.YEAR);//得到年
			Month = a.get(Calendar.JANUARY)+k;//由于月份是从0开始的所以加1
			if(k<10){
			 sql = "select SUM(usephonetime) AS usephonetime from dayneckstate where username = '"+userName+"' And datetime like '"+Year+"-0"+Month+"%';";
			}
			else {
			 sql = "select SUM(usephonetime) AS usephonetime from dayneckstate where username = '"+userName+"' And datetime like '"+Year+"-"+Month+"%';";
	
			}//获取DB对象
			DBManage sqlDbManage = DBManage.creatInstance();
			sqlDbManage.connectDB(); 
			try {
				//操作DB对象 
				ResultSet resultSet = sqlDbManage.excuteQuery(sql);
				int count = 0;		
				while(resultSet.next()){
					count++;
					Map<String,Object> map = new HashMap<String, Object>();
					int usephonetime = resultSet.getInt("usephonetime");
					sumUsephonetimeY = usephonetime + sumUsephonetimeY;
					map.put("usephonetime", sumUsephonetimeY);		
					listA.add(map);
					sumUsephonetimeY = 0;
				}
						
			
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	

		return listA;
	}
}
