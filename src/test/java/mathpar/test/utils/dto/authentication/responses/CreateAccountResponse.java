package mathpar.test.utils.dto.authentication.responses;

import lombok.Data;
import mathpar.test.utils.dto.frontend.responses.TokenResponse;

import java.util.Date;

@Data
public class CreateAccountResponse {
    private long accountId;
    private String fullName;
    private Date creationDate;

    private TokenResponse authentication;
}
