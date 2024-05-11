package pl.crystalbud.crystallogistics.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.crystalbud.crystallogistics.entity.Authority;
import pl.crystalbud.crystallogistics.repository.CrystalUserRepository;

@Service
@RequiredArgsConstructor
public class CrystalUserDetailService implements UserDetailsService {

    private final CrystalUserRepository crystalUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return crystalUserRepository.findByUsername(username)
                .map(user-> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .authorities(user.getAuthorities().stream()
                                .map(Authority::getAuthority)
                                .map(SimpleGrantedAuthority::new)
                                .toList())
                        .build())
                .orElseThrow(()->new UsernameNotFoundException("User %s not found".formatted(username)));
    }
}
