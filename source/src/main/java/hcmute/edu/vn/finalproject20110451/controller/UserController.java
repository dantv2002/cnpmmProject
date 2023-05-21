package hcmute.edu.vn.finalproject20110451.controller;

import hcmute.edu.vn.finalproject20110451.dto.User.UserDto;
import hcmute.edu.vn.finalproject20110451.entity.UserEntity;
import hcmute.edu.vn.finalproject20110451.service.User.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController( UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/updateName")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateInfo( @Valid @RequestBody UserDto dto, Principal principal) {
        UserEntity user = userService.updateName(dto, principal);
        return
                new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PutMapping("/updatePassword")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updatePassword( @Valid @RequestBody UserDto dto, Principal principal) {
        UserEntity user = userService.updatePassword(dto, principal);
        return
                new ResponseEntity<>(user, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteUser( @Valid Principal principal) {
        boolean user = userService.deleteUser(principal);
        return
                new ResponseEntity<>("User " + principal.getName() +" delete status: " + user, HttpStatus.OK);
    }

    @DeleteMapping("/adminDelete/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUserByAdmin(@PathVariable String email, @Valid Principal principal) {
        boolean user = userService.admidDeleteUser(email, principal);
        return
                new ResponseEntity<>("User " + principal.getName() +" delete status: " + user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/filter")
    public ResponseEntity<Page<UserEntity>> filter(@RequestParam String search,
                                                   @RequestParam int page,
                                                   @RequestParam int size,
                                                   @RequestParam String column,
                                                   @RequestParam String sort) {
        return new ResponseEntity<>(userService.filter(search, page, size, column, sort),
                HttpStatus.OK);
    }

}

