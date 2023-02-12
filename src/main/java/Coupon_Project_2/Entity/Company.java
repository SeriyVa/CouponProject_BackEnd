package Coupon_Project_2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


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

public class Company {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Coupon> CouponsList = new ArrayList<>();

}
