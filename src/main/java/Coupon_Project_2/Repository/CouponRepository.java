package Coupon_Project_2.Repository;

import Coupon_Project_2.Entity.Category;
import Coupon_Project_2.Entity.Coupon;
import lombok.Value;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    //check by title and company id if coupon exist and return true or false.
    boolean existsByTitleAndCompany_Id(String title, int CompanyId);

    //check by id if coupon exist and return true or false.
    boolean existsById(int id);

    //delete coupon by id coupon and id company.
    @Transactional
    @Modifying
    void deleteByIdAndCompany_id(int id, int companyId);

    //get one coupon by id.
    Coupon findById(int id);


    //update coupon details.
    @Transactional
    @Modifying
    @Query(value = "update coupon c set c.amount= ?1, c.category= ?2, c.description= ?3, c.end_date= ?4, c.image= ?5, c.price= ?6, c.start_date= ?7, c.title= ?8 where c.id= ?9", nativeQuery = true)
    void setFixedAmountAndCategoryAndDescriptionAndEnd_dateAndImageAndPriceAndStart_dateAndTitleFor(int amount, int category, String description, String end_date, String image, double price, String start_date, String Title, int couponId);

    //get all company coupons.
    @Query(value = "select * from coupon c where c.company_id = :company_id", nativeQuery = true)
    List<Coupon> getAllByCompany_Id(@Param("company_id") int companyId);

    // get all company coupons by category.
    @Query(value = "select * from coupon c where c.category= :categoryChoose and c.company_id= :company_id", nativeQuery = true)
    List<Coupon> findByCategoryAndCompany_Id(@Param("categoryChoose") int choosedCategory, @Param("company_id") int companyId);

    //get all company coupons by max price.
    @Query(value = "select * from coupon c where c.price <= :maxPrice and c.company_id= :company_id ", nativeQuery = true)
    List<Coupon> getCouponsByMaxPriceAndCompany_id(@Param("maxPrice") double maxPrice, @Param("company_id") int companyId);

    //insert purchased coupon to customer list(customer-vs-coupon).
    @Transactional
    @Modifying
    @Query(value = "insert into customer_vs_coupon (customer_id, coupon_id) values (:customerId, :couponId)", nativeQuery = true)
    void insertCustomer_vs_coupon(@Param("customerId") int customerId, @Param("couponId") int couponId);

    //update amount coupon after the customer get one.
    @Transactional
    @Modifying
    @Query(value = "update coupon c set c.amount = amount - ?1 where c.id= ?2", nativeQuery = true)
    void setFixedAmountFor(int count, int couponId);

    //delete coupon from the customer list.
    @Transactional
    @Modifying
    @Query(value = "delete from customer_vs_coupon c where c.customer_id = :customerId and c.coupon_id =:couponId", nativeQuery = true)
    int deleteAllByCustomer_idAndByCoupon_id(@Param("customerId") int customerId, @Param("couponId") int couponId);

    // get all castomer coupons.
    @Query(value = "select p.*, a.* from coupon p, customer_vs_coupon a where p.id=a.coupon_id and a.customer_id= ?1", nativeQuery = true)
    List<Coupon> getAllByCustomer_id(int customerId);

    //get all customer coupons by category.
    @Query(value = "select p.*, a.* from coupon p, customer_vs_coupon a where p.id=a.coupon_id and a.customer_id= ?1 and p.category= ?2", nativeQuery = true)
    List<Coupon> getCouponsByCustomer_idAndByCategory(int customerId, int choosedCategory);

    //get all customer coupons by max price.
    @Query(value = "select p.*, a.* from coupon p, customer_vs_coupon a where p.id=a.coupon_id and a.customer_id= ?1 and p.price<= ?2", nativeQuery = true)
    List<Coupon> getAllByCustomer_idAndByMaxprice(int customerId, double maxPrice);


    //    loocking for coupons that was expired.
    @Transactional
    @Modifying
    @Query(value = "delete from coupon where end_date < :expiredDate", nativeQuery = true)
    int deleteAllByEnd_dateBefore(@Param("expiredDate") String expiredDate);

    // delete only by coupon id
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM customer_vs_coupon WHERE coupon_id IN "
            + "(SELECT id FROM coupon WHERE company_id = ?1)", nativeQuery = true)
    void deletePurchasesByCompanyId(int companyId);




    // delete coupons by company id
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM coupon WHERE company_id = ?",nativeQuery = true)
    void deleteCouponByCompanyId(int companyId);


}