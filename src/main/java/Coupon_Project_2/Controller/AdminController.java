package Coupon_Project_2.Controller;

import Coupon_Project_2.Entity.Company;
import Coupon_Project_2.Entity.Customer;
import Coupon_Project_2.Service.AdminService;
import Coupon_Project_2.Service.CustomerService;
import Coupon_Project_2.SystemExeption.MyCouponSystemExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {


    private final AdminService adminService;


    @PutMapping("/addCompany")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestHeader("Authorization") UUID token, @RequestBody Company newCompany) throws MyCouponSystemExeption {
        adminService.addCompany(newCompany, token);


    }

    @PostMapping("/updateCompany")
    @ResponseStatus(HttpStatus.OK)
    public void updateCompany(@RequestHeader("Authorization") UUID token, @RequestBody Company updatedCompany) throws MyCouponSystemExeption {
//        System.out.println(companyEmail+companyId);
        adminService.updateCompany(updatedCompany, token);

    }

    @DeleteMapping("/deleteCompany/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@RequestHeader("Authorization") UUID token, @PathVariable int companyId) throws MyCouponSystemExeption {
        adminService.deleteCompany(companyId, token);

    }

    @GetMapping("/getAllCompanies")
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getAllCompanies(@RequestHeader("Authorization") UUID token) throws MyCouponSystemExeption {
        List<Company> companyList = adminService.getAllCompanies(token);
        return companyList;
    }


    @GetMapping("/getOneCompany/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public Company getOneCompany(@RequestHeader("Authorization") UUID token, @PathVariable int companyId) throws MyCouponSystemExeption {
        Company oneCompany = adminService.getOneCompany(companyId, token);

        return oneCompany;
    }

    @PostMapping("/addCustomer")
    @ResponseStatus(HttpStatus.OK)
    public void addCustomer(@RequestHeader("Authorization") UUID token, @RequestBody Customer newCustomer) throws MyCouponSystemExeption {
        adminService.addCustomer(newCustomer, token);

    }

    @PutMapping("/updateCustomer")
    public void updateCustomer(@RequestHeader("Authorization") UUID token, @RequestBody Customer updatedCustomer) throws MyCouponSystemExeption {
        adminService.updateCustomer(updatedCustomer, token);

    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public String deleteCustomer(@RequestHeader("Authorization") UUID token, @PathVariable int customerId) throws MyCouponSystemExeption {

        adminService.deleteCustomer(customerId, token);

        return "Customer with id " + customerId + "deleted";
    }

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers(@RequestHeader("Authorization") UUID token) throws MyCouponSystemExeption {
        List<Customer> customerList = adminService.getAllCustomers(token);

        return customerList;
    }

    @GetMapping("/getOneCustomer/{customerId}")
    public void getOneCustomer(@RequestHeader("Authorization") UUID token, @PathVariable int customerId) throws MyCouponSystemExeption {
        Customer customer = adminService.getOneCustomer(customerId, token);
        System.out.println(customer);
    }


}
