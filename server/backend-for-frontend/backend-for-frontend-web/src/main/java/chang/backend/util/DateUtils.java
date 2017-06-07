package chang.backend.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chang on 2017/3/18.
 */
public class DateUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String convert2DateStr(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return formatter.format(date);
    }

}
