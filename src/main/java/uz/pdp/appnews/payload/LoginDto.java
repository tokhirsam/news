package uz.pdp.appnews.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDto {


    @NotNull(message = "Username can not be empty")
    private String username;

    @NotNull(message = "Password can not be empty")
    private String password;
}
