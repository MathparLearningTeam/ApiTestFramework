package mathpar.test.utils.dto.frontend.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class UserResponse{
    private long id;
    private String userName;
    private String userEmail;
    private String type;
    private SchoolResponse school;

    public UserResponse(long id, String email, String name){
        this.userEmail = email;
        this.userName = name;
        this.id = id;
    }

    public UserResponse withSchool(SchoolResponse school){
        this.school = school;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return id == that.id &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userEmail, type);
    }
}