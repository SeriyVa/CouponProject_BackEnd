package Coupon_Project_2.Security;

import Coupon_Project_2.ManagerLogin.ClientType;
import Coupon_Project_2.SystemExeption.MyCouponSystemExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenManager {
    private final Map<UUID, Information> tokens;

    public UUID addToken(Information information) {
        deleteToken(information.getId());
        UUID token = UUID.randomUUID();
        tokens.put(token, information);

        return token;
    }

    public void deleteToken(int id) {
        tokens.entrySet().removeIf((token) -> token.getValue().getId() == id);
    }

    private int getUserIdPerType(UUID token, ClientType clientType) throws MyCouponSystemExeption {
        Information information = tokens.get(token);
        if (information == null) {
            throw new MyCouponSystemExeption("Authrized token");
        }
        if (information.getClientType() != clientType) {
            throw new MyCouponSystemExeption("Anotorized Action");
        }
        return information.getId();
    }


    public int getCustomerId(UUID token) throws MyCouponSystemExeption {

        return getUserIdPerType(token, ClientType.Customer);
    }

    public int getCompanyId(UUID token) throws MyCouponSystemExeption {

        return getUserIdPerType(token, ClientType.Company);
    }

    public ClientType getClientType(UUID token) throws MyCouponSystemExeption {

        Information information = tokens.get(token);
        if (information == null) {
            throw new MyCouponSystemExeption("Authrized token");
        }

        return information.getClientType();
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void deleteExpToken() {

        tokens.entrySet().removeIf((token) -> token.getValue().getExpTime().isBefore(LocalDateTime.now()));

    }

    public boolean isAuth(UUID token, ClientType clientType) throws MyCouponSystemExeption {
        Information information = tokens.get(token);
        if (information == null) {
            throw new MyCouponSystemExeption("Authrized token");
        }
        return tokens.get(token).getClientType().equals(clientType);
    }
}
