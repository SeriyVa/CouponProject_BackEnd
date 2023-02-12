package Coupon_Project_2.Service;

import Coupon_Project_2.ManagerLogin.ClientType;
import Coupon_Project_2.Security.Information;
import Coupon_Project_2.Security.LoginResponse;
import Coupon_Project_2.Security.TokenManager;
import Coupon_Project_2.SystemExeption.MyCouponSystemExeption;
import Coupon_Project_2.Entity.Company;
import Coupon_Project_2.Entity.Coupon;
import Coupon_Project_2.Repository.CompanyRepository;
import Coupon_Project_2.Repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CompanyService {


   private final CouponRepository couponRepository;
   private final CompanyRepository companyRepository;
   private final TokenManager tokenManager;


    public LoginResponse login(String email, String password) throws MyCouponSystemExeption {
        Company company = companyRepository.findByEmailAndPassword(email,password).orElseThrow(()->new MyCouponSystemExeption("Email or password is wrong"));

        Information information = Information.builder().expTime(LocalDateTime.now().plusHours(1)).email(company.getEmail()).clientType(ClientType.Company).id(company.getId()).build();
        UUID token = tokenManager.addToken(information);

        if (token==null){
            throw new MyCouponSystemExeption("Incorect login ditails ");
        }
        return new LoginResponse(email,password,ClientType.Company,token,company.getId());
    }

    public void isCompany(UUID token) throws MyCouponSystemExeption {
        if (!tokenManager.isAuth(token, ClientType.Company)){
            throw new MyCouponSystemExeption("Unauthoth action");
        }
    }

    public void addCoupon(Coupon coupon, int companyId,UUID token) throws MyCouponSystemExeption {
        isCompany(token);
        boolean couponExist = couponRepository.existsByTitleAndCompany_Id(coupon.getTitle(), companyId);
        if (couponExist) {
            throw new MyCouponSystemExeption("This title exist yet!");
        } else {
            if (coupon.getEndDate().isBefore(LocalDate.now())) {
                throw new MyCouponSystemExeption("The date has expired");
            } else {
                coupon.setCompany(companyRepository.findById(companyId).orElseThrow(()->new MyCouponSystemExeption("Id not exist")));
                couponRepository.save(coupon);
                System.out.println("New coupon added.");
            }
        }
    }

    public void updateCoupon(Coupon coupon, UUID token) throws MyCouponSystemExeption {
        isCompany(token);

            System.out.println(coupon);
            if (coupon.getAmount() < 1 || coupon.getEndDate().isBefore(LocalDate.now()) || coupon.getPrice() < 0) {
                throw new MyCouponSystemExeption("The amount or date or price parameters are wrong!");
            } else {
                couponRepository.setFixedAmountAndCategoryAndDescriptionAndEnd_dateAndImageAndPriceAndStart_dateAndTitleFor(coupon.getAmount(), coupon.getCategory().ordinal(), coupon.getDescription(), coupon.getEndDate().toString(), coupon.getImage(), coupon.getPrice(), coupon.getStartDate().toString(), coupon.getTitle(), coupon.getId());
                System.out.println("The coupon's details has changed.");
            }
    }


    public void deleteCoupon(int couponId, int companyId, UUID token) throws MyCouponSystemExeption {
        isCompany(token);
        boolean couponExist = couponRepository.existsById(couponId);
        if (couponExist) {
            couponRepository.deleteByIdAndCompany_id(couponId, companyId);
            System.out.println("Coupon with id: " + couponId + " has been deleted.");
        } else {
            throw new MyCouponSystemExeption("There is no coupon with id: " + couponId);
        }
    }

    public List<Coupon> getAllCompanyCoupons(int companyId,UUID token) throws MyCouponSystemExeption {
        isCompany(token);
        List<Coupon> companyCouponsList = couponRepository.getAllByCompany_Id(companyId);
        if (companyCouponsList.isEmpty()) {
            throw new MyCouponSystemExeption("This company has no coupons!");
        }

        return companyCouponsList;
    }

    public Coupon getOneCoupon(int couponId,UUID token) throws MyCouponSystemExeption {
        isCompany(token);
        Coupon coupon = couponRepository.findById(couponId);
        if (coupon == null) {

            throw new MyCouponSystemExeption("You don't have this coupon on the list");
        }
        return coupon;
    }


    public List<Coupon> getCompanyCouponsByCategory(int category, int companyId,UUID token) throws MyCouponSystemExeption {
        isCompany(token);

        List<Coupon> couponListByCategory = couponRepository.findByCategoryAndCompany_Id(category, companyId);

        if (couponListByCategory.isEmpty()) {
            throw new MyCouponSystemExeption("This company has no coupons from that category!");
        }

        return couponListByCategory;

    }

    public List<Coupon> getCouponByMaxPrice(double price, int companyId,UUID token) throws MyCouponSystemExeption {
        isCompany(token);
        List<Coupon> couponListByMaxPrice = couponRepository.getCouponsByMaxPriceAndCompany_id(price, companyId);
        if (couponListByMaxPrice.isEmpty()) {
            throw new MyCouponSystemExeption("This company has no coupons with that price!");
        }

        return couponListByMaxPrice;
    }



    public Company getCompanyDetails(int companyId) throws MyCouponSystemExeption {
        Company companyDetails = companyRepository.findById(companyId).orElseThrow(()->new MyCouponSystemExeption("Email or password is wrong"));

        return companyDetails;
    }

}
