package tjc.lucien.intelligen.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjc.lucien.intelligen.entity.ParkCompany;

/**
 * Created by Administrator on 2017/3/16.
 */
@Repository
public interface ParkCompanyDao extends JpaRepository<ParkCompany, Integer>{

    /* 根据Id获取公司 */
    ParkCompany findById(int id);

    /* 根据Id获取公司 */
    Page<ParkCompany> findById(int id, Pageable pageable);

    /* 根据名称获取公司 */
    ParkCompany findByComName(String comName);

    /* 获取已经发布的公司 */
    Page<ParkCompany> findByIsPassAuth(boolean b, Pageable pageable);
}
