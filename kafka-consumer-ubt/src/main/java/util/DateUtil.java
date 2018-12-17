package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by x_deng on 2017/12/8.
 */
public class DateUtil {

    public static String getWeek(){
        Date date=new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        return  dateFm.format(date);
    }

    public static String getTime(){

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String getTimeStamp(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public static String getDate(){

        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
