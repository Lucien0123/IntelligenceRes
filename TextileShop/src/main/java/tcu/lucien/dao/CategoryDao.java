package tcu.lucien.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcu.lucien.entity.Category;

/**
 * Created by Administrator on 2016/11/4.
 */
@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

}
