package br.com.dashmottu.model.dto;

import br.com.dashmottu.model.enums.UserRole;

public class UserDto {

    public record Response(Long id, String login, UserRole role, boolean status) {}
}
