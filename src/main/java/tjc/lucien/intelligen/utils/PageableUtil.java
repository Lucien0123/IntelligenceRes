package tjc.lucien.intelligen.utils;

import org.apache.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */
public class PageableUtil {
    public static Logger log = Logger.getLogger(PageableUtil.class);

    /**
     * 根据orderStr获取降序分页
     * @param pageNumber
     * @param pageSize
     * @param orderStr
     * @return
     */
    public static Pageable returnDESCPageable(int pageNumber, int pageSize, String orderStr){
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, orderStr);
        log.info("根据"+ orderStr + "降排序获取第" + pageNumber + "页，的" + pageSize + "条数据.");
        final List<Sort.Order> orders = new ArrayList<>();
        orders.add(order);
        Pageable pageable = new PageRequest(pageNumber, pageSize, new Sort(orders));
        return pageable;
    }

    /**
     * 根据orderStr获取升序分页
     * @param pageNumber
     * @param pageSize
     * @param orderStr
     * @return
     */
    public static Pageable returnASCPageable(int pageNumber, int pageSize, String orderStr){
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, orderStr);
        log.info("根据"+ orderStr + "升排序获取第" + pageNumber + "页，的" + pageSize + "条数据.");
        final List<Sort.Order> orders = new ArrayList<>();
        orders.add(order);
        Pageable pageable = new PageRequest(pageNumber, pageSize, new Sort(orders));
        return pageable;
    }

}
