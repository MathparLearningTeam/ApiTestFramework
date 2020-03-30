package mathpar.test.utils.dto.school.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSchoolProfilesResponse {
    private SchoolProfileResponse director;
    private List<SchoolProfileResponse> headTeachers = new ArrayList<>();
    private List<SchoolProfileResponse> teachers = new ArrayList<>();
    private List<SchoolProfileResponse> students = new ArrayList<>();
}