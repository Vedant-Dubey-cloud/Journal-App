package net.engineeringdigest.journalApp.services;
import net.engineeringdigest.journalApp.Repository.userRepository;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class UserService {
    @Autowired
    private userRepository userrepository;
    public void saveNewEntry(User user)
    {   user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userrepository.save(user);
    }
//    @Autowired
//    PasswordEncoder passwordEncoder;
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public void saveNewUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userrepository.save(user);

    }
    public List<User> getAll()
    {
        return userrepository.findAll();
    }

    public Optional<User> getById(ObjectId myId)
    {
        return userrepository.findById(myId);
    }
    public User deleteById(ObjectId myId)
    {
        userrepository.deleteById(myId);
        return null;
    }
    public User findByUserName(String userName)
    {
        return userrepository.findByUserName(userName);
    }

    public void saveEntry(User user)
    {
        userrepository.save(user);
    }
}
