package Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import DBManage.DBManage;

public class RecordTimeService {

public boolean recordTime(String time,String userName,String avergeAngle,String maxAngle) {
		
		
		//随机生成id
		String idString = getUUID();
		//获取系统当前时间
		SimpleDateFormat sdDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String cDateString = sdDateFormat.format(date);
		
		String insertSqlLocation = "insert into dayneckstate (usephonetime,username,averneckangle,maxneckangle,id,datetime) VALUES ('"+time+"','"+userName+"','"+avergeAngle+"','"+maxAngle+"','"+idString+"','"+cDateString+"')";
		
		//获取DB对象
		DBManage sqlDbManage = DBManage.creatInstance();
		sqlDbManage.connectDB(); 
		
		//操作DB对象查询
		try {
			int ret = sqlDbManage.executeUpdate(insertSqlLocation);
			if(ret != 0){
			    sqlDbManage.closeDB();
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		sqlDbManage.closeDB();
		return false;	
	}
	
	public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
        String uuidStr=str.replace("-", "");
        return uuidStr;
      }
}
