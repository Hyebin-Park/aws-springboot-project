package com.firstproject.springboot.domain;

import com.firstproject.springboot.domain.posts.Posts;
import com.firstproject.springboot.domain.posts.PostsRepository;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트용 게시글";
        String content = "테스트용 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("HB")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {

        // given
        LocalDateTime now = LocalDateTime.of(2021, 1, 1, 12, 1, 30);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("HB")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        System.out.println(">>>createDate : " + posts.getCreatedDate() + "modifiedDate : " + posts.getModifiedTime());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedTime()).isAfter(now);

    }
}
