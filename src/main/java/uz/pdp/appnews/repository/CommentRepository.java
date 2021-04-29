package uz.pdp.appnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnews.entity.Comment;
import uz.pdp.appnews.entity.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
List<Comment> findAllByPostId(Long post_id);

}
