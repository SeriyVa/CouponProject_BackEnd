package Coupon_Project_2.Service;

import Coupon_Project_2.Entity.Company;
import Coupon_Project_2.ManagerLogin.ClientType;
import Coupon_Project_2.Security.Information;
import Coupon_Project_2.Security.LoginResponse;
import Coupon_Project_2.Security.TokenManager;
import Coupon_Project_2.SystemExeption.MyCouponSystemExeption;
import Coupon_Project_2.Entity.Coupon;
import Coupon_Project_2.Entity.Customer;
import Coupon_Project_2.Repository.CouponRepository;
import Coupon_Project_2.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;
    private final TokenManager tokenManager;


    public LoginResponse login(String email, String password) throws MyCouponSystemExeption {
        Customer customer = customerRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new MyCouponSystemExeption("Email or password is wrong"));

        Information information = Information.builder().expTime(LocalDateTime.now().plusHours(1)).email(customer.getEmail()).clientType(ClientType.Customer).id(customer.getId()).build();
        UUID token = tokenManager.addToken(information);

        if (token == null) {
            throw new MyCouponSystemExeption("Incorrect login details ");
        }
        return new LoginResponse(email, password, ClientType.Customer, token, customer.getId());
    }

    public void isCustomer(UUID token) throws MyCouponSystemExeption {
        if (!tokenManager.isAuth(token, ClientType.Customer)) {
            throw new MyCouponSystemExeption("Unauthoth action");
        }
    }

    public List<Coupon> getAllCoupons(UUID token) throws MyCouponSystemExeption {
        isCustomer(token);

        List<Coupon> allCouponsList = couponRepository.findAll();
        if (allCouponsList == null) {
            throw new MyCouponSystemExeption("The List is empty");
        }

        return allCouponsList;
    }


    public void addCouponPurchase(int customerId, int couponId, UUID token) throws MyCouponSystemExeption {
        isCustomer(token);
        if (couponRepository.findById(couponId).getAmount() < 1) {
            throw new MyCouponSystemExeption("The all of coupons with id " + couponId + " is sold out");
        } else {
            List<Coupon> customerCouponsList = couponRepository.getAllByCustomer_id(customerId);

            for (Coupon coupons : customerCouponsList) {

                if (coupons.getId() == couponId) {
                    throw new MyCouponSystemExeption("The customer have the same coupon!");
                }
            }


            couponRepository.insertCustomer_vs_coupon(customerId, couponId);

            couponRepository.setFixedAmountFor(1, couponId);

            System.out.println("The coupon with id: " + couponId + " has added.");
        }
    }


    public void deleteCouponPurchase(int customerId, int couponId, UUID token) throws MyCouponSystemExeption {
        isCustomer(token);
        int couponDeleted = couponRepository.deleteAllByCustomer_idAndByCoupon_id(customerId, couponId);
        if (couponDeleted == 0) {
            throw new MyCouponSystemExeption("Maybe this customer never purchased this coupon or the id of customer is wrong");
        } else {
            System.out.println("The coupon with id: " + couponId + " has deleted.");
        }
    }

    public List<Coupon> getAllCustomerCoupons(int customerId, UUID token) throws MyCouponSystemExeption {
        isCustomer(token);
        List<Coupon> coupons = couponRepository.getAllByCustomer_id(customerId);
        if (coupons.isEmpty()) {
            throw new MyCouponSystemExeption("This customer has no coupons");
        }

        return coupons;
    }

    public List<Coupon> getCustomerCouponsByCategory(int customerId, int category, UUID token) throws MyCouponSystemExeption {
        isCustomer(token);

        List<Coupon> couponListByCategory = couponRepository.getCouponsByCustomer_idAndByCategory(customerId, category);
        if (couponListByCategory.isEmpty()) {
            throw new MyCouponSystemExeption("This customer has no coupons from that category!");
        }

        return couponListByCategory;
    }

    public List<Coupon> getCustomerCouponsByMaxPrice(int customerId, double maxPrice, UUID token) throws MyCouponSystemExeption {
        isCustomer(token);
        List<Coupon> couponListByMaxPrice = couponRepository.getAllByCustomer_idAndByMaxprice(customerId, maxPrice);
        if (couponListByMaxPrice.isEmpty()) {
            throw new MyCouponSystemExeption("This customer has no coupons with that price!");
        }

        return couponListByMaxPrice;
    }

    public Customer getCustomerDetails(int customerId, UUID token) throws MyCouponSystemExeption {
        isCustomer(token);
        Customer customerDetails = customerRepository.findById(customerId);

        return customerDetails;
    }


}
