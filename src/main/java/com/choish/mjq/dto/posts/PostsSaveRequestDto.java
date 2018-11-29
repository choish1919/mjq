package com.choish.mjq.dto.posts;

import com.choish.mjq.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    private Long authorid;
    private Long reward;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, Long reward, Long authorid){
        this.title = title;
        this.content = content;
        this.author = author;
        this.reward = reward;
        this.authorid = authorid;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .reward(reward)
                .authorid(authorid)
                .build();
    }
}
