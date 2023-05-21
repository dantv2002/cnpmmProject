package hcmute.edu.vn.finalproject20110451.security;

import hcmute.edu.vn.finalproject20110451.entity.UserEntity;
import hcmute.edu.vn.finalproject20110451.exception.UsernameInvalidException;
import hcmute.edu.vn.finalproject20110451.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public JwtUserDetail loadUserByUsername(String email) throws UsernameInvalidException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameInvalidException(
                                String.format("Tài khoản có email %s không tồn tại", email)));

        return new JwtUserDetail(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()),
                user.isTrangThai()
        );
    }

}
