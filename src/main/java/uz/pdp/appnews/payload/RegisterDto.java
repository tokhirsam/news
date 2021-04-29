package uz.pdp.appnews.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterDto {

    @NotNull(message = "Full name can not be empty")
    private String fullName;

    @NotNull(message = "Username can not be empty")
    private String username;

    @NotNull(message = "Password can not be empty")
    private String password;

    @NotNull(message = "Pre password can not be empty")
    private String prePassword;
}
