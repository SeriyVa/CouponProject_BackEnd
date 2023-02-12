package Coupon_Project_2.Controller;

import Coupon_Project_2.ManagerLogin.LoginManager;
import Coupon_Project_2.Security.LoginRequest;
import Coupon_Project_2.Security.LoginResponse;
import Coupon_Project_2.SystemExeption.MyCouponSystemExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {

    private final LoginManager loginManager;


    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws MyCouponSystemExeption {
        return loginManager.login(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest.getClientType());
    }

}
