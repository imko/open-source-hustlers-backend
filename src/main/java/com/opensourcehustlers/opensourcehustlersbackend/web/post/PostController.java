package com.opensourcehustlers.opensourcehustlersbackend.web.post;

import com.opensourcehustlers.opensourcehustlersbackend.domain.post.PostRequestDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.post.PostResponseDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.post.PostService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping(path = "/v1/api/posts", produces = "application/json")
@RestController
public class PostController {

  private final PostService postService;

  @GetMapping
  public List<PostResponseDTO> findAll() {
    return postService.findAll();
  }

  @GetMapping("/{id}")
  public PostResponseDTO findById(@PathVariable("id") Long id) {
    return postService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PostResponseDTO create(@Valid @RequestBody PostRequestDTO data) {
    return postService.save(data);
  }

  @PutMapping("/{id}")
  public PostResponseDTO update(
      @PathVariable("id") Long id, @Valid @RequestBody PostRequestDTO data) {
    return postService.save(id, data);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long id) {
    postService.deleteById(id);
  }
}
