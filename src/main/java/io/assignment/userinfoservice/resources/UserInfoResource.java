package io.assignment.userinfoservice.resources;

import io.assignment.userinfoservice.model.User;
import io.assignment.userinfoservice.model.UserDetails;
import io.assignment.userinfoservice.repository.UserDetailsReposirtory;
import io.assignment.userinfoservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserInfoResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsReposirtory userDetailsReposirtory;

    @RequestMapping("/user/{userId}")
    public UserDetails getUserDetails(@PathVariable("userId") long userId) {
        List<UserDetails> userDetails = userDetailsReposirtory.findByUserId(userId);

       // userRating.initData(userId);
        return userDetails.get(userDetails.size()-1);

    }

    @PostMapping("/user/{userId}")
    public UserDetails setUser(@RequestBody UserDetails userDetails, @PathVariable("userId") long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        userDetails.setUser(user);
        userDetails = userDetailsReposirtory.saveAndFlush(userDetails);
        // userRating.initData(userId);
        return userDetails;
    }

    @PostMapping("/user")
    public User setUser(@RequestBody User user) {
        user = userRepository.saveAndFlush(user);
        // userRating.initData(userId);
        return user;
    }


}
