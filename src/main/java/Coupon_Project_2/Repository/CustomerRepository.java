package Coupon_Project_2.Repository;

import Coupon_Project_2.Entity.Company;
import Coupon_Project_2.Entity.Coupon;
import Coupon_Project_2.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    //check by email if customer exists
    boolean existsByEmail(String email);

    Optional<Customer> findByEmailAndPassword(String email, String password);

    //check by id if customer exists
    boolean existsById(int Id);

    //update customer details
    @Transactional
    @Modifying
    @Query(value = "update customer c set c.first_name= ?1, c.last_name= ?2, c.email= ?3 where c.id= ?4", nativeQuery = true)
    void setFixedFirst_nameAndLast_nameAndEmailFor(String firstName, String lastName, String email, int customerId);

    //experiment
//    @Query(value = "select case  when count(*)>0 then true else false end from customer_vs_coupon where  customer_id= ?1 and  coupon_id= ?2", nativeQuery = true)
//    boolean existsByCustomer_idAndCoupon_id(int customerId, int couponId);

    //delete customer by id.
    void deleteById(int id);

    //get customer by id.
    Customer findById(int Id);


}
