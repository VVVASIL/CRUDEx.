package com.mycompany.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository ur;

    public List<User> listAll(){
        return (List<User>) ur.findAll();
    }

    public void save(User user) {
        ur.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = ur.findById(id);
        if (result.isPresent())
        {
            return result.get();
        }
        throw new UserNotFoundException("Couldn't find any Users with ID: " + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = ur.countById(id);
        if (count==null || count == 0){
            throw new UserNotFoundException("Couldn't find any Users with ID: " + id);
        }
        ur.deleteById(id);
    }
}
