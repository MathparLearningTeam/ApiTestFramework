package mathpar.test.utils.dto.authentication.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAccountPayload {
    private String email;
    private String password;
    private String fullName;
}
