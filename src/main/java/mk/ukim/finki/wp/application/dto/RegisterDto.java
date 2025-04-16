package mk.ukim.finki.wp.application.dto;

import mk.ukim.finki.wp.domain.model.Role;

public record RegisterDto(String username, String password, String name, String surname, Role role) {}

