package br.com.dashmottu.model.dto;

import br.com.dashmottu.model.enums.UserRole;

public record SignupDTO(String login, String password, UserRole role) {
}
