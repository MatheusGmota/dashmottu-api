package br.com.dashmottu.service;

import br.com.dashmottu.infra.security.TokenService;
import br.com.dashmottu.model.OperationResult;
import br.com.dashmottu.model.dto.AuthenticationDTO;
import br.com.dashmottu.model.dto.LoginResponseDto;
import br.com.dashmottu.model.dto.RegisterDTO;
import br.com.dashmottu.model.dto.UserDto;
import br.com.dashmottu.model.entities.User;
import br.com.dashmottu.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PatioService patioService;

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

    public OperationResult<Object> login(AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            User user = (User) auth.getPrincipal();
            var token = tokenService.generateToken((User) auth.getPrincipal());

            LoginResponseDto response = new LoginResponseDto(user.getId(), user.getLogin(), token);
            return new OperationResult<>().success(response);
        }
        catch (AuthenticationException e) {
            return new OperationResult<>()
                    .failure("Usuário ou senha incorretos", 401);
        }
    }

    public OperationResult<Object> register(@Valid RegisterDTO data, Long idPatio) {
        if (this.repository.findByLogin(data.login()).isPresent())
            return new OperationResult<>().failure("Usuário já cadastrado");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        if (idPatio != null) {
            Object o = patioService.salvarUsuario(newUser, idPatio);
            if (o == null)
                return new OperationResult<>().failure("Pátio não encontrado");
        } else {
            this.repository.save(newUser);
        }

        return new OperationResult<>().success(newUser, 201);
    }
}
