package com.opensourcehustlers.opensourcehustlersbackend.domain.post;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PostValidationTests {

  private static Validator validator;

  @BeforeAll
  public static void setup() {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
  }

  @Test
  @DisplayName("Test when all fields are correct and validation succeeds")
  public void testAllFieldsCorrect() {
    var post =
        Post.builder()
            .title("title")
            .description("description")
            .content("content")
            .visibility(PostVisibility.PUBLIC)
            .githubUrl("url")
            .tags(List.of())
            .build();
    Set<ConstraintViolation<Post>> violations = validator.validate(post);
    assertThat(violations).isEmpty();
  }

  @Test
  @DisplayName("Test when title is not defined and validation fails")
  public void testTitleNotDefined() {
    var post =
        Post.builder()
            .description("description")
            .content("content")
            .visibility(PostVisibility.PUBLIC)
            .githubUrl("url")
            .tags(List.of())
            .build();
    Set<ConstraintViolation<Post>> violations = validator.validate(post);
    assertThat(violations).hasSize(1);
    assertThat(violations.iterator().next().getMessage()).isEqualTo("Title must be provided");
  }

  @Test
  @DisplayName("Test when title is empty and validation fails")
  public void testTitleEmpty() {
    var post =
        Post.builder()
            .title("")
            .description("description")
            .content("content")
            .visibility(PostVisibility.PUBLIC)
            .githubUrl("url")
            .tags(List.of())
            .build();
    Set<ConstraintViolation<Post>> violations = validator.validate(post);
    assertThat(violations).hasSize(1);
    assertThat(violations.iterator().next().getMessage()).isEqualTo("Title must be provided");
  }

  @Test
  @DisplayName("Test when description is not defined and validation fails")
  public void testDescriptionNotDefined() {
    var post =
        Post.builder()
            .title("title")
            .content("content")
            .visibility(PostVisibility.PUBLIC)
            .githubUrl("url")
            .tags(List.of())
            .build();
    Set<ConstraintViolation<Post>> violations = validator.validate(post);
    assertThat(violations).hasSize(1);
    assertThat(violations.iterator().next().getMessage()).isEqualTo("Description must be provided");
  }

  @Test
  @DisplayName("Test when description is empty and validation fails")
  public void testDescriptionEmpty() {
    var post =
        Post.builder()
            .title("title")
            .description("")
            .content("content")
            .visibility(PostVisibility.PUBLIC)
            .githubUrl("url")
            .tags(List.of())
            .build();
    Set<ConstraintViolation<Post>> violations = validator.validate(post);
    assertThat(violations).hasSize(1);
    assertThat(violations.iterator().next().getMessage()).isEqualTo("Description must be provided");
  }

  @Test
  @DisplayName("Test when content is not defined and validation fails")
  public void testContentNotDefined() {
    var post =
        Post.builder()
            .title("title")
            .description("description")
            .visibility(PostVisibility.PUBLIC)
            .githubUrl("url")
            .tags(List.of())
            .build();
    Set<ConstraintViolation<Post>> violations = validator.validate(post);
    assertThat(violations).hasSize(1);
    assertThat(violations.iterator().next().getMessage()).isEqualTo("Content must be provided");
  }

  @Test
  @DisplayName("Test when content is empty and validation fails")
  public void testContentEmpty() {
    var post =
        Post.builder()
            .title("title")
            .description("description")
            .content("")
            .visibility(PostVisibility.PUBLIC)
            .githubUrl("url")
            .tags(List.of())
            .build();
    Set<ConstraintViolation<Post>> violations = validator.validate(post);
    assertThat(violations).hasSize(1);
    assertThat(violations.iterator().next().getMessage()).isEqualTo("Content must be provided");
  }

  @Test
  @DisplayName("Test when visibility is not defined and validation fails")
  public void testVisibilityNotDefined() {
    var post =
        Post.builder()
            .title("title")
            .description("description")
            .content("content")
            .githubUrl("url")
            .tags(List.of())
            .build();
    Set<ConstraintViolation<Post>> violations = validator.validate(post);
    assertThat(violations).hasSize(1);
    assertThat(violations.iterator().next().getMessage())
        .isEqualTo("Post visibility must be provided");
  }

  @Test
  @DisplayName("Test when github url is not defined and validation fails")
  public void testGithubUrlNotDefined() {
    var post =
        Post.builder()
            .title("title")
            .description("description")
            .content("content")
            .visibility(PostVisibility.PUBLIC)
            .tags(List.of())
            .build();
    Set<ConstraintViolation<Post>> violations = validator.validate(post);
    assertThat(violations).hasSize(1);
    assertThat(violations.iterator().next().getMessage()).isEqualTo("GitHub URL must be provided");
  }

  @Test
  @DisplayName("Test when tags are not defined and validation succeeds")
  public void testTagsNotDefined() {
    var post =
        Post.builder()
            .title("title")
            .description("description")
            .content("content")
            .visibility(PostVisibility.PUBLIC)
            .githubUrl("url")
            .build();
    Set<ConstraintViolation<Post>> violations = validator.validate(post);
    assertThat(violations).isEmpty();
  }

  @Test
  @DisplayName("Test when tags are empty and validation succeeds")
  public void testTagsEmpty() {
    var post =
        Post.builder()
            .title("title")
            .description("description")
            .content("content")
            .visibility(PostVisibility.PUBLIC)
            .githubUrl("url")
            .tags(List.of())
            .build();
    Set<ConstraintViolation<Post>> violations = validator.validate(post);
    assertThat(violations).isEmpty();
  }
}
