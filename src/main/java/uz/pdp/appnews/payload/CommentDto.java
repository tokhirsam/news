package uz.pdp.appnews.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class CommentDto {
    @NotNull(message = "Comment text can not be empty")
    private String text;

    private Long postId;
}
