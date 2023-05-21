package hcmute.edu.vn.finalproject20110451.service.User;

import hcmute.edu.vn.finalproject20110451.dto.User.UserDto;
import hcmute.edu.vn.finalproject20110451.dto.User.registerDto;
import hcmute.edu.vn.finalproject20110451.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.security.Principal;

public interface UserService {
    UserEntity create(registerDto dto, Principal principal);
    UserEntity updateName(UserDto dto, Principal principal);
    UserEntity updatePassword(UserDto dto, Principal principal);

    boolean deleteUser(Principal principal);

    boolean admidDeleteUser(String email, Principal principal);

    Page<UserEntity> filter(String search, int page, int size, String column, String sort);
}
