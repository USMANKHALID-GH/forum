package com.usman.forum.service.Implementation;

import com.usman.forum.exception.BusinessException;
import com.usman.forum.model.Role;
import com.usman.forum.model.User;
import com.usman.forum.repository.RoleRepository;
import com.usman.forum.repository.UserRepository;
import com.usman.forum.service.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class RoleImpl implements RoleService {

    private  final RoleRepository roleRepository;
    private final  UserImp userImp;
    private final UserRepository userRepository;


    @Override
    public void saveRole(@Valid Role role) {

        roleRepository.save(role);

    }

    @Override
    public void asignRole(Long userId, Long roleId) {
//        User user=userImp.findUser(userId);
//        Role role=roleRepository.findById(roleId).orElseThrow(
//                ()-> new BusinessException(HttpStatus.NOT_FOUND,"ROLE ID NOT FOUND"+roleId));
//        log.info(role.toString());
//        log.info(user.toString());
//        role.setUser(List.of(user));
//        log.info("saving roles1");
//        user.setRoles(List.of(role));
//        log.info("saving 2");
////        userRepository.save(user);
//        log.info("saving 3");
//        roleRepository.save(role);
//        log.info("saving 4");
    }
}
