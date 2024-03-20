package com.opensourcehustlers.opensourcehustlersbackend.web.post;

import com.opensourcehustlers.opensourcehustlersbackend.domain.post.PostOptionResponseDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.post.PostRequestDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.post.PostResponseDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.post.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Posts")
@AllArgsConstructor
@RequestMapping(path = "/v1/api/posts", produces = "application/json")
@RestController
public class PostController {

  private final PostService postService;

  @GetMapping
  public ResponseEntity<List<PostResponseDTO>> findAll(
      @RequestParam(name = "page", required = false, defaultValue = "0") Integer pageNumber,
      @RequestParam(name = "size", required = false) Integer pageSize,
      @RequestParam(name = "sort", required = false, defaultValue = "id") String sortBy,
      @RequestParam(name = "order", required = false, defaultValue = "asc") String sortOrder) {
    return ResponseEntity.ok(postService.findAll(pageNumber, pageSize, sortBy, sortOrder));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostResponseDTO> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(postService.findById(id));
  }

  @GetMapping("/options")
  public ResponseEntity<PostOptionResponseDTO> findAllPostOptions() {
    return ResponseEntity.ok(postService.findAllPostOptions());
  }

  @Operation(
      summary = "Create a new post",
      description = "Create a new post",
      security = {@SecurityRequirement(name = "bearerToken")})
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<PostResponseDTO> create(@Valid @RequestBody PostRequestDTO data) {
    return ResponseEntity.ok(postService.save(data));
  }

  @Operation(
      summary = "Update a post",
      description = "Update an existing post or create a new post",
      security = {@SecurityRequirement(name = "bearerToken")})
  @PutMapping("/{id}")
  public ResponseEntity<PostResponseDTO> update(
      @PathVariable("id") Long id, @Valid @RequestBody PostRequestDTO data) {
    return ResponseEntity.ok(postService.save(id, data));
  }

  @Operation(
      summary = "Delete a post",
      description = "Delete a post",
      security = {@SecurityRequirement(name = "bearerToken")})
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long id) {
    postService.deleteById(id);
  }
}
