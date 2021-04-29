package uz.pdp.appnews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnews.aop.CheckPermission;
import uz.pdp.appnews.entity.Post;
import uz.pdp.appnews.payload.ApiResponse;
import uz.pdp.appnews.payload.RoleDto;
import uz.pdp.appnews.service.PostService;
import uz.pdp.appnews.service.RoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService service;

    @PreAuthorize(value = "hasAuthority('ADD_POST')")
    @PostMapping
    public HttpEntity<?> addPost(@Valid @RequestBody Post post){
        ApiResponse apiResponse = service.add(post);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @CheckPermission(value = "EDIT_POST")
    @PutMapping("/{id}")
    public HttpEntity<?> editPost(@PathVariable Long id, @Valid @RequestBody Post post){
        ApiResponse apiResponse = service.edit(id,post);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        ApiResponse apiResponse = service.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePost(@PathVariable Long id){
        ApiResponse response = service.delete(id);
        return ResponseEntity.status(response.isSuccess()?202:409).body(response);
    }

}
