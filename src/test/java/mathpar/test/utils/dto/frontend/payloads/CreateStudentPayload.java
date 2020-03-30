package mathpar.test.utils.dto.frontend.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateStudentPayload{
    private String name;
    private String email;
}