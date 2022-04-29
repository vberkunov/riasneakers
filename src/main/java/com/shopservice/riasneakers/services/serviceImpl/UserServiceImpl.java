package com.shopservice.riasneakers.services.serviceImpl;



import com.shopservice.riasneakers.dto.UserDto;
import com.shopservice.riasneakers.dto.response.AuthResponse;
import com.shopservice.riasneakers.dto.response.RegisterResponse;
import com.shopservice.riasneakers.entity.Role;
import com.shopservice.riasneakers.entity.User;
import com.shopservice.riasneakers.repository.RoleRepository;
import com.shopservice.riasneakers.repository.UserRepository;
import com.shopservice.riasneakers.security.JwtProvider;
import com.shopservice.riasneakers.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public UserServiceImpl(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository,  JwtProvider jwtProvider) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public RegisterResponse saveAdmin(UserDto user) {
        RegisterResponse response = new RegisterResponse();
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setUsername(user.getUsername());
        Role userRole = roleRepository.findByRoleName("ROLE_ADMIN");
        newUser.setRole(userRole);
        try {
            userRepository.save(newUser);
            response.setStatus("Ok");
            }catch (Exception e){
            response.setStatus("Failed");
            response.setMessage(e.getMessage());
            }
        return response;
    }

    public RegisterResponse saveUser(UserDto user) {
       RegisterResponse response = new RegisterResponse();
//        User newUser = new User();
//        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
//        newUser.setUsername(user.getUsername());
//        Role userRole = roleRepository.findByRoleName("ROLE_USER");
//        newUser.setRole(userRole);
//        PersonalCard card = cardRepository.findById(user.getUsername()).orElse(null);
//        if(card == null){
//            response.setMessage("Creating password failed");
//            response.setStatus("Failed");
//        }else{
//            try {
//                User userFromDb = userRepository.save(newUser);
//                card.setUserId(userFromDb.getId());
//                card.setFirstLogin(false);
//                cardRepository.save(card);
//                response.setStatus("Ok");
//            }catch (Exception e){
//                response.setMessage("Saving new user in data base failed");
//                response.setStatus("Failed");
//            }
//
//        }
        return response;

    }

    public User findByLogin(String username) {

        return userRepository.findByUsername(username);
    }

    public User findByLoginAndPassword(String login, String password) {
        User userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    public AuthResponse authentificateUser(UserDto request) {
        User user= findByLoginAndPassword(request.getUsername(), request.getPassword());
        if(user!=null) {
            String token = jwtProvider.generateToken(user.getUsername());
            return new AuthResponse(token, "");
        }else{
            return new AuthResponse("","Failed Auth");
        }
    }
}
