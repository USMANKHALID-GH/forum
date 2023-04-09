package com.usman.forum.service.Implementation;

import com.usman.forum.config.Config;
import com.usman.forum.config.JwtUtil;
import com.usman.forum.dto.AuthenticationResponse;
import com.usman.forum.exception.BusinessException;
import com.usman.forum.model.EnumRole;
import com.usman.forum.model.User;
import com.usman.forum.repository.UserRepository;
import com.usman.forum.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
@Slf4j
public class UserImp implements UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    @Override
    public AuthenticationResponse register(@Valid User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(EnumRole.USER);
        userRepository.save(user);
        var token=jwtUtil.generateToken(user);
        log.info("\n,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,serve");
        return AuthenticationResponse.builder()
                .token(token).build();
    }

    @Override
    public AuthenticationResponse authenticate(User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );

        var userEmail=userRepository.findUserByEmail(user.getEmail()).orElseThrow(()->
                new UsernameNotFoundException("There is no such user in our System"));
        var token=jwtUtil.generateToken(userEmail);

        return AuthenticationResponse.builder()
                .token(token).build();
    }

    @Override
    public void saveUser(@Valid User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Override
    public void updateUser(Long id, @Valid User user) {
        User user1=findUser(id);
        if(!(user.getRelatedField().isEmpty()  || user.getRelatedField()==null)){
            user1.setRelatedField(user.getRelatedField());
        }
        if(!(user.getEmail().isEmpty()  || user.getEmail()==null)){
            user1.setEmail(user.getEmail());
        }
        if(!(user.getFirstName().isEmpty()  || user.getFirstName()==null)){
            user1.setFirstName(user.getFirstName());
        }
        if(!(user.getLastName().isEmpty()  || user.getLastName()==null)){
            user1.setLastName(user.getLastName());
        }
        if(!(user.getPhoneNumber().isEmpty()  || user.getPhoneNumber()==null)){
            user1.setPhoneNumber(user.getPhoneNumber());
        }

     }

    @Override
    public void deleteUser(Long id) {
        User user=findUser(id);
        userRepository.delete(user);
    }

    @Override
    public User findUser(Long id) {
        return userRepository.findById(id).
                orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND, "There is  not such Id in our System: "+id));
    }

    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}
