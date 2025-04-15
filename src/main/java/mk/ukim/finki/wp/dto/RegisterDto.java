package mk.ukim.finki.wp.dto;

import mk.ukim.finki.wp.model.Role;

public record RegisterDto(String username, String password, String name, String surname, Role role) {}

