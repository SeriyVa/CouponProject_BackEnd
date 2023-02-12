package Coupon_Project_2.test;

import Coupon_Project_2.Entity.Category;
import Coupon_Project_2.Entity.Company;
import Coupon_Project_2.Entity.Coupon;
import Coupon_Project_2.Entity.Customer;
import Coupon_Project_2.Repository.CompanyRepository;
import Coupon_Project_2.Repository.CouponRepository;
import Coupon_Project_2.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Component
@Order(1)
@RequiredArgsConstructor
public class Init implements CommandLineRunner {

    public final CompanyRepository companyRepository;
    public final CustomerRepository customerRepository;
    public final CouponRepository couponRepository;


    @Override
    public void run(String... args) {

        //adding companies

        Company company1 = Company.builder().name("Apple").email("Apple@apple.com").password("1234").build();
        Company company2 = Company.builder().name("Google").email("Google@google.com").password("1234").build();
        Company company3 = Company.builder().name("Intel").email("Intel@intel.com").password("1234").build();
        Company company4 = Company.builder().name("Nvidia").email("Nvidia@nvidia.com").password("1234").build();
        Company company5 = Company.builder().name("Asus").email("Asus@asus.com").password("1234").build();


        companyRepository.save(company1);
        companyRepository.save(company2);
        companyRepository.save(company3);
        companyRepository.save(company4);
        companyRepository.save(company5);

        //adding customers

        Customer customer1 = Customer.builder().firstName("Ben").lastName("Cohen").email("ben@ben.com").password("1234").build();
        Customer customer2 = Customer.builder().firstName("Udi").lastName("Cohen").email("udi@udi.com").password("1234").build();
        Customer customer3 = Customer.builder().firstName("Os").lastName("Vaseniov").email("osi@gmail.com").password("1234").build();
        Customer customer4 = Customer.builder().firstName("Seriy").lastName("Fishman").email("seriy@google.com").password("1234").build();
        Customer customer5 = Customer.builder().firstName("Eddy").lastName("Abzah").email("Eddy@eddy.com").password("1234").build();

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);

        //adding coupons

        Coupon coupon1 = Coupon.builder().amount(10).category(Category.FOOD).company(company1).description("text").startDate(LocalDate.of(2021, 2, 15)).endDate(LocalDate.of(2023, 12, 12)).image("s").title("Burito").price(15).build();
        Coupon coupon2 = Coupon.builder().amount(10).category(Category.VACATION).company(company2).description("text").startDate(LocalDate.of(2021, 2, 15)).endDate(LocalDate.of(2023, 9, 30)).image("s").title("Spain").price(20).build();
        Coupon coupon3 = Coupon.builder().amount(10).category(Category.FASHION).company(company3).description("text").startDate(LocalDate.of(2021, 2, 15)).endDate(LocalDate.of(2023, 12, 30)).image("s").title("Factory54").price(50).build();
        Coupon coupon4 = Coupon.builder().amount(10).category(Category.ELECTRICITY).company(company5).description("text").startDate(LocalDate.of(2021, 2, 15)).endDate(LocalDate.of(2023, 12, 30)).image("s").title("Computers").price(30).build();
        Coupon coupon5 = Coupon.builder().amount(10).category(Category.RESTAURANT).company(company5).description("text").startDate(LocalDate.of(2021, 2, 15)).endDate(LocalDate.of(2025, 12, 30)).image("s").title("1+1").price(20).build();
        Coupon coupon6 = Coupon.builder().amount(10).category(Category.VACATION).company(company5).description("text").startDate(LocalDate.of(2021, 2, 15)).endDate(LocalDate.of(2023, 12, 30)).image("s").title("Mexico").price(35).build();
        Coupon coupon7 = Coupon.builder().amount(10).category(Category.VACATION).company(company5).description("text").startDate(LocalDate.of(2021, 2, 15)).endDate(LocalDate.of(2023, 12, 30)).image("s").title("Spain").price(20).build();
        Coupon coupon8 = Coupon.builder().amount(10).category(Category.VACATION).company(company2).description("text").startDate(LocalDate.of(2021, 2, 15)).endDate(LocalDate.of(2023, 12, 30)).image("s").title("USA").price(23).build();
        Coupon coupon9 = Coupon.builder().amount(10).category(Category.FOOD).company(company2).description("text").startDate(LocalDate.of(2021, 2, 15)).endDate(LocalDate.of(2023, 12, 30)).image("s").title("Hamburger").price(43).build();

        couponRepository.save(coupon1);
        couponRepository.save(coupon2);
        couponRepository.save(coupon3);
        couponRepository.save(coupon4);
        couponRepository.save(coupon5);
        couponRepository.save(coupon6);
        couponRepository.save(coupon7);
        couponRepository.save(coupon8);
        couponRepository.save(coupon9);


    }
}
