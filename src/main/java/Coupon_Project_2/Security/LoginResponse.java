package Coupon_Project_2.Security;

import Coupon_Project_2.ManagerLogin.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse {

    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ClientType clientType;
    private UUID token;
    private int id;
}
