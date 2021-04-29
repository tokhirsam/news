package uz.pdp.appnews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnews.aop.CheckPermission;
import uz.pdp.appnews.payload.ApiResponse;
import uz.pdp.appnews.payload.RoleDto;
import uz.pdp.appnews.payload.UserDto;
import uz.pdp.appnews.service.RoleService;
import uz.pdp.appnews.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleService service;

    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDto dto){
        ApiResponse apiResponse = service.addRole(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAuthority('EDIT_ROLE')")
    @CheckPermission(value = "EDIT_ROLE")
    @PutMapping("/{id}")
    public HttpEntity<?> editRole(@PathVariable Long id, @Valid @RequestBody RoleDto dto){
        ApiResponse apiResponse = service.edit(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
