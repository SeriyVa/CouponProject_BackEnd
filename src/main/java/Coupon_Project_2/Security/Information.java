package Coupon_Project_2.Security;

import Coupon_Project_2.ManagerLogin.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Information {

    private int id;
    private String email;
    private LocalDateTime expTime;
    @Enumerated(EnumType.STRING)
    private ClientType clientType;


}
