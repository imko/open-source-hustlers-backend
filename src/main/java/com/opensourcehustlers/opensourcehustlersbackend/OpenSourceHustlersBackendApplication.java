package com.opensourcehustlers.opensourcehustlersbackend;

import com.opensourcehustlers.opensourcehustlersbackend.domain.post.Post;
import com.opensourcehustlers.opensourcehustlersbackend.domain.post.PostRepository;
import com.opensourcehustlers.opensourcehustlersbackend.domain.post.PostVisibility;
import com.opensourcehustlers.opensourcehustlersbackend.domain.skill.Skill;
import com.opensourcehustlers.opensourcehustlersbackend.domain.skill.SkillRepository;
import com.opensourcehustlers.opensourcehustlersbackend.domain.tag.Tag;
import com.opensourcehustlers.opensourcehustlersbackend.domain.tag.TagRepository;
import com.opensourcehustlers.opensourcehustlersbackend.domain.user.User;
import com.opensourcehustlers.opensourcehustlersbackend.domain.user.UserRepository;
import com.opensourcehustlers.opensourcehustlersbackend.domain.user.UserRole;
import java.time.Instant;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OpenSourceHustlersBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(OpenSourceHustlersBackendApplication.class, args);
  }

  @Bean
  public CommandLineRunner run(
      UserRepository userRepository,
      SkillRepository skillRepository,
      PostRepository postRepository,
      TagRepository tagRepository,
      PasswordEncoder passwordEncoder) {
    return args -> {
      Skill frontend = Skill.builder().name("frontend").color("#332211").build();
      Skill backend = Skill.builder().name("backend").color("#554433").build();
      Skill designer = Skill.builder().name("designer").color("#0f0f0f").build();

      skillRepository.save(frontend);
      skillRepository.save(backend);
      skillRepository.save(designer);

      User admin =
          User.builder()
              .email("admin@admin.com")
              .displayName("admin")
              .enabled(true)
              .userRole(UserRole.ADMIN)
              .skills(List.of(frontend, designer, backend))
              .password(passwordEncoder.encode("password"))
              .lastActiveDate(Instant.now())
              .build();
      User user =
          User.builder()
              .email("user@user.com")
              .displayName("user")
              .enabled(true)
              .userRole(UserRole.USER)
              .skills(List.of(frontend, backend))
              .password(passwordEncoder.encode("password"))
              .lastActiveDate(Instant.now())
              .build();

      userRepository.save(admin);
      userRepository.save(user);

      Tag java = Tag.builder().name("java").color("#efefef").build();
      Tag typescript = Tag.builder().name("typescript").color("#fefefe").build();
      Tag golang = Tag.builder().name("golang").color("#121232").build();

      tagRepository.save(java);
      tagRepository.save(typescript);
      tagRepository.save(golang);

      Post p1 =
          Post.builder()
              .title("title 1")
              .description("description 1")
              .content("content 1")
              .githubUrl("github url 1")
              .visibility(PostVisibility.PUBLIC)
              .tags(List.of(java, typescript))
              .createdBy(admin.getEmail())
              .lastModifiedBy(admin.getEmail())
              .build();
      Post p2 =
          Post.builder()
              .title("title 2")
              .description("description 2")
              .content("content 2")
              .githubUrl("github url 2")
              .visibility(PostVisibility.PRIVATE)
              .tags(List.of(golang))
              .createdBy(user.getEmail())
              .lastModifiedBy(user.getEmail())
              .build();

      postRepository.save(p1);
      postRepository.save(p2);
    };
  }
}
