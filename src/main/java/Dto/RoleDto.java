package Dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Long id;
    private String RoleName;

    List<UserDto> userDtos  = new ArrayList<>();
}