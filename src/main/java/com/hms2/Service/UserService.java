package com.hms2.Service;

import com.hms2.Entity.User;
import com.hms2.Repositry.UserRepository;
import com.hms2.payload.LoginDto;
import com.hms2.payload.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private JwtService jwtService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, JwtService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }
       User mapToEntity(UserDto dto){
           User user = modelMapper.map(dto, User.class);
           return user;
       }
    UserDto mapToDto(User user){

        UserDto dto = modelMapper.map(user, UserDto.class);
        return dto;
    }

    public UserDto addUser(UserDto dto) {
        User user = mapToEntity(dto);
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
       if (opUsername.isPresent()) {
           throw new RuntimeException("Username already exists");
        }

        Optional<User> opEmail = userRepository.findByEmail(user.getEmail());
          if(opEmail.isPresent()){

              throw new RuntimeException("Email already exists");
          }
        Optional<User> opPassword = userRepository.findByPassword(user.getPassword());
        if(opPassword.isPresent()){
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10)));
        user.setRole("ROLE_USER");
        User save = userRepository.save(user);
        UserDto userDto = mapToDto(save);
        return userDto;
    }
  public String Verifylogin(LoginDto dto){
      Optional<User> opUsername = userRepository.findByUsername(dto.getUsername());
   if (opUsername.isPresent()){
       User user = opUsername.get();
       if(BCrypt.checkpw(dto.getPassword(),user.getPassword())){
           String token = jwtService.generateToken(user.getUsername());
           return  token;
       }
   }else {
       return null;
   }
return null;
  }

    public UserDto addPropertyOwner(UserDto dto) {
        User user = mapToEntity(dto);
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Optional<User> opEmail = userRepository.findByEmail(user.getEmail());
        if(opEmail.isPresent()){

            throw new RuntimeException("Email already exists");
        }
        Optional<User> opPassword = userRepository.findByPassword(user.getPassword());
        if(opPassword.isPresent()){
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10)));
        user.setRole("ROLE_OWNER");
        User save = userRepository.save(user);
        UserDto userDto = mapToDto(save);
        return userDto;

    }

    public UserDto addBlogManager(UserDto dto) {
        User user = mapToEntity(dto);
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Optional<User> opEmail = userRepository.findByEmail(user.getEmail());
        if(opEmail.isPresent()){

            throw new RuntimeException("Email already exists");
        }
        Optional<User> opPassword = userRepository.findByPassword(user.getPassword());
        if(opPassword.isPresent()){
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10)));
        user.setRole("ROLE_BLOGMANAGER");
        User save = userRepository.save(user);
        UserDto userDto = mapToDto(save);
        return userDto;
    }
}
