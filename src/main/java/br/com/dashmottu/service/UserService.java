package br.com.dashmottu.service;

import br.com.dashmottu.model.dto.AuthenticationDTO;
import br.com.dashmottu.model.dto.UserDto;
import br.com.dashmottu.model.entities.User;
import br.com.dashmottu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public Object editar(AuthenticationDTO request, Long id) {
        User user = repository.findById(id).orElse(null);
        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        if (user != null) {
            user.setLogin(request.login());
            user.setPassword(encryptedPassword);
            user.setId(id);
            return repository.save(user);
        }
        return null;
    }

    public UserDto.Response obterPorId(Long id) {
        User user = this.repository.findById(id).orElse(null);
        if (user == null) return null;
        return new UserDto.Response(user.getId(), user.getLogin(), user.getRole(), true);
    }

    public Object delete(Long id) {
        User user = this.repository.findById(id).orElse(null);
        if (user == null) return null;
        this.repository.delete(user);
        return "Deletado";
    }
}
