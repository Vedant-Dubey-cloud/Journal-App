package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.Repository.userRepository;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component

public class UserDetailServiceimpl implements UserDetailsService {

    @Autowired
    userRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user=userRepository.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("username not found"+username));
        User user = userRepository.findByUserName(username);
        if (user != null) {
            UserDetails userDetails =org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles()
                            .toArray(new String[0])).build();
            return userDetails;
        }
        return null;

    }

}
