package Coupon_Project_2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Customer {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Customer_Vs_Coupon", joinColumns = {@JoinColumn(name = "customerId")}, inverseJoinColumns = {@JoinColumn(name = "couponId")})
    @ToString.Exclude
    private List<Coupon> CustomerCouponsList = new ArrayList<>();


}
