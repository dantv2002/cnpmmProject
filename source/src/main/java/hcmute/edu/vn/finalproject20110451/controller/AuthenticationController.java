package hcmute.edu.vn.finalproject20110451.controller;

import hcmute.edu.vn.finalproject20110451.dto.User.loginDto;
import hcmute.edu.vn.finalproject20110451.dto.User.registerDto;
import hcmute.edu.vn.finalproject20110451.entity.UserEntity;
import hcmute.edu.vn.finalproject20110451.exception.InvalidException;
import hcmute.edu.vn.finalproject20110451.security.CustomUserDetailService;
import hcmute.edu.vn.finalproject20110451.security.JwtTokenUtils;
import hcmute.edu.vn.finalproject20110451.security.JwtUserDetail;
import hcmute.edu.vn.finalproject20110451.security.TokenDetail;
import hcmute.edu.vn.finalproject20110451.service.User.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    private final CustomUserDetailService customUserDetailService;

    private final JwtTokenUtils jwtTokenUtils;

    private final UserService userService;

    public AuthenticationController(CustomUserDetailService customUserDetailService,
                                    JwtTokenUtils jwtTokenUtils, UserService userService) {
        this.customUserDetailService = customUserDetailService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody loginDto dto) throws InvalidException {
        final JwtUserDetail userDetails = customUserDetailService
                .loadUserByUsername(dto.getEmail());
        if(!userDetails.isEnabled()){
            throw new InvalidException("User "+userDetails.getUsername()+" đã bị vô hiệu hóa");
        }
        final TokenDetail result = jwtTokenUtils.getTokenDetail(userDetails);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody registerDto dto) {
        UserEntity user = userService.create(dto, null);
        return
                new ResponseEntity<>(String.format("User %s register successfully with email %s !!!", user.getName(), user.getEmail()), HttpStatus.CREATED);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> sayHello(Principal principal) {
        return new ResponseEntity<>(String.format("Hello %s",principal.getName()), HttpStatus.OK);
    }
}

