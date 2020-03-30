package mathpar.test.utils.dto.school.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mathpar.test.utils.dto.ProfileResponse;
import mathpar.test.utils.enums.SchoolRoles;

import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolProfileResponse extends ProfileResponse {
    private long accountId;
    private String profileName;
    private SchoolRoles role;
    private Date creationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SchoolProfileResponse that = (SchoolProfileResponse) o;
        return accountId == that.accountId && profileId == that.profileId &&
                profileName.equals(that.profileName) &&
                role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountId, profileName, role);
    }
}
