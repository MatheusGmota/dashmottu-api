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

import java.util.List;
import java.util.Optional;

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

    public OperationResult<Object> obterUsersPorPatio(Long idPatio) {

        try {
            List<User> users = repository.findByPatio(idPatio);
            if (!users.isEmpty()) {
                return OperationResult.success(
                        users.stream().map(user ->
                                new UserDto.Response(user.getId(), user.getLogin(), user.getRole(), true)
                        ).toList()
                );
            }
            return OperationResult.success(users);
        } catch (Exception e) {
            return OperationResult.failure(e.getMessage());
        }
    }

    public OperationResult<Object> editar(AuthenticationDTO request, Long id) {
        User user = repository.findById(id).orElse(null);

        if (user != null) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
            user.setLogin(request.login());
            user.setPassword(encryptedPassword);
            user.setId(id);
            repository.save(user);
            return OperationResult.success(user.getId());
        }
        return OperationResult.failure("Usuário não encontrado.");
    }

    public OperationResult<Object> obterPorId(Long id) {
        User user = this.repository.findById(id).orElse(null);
        if (user == null) return OperationResult.failure("Usuário não encontrado");
        return OperationResult.success(
                new UserDto.Response(user.getId(), user.getLogin(), user.getRole(), true)
        );

    }

    public OperationResult<Object> delete(Long id) {
        Optional<User> byId = this.repository.findById(id);
        if (byId.isEmpty()) return OperationResult.failure("Usuário não encontrado");
        this.repository.delete(byId.get());
        return OperationResult.success("Deletado");
    }

    public OperationResult<Object> login(AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            User user = (User) auth.getPrincipal();
            var token = tokenService.generateToken((User) auth.getPrincipal());

            LoginResponseDto response = new LoginResponseDto(user.getId(), user.getLogin(), token);
            return OperationResult.success(response);
        }
        catch (AuthenticationException e) {
            return OperationResult
                    .failure("Usuário ou senha incorretos", 401);
        }
    }

    public OperationResult<Object> register(@Valid RegisterDTO data, Long idPatio) {
        if (this.repository.findByLogin(data.login()).isPresent())
            return OperationResult.failure("Usuário já cadastrado");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        if (idPatio != null) {
            Object o = patioService.salvarUsuario(newUser, idPatio);
            if (o == null)
                return OperationResult.failure("Pátio não encontrado");
        } else {
            this.repository.save(newUser);
        }

        return OperationResult.success(newUser, 201);
    }
}
