package tcu.lucien.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcu.lucien.entity.Item;

/**
 * Created by Administrator on 2016/12/25.
 */
@Repository
public interface ItemDao extends JpaRepository<Item, Integer> {

}
