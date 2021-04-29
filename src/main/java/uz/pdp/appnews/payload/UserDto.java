package uz.pdp.appnews.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull(message = "Full name can not be empty")
    private String fullName;

    @NotNull(message = "Username can not be empty")
    private String username;

    @NotNull(message = "Password can not be empty")
    private String password;

    @NotNull(message = "Role can not be empty")
    private Integer roleId;
}
