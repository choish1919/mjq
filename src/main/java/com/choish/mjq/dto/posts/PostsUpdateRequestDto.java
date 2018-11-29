package com.choish.mjq.dto.posts;

import com.choish.mjq.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    private Long reward;
    private String modified_date;

    @Builder
    public PostsUpdateRequestDto(String title, String content, Long reward, String modified_date){
        this.title = title;
        this.content = content;
        this.reward = reward;
        this.modified_date = modified_date;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .reward(reward)
                .modified_date(modified_date)
                .build();
    }
}