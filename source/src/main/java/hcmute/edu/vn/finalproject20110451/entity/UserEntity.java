package hcmute.edu.vn.finalproject20110451.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class UserEntity extends BaseEntity {
    @Id
    private String id;

    private String email;

    private String name;

    @JsonIgnore
    private String password;

    private List<String> roles = new ArrayList<>();

    private boolean trangThai = true;

    public UserEntity(String email, String name, String password, List<String> roles) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }
}
