package uz.pdp.appnews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnews.aop.CheckPermission;
import uz.pdp.appnews.entity.Comment;
import uz.pdp.appnews.entity.Post;
import uz.pdp.appnews.payload.ApiResponse;
import uz.pdp.appnews.payload.CommentDto;
import uz.pdp.appnews.service.CommentService;
import uz.pdp.appnews.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService service;

    @PreAuthorize(value = "hasAuthority('ADD_COMMENT')")
    @PostMapping
    public HttpEntity<?> addComment(@Valid @RequestBody CommentDto dto){
        ApiResponse apiResponse = service.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @CheckPermission(value = "EDIT_COMMENT")
    @PutMapping("/{id}")
    public HttpEntity<?> editComment(@PathVariable Long id, String text){
        ApiResponse apiResponse = service.edit(id,text);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsByPost(Long postId){
        return ResponseEntity.ok(service.getAllCommentsByPost(postId));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        ApiResponse apiResponse = service.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_COMMENT')")
    @DeleteMapping("/byEditor/{id}")
    public HttpEntity<?> deleteCommentByEditor(@PathVariable Long id){
        ApiResponse response = service.deleteCommentByEditor(id);
        return ResponseEntity.status(response.isSuccess()?202:409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_MY_COMMENT')")
    @DeleteMapping("/byUser/{id}")
    public HttpEntity<?> deleteCommentByUser(@PathVariable Long id){
        ApiResponse response = service.deleteCommentByUser(id);
        return ResponseEntity.status(response.isSuccess()?202:409).body(response);
    }



}
