package uz.pdp.appnews.payload;

import lombok.Data;
import uz.pdp.appnews.entity.enums.Permissions;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RoleDto {

    @NotBlank(message = "Name can not be empty")
    private String name;


    private String description;

    @NotEmpty
    private List<Permissions> permissionsList;

}
