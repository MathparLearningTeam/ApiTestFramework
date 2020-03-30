package mathpar.test.utils.dto.frontend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mathpar.test.utils.dto.school.responses.SchoolProfileResponse;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse {
    private long id;
    private String name;
    private SchoolProfileResponse teacher;
    private SchoolResponse school;
    private List<SchoolProfileResponse> students;
}
