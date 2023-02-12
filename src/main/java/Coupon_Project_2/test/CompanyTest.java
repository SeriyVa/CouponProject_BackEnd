package Coupon_Project_2.test;

import Coupon_Project_2.Entity.Category;
import Coupon_Project_2.Entity.Coupon;
import Coupon_Project_2.ManagerLogin.ClientType;
import Coupon_Project_2.ManagerLogin.LoginManager;
import Coupon_Project_2.Service.CompanyService;
import Coupon_Project_2.SystemExeption.MyCouponSystemExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.rmi.MarshalException;
import java.time.LocalDate;

@Component
@Order(4)
@RequiredArgsConstructor
public class CompanyTest implements CommandLineRunner {


    final LoginManager loginManager;

    public final int companyId = 4;
    public final String email = "Nvidia@1";
    public final String password = "1234";


    @Override
    public void run(String... args) {
//        try {
//
//            CompanyService companyService = (CompanyService) loginManager.Login(ClientType.Company, companyId, email, password);
//
//
////            System.out.println("You have added a new coupon!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            Coupon coupon = Coupon.builder().amount(10).category(Category.ELECTRICITY).description("a").startDate(LocalDate.of(2021, 2, 15)).endDate(LocalDate.of(2022, 10, 12)).image("s").title("Blender").price(1000).build();
////
////            companyService.addCoupon(coupon, companyId);
////
////
////            System.out.println("You have updated a coupon!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            Coupon coupon2 = companyService.getOneCoupon(14);
////            coupon2.setCategory(Category.FOOD);
////            companyService.updateCoupon(coupon2,companyId);
////
////
////            System.out.println("You have deleted a coupon!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            companyService.deleteCoupon(14, companyId);
////
////
////            System.out.println("You got all company coupons!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            System.out.println(companyService.getAllCompanyCoupons(companyId));
////
////
////            System.out.println("You got company coupons by category!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            System.out.println(companyService.getCompanyCouponsByCategory(Category.ELECTRICITY.ordinal(), companyId));
////
////
////            System.out.println("You got company coupons by price!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            System.out.println(companyService.getCouponByMaxPrice(1000, companyId));
////
////
////            System.out.println("You got company details!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            System.out.println(companyService.getCompanyDetails(companyId));
//
//        } catch (MyCouponSystemExeption MCSE) {
//            System.out.println(MCSE.getLocalizedMessage());
//        }
    }


}
