package Coupon_Project_2.Repository;

import Coupon_Project_2.Entity.Category;
import Coupon_Project_2.Entity.Company;
import Coupon_Project_2.Entity.Coupon;
import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {



    void deleteCompanyById(int CompanyId);
    //check by name and email if company exists.
    boolean existsByNameOrEmail(String name, String email);

    //check by email if company exists.
    boolean existsByEmail(String email);

    //check by id if company exists.
    boolean existsById(int id);

    //update company details.
    @Transactional
    @Modifying
    @Query(value = "update company c set c.email= ?1 where c.id= ?2", nativeQuery = true)
    void setFixedEmailFor(String companyNewEmail, int id);

    Optional<Company> findByEmailAndPassword(String email, String password);


}
