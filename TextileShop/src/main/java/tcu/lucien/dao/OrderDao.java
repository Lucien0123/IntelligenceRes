package tcu.lucien.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcu.lucien.entity.Orders;

import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 */
@Repository
public interface OrderDao extends JpaRepository<Orders, Integer> {

    public List<Orders> findByBuyerId(Integer userId);
}
