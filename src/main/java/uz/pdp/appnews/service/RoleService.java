package uz.pdp.appnews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnews.entity.Role;
import uz.pdp.appnews.payload.ApiResponse;
import uz.pdp.appnews.payload.RoleDto;
import uz.pdp.appnews.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;



    public ApiResponse addRole(RoleDto dto) {
        if (roleRepository.existsByName(dto.getName()))
            return new ApiResponse("Role with this name already exists", false);

        Role role = new Role(
                dto.getName(),
                dto.getPermissionsList(),
                dto.getDescription()
        );
        roleRepository.save(role);
        return new ApiResponse("Role has been created", true);
    }

    public ApiResponse edit(RoleDto dto) {
        return null;
    }
}
