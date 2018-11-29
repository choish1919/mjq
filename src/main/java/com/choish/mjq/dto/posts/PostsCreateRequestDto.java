package com.choish.mjq.dto.posts;

import com.choish.mjq.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsCreateRequestDto {
    private String title;
    private String content;
    private String author;
    private Long authorid;
    private Long reward;
    private String created_date;

    @Builder
    public PostsCreateRequestDto(String title, String content, String author, Long reward, Long authorid, String created_date){
        this.title = title;
        this.content = content;
        this.author = author;
        this.reward = reward;
        this.authorid = authorid;
        this.created_date = created_date;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .reward(reward)
                .authorid(authorid)
                .created_date(created_date)
                .build();
    }
}
