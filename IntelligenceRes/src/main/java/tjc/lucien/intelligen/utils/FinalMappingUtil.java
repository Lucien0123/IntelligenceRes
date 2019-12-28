package tjc.lucien.intelligen.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
public class FinalMappingUtil {

    public static final String PARKNAME = "天津产业园区";

    public static final String PUBLISHJOB = "发布招聘信息";

    public static final String PUBLISHNEWS = "发布新闻公告";

    public static final String CUR_ADMINER = "cur_adminer";

    public static final String DELIVERRED = "已投递";

    public static final String NOSUIT = "不合适";

    public static final String NOTICEINTERVIEW = "通知面试";

    public static final String PASSINTERVIEW = "通过面试";

    public static final String PARKADDRESS = "天津市-西青区-产业园区";

    public static final String QUESTIONLABEL = "面试问题&br&公司情况&br&园区企业";

    public static final List<String> BUSSINESSFORM = new ArrayList<String>(){
        {
            add("移动互联网");
            add("电子商务");
            add("金融");
            add("企业服务");
            add("教育");
            add("文化娱乐");
            add("游戏");
            add("O2O");
            add("硬件");
        }
    };

    /* 薪资 */
    public static final List<String> JOBSALARYS = new ArrayList<String>(){
        {
            add("1K-2K");
            add("2K-5K");
            add("5K-10K");
            add("10K-15K");
            add("15K以上");
        }
    };

    /* 学历要求 */
    public static final List<String> EDUCATIONS = new ArrayList<String>(){
        {
            add("不要求");
            add("大专");
            add("本科");
            add("硕士");
            add("博士");
        }
    };

    /* 工作形式实习、兼职、全职 */
    public static final List<String> WORKPOSITIONS = new ArrayList<String>(){
        {
            add("兼职");
            add("实习");
            add("全职");
        }
    };

    /* 工作经验要求 */
    public static final List<String> WORKEXPERIENCES = new ArrayList<String>(){
        {
            add("不要求");
            add("应届毕业生");
            add("3年及以下");
            add("3-5年");
            add("10年以上");
            add("5-10年");
        }
    };

}
