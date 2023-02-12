package Coupon_Project_2.ManagerLogin;

import Coupon_Project_2.Entity.Company;
import Coupon_Project_2.Entity.Customer;
import Coupon_Project_2.Repository.CompanyRepository;
import Coupon_Project_2.Repository.CouponRepository;
import Coupon_Project_2.Repository.CustomerRepository;
import Coupon_Project_2.Security.LoginResponse;
import Coupon_Project_2.Service.AdminService;
import Coupon_Project_2.Service.CompanyService;
import Coupon_Project_2.Service.CustomerService;
import Coupon_Project_2.SystemExeption.MyCouponSystemExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginManager {



    private final AdminService adminService;
    private final CustomerService customerService;
    private final CompanyService companyService;


    public LoginResponse login(String email, String password, ClientType clientType) throws MyCouponSystemExeption {

      // add email and password validation

        LoginResponse loginResponse = null;
        switch (clientType){
            case Admin:
                loginResponse=adminService.login(email,password);
                break;
            case Company:
                loginResponse=companyService.login(email,password);
                break;
            case Customer:
                loginResponse=customerService.login(email,password);
                break;
        }

        return loginResponse;
    }


}
