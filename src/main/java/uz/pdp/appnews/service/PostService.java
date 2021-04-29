package uz.pdp.appnews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnews.entity.Post;
import uz.pdp.appnews.payload.ApiResponse;
import uz.pdp.appnews.repository.PostRepository;

import java.nio.channels.Pipe;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;


    public ApiResponse add(Post post){
        if (postRepository.existsByUrl(post.getUrl()))
            return new ApiResponse("Article already exists in this url", false);
        Post post1 = new Post(post.getTitle(), post.getText(), post.getUrl());
        postRepository.save(post1);
        return new ApiResponse("New artice saved", true);
    }

    public List<Post> getAll(){
       return postRepository.findAll();
    }
    public ApiResponse getOne(Long id){
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.map(post -> new ApiResponse("Article you are looking for:", true, post)).
                orElseGet(() -> new ApiResponse("Article with this Id does not exist", false));
    }
    public ApiResponse delete(Long id){
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent())
            return new ApiResponse("Article with this Id does not exist", false);
        postRepository.deleteById(id);
        return new ApiResponse("The article deleted", true);
    }

    public ApiResponse edit(Long id, Post post){
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent())
            return new ApiResponse("Article with this Id does not exist", false);
        Post post1 = optionalPost.get();
        post1.setText(post.getText());
        post1.setTitle(post.getTitle());
        post1.setUrl(post.getUrl());
        postRepository.save(post1);
        return new ApiResponse("The article edited", true);
    }
}
