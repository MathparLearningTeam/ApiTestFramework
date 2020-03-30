package mathpar.test.utils.dto.frontend.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestoreCredentialsPayload {
    private String email;
    private long userId;
}