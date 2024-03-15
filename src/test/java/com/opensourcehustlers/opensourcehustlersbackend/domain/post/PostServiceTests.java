package com.opensourcehustlers.opensourcehustlersbackend.domain.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.opensourcehustlers.opensourcehustlersbackend.exception.post.PostNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {

  @Mock private PostRepository postRepository;

  @InjectMocks private PostService postService;

  @Test
  @DisplayName("Test get an empty list of posts")
  public void testGetAllEmpty() {
    Sort sort = Sort.by("id");
    when(postRepository.findAll(sort)).thenReturn(Collections.emptyList());
    assertThat(postService.findAll(0, null, "id", "asc")).hasSize(0);
  }

  @Test
  @DisplayName("Test get a list of posts with only one")
  public void testGetAllOnlyOne() {
    var post =
        Post.builder()
            .title("title")
            .content("content")
            .visibility(PostVisibility.PUBLIC)
            .githubUrl("url")
            .tags(List.of())
            .build();

    Sort sort = Sort.by("id");
    when(postRepository.findAll(sort)).thenReturn(List.of(post));

    assertThat(
            postService.findAll(0, null, "id", "asc").stream()
                .filter(p -> p.getTitle().equals(post.getTitle()))
                .collect(Collectors.toList()))
        .hasSize(1);
  }

  @Test
  @DisplayName("Test get a list of posts with mroe than one")
  public void testGetAllMoreThanOne() {
    var p1 =
        Post.builder()
            .title("title_1")
            .content("content_1")
            .visibility(PostVisibility.PUBLIC)
            .githubUrl("url")
            .tags(List.of())
            .build();
    var p2 =
        Post.builder()
            .title("title_2")
            .content("content_2")
            .visibility(PostVisibility.PUBLIC)
            .githubUrl("url")
            .tags(List.of())
            .build();

    Sort sort = Sort.by("id");
    when(postRepository.findAll(sort)).thenReturn(List.of(p1, p2));

    assertThat(
            postService.findAll(0, null, "id", "asc").stream()
                .filter(
                    p -> p.getTitle().equals(p1.getTitle()) || p.getTitle().equals(p2.getTitle()))
                .collect(Collectors.toList()))
        .hasSize(2);
  }

  @Test
  @DisplayName("Test get post by existing ID")
  public void testGetByIdExists() {
    var post =
        Post.builder()
            .title("title")
            .content("content")
            .visibility(PostVisibility.PUBLIC)
            .githubUrl("url")
            .tags(List.of())
            .build();

    when(postRepository.findById(1L)).thenReturn(Optional.of(post));

    var actualPost = postService.findById(1L);

    assertThat(actualPost).isNotNull();
    assertThat(actualPost.getTitle()).isEqualTo(post.getTitle());
    assertThat(actualPost.getContent()).isEqualTo(post.getContent());
    assertThat(actualPost.getVisibility()).isEqualTo(post.getVisibility());
  }

  @Test
  @DisplayName("Test get post by non-existing ID")
  public void testGetByIdNotExist() {
    when(postRepository.findById(123L)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> postService.findById(123L))
        .isInstanceOf(PostNotFoundException.class)
        .hasMessage("Post not found: 123");
  }

  @Test
  @DisplayName("Test save post with all fields correct")
  public void testSaveAllFieldsCorrect() {
    var post =
        Post.builder()
            .title("title")
            .description("description")
            .content("content")
            .visibility(PostVisibility.PUBLIC)
            .githubUrl("url")
            .tags(List.of())
            .build();

    when(postRepository.save(post)).thenReturn(post);

    var postRequestDto =
        PostRequestDTO.builder()
            .title(post.getTitle())
            .description(post.getDescription())
            .content(post.getContent())
            .visibility(post.getVisibility())
            .githubUrl(post.getGithubUrl())
            .tags(post.getTags())
            .build();

    var actualPost = postService.save(postRequestDto);

    assertThat(actualPost).isNotNull();
    assertThat(actualPost.getTitle()).isEqualTo(post.getTitle());
    assertThat(actualPost.getDescription()).isEqualTo(post.getDescription());
    assertThat(actualPost.getContent()).isEqualTo(post.getContent());
    assertThat(actualPost.getVisibility()).isEqualTo(post.getVisibility());
  }

  @Test
  @DisplayName("Test update post with all fields correct")
  public void testUpdateAllFieldsCorrect() {
    var post =
        Post.builder()
            .title("title")
            .content("content")
            .description("description")
            .visibility(PostVisibility.PUBLIC)
            .githubUrl("url")
            .tags(List.of())
            .build();

    var postRequestDto =
        PostRequestDTO.builder()
            .userId(1L)
            .title(post.getTitle())
            .description(post.getDescription())
            .content(post.getContent())
            .visibility(post.getVisibility())
            .githubUrl(post.getGithubUrl())
            .tags(post.getTags())
            .build();

    when(postRepository.save(post)).thenReturn(post);

    var actualPost = postService.save(1L, postRequestDto);

    assertThat(actualPost).isNotNull();
    assertThat(actualPost.getTitle()).isEqualTo(post.getTitle());
    assertThat(actualPost.getDescription()).isEqualTo(post.getDescription());
    assertThat(actualPost.getContent()).isEqualTo(post.getContent());
    assertThat(actualPost.getVisibility()).isEqualTo(post.getVisibility());
  }
}
