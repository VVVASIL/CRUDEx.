package com.mycompany;

import com.mycompany.user.User;
import com.mycompany.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("f2ewewsd@mail.com");
        user.setPassword("12wf45q");
        user.setFirstname("Igor");
        user.setLastname("Orlov");

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User u:
             users) {
            System.out.println(u);
        }
    }

    @Test
    public void testUpdate(){
        Optional<User> optionalUser = userRepository.findById(7);
        User user = optionalUser.get();
        user.setPassword("240698");
        userRepository.save(user);

        User updatedUser = userRepository.findById(7).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("240698");
    }

    @Test
    public void testGet(){
        Optional<User> optionalUser = userRepository.findById(1);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete(){
        userRepository.deleteById(1);
        Optional<User> optionalUser = userRepository.findById(1);

        Assertions.assertThat(optionalUser).isNotPresent();
    }

}
