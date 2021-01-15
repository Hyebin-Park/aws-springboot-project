package com.firstproject.springboot.Service.posts;

import com.firstproject.springboot.domain.posts.Posts;
import com.firstproject.springboot.domain.posts.PostsRepository;
import com.firstproject.springboot.web.Dto.PostsListResponseDto;
import com.firstproject.springboot.web.Dto.PostsResponseDto;
import com.firstproject.springboot.web.Dto.PostsSaveRequestDto;
import com.firstproject.springboot.web.Dto.PostsUpdateRequestsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    // 등록
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId(); // DTO 거쳐서 DB 수정해주고, 나중에 쓸 id 반환
    }

    // 수정
    @Transactional
    public Long update(Long id, PostsUpdateRequestsDto requestDto) {
        Posts posts = postsRepository.findById(id) // 수정할 포스트 찾고
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent()); // 업데이트용 DTO에서 뽑아온 데이터로 DB 수정

        return id;
    }

    // 조회
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id) // 포스트 찾고
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostsResponseDto(entity); // 응답용 DTO 업데이트 후 반환
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
