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

      Tag java = Tag.builder().name("Java").color("yellow-900").build();
      Tag typescript = Tag.builder().name("TypeScript").color("blue-600").build();
      Tag golang = Tag.builder().name("Golang").color("sky-400").build();
      Tag cpp = Tag.builder().name("CPP").color("blue-900").build();
      Tag c = Tag.builder().name("C").color("sky-700").build();
      Tag python = Tag.builder().name("Python").color("yellow-300").build();
      Tag javascript = Tag.builder().name("JavaScript").color("amber-500").build();

      tagRepository.save(java);
      tagRepository.save(typescript);
      tagRepository.save(golang);
      tagRepository.save(cpp);
      tagRepository.save(c);
      tagRepository.save(python);
      tagRepository.save(javascript);

      Post p1 =
          Post.builder()
              .title("At ducimus quidem est repellat.")
              .description("Ad provident nisi laboriosam cum delectus totam! Aspernatur, eligendi.")
              .content("Mollitia repellendus sint minima hic quisquam ipsa reprehenderit tempora accusamus atque, dolor repudiandae. Mollitia a impedit asperiores exercitationem harum natus optio accusantium ullam id. Officia quod esse dolorum ipsam eligendi, temporibus tempore. Iste asperiores ratione saepe?")
              .githubUrl("github url 1")
              .visibility(PostVisibility.PUBLIC)
              .tags(List.of(java, typescript))
              .createdBy(admin.getEmail())
              .lastModifiedBy(admin.getEmail())
              .build();
      Post p2 =
          Post.builder()
              .title("Dolor sit amet consectetur adipisicing elit. Ducimus quam vero illum.")
              .description("Atque, tempore dolor architecto ab a illo, ad provident nisi laboriosam cum delectus totam! Aspernatur, eligendi.")
              .content("Voluptas molestiae ex magni eos, atque, tempore dolor architecto ab a illo, ad provident nisi laboriosam cum delectus totam! Aspernatur, eligendi. Lorem, ipsum dolor sit amet consectetur adipisicing elit. Laudantium voluptas molestiae ex magni eos, atque, tempore dolor architecto ab a illo, ad provident nisi laboriosam cum delectus totam! Aspernatur, eligendi.")
              .githubUrl("github url 2")
              .visibility(PostVisibility.PRIVATE)
              .tags(List.of(golang, javascript))
              .createdBy(user.getEmail())
              .lastModifiedBy(user.getEmail())
              .build();
      Post p3 =
          Post.builder()
              .title("Amet consectetur, adipisicing elit. Deleniti, dolores?")
              .description("Quasi doloribus modi consequuntur est nemo ea aspernatur quia quas maxime animi.")
              .content("Nesciunt, at commodi quibusdam quidem pariatur ex voluptatibus fuga, ipsum itaque ipsa autem suscipit. Beatae voluptatum doloribus veritatis architecto quam, eveniet vel.")
              .githubUrl("github url 3")
              .visibility(PostVisibility.PUBLIC)
              .tags(List.of(golang, java, cpp))
              .createdBy(admin.getEmail())
              .lastModifiedBy(admin.getEmail())
              .build();
      Post p4 =
          Post.builder()
              .title("Adipisicing elit.")
              .description("Consectetur adipisicing elit. Dicta, ducimus dignissimos! Unde, provident sunt.")
              .content("Tema consectetur adipisicing elit. At optio blanditiis ad quos sed, rerum corporis suscipit ratione reprehenderit aperiam harum error? Obcaecati neque dignissimos culpa cumque ipsa ab dolore!")
              .githubUrl("github url 4")
              .visibility(PostVisibility.PRIVATE)
              .tags(List.of(golang, java))
              .createdBy(admin.getEmail())
              .lastModifiedBy(admin.getEmail())
              .build();
      Post p5 =
          Post.builder()
              .title("Construct ipsum dolor sit amet consectetur adipisicing.")
              .description("Harum iure quibusdam beatae nemo mollitia eveniet, illum modi facilis fuga cupiditate.")
              .content("Elit Cumque, dignissimos ducimus.")
              .githubUrl("github url 5")
              .visibility(PostVisibility.PUBLIC)
              .tags(List.of(java))
              .createdBy(user.getEmail())
              .lastModifiedBy(user.getEmail())
              .build();
      Post p6 =
          Post.builder()
              .title("Tenetur illo in molestias enim aperiam tempora nihil cupiditate, voluptatibus, ipsa quam impedit, fugit voluptatem!")
              .description("Nesciunt quia neque voluptas? Velit eius voluptas libero architecto, omnis sequi eligendi?")
              .content("Qui maiores ducimus iste doloremque assumenda in, modi exercitationem repudiandae earum debitis, pariatur aspernatur quae nisi asperiores sequi veniam perferendis totam consequuntur nesciunt quia neque voluptas? Velit eius voluptas libero architecto, omnis sequi eligendi?")
              .githubUrl("github url 6")
              .visibility(PostVisibility.PRIVATE)
              .tags(List.of(cpp, c))
              .createdBy(user.getEmail())
              .lastModifiedBy(user.getEmail())
              .build();
      Post p7 =
          Post.builder()
              .title("Doloremque officiis illo dolore at esse. Sapiente?")
              .description("Itaque tenetur natus molestiae, atque similique blanditiis aspernatur sapiente, dignissimos quaerat? Delectus, placeat vel!")
              .content("Provident minus a reprehenderit totam asperiores quibusdam impedit, unde dolorem illo, delectus porro soluta? Quidem recusandae itaque tenetur natus molestiae, atque similique blanditiis aspernatur sapiente, dignissimos quaerat? Delectus, placeat vel!")
              .githubUrl("github url 7")
              .visibility(PostVisibility.PRIVATE)
              .tags(List.of(python, java, typescript))
              .createdBy(user.getEmail())
              .lastModifiedBy(user.getEmail())
              .build();
      Post p8 =
          Post.builder()
              .title("Ab alias eum modi laborum minus aut. Corrupti, ratione repellendus.")
              .description("Vitae soluta beatae non cupiditate assumenda libero quae odio. Nihil exercitationem quam, quas saepe ipsam esse nam id officia voluptatem fuga, magnam in vero.")
              .content("Non cupiditate assumenda libero quae odio. Nihil exercitationem quam, quas saepe ipsam esse nam id officia voluptatem fuga, magnam in vero.")
              .githubUrl("github url 8")
              .visibility(PostVisibility.PUBLIC)
              .tags(List.of(javascript, python))
              .createdBy(user.getEmail())
              .lastModifiedBy(user.getEmail())
              .build();
      Post p9 =
          Post.builder()
              .title("Aperiam cum, quae nam ea modi unde, nostrum doloremque debitis culpa, reiciendis odit officiis laboriosam.")
              .description("Minus quia reiciendis! Fugit, eligendi ut? Quos, dolores!")
              .content("Error ipsam numquam id nobis repudiandae atque perferendis eligendi, rerum inventore eaque possimus iure, minus quia reiciendis! Fugit, eligendi ut? Quos, dolores!")
              .githubUrl("github url 9")
              .visibility(PostVisibility.PUBLIC)
              .tags(List.of(golang, typescript))
              .createdBy(admin.getEmail())
              .lastModifiedBy(admin.getEmail())
              .build();
      Post p10 =
          Post.builder()
              .title("Fugiat in temporibus autem consectetur. Maiores, fuga. Accusantium aliquam blanditiis reprehenderit.")
              .description("Aliquid illum rem fugiat amet optio culpa sint distinctio! Ipsam maiores autem at facere!")
              .content("Debitis maiores architecto eos eius id, sunt voluptates dolor incidunt cumque consectetur impedit commodi aliquid illum rem fugiat amet optio culpa sint distinctio! Ipsam maiores autem at facere!")
              .githubUrl("github url 10")
              .visibility(PostVisibility.PUBLIC)
              .tags(List.of(typescript, java))
              .createdBy(user.getEmail())
              .lastModifiedBy(user.getEmail())
              .build();

      postRepository.save(p1);
      postRepository.save(p2);
      postRepository.save(p3);
      postRepository.save(p4);
      postRepository.save(p5);
      postRepository.save(p6);
      postRepository.save(p7);
      postRepository.save(p8);
      postRepository.save(p9);
      postRepository.save(p10);
    };
  }
}
