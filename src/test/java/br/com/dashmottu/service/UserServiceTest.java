package br.com.dashmottu.service;

import br.com.dashmottu.infra.security.TokenService;
import br.com.dashmottu.model.OperationResult;
import br.com.dashmottu.model.dto.AuthenticationDTO;
import br.com.dashmottu.model.dto.LoginResponseDto;
import br.com.dashmottu.model.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private UserService usuarioService;

    @Test
    void deveRetornarSucessoQuandoLoginCorreto() {
        // Arrange
        AuthenticationDTO dto = new AuthenticationDTO("usuario", "senha123");
        User user = new User();
        user.setId(1L);
        user.setLogin("usuario");

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(user);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(tokenService.generateToken(user)).thenReturn("token123");

        // Act
        OperationResult<Object> result = usuarioService.login(dto);

        // Assert
        assertNotNull(result);
        assertInstanceOf(LoginResponseDto.class, result.getData());
        LoginResponseDto response = (LoginResponseDto) result.getData();
        assertEquals("usuario", response.login());
        assertEquals("token123", response.token());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenService).generateToken(user);
    }

    @Test
    void deveRetornarFalhaQuandoLoginIncorreto() {
        // Arrange
        AuthenticationDTO dto = new AuthenticationDTO("usuario", "senhaErrada");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Usuário ou senha incorretos"));

        // Act
        OperationResult<Object> result = usuarioService.login(dto);

        // Assert
        assertNotNull(result);
        assertEquals("Usuário ou senha incorretos", result.getErrorMessage());
        assertEquals(401, result.getStatusCode());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenService, never()).generateToken(any());
    }
}