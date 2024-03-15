package com.opensourcehustlers.opensourcehustlersbackend.domain.post;

import static org.assertj.core.api.Assertions.assertThat;

import com.opensourcehustlers.opensourcehustlersbackend.config.DataConfig;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("integration")
public class PostRepositoryTests {

//  @Autowired
//  private PostRepository postRepository;
//
//  @Autowired private TestEntityManager testEntityManager;
//
//  @BeforeEach
//  public void setup() {
//    postRepository.deleteAll();
//    testEntityManager.clear();
//    testEntityManager.flush();
//  }
//
//  @Test
//  @DisplayName("Test find all empty")
//  public void testFindAllEmpty() {
//    Iterable<Post> posts = postRepository.findAll();
//    assertThat(posts).isEmpty();
//  }
//
//  @Test
//  @DisplayName("Test find all with only one post")
//  public void testFindAllOnlyOne() {
//    var post =
//        Post.builder()
//            .title("title")
//            .description("description")
//            .content("content")
//            .visibility(PostVisibility.PUBLIC)
//            .githubUrl("url")
//            .tags(List.of())
//            .build();
//
//    testEntityManager.persist(post);
//
//    Iterable<Post> posts = postRepository.findAll();
//
//    assertThat(
//        StreamSupport
//            .stream(posts.spliterator(), true)
//            .filter(p -> p.getTitle().equals(post.getTitle()))
//            .collect(Collectors.toList()))
//        .hasSize(1);
//  }
//
//  @Test
//  @DisplayName("Test find all with more than one post")
//  public void testFindAllMoreThanOne() {
//    var p1 =
//        Post.builder()
//            .title("title_1")
//            .description("description_1")
//            .content("content_1")
//            .visibility(PostVisibility.PUBLIC)
//            .githubUrl("url")
//            .tags(List.of())
//            .build();
//    var p2 =
//        Post.builder()
//            .title("title_2")
//            .description("description_2")
//            .content("content_2")
//            .visibility(PostVisibility.PUBLIC)
//            .githubUrl("url")
//            .tags(List.of())
//            .build();
//
//    testEntityManager.persist(p1);
//    testEntityManager.persist(p2);
//
//    Iterable<Post> posts = postRepository.findAll();
//
//    assertThat(
//        StreamSupport.stream(posts.spliterator(), true)
//                     .filter(
//                         post ->
//                             post.getTitle().equals(p1.getTitle())
//                                 || post.getTitle().equals(p2.getTitle()))
//                     .collect(Collectors.toList()))
//        .hasSize(2);
//  }
//
//  @Test
//  @DisplayName("Test find post with existing ID")
//  public void testFindByIdExists() {
//    var post =
//        Post.builder()
//            .title("title")
//            .description("description")
//            .content("content")
//            .visibility(PostVisibility.PUBLIC)
//            .githubUrl("url")
//            .tags(List.of())
//            .build();
//
//    var postId = (Long) testEntityManager.persistAndGetId(post);
//
//    Optional<Post> result = postRepository.findById(postId);
//
//    assertThat(result).isPresent();
//    assertThat(result.get().getTitle()).isEqualTo(post.getTitle());
//  }
//
//  @Test
//  @DisplayName("Test find post with non-existing ID")
//  public void testFindByIdNotExist() {
//    var post =
//        Post.builder()
//            .title("title")
//            .description("description")
//            .content("content")
//            .visibility(PostVisibility.PUBLIC)
//            .githubUrl("url")
//            .tags(List.of())
//            .build();
//
//    testEntityManager.persist(post);
//
//    Optional<Post> result = postRepository.findById(123L);
//
//    assertThat(result).isEmpty();
//  }
//
//  @Test
//  @DisplayName("Test delete post by ID")
//  public void testDeleteById() {
//    var post =
//        Post.builder()
//            .title("title")
//            .description("description")
//            .content("content")
//            .visibility(PostVisibility.PUBLIC)
//            .githubUrl("url")
//            .tags(List.of())
//            .build();
//
//    var postId = (Long) testEntityManager.persistAndGetId(post);
//
//    postRepository.deleteById(postId);
//
//    assertThat(testEntityManager.find(Post.class, postId)).isNull();
//  }
}
