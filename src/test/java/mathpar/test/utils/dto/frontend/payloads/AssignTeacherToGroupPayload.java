package mathpar.test.utils.dto.frontend.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssignTeacherToGroupPayload{
    private final long teacherId;
    private final long groupId;
}
