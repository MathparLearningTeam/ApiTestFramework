package mathpar.test.utils.dto.frontend.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import mathpar.test.utils.dto.school.responses.SchoolProfileResponse;

@Data
@NoArgsConstructor
public class SchoolResponse{
    private long id;
    private String schoolName;
    private String schoolAddress;
    private SchoolProfileResponse director;

    public SchoolResponse(long schoolId, String schoolName, String schoolAddress){
        this.id = schoolId;
        this.schoolName = schoolName;
        this.schoolAddress = schoolAddress;
    }

    public SchoolResponse withDirector(SchoolProfileResponse director){
        this.director = director;
        return this;
    }
}