package mathpar.test.utils.dto.frontend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private String token;
    private Date expirationDate;

    public long getAccountId(){
        return Long.parseLong(token.substring(0, token.indexOf("_")));
    }
}
