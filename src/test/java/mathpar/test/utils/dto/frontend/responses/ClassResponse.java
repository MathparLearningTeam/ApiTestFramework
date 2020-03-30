package mathpar.test.utils.dto.frontend.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import mathpar.test.utils.dto.school.responses.SchoolProfileResponse;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class ClassResponse {
    private long id;
    private String name;
    private List<SchoolProfileResponse> students;

    public ClassResponse(long id, String name) {
        this.id = id;
        this.name = name;
        this.students = Collections.emptyList();
    }

    public ClassResponse withStudents(List<SchoolProfileResponse> students){
        this.students = students;
        return this;
    }
}
