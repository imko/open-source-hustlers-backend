package com.opensourcehustlers.opensourcehustlersbackend.domain.post;

import com.opensourcehustlers.opensourcehustlersbackend.exception.post.PostNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PostService {

  private final PostRepository postRepository;

  public List<PostResponseDTO> findAll() {
    return postRepository.findAll().stream()
        .map(
            post ->
                PostResponseDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .description(post.getDescription())
                    .content(post.getContent())
                    .githubUrl(post.getGithubUrl())
                    .visibility(post.getVisibility())
                    .createdBy(post.getCreatedBy())
                    .createdDate(post.getCreatedDate())
                    .lastModifiedDate(post.getLastModifiedDate())
                    .build())
        .collect(Collectors.toList());
  }

  public PostResponseDTO findById(Long id) {
    return postRepository
        .findById(id)
        .map(
            post ->
                PostResponseDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .description(post.getDescription())
                    .content(post.getContent())
                    .githubUrl(post.getGithubUrl())
                    .visibility(post.getVisibility())
                    .createdBy(post.getCreatedBy())
                    .createdDate(post.getCreatedDate())
                    .lastModifiedDate(post.getLastModifiedDate())
                    .build())
        .orElseThrow(() -> new PostNotFoundException(id));
  }

  public PostResponseDTO save(PostRequestDTO data) {
    Post post =
        Post.builder()
            .title(data.getTitle())
            .description(data.getDescription())
            .content(data.getContent())
            .githubUrl(data.getGithubUrl())
            .visibility(data.getVisibility())
            .build();
    var savedPost = postRepository.save(post);

    return PostResponseDTO.builder()
        .id(savedPost.getId())
        .title(savedPost.getTitle())
        .description(savedPost.getDescription())
        .content(savedPost.getContent())
        .githubUrl(savedPost.getGithubUrl())
        .visibility(savedPost.getVisibility())
        .createdBy(savedPost.getCreatedBy())
        .createdDate(savedPost.getCreatedDate())
        .lastModifiedDate(savedPost.getLastModifiedDate())
        .build();
  }

  public PostResponseDTO save(Long id, PostRequestDTO data) {
    return postRepository
        .findById(id)
        .map(
            existingPost -> {
              var postToUpdate =
                  Post.builder()
                      .id(existingPost.getId())
                      .title(data.getTitle())
                      .description(data.getDescription())
                      .content(data.getContent())
                      .githubUrl(data.getGithubUrl())
                      .visibility(data.getVisibility())
                      .createdBy(existingPost.getCreatedBy())
                      .createdDate(existingPost.getCreatedDate())
                      .lastModifiedBy(existingPost.getLastModifiedBy())
                      .lastModifiedDate(existingPost.getLastModifiedDate())
                      .version(existingPost.getVersion())
                      .build();

              var updatedPost = postRepository.save(postToUpdate);

              return PostResponseDTO.builder()
                  .id(updatedPost.getId())
                  .title(updatedPost.getTitle())
                  .description(updatedPost.getDescription())
                  .content(updatedPost.getContent())
                  .githubUrl(updatedPost.getGithubUrl())
                  .visibility(updatedPost.getVisibility())
                  .createdBy(updatedPost.getCreatedBy())
                  .createdDate(updatedPost.getCreatedDate())
                  .lastModifiedDate(updatedPost.getLastModifiedDate())
                  .build();
            })
        .orElseGet(() -> save(data));
  }

  public void deleteById(Long id) {
    postRepository.deleteById(id);
  }
}
