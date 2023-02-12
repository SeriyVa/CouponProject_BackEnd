package Coupon_Project_2.Controller;

import Coupon_Project_2.Entity.Category;
import Coupon_Project_2.Entity.Coupon;
import Coupon_Project_2.Service.AdminService;
import Coupon_Project_2.Service.CompanyService;
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
@RequestMapping("/company")
public class CompanyController {


    private final CompanyService companyService;



    @PutMapping("/addCoupon")

    public void addCoupon(@RequestHeader("Authorization") UUID token, @RequestHeader("IdCompany") int idCompany, @RequestBody Coupon newCoupon) throws MyCouponSystemExeption {
        System.out.println(newCoupon);
        companyService.addCoupon(newCoupon, idCompany, token);

    }


    @PutMapping("/updateCoupon")
    @ResponseBody
    public String updateCoupon(@RequestHeader ("Authorization") UUID token,  @RequestBody Coupon updatedCoupon) throws MyCouponSystemExeption {
        System.out.println(updatedCoupon);
        companyService.updateCoupon(updatedCoupon, token);

        return "The coupon is updated";
    }

    @DeleteMapping("/deleteCoupon/{couponId}")
    public String deleteCoupon(@RequestHeader("Authorization") UUID token, @RequestHeader("companyId") int companyId, @PathVariable int couponId) throws MyCouponSystemExeption {
        companyService.deleteCoupon(couponId, companyId, token);
        return "Coupon with id " + couponId + " deleted";
    }

    @GetMapping("/getCompanyCoupons")

    public List<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token, @RequestHeader int companyId) throws MyCouponSystemExeption {

        List<Coupon> companyCoupons = companyService.getAllCompanyCoupons(companyId, token);

        return companyCoupons;


    }
//
//    @GetMapping("/getCompanyCouponsByCategory/{companyId}/{category}")
//    @ResponseBody
//    public List<Coupon> getCompanyCouponsByCategory(@PathVariable int companyId, @PathVariable Category category) {
//
//        List<Coupon> couponListByCategory = couponService.getCompanyCouponsByCategory(category,companyId);
//
//        return couponListByCategory;
//    }
//
//
//    @GetMapping("/getCompanyCouponsByMaxPrice/{companyId}/{maxPrice}")
//    @ResponseBody
//    public List<Coupon> getCompanyCouponsByMaxPrice(@PathVariable int companyId, @PathVariable double maxPrice) {
//
//        List<Coupon> couponListByMaxPrice = couponService.getCouponByMaxPrice(maxPrice,companyId);
//
//        return couponListByMaxPrice;
//    }
}
