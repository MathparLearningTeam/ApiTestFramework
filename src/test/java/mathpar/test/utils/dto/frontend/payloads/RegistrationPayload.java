package mathpar.test.utils.dto.frontend.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationPayload{
    private String directorEmail;
    private String directorPassword;
    private String directorName;
    private String schoolName;
    private String schoolAddress;
}