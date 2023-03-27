package com.usman.forum.service.Implementation;

import com.usman.forum.exception.BusinessException;
import com.usman.forum.model.User;
import com.usman.forum.repository.UserRepository;
import com.usman.forum.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public void saveUser(@Valid User user) {
        userRepository.save(user);

    }

    @Override
    public void updateUser(Long id, @Valid User user) {
        User user1=findUserByID(id);
        if(!(user.getField().isEmpty()  || user.getField()==null)){
            user1.setField(user.getField());
        }
        if(!(user.getEmail().isEmpty()  || user.getEmail()==null)){
            user1.setEmail(user.getEmail());
        }
        if(!(user.getFirstName().isEmpty()  || user.getFirstName()==null)){
            user1.setFirstName(user.getFirstName());
        }
        if(!(user.getSecondName().isEmpty()  || user.getSecondName()==null)){
            user1.setSecondName(user.getSecondName());
        }
        if(!(user.getNumber().isEmpty()  || user.getNumber()==null)){
            user1.setNumber(user.getNumber());
        }

     }


     private User findUserByID(Long id){
         User user= userRepository.findById(id).
                 orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND, "There is  not such Id in our System: "+id));
         return user;
     }
}
