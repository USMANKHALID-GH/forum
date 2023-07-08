package com.usman.forum.repository;

import com.usman.forum.dto.UserDto;
import com.usman.forum.model.Role;
import com.usman.forum.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class UserRepositoryTest {
//    given when then

    @Autowired
    private UserRepository userRepository;

    User user;


    @BeforeEach
    void setUp() {

       user = new User();
        user.setFirstName("uaman");
                user.setLastName("khalid");

                user.setRelatedField("java");
                        user.setEmail("loftyusman@444.com");
                user.setPassword("123");
                user.setPhoneNumber("1222");

        userRepository.save(user);

    }


    @Test
    void findUserByEmailExist() {

        Optional<User> user1=userRepository.findUserByEmail("loftyusman@444.com");
        assertThat(user1.get().getEmail()).isEqualTo("loftyusman@444.com");
    }


    @Test
    void findUserByEmailNotExist() {

        Optional<User> user2=userRepository.findUserByEmail("usman");
        assertThat(user2.isPresent()).isFalse();
    }


    @AfterEach
    void tearDown() {
        user=null;
        userRepository.deleteAll();

    }
}