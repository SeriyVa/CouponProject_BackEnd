package Coupon_Project_2;

import Coupon_Project_2.Entity.Category;
import Coupon_Project_2.Entity.Company;
import Coupon_Project_2.Entity.Coupon;
import Coupon_Project_2.Entity.Customer;
import Coupon_Project_2.Service.AdminService;
import Coupon_Project_2.Service.CompanyService;
import Coupon_Project_2.Service.CustomerService;
import Coupon_Project_2.SystemExeption.MyCouponSystemExeption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
@EnableScheduling
public class Test {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Test.class, args);
        System.out.println("Server is running!");
        CompanyService companyService = ctx.getBean(CompanyService.class);
        CustomerService customerService = ctx.getBean(CustomerService.class);
        AdminService adminService = ctx.getBean(AdminService.class);





    }
}