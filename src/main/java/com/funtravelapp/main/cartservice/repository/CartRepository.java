package com.funtravelapp.main.cartservice.repository;

import com.funtravelapp.main.cartservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
//    @Query("SELECT c FROM Cart c WHERE c.customerId = ?1 AND c.packageId = ?2")
//    Boolean existsByCustomerIdAndPackageId(int customerId, int packageId);

    @Query("SELECT c FROM Cart c WHERE c.customerId = ?1")
    List<Cart> findCartByCustomerId(int customerId);

    @Query("SELECT c FROM Cart c WHERE c.customerId = ?1 AND packageId = ?2")
    Cart findByCustomerIdAndPackageId(Integer customerId, Integer packageId);
}
