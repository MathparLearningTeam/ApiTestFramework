package mathpar.test.utils.dto.school.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mathpar.test.utils.dto.ProfileResponse;
import mathpar.test.utils.enums.SchoolRoles;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SchoolProfileResponse extends ProfileResponse {
    private long accountId;
    private String profileName;
    private SchoolRoles role;
    private Date creationDate;
}
