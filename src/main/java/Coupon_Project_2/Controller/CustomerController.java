package Coupon_Project_2.Controller;

import Coupon_Project_2.Entity.Coupon;
import Coupon_Project_2.Entity.Customer;
import Coupon_Project_2.Service.CompanyService;
import Coupon_Project_2.Service.CustomerService;
import Coupon_Project_2.SystemExeption.MyCouponSystemExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;


//    @GetMapping("/getCustomerDetails/{customerId}")
//    @ResponseBody
//    public Customer getCustomerDetails(@PathVariable int customerId){
//
//        Customer customer = customerService.getOneCustomer(customerId);
//
//
//        return customer;
//    }

    @GetMapping("/getCustomerCoupons")
    public List<Coupon> getCustomerCoupons(@RequestHeader("Authorization") UUID token,@RequestHeader     int customerId) throws MyCouponSystemExeption {
        List<Coupon> customerCoupons = customerService.getAllCustomerCoupons(customerId,token);

        return customerCoupons;
    }

    @GetMapping("/getAllCoupons")
    public List<Coupon> getAllCoupons(@RequestHeader("Authorization") UUID token) throws MyCouponSystemExeption {
        List<Coupon> allCouponsList = customerService.getAllCoupons(token);

        return allCouponsList;
    }

    @GetMapping("/addPurchaseCoupon")
    public String addPurchaseCoupon(@RequestHeader("Authorization") UUID token, @RequestHeader int customerId, @RequestHeader int couponId) throws MyCouponSystemExeption {
        customerService.addCouponPurchase(customerId, couponId, token);

        return "The customer with id: " + customerId + " purchased a new coupon with id: " + couponId;

    }

    @GetMapping("/deletePurchaseCoupon")
    @ResponseBody
    public void deletePurchaseCoupon(@RequestHeader("Authorization") UUID token, @RequestHeader int customerId, @RequestHeader int couponId) throws MyCouponSystemExeption {

        customerService.deleteCouponPurchase( customerId, couponId, token);

    }
}
