package uz.pdp.appnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnews.entity.Post;
import uz.pdp.appnews.entity.Role;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByUrl(String url);

}
