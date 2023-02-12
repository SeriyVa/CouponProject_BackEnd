package Coupon_Project_2.test;

import Coupon_Project_2.Entity.Company;
import Coupon_Project_2.Entity.Customer;
import Coupon_Project_2.ManagerLogin.ClientType;
import Coupon_Project_2.ManagerLogin.LoginManager;
import Coupon_Project_2.Service.AdminService;
import Coupon_Project_2.SystemExeption.MyCouponSystemExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class AdminTest implements CommandLineRunner {

        @Autowired
    LoginManager loginManager;


    @Override
    public void run(String... args) {
//        try {
//
//            AdminService adminService = (AdminService) loginManager.Login(ClientType.Admin, 0, "Admin@", "Admin");
//
////            System.out.println("You have added a new company!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////            Company company = Company.builder().name("Wix").email("wix@").Password("1234").build();
////            adminService.addCompany(company);
////
////            System.out.println("You have updated company!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            company.setEmail("Wix@togo");
////            adminService.updateCompany(company);
////
////            System.out.println("You have deleted company!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            adminService.deleteCompany(3);
////
////            System.out.println("You have got all companies!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            System.out.println(adminService.getAllCompanies());
////
////            System.out.println("You have got one company!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            System.out.println(adminService.getOneCompany(1));
////
////
////            System.out.println("You have added a new customer!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            Customer newCustomer = Customer.builder().firstName("Ori").lastName("Levi").email("ori@").password("1234").build();
////            adminService.addCustomer(newCustomer);
////
////            System.out.println("You have update customers details!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            newCustomer.setEmail("Ori@gmail.com");
////            adminService.updateCustomer(newCustomer);
////
////            System.out.println("You have deleted customer!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            adminService.deleteCustomer(10);
////
////
////            System.out.println("You have added a new coupon!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            System.out.println(adminService.getAllCustomers());
////
////            System.out.println("You have got a one company!+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////            System.out.println(adminService.getOneCompany(15));
//
////            System.out.println( adminService.getOneCompany("Apple@1", "1234"));
//
//
//        } catch (MyCouponSystemExeption MCSE) {
//            System.out.println(MCSE.getLocalizedMessage());
//        }
    }
}
