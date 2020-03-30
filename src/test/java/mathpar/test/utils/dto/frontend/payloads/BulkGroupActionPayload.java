package mathpar.test.utils.dto.frontend.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BulkGroupActionPayload {
    private final long groupId;
    private final List<Long> students;
}