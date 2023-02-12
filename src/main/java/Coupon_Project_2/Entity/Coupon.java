package Coupon_Project_2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
//import org.codehaus.jackson.annotate.JsonProperty;


@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"company", "customersList"})
@Builder

public class Coupon {
    @Id
    @GeneratedValue
    private int id;
    @Column
    @Enumerated(EnumType.ORDINAL)
    private Category category;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @Column
    private int amount;
    @Column
    private double price;
    @Column
    private String image;


    @ManyToMany(mappedBy = "CustomerCouponsList", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Customer> customersList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    @JsonIgnore
    private Company company;


}
