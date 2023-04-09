package com.usman.forum.service.Implementation;

import com.usman.forum.model.Role;
import com.usman.forum.repository.RoleRepository;
import com.usman.forum.service.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
@Slf4j
public class RoleImpl implements RoleService {

    private  final RoleRepository roleRepository;


    @Override
    public void saveRole(@Valid Role role) {

        roleRepository.save(role);

    }
}
