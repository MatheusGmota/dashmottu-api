package br.com.dashmottu.model.dto;

import br.com.dashmottu.model.enums.UserRole;

public record UserDto(Long id, String login, UserRole role, boolean status) {
}
