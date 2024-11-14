package Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "name cannot be null")
    private String name;


    @NotBlank(message = "email cannot be null")
    private String email;

    @NotBlank(message = "password cannot be null")
    private String password;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number should be 10 digits")
    private String phoneNo;



    List<RoleDto> roleDtos = new ArrayList<>();
}
