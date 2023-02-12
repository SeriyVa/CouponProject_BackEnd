package Coupon_Project_2;

import Coupon_Project_2.Repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Component
public class CouponExpirationDailyJob {


    @Autowired
    CouponRepository couponRepository;

    //check every 24 hours if the coupons expired. start working after 7 seconds.
    @Scheduled(fixedRate = 86400000, initialDelay = 7000)

    public void removeExpiredCoupons() {
        System.out.println("The remover of the expired coupon start working!");
        int deletedCoupons = couponRepository.deleteAllByEnd_dateBefore(LocalDate.now().toString());


        if (deletedCoupons != 0) {
            System.out.println( deletedCoupons+" expired coupon/s has been deleted!");
        }

    }


}

