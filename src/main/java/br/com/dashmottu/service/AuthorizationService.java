package br.com.dashmottu.service;

import br.com.dashmottu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return repository.findByLogin(username).orElseThrow();
        } catch (UsernameNotFoundException exception) {
            throw new RuntimeException("Usuário não encontrado: " + username);
        }
    }
}
