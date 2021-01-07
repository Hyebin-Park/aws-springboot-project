package com.firstproject.springboot.web;

import com.firstproject.springboot.Service.posts.PostsService;
import com.firstproject.springboot.web.Dto.PostsSveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSveRequestDto requestDto) {
        return postsService.save(requestDto);
    }
}
