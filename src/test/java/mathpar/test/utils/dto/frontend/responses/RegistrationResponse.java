package mathpar.test.utils.dto.frontend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse{
    private SchoolResponse school;
    private TokenResponse token;
}