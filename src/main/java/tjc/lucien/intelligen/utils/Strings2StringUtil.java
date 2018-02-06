package tjc.lucien.intelligen.utils;

import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017/3/21.
 */
public class Strings2StringUtil {

    public static Logger log = Logger.getLogger(Strings2StringUtil.class);

    /**
     * @param strings
     * @return
     * @DESC 将字符串数组转化为字符串，用&br&分割，便于存储数据库
     */
    public static String Strings2String(String[] strings) {

        String str = new String("");
        if (strings != null && strings.length > 0) {
            for (int i=0;i<strings.length;i++) {
                if (i == 0) {
                    log.info("字符串数组的第一个元素值：" + strings[i]);
                    str += strings[i];
                } else {
                    str += "&br&" + strings[i];
                }
            }
        }
        log.info("strs转化后的字符串：" + str);
        return str;
    }
}
