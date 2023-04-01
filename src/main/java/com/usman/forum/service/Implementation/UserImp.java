package com.usman.forum.service.Implementation;

import com.usman.forum.Config;
import com.usman.forum.exception.BusinessException;
import com.usman.forum.model.User;
import com.usman.forum.repository.UserRepository;
import com.usman.forum.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
@Slf4j
public class UserImp implements UserService {

    private final UserRepository userRepository;
    private Config config;

    @Override
    public void saveUser(@Valid User user) {

        user.setPassword((config.passwordEncryptor().encrypt(user.getPassword())));
        userRepository.save(user);

    }

    @Override
    public void updateUser(Long id, @Valid User user) {
        User user1=findUserByID(id);
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
        User user=findUserByID(id);
        userRepository.delete(user);
    }

    @Override
    public User findUser(Long id) {
        return findUserByID(id);
    }

    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    private User findUserByID(Long id){
         User user= userRepository.findById(id).
                 orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND, "There is  not such Id in our System: "+id));
         return user;
     }


}
