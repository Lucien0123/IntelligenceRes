package tjc.lucien.intelligen.utils;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/21.
 */
public class FormatDate2StringUtil {
    public static Logger log = Logger.getLogger(FormatDate2StringUtil.class);

    public static String formatDate2String(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String res = format.format(date);
        log.info("格式化后的日期字符串：" + res);
        return res;
    }
}
