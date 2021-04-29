package uz.pdp.appnews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.appnews.entity.Comment;
import uz.pdp.appnews.entity.Post;
import uz.pdp.appnews.entity.User;
import uz.pdp.appnews.payload.ApiResponse;
import uz.pdp.appnews.payload.CommentDto;
import uz.pdp.appnews.repository.CommentRepository;
import uz.pdp.appnews.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;


    public ApiResponse add(CommentDto dto){
        Optional<Post> optionalPost = postRepository.findById(dto.getPostId());
        if (!optionalPost.isPresent())
            return new ApiResponse("Article with this Id does not exist", false);
        Comment comment = new Comment(dto.getText(), optionalPost.get());
        commentRepository.save(comment);
        return new ApiResponse("New comment saved", true);
    }

    public List<Comment> getAllCommentsByPost(Long postId){
        return commentRepository.findAllByPostId(postId);
    }
    public ApiResponse getOne(Long id){
        Optional<Comment> optionalComment= commentRepository.findById(id);
        return optionalComment.map(comment -> new ApiResponse("Comment you are looking for:", true, comment)).
                orElseGet(() -> new ApiResponse("Comment with this Id does not exist", false));
    }
    public ApiResponse deleteCommentByEditor(Long id){
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent())
            return new ApiResponse("Comment with this Id does not exist", false);
        commentRepository.deleteById(id);
        return new ApiResponse("The Comment deleted", true);
    }

    public ApiResponse deleteCommentByUser(Long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent())
            return new ApiResponse("Comment with this Id does not exist", false);
        if (!optionalComment.get().getCreatedBy().equals(user))
            return new ApiResponse("This comment does not belong to you, you can not delete other's comment", false);
        commentRepository.deleteById(id);
        return new ApiResponse("The Comment deleted", true);
    }

    public ApiResponse edit(Long id, String text){
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent())
            return new ApiResponse("Comment with this Id does not exist", false);
        Comment comment = optionalComment.get();
        comment.setText(text);
        commentRepository.save(comment);
        return new ApiResponse("The comment edited", true);
    }
}
