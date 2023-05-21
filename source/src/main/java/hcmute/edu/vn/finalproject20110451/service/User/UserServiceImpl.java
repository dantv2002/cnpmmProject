package hcmute.edu.vn.finalproject20110451.service.User;

import hcmute.edu.vn.finalproject20110451.dto.User.UserDto;
import hcmute.edu.vn.finalproject20110451.dto.User.registerDto;
import hcmute.edu.vn.finalproject20110451.entity.UserEntity;
import hcmute.edu.vn.finalproject20110451.exception.InvalidException;
import hcmute.edu.vn.finalproject20110451.exception.NotFoundException;
import hcmute.edu.vn.finalproject20110451.exception.UsernameInvalidException;
import hcmute.edu.vn.finalproject20110451.repository.UserRepository;
import hcmute.edu.vn.finalproject20110451.utils.EnumRole;
import hcmute.edu.vn.finalproject20110451.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity create(registerDto dto, Principal principal) {
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPassword())) {
            throw new InvalidException("Password không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Name không được bỏ trống");
        }
        if (userRepository.existsByEmail(dto.getEmail().trim())) {
            throw new InvalidException(String.format("User có email %s đã tồn tại",
                    dto.getEmail()));
        }
        List<String> roles = new ArrayList<>(Arrays.asList(EnumRole.ROLE_USER.toString()));
        UserEntity user = new UserEntity(dto.getEmail().trim(), dto.getName().trim(), dto.getPassword().trim(), roles);
        userRepository.save(user);
        return user;

    }

    @Override
    public UserEntity updateName(UserDto dto, Principal principal) {
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Name không được bỏ trống");
        }
        UserEntity user = userRepository.findByEmailAndTrangThai(principal.getName().trim(), true)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Người dùng %s không tồn tại hoặc đã bị vô hiệu hóa", principal.getName().trim())));
        user.setName(dto.getName().trim());
        userRepository.save(user);
        return user;
    }

    @Override
    public UserEntity updatePassword(UserDto dto, Principal principal) {
        if (ObjectUtils.isEmpty(dto.getPassword())) {
            throw new InvalidException("Password không được bỏ trống");
        }
        if (dto.getPassword().trim().length() < 6) {
            throw new InvalidException("Password phải có ít nhất 6 kí tự");
        }
        UserEntity user = userRepository.findByEmailAndTrangThai(principal.getName().trim(), true)
                .orElseThrow(() -> new UsernameInvalidException(String
                        .format("Người dùng %s không tồn tại hoặc đã bị vô hiệu hóa", principal.getName().trim())));
        user.setPassword(dto.getPassword().trim());
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean deleteUser(Principal principal) {
        UserEntity user = userRepository.findByEmailAndTrangThai(principal.getName().trim(), true)
                .orElseThrow(() -> new UsernameInvalidException(String
                        .format("Người dùng %s không tồn tại hoặc đã bị vô hiệu hóa", principal.getName().trim())));
        user.setTrangThai(false);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean admidDeleteUser(String email, Principal principal) {
        boolean isAdmin = userRepository.existsByEmail(principal.getName().trim());
        if(!isAdmin)
            return false;
        UserEntity user = userRepository.findByEmailAndTrangThai(email.trim(), true)
                .orElseThrow(() -> new InvalidException(String
                        .format("Người dùng %s không tồn tại hoặc đã bị vô hiệu hóa", email.trim())));
        user.setTrangThai(false);
        userRepository.save(user);
        return true;
    }

    @Override
    public Page<UserEntity> filter(String search, int page, int size, String column, String sort) throws NotFoundException {
        Pageable pageable = PageUtils.createPageable(page, size, column, sort);
        Page<UserEntity> pageUser;
        try{
            pageUser = userRepository.findAllByEmailContainsOrNameContains(search, search, pageable);
        }catch(Exception e) {
                throw new NotFoundException("Không tìm thấy thông tin như yêu cầu!");
        }
        return pageUser;
    }

}
