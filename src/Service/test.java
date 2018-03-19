package Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.collections.functors.IfClosure;

public class test {
	//获取系统当前时间
			SimpleDateFormat sdDateFormat  = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String cDateString = sdDateFormat.format(date);
			String dateTimeS = cDateString+" 06:00:00";
			String dateTimeE = cDateString+" 09:00:00";
	
			String sql = "select * from  dayneckstate  where  datetime  between  "+dateTimeS+"  and  "+dateTimeE+"";
			
			
}
