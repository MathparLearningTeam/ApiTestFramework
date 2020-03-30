package mathpar.test.utils.properties;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountData {
    private String email;
    private String password;
    private String fullName;
}
