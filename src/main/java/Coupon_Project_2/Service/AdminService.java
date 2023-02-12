package Coupon_Project_2.Service;

import Coupon_Project_2.ManagerLogin.ClientType;
import Coupon_Project_2.Repository.CouponRepository;
import Coupon_Project_2.Security.Information;
import Coupon_Project_2.Security.LoginResponse;
import Coupon_Project_2.Security.TokenManager;
import Coupon_Project_2.SystemExeption.MyCouponSystemExeption;
import Coupon_Project_2.Entity.Company;
import Coupon_Project_2.Entity.Customer;
import Coupon_Project_2.Repository.CompanyRepository;
import Coupon_Project_2.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {


    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;
    private final TokenManager tokenManager;


    public LoginResponse login(String email, String password) throws MyCouponSystemExeption {
        if (!(email.equalsIgnoreCase("Admin@admin.com") && password.equals("Admin"))) {
            throw new MyCouponSystemExeption("Email or password is wrong!");
        }
        Information information = Information.builder().expTime(LocalDateTime.now().plusHours(1)).email("Admin@admin.com").clientType(ClientType.Admin).id(-1).build();
        UUID token = tokenManager.addToken(information);

        if (token == null) {
            throw new MyCouponSystemExeption("Incorect login ditails ");
        }
        return new LoginResponse(email, password, ClientType.Admin, token, -1);
    }

    public void isAdmin(UUID token) throws MyCouponSystemExeption {
        if (!tokenManager.isAuth(token, ClientType.Admin)) {
            throw new MyCouponSystemExeption("Unauthoth action");
        }
    }


    public void addCompany(Company company, UUID token) throws MyCouponSystemExeption {
        isAdmin(token);
        boolean companyExist = companyRepository.existsByNameOrEmail(company.getName(), company.getEmail());
        if (companyExist) {
            throw new MyCouponSystemExeption("You have company with the same name or email!");


        } else {
            companyRepository.save(company);
            System.out.println("New company added.");

        }
    }

    public void updateCompany(Company updatedCompany, UUID token) throws MyCouponSystemExeption {
        isAdmin(token);
        boolean companyExist = companyRepository.existsByEmail(updatedCompany.getEmail());
        if (companyExist) {
            throw new MyCouponSystemExeption("You have company with the same email!");
        } else {
            companyRepository.setFixedEmailFor(updatedCompany.getEmail(), updatedCompany.getId());

        }
    }

    public void deleteCompany(int companyId, UUID token) throws MyCouponSystemExeption {
        isAdmin(token);
        boolean companyExist = companyRepository.existsById(companyId);
        if (companyExist) {

            couponRepository.deletePurchasesByCompanyId(companyId);
            couponRepository.deleteCouponByCompanyId(companyId);
            companyRepository.deleteById(companyId);
            System.out.println("Company with id " + companyId + " deleted!");

        } else {
            throw new MyCouponSystemExeption("The company with id " + companyId + " doesn't exist! ");

        }
    }

    public List<Company> getAllCompanies(UUID token) throws MyCouponSystemExeption {
        isAdmin(token);
        List<Company> companyList = companyRepository.findAll();
        if (companyList.isEmpty()) {
            throw new MyCouponSystemExeption("There are not companies on the list");
        }

        return companyList;
    }

    public Company getOneCompany(int id, UUID token) throws MyCouponSystemExeption {
        isAdmin(token);
        Company companyFromList = companyRepository.findById(id).orElseThrow(() -> new ClassCastException("Id not exist"));
        if (companyFromList == null) {
            System.out.println("");
        }

        return companyFromList;
    }


    public void addCustomer(Customer customer, UUID token) throws MyCouponSystemExeption {
        isAdmin(token);
        boolean emailExist = customerRepository.existsByEmail(customer.getEmail());
        if (emailExist) {
            throw new MyCouponSystemExeption("You have customer with the same email!");
        }
        customerRepository.save(customer);
        System.out.println("New customer added.");
    }

    public void updateCustomer(Customer customer, UUID token) throws MyCouponSystemExeption {
        isAdmin(token);
        boolean emailExist = customerRepository.existsByEmail(customer.getEmail());
        if (emailExist) {
            throw new MyCouponSystemExeption("You have customer with the same email!");
        }
        customerRepository.setFixedFirst_nameAndLast_nameAndEmailFor(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getId());
        System.out.println("The customer's details has changed.");
    }

    public void deleteCustomer(int customerId, UUID token) throws MyCouponSystemExeption {
        isAdmin(token);
        boolean customerExist = customerRepository.existsById(customerId);
        if (customerExist) {
            customerRepository.deleteById(customerId);
            System.out.println("Customer with id " + customerId + " deleted.");
        } else {
            throw new MyCouponSystemExeption("The customer with id " + customerId + " doesn't exist!");
        }

    }

    public List<Customer> getAllCustomers(UUID token) throws MyCouponSystemExeption {
        isAdmin(token);
        List<Customer> customerList = customerRepository.findAll();
        if (customerList.isEmpty()) {
            throw new MyCouponSystemExeption("There are not customers on the list");
        }
        return customerList;
    }

    public Customer getOneCustomer(int customerId, UUID token) throws MyCouponSystemExeption {
        isAdmin(token);
        Customer customer = customerRepository.findById(customerId);
        if (customer == null) {
            throw new MyCouponSystemExeption("There are not customer with id " + customerId + " on the list");
        }
        return customer;
    }


}


