package lt.freeland.uaa.service;

import java.util.HashSet;
import java.util.Set;
import lt.freeland.common.domain.User;
import lt.freeland.uaa.repository.UserRepository;
import lt.freeland.uaa.beans.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author freeland
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach((role) -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new CustomUserDetails(user);
    }
}
