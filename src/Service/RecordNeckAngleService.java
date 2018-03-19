package Service;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import DBManage.DBManage;

public class RecordNeckAngleService {

	public boolean recordAngle(String angle,String userName) {
		
		//获取系统当前时间
		SimpleDateFormat sdDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String cDateString = sdDateFormat.format(date);
		//随机生成id
		String idString = getUUID();
		
		String insertSqlLocation = "insert into neckstate (neck_angle,username,datetime,id) VALUES ('"+angle+"','"+userName+"','"+cDateString+"','"+idString+"')";
		
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
