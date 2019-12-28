package tcu.lucien.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcu.lucien.entity.Product;

import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 */
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    public List<Product> findByType(Integer type);

    public Product findById(Integer productId);

    public List<Product> findByCateid(int cateId);
}
