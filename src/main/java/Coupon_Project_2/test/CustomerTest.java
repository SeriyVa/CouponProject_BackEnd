package Coupon_Project_2.test;

import Coupon_Project_2.Entity.Category;
import Coupon_Project_2.ManagerLogin.ClientType;
import Coupon_Project_2.ManagerLogin.LoginManager;
import Coupon_Project_2.Service.CustomerService;
import Coupon_Project_2.SystemExeption.MyCouponSystemExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
@RequiredArgsConstructor
public class CustomerTest implements CommandLineRunner {

    final LoginManager loginManager;

    public final int customerId = 6;
    public final String email = "ben@";
    public final String password = "1234";

    @Override
    public void run(String... args) {
//        try {
//
//            CustomerService customerService = (CustomerService) loginManager.Login(ClientType.Customer, customerId, email, password);
//
//            System.out.println("You have purchased a new coupon!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//
//            customerService.addCouponPurchase(customerId, 19);
//
//
//            System.out.println("You have deleted a purchased coupon!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//
//            customerService.deleteCouponPurchase(customerId, 19);
//
//
////            System.out.println("You got customers coupons!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////            System.out.println(customerService.getAllCustomerCoupons(customerId));
////
////
////            System.out.println("You got customers coupons by category!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////            System.out.println(customerService.getCustomerCouponsByCategory(customerId, Category.ELECTRICITY.ordinal()));
//
//
////            System.out.println("You got customers coupons by max price!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////            System.out.println(customerService.getCustomerCouponsByMaxPrice(customerId, 0));
//
////
////            System.out.println("You got customers details!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////            System.out.println(customerService.getCustomerDetails(customerId));
//
//
//        } catch (MyCouponSystemExeption MCSE) {
//            System.out.println(MCSE.getLocalizedMessage());
//        }


    }
}
