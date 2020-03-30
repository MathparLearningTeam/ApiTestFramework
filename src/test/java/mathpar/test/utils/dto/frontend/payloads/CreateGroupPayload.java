package mathpar.test.utils.dto.frontend.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateGroupPayload{
    private final String name;
    private final long teacherId;
}