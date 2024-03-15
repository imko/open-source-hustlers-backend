package com.opensourcehustlers.opensourcehustlersbackend.domain.post;

import com.opensourcehustlers.opensourcehustlersbackend.domain.tag.Tag;
import com.opensourcehustlers.opensourcehustlersbackend.domain.tag.TagRepository;
import com.opensourcehustlers.opensourcehustlersbackend.domain.user.User;
import com.opensourcehustlers.opensourcehustlersbackend.domain.user.UserRepository;
import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.UserNotFoundException;
import com.opensourcehustlers.opensourcehustlersbackend.exception.post.InvalidUserPostException;
import com.opensourcehustlers.opensourcehustlersbackend.exception.post.PostNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class PostService {

  private final PostRepository postRepository;
  private final TagRepository tagRepository;
  private final UserRepository userRepository;
  private final AuditorAware<String> auditorAware;

  public List<PostResponseDTO> findAll(
      Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
    Sort sort = Sort.by(sortBy);

    if (sortOrder.equalsIgnoreCase("desc")) {
      sort = Sort.by(sortBy).descending();
    }

    if (pageSize == null) {
      return postRepository.findAll(sort).stream()
          .map(
              post ->
                  PostResponseDTO.builder()
                      .id(post.getId())
                      .title(post.getTitle())
                      .description(post.getDescription())
                      .content(post.getContent())
                      .githubUrl(post.getGithubUrl())
                      .visibility(post.getVisibility())
                      .tags(post.getTags())
                      .createdBy(post.getCreatedBy())
                      .createdDate(post.getCreatedDate())
                      .lastModifiedDate(post.getLastModifiedDate())
                      .build())
          .collect(Collectors.toList());
    }

    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

    return postRepository.findAll(pageable).stream()
        .map(
            post ->
                PostResponseDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .description(post.getDescription())
                    .content(post.getContent())
                    .githubUrl(post.getGithubUrl())
                    .visibility(post.getVisibility())
                    .tags(post.getTags())
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
                    .tags(post.getTags())
                    .createdBy(post.getCreatedBy())
                    .createdDate(post.getCreatedDate())
                    .lastModifiedDate(post.getLastModifiedDate())
                    .build())
        .orElseThrow(() -> new PostNotFoundException(id));
  }

  public PostOptionResponseDTO findAllPostOptions() {
    List<Tag> tags = tagRepository.findAll();
    List<PostVisibility> visibilities = List.of(PostVisibility.PUBLIC, PostVisibility.PRIVATE);
    return PostOptionResponseDTO.builder().tags(tags).visibilities(visibilities).build();
  }

  public PostResponseDTO save(PostRequestDTO data) {
    Post post =
        Post.builder()
            .title(data.getTitle())
            .description(data.getDescription())
            .content(data.getContent())
            .githubUrl(data.getGithubUrl())
            .visibility(data.getVisibility())
            .tags(data.getTags())
            .build();
    var savedPost = postRepository.save(post);

    return PostResponseDTO.builder()
        .id(savedPost.getId())
        .title(savedPost.getTitle())
        .description(savedPost.getDescription())
        .content(savedPost.getContent())
        .githubUrl(savedPost.getGithubUrl())
        .visibility(savedPost.getVisibility())
        .tags(savedPost.getTags())
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
              User user =
                  userRepository
                      .findById(data.getUserId())
                      .orElseThrow(() -> new UserNotFoundException(data.getUserId()));

              if (!existingPost.getCreatedBy().equals(user.getEmail())) {
                throw new InvalidUserPostException(user.getEmail(), existingPost.getId());
              }

              var postToUpdate =
                  Post.builder()
                      .id(existingPost.getId())
                      .title(data.getTitle())
                      .description(data.getDescription())
                      .content(data.getContent())
                      .githubUrl(data.getGithubUrl())
                      .visibility(data.getVisibility())
                      .tags(data.getTags())
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
                  .tags(updatedPost.getTags())
                  .createdBy(updatedPost.getCreatedBy())
                  .createdDate(updatedPost.getCreatedDate())
                  .lastModifiedDate(updatedPost.getLastModifiedDate())
                  .build();
            })
        .orElseGet(() -> save(data));
  }

  public void deleteById(Long id) {
    String userEmail = auditorAware.getCurrentAuditor().orElse("anonymous");
    Optional<Post> optionalPost = postRepository.findById(id);

    if (optionalPost.isPresent() && !optionalPost.get().getCreatedBy().equals(userEmail)) {
      throw new InvalidUserPostException(userEmail, id);
    }

    postRepository.deleteById(id);
  }
}
