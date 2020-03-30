package mathpar.test.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Account {
    private long id;
    private String email;
    private String password;
    private String fullName;
    private Date creationDate;
}
