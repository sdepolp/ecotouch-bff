package cl.ecotouch.bff.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UsersDto {
    String username;
    String password;
    Integer profile;
    String email;
    String name;
    String rut;
    String mobile;
}
