package tcu.lucien.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tcu.lucien.entity.Address;

import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 */
@Transactional
@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {

    //去除默认地址
    @Modifying(clearAutomatically = true)
    @Query("update Address address set address.defaultvalue = false where address.username =:username")
    public void defaultAddress(@Param("username") String username);

    //设置新的默认地址
    @Modifying(clearAutomatically = true)
    @Query("update Address address set address.defaultvalue = true where address.username =:username and address.id=:addressid")
    public void defaultAddress(@Param("username") String username, @Param("addressid") Integer addressid);


    public List<Address> findByUsername(String username);

    public Address findById(Integer addressid);
}
