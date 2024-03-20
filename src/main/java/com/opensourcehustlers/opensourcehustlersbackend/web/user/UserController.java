package com.opensourcehustlers.opensourcehustlersbackend.web.user;

import com.opensourcehustlers.opensourcehustlersbackend.domain.user.UserEditRequestDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.user.UserResponseDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.user.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users")
@SecurityRequirement(name = "bearerToken")
@AllArgsConstructor
@RequestMapping(path = "/v1/api/users", produces = "application/json")
@RestController
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<UserResponseDTO>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDTO> put(
      @PathVariable("id") Long id, @Valid @RequestBody UserEditRequestDTO data) {
    return ResponseEntity.ok(userService.save(id, data));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    userService.deleteById(id);
  }
}
