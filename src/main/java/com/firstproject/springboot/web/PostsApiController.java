package com.firstproject.springboot.web;

import com.firstproject.springboot.Service.posts.PostsService;
import com.firstproject.springboot.web.Dto.PostsResponseDto;
import com.firstproject.springboot.web.Dto.PostsSaveRequestDto;
import com.firstproject.springboot.web.Dto.PostsUpdateRequestsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    // 등록(post)
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    // 수정(put)
    @PutMapping("/api/v1/posts/{id}")
    public Long update (@PathVariable Long id, @RequestBody PostsUpdateRequestsDto requestDto) {
        return postsService.update(id, requestDto);
    }

    // 조회(get)
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

}
