package com.hms2.Controller;

import com.hms2.Entity.User;
import com.hms2.Service.UserService;
import com.hms2.payload.JwtToken;
import com.hms2.payload.LoginDto;
import com.hms2.payload.ProfileDto;
import com.hms2.payload.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    // For user signup
    //http://localhost:8080/api/auth/sign-up

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto>CreateUser(
            @RequestBody UserDto dto
            ){
        UserDto userDto = userService.addUser(dto);
        return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }

    //For PropertyOwner sign-up
    //http://localhost:8080/api/auth/property/sign-up

    @PostMapping("/property/sign-up")
    public ResponseEntity<UserDto>CreatePropertyOwnerAccount(
            @RequestBody UserDto dto
    ){
        UserDto userDto = userService.addPropertyOwner(dto);
        return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }

    // For Blogmanager sign-up
    //http://localhost:8080/api/auth/blog/sign-up

    @PostMapping("/blog/sign-up")
    public ResponseEntity<UserDto>CreateBlogManagerAccount(
            @RequestBody UserDto dto
    ){
        UserDto userDto = userService.addBlogManager(dto);
        return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }

// for user,owner,blogmanager,Admin login
    //http://localhost:8080/api/auth/login

    @PostMapping("/login")
    public ResponseEntity<?>verfiyLogin(
            @RequestBody LoginDto dto
            ) {
        String token = userService.Verifylogin(dto);
        JwtToken jwtToken= new JwtToken();
        jwtToken.setToken(token);
        jwtToken.setType("JWT");
        if (token!= null) {
            return  new ResponseEntity<>(jwtToken,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("invalid",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // who login now for user profile
    //http://localhost:8080/api/auth/userprofile

    @GetMapping("/userprofile")
    public ResponseEntity<ProfileDto>getUserProfile(
            @AuthenticationPrincipal User user

    ){
        ProfileDto profileDto = new ProfileDto();
        profileDto.setUsername(user.getUsername());
        profileDto.setEmail(user.getEmail());
        profileDto.setName(user.getName());
        return  new ResponseEntity<>(profileDto,HttpStatus.OK);
    }
}
