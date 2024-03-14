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
              .content("{\"type\":\"doc\",\"content\":[{\"type\":\"heading\",\"attrs\":{\"level\":1},\"content\":[{\"type\":\"text\",\"text\":\"Heading1\"}]},{\"type\":\"heading\",\"attrs\":{\"level\":2},\"content\":[{\"type\":\"text\",\"text\":\"heading2\"}]},{\"type\":\"heading\",\"attrs\":{\"level\":3},\"content\":[{\"type\":\"text\",\"text\":\"heading3\"}]},{\"type\":\"heading\",\"attrs\":{\"level\":4},\"content\":[{\"type\":\"text\",\"text\":\"heading4\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"}],\"text\":\"This is a bold text\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"},{\"type\":\"italic\"},{\"type\":\"strike\"}],\"text\":\"This is bold italic and strikethrough. \"}]},{\"type\":\"codeBlock\",\"attrs\":{\"language\":null},\"content\":[{\"type\":\"text\",\"text\":\"Code block\"}]}]}")
              .githubUrl("www.github.com/admin/at1")
              .visibility(PostVisibility.PUBLIC)
              .tags(List.of(java, typescript))
              .createdBy(admin.getEmail())
              .lastModifiedBy(admin.getEmail())
              .build();
      Post p2 =
          Post.builder()
              .title("Dolor sit amet consectetur adipisicing elit. Ducimus quam vero illum.")
              .description("Atque, tempore dolor architecto ab a illo, ad provident nisi laboriosam cum delectus totam! Aspernatur, eligendi.")
              .content("{\"type\":\"doc\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Here is a list of items:\"}]},{\"type\":\"bulletList\",\"content\":[{\"type\":\"listItem\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"We need to do this one first.\"}]}]},{\"type\":\"listItem\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Test 2\"}]}]}]},{\"type\":\"blockquote\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"This is my quote!\"}]}]}]}")
              .githubUrl("www.github.com/user/do1")
              .visibility(PostVisibility.PRIVATE)
              .tags(List.of(golang, javascript))
              .createdBy(user.getEmail())
              .lastModifiedBy(user.getEmail())
              .build();
      Post p3 =
          Post.builder()
              .title("Amet consectetur, adipisicing elit. Deleniti, dolores?")
              .description("Quasi doloribus modi consequuntur est nemo ea aspernatur quia quas maxime animi.")
              .content("{\"type\":\"doc\",\"content\":[{\"type\":\"orderedList\",\"attrs\":{\"start\":1},\"content\":[{\"type\":\"listItem\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"First item\"}]}]},{\"type\":\"listItem\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Second item\"}]}]}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"marks\":[{\"type\":\"code\"}],\"text\":\"This is a line of code\"},{\"type\":\"text\",\"text\":\" \"}]},{\"type\":\"horizontalRule\"},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Hello a new line!\"}]}]}")
              .githubUrl("www.github.com/admin/am")
              .visibility(PostVisibility.PUBLIC)
              .tags(List.of(golang, java, cpp))
              .createdBy(admin.getEmail())
              .lastModifiedBy(admin.getEmail())
              .build();
      Post p4 =
          Post.builder()
              .title("Adipisicing elit.")
              .description("Consectetur adipisicing elit. Dicta, ducimus dignissimos! Unde, provident sunt.")
              .content("{\"type\":\"doc\",\"content\":[{\"type\":\"heading\",\"attrs\":{\"level\":2},\"content\":[{\"type\":\"text\",\"text\":\"Hello!\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"}],\"text\":\"this is a basic \"},{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"},{\"type\":\"italic\"}],\"text\":\"basic\"},{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"}],\"text\":\" example of tiptap. Sure, there are all kind of basic text styles you’d probably expect from a text editor. But wait until you see the lists:\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"That’s a bullet list with one …\"}]},{\"type\":\"bulletList\",\"content\":[{\"type\":\"listItem\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"… or two list items.\"}]}]}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Isn’t that great? And all of that is editable. But wait, there’s more. Let’s try a code block:\"}]},{\"type\":\"codeBlock\",\"attrs\":{\"language\":\"css\"},\"content\":[{\"type\":\"text\",\"text\":\"body {\\n  display: none;\\n}\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"I know, I know, this is impressive. It’s only the tip of the iceberg though. Give it a try and click a little bit around. Don’t forget to check the other examples too.\"}]},{\"type\":\"blockquote\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Wow, that’s amazing. Good work, boy! 👏 \"},{\"type\":\"hardBreak\"},{\"type\":\"text\",\"text\":\"— Mom\"}]}]}]}")
              .githubUrl("www.github.com/admin/ad")
              .visibility(PostVisibility.PRIVATE)
              .tags(List.of(golang, java))
              .createdBy(admin.getEmail())
              .lastModifiedBy(admin.getEmail())
              .build();
      Post p5 =
          Post.builder()
              .title("Construct ipsum dolor sit amet consectetur adipisicing.")
              .description("Harum iure quibusdam beatae nemo mollitia eveniet, illum modi facilis fuga cupiditate.")
              .content("{\"type\":\"doc\",\"content\":[{\"type\":\"heading\",\"attrs\":{\"level\":2},\"content\":[{\"type\":\"text\",\"text\":\"World!\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"}],\"text\":\"this is a basic \"},{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"},{\"type\":\"italic\"}],\"text\":\"basic\"},{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"}],\"text\":\" example of tiptap. Sure, there are all kind of basic text styles you’d probably expect from a text editor. But wait until you see the lists:\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"That’s a bullet list with one …\"}]},{\"type\":\"bulletList\",\"content\":[{\"type\":\"listItem\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"… or two list items.\"}]}]}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Isn’t that great? And all of that is editable. But wait, there’s more. Let’s try a code block:\"}]},{\"type\":\"codeBlock\",\"attrs\":{\"language\":\"css\"},\"content\":[{\"type\":\"text\",\"text\":\"body {\\n  display: none;\\n}\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"I know, I know, this is impressive. It’s only the tip of the iceberg though. Give it a try and click a little bit around. Don’t forget to check the other examples too.\"}]},{\"type\":\"blockquote\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Wow, that’s amazing. Good work, boy! 👏 \"},{\"type\":\"hardBreak\"},{\"type\":\"text\",\"text\":\"— Mom\"}]}]}]}")
              .githubUrl("www.github.com/user/co")
              .visibility(PostVisibility.PUBLIC)
              .tags(List.of(java))
              .createdBy(user.getEmail())
              .lastModifiedBy(user.getEmail())
              .build();
      Post p6 =
          Post.builder()
              .title("Tenetur illo in molestias enim aperiam tempora nihil cupiditate, voluptatibus, ipsa quam impedit, fugit voluptatem!")
              .description("Nesciunt quia neque voluptas? Velit eius voluptas libero architecto, omnis sequi eligendi?")
              .content("{\"type\":\"doc\",\"content\":[{\"type\":\"heading\",\"attrs\":{\"level\":2},\"content\":[{\"type\":\"text\",\"text\":\"Hello world!\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"}],\"text\":\"this is a basic \"},{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"},{\"type\":\"italic\"}],\"text\":\"basic\"},{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"}],\"text\":\" example of tiptap. Sure, there are all kind of basic text styles you’d probably expect from a text editor. But wait until you see the lists:\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"That’s a bullet list with one …\"}]},{\"type\":\"bulletList\",\"content\":[{\"type\":\"listItem\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"… or two list items.\"}]}]}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Isn’t that great? And all of that is editable. But wait, there’s more. Let’s try a code block:\"}]},{\"type\":\"codeBlock\",\"attrs\":{\"language\":\"css\"},\"content\":[{\"type\":\"text\",\"text\":\"body {\\n  display: none;\\n}\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"I know, I know, this is impressive. It’s only the tip of the iceberg though. Give it a try and click a little bit around. Don’t forget to check the other examples too.\"}]},{\"type\":\"blockquote\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Wow, that’s amazing. Good work, boy! 👏 \"},{\"type\":\"hardBreak\"},{\"type\":\"text\",\"text\":\"— Mom\"}]}]}]}")
              .githubUrl("www.github.com/user/te")
              .visibility(PostVisibility.PRIVATE)
              .tags(List.of(cpp, c))
              .createdBy(user.getEmail())
              .lastModifiedBy(user.getEmail())
              .build();
      Post p7 =
          Post.builder()
              .title("Doloremque officiis illo dolore at esse. Sapiente?")
              .description("Itaque tenetur natus molestiae, atque similique blanditiis aspernatur sapiente, dignissimos quaerat? Delectus, placeat vel!")
              .content("{\"type\":\"doc\",\"content\":[{\"type\":\"heading\",\"attrs\":{\"level\":2},\"content\":[{\"type\":\"text\",\"text\":\"This is my last posting\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"}],\"text\":\"this is a basic \"},{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"},{\"type\":\"italic\"}],\"text\":\"basic\"},{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"}],\"text\":\" example of tiptap. Sure, there are all kind of basic text styles you’d probably expect from a text editor. But wait until you see the lists:\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"That’s a bullet list with one …\"}]},{\"type\":\"bulletList\",\"content\":[{\"type\":\"listItem\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"… or two list items.\"}]}]}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Isn’t that great? And all of that is editable. But wait, there’s more. Let’s try a code block:\"}]},{\"type\":\"codeBlock\",\"attrs\":{\"language\":\"css\"},\"content\":[{\"type\":\"text\",\"text\":\"body {\\n  display: none;\\n}\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"I know, I know, this is impressive. It’s only the tip of the iceberg though. Give it a try and click a little bit around. Don’t forget to check the other examples too.\"}]},{\"type\":\"blockquote\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Wow, that’s amazing. Good work, boy! 👏 \"},{\"type\":\"hardBreak\"},{\"type\":\"text\",\"text\":\"— Mom\"}]}]}]}")
              .githubUrl("www.github.com/user/do")
              .visibility(PostVisibility.PRIVATE)
              .tags(List.of(python, java, typescript))
              .createdBy(user.getEmail())
              .lastModifiedBy(user.getEmail())
              .build();
      Post p8 =
          Post.builder()
              .title("Ab alias eum modi laborum minus aut. Corrupti, ratione repellendus.")
              .description("Vitae soluta beatae non cupiditate assumenda libero quae odio. Nihil exercitationem quam, quas saepe ipsam esse nam id officia voluptatem fuga, magnam in vero.")
              .content("{\"type\":\"doc\",\"content\":[{\"type\":\"heading\",\"attrs\":{\"level\":2},\"content\":[{\"type\":\"text\",\"text\":\"Will this be the best project?\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"}],\"text\":\"this is a basic \"},{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"},{\"type\":\"italic\"}],\"text\":\"basic\"},{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"}],\"text\":\" example of tiptap. Sure, there are all kind of basic text styles you’d probably expect from a text editor. But wait until you see the lists:\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"That’s a bullet list with one …\"}]},{\"type\":\"bulletList\",\"content\":[{\"type\":\"listItem\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"… or two list items.\"}]}]}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Isn’t that great? And all of that is editable. But wait, there’s more. Let’s try a code block:\"}]},{\"type\":\"codeBlock\",\"attrs\":{\"language\":\"css\"},\"content\":[{\"type\":\"text\",\"text\":\"body {\\n  display: none;\\n}\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"I know, I know, this is impressive. It’s only the tip of the iceberg though. Give it a try and click a little bit around. Don’t forget to check the other examples too.\"}]},{\"type\":\"blockquote\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Wow, that’s amazing. Good work, boy! 👏 \"},{\"type\":\"hardBreak\"},{\"type\":\"text\",\"text\":\"— Mom\"}]}]}]}")
              .githubUrl("www.github.com/user/ab")
              .visibility(PostVisibility.PUBLIC)
              .tags(List.of(javascript, python))
              .createdBy(user.getEmail())
              .lastModifiedBy(user.getEmail())
              .build();
      Post p9 =
          Post.builder()
              .title("Aperiam cum, quae nam ea modi unde, nostrum doloremque debitis culpa, reiciendis odit officiis laboriosam.")
              .description("Minus quia reiciendis! Fugit, eligendi ut? Quos, dolores!")
              .content("{\"type\":\"doc\",\"content\":[{\"type\":\"heading\",\"attrs\":{\"level\":2},\"content\":[{\"type\":\"text\",\"text\":\"Bye everyone!\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"}],\"text\":\"this is a basic \"},{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"},{\"type\":\"italic\"}],\"text\":\"basic\"},{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"}],\"text\":\" example of tiptap. Sure, there are all kind of basic text styles you’d probably expect from a text editor. But wait until you see the lists:\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"That’s a bullet list with one …\"}]},{\"type\":\"bulletList\",\"content\":[{\"type\":\"listItem\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"… or two list items.\"}]}]}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Isn’t that great? And all of that is editable. But wait, there’s more. Let’s try a code block:\"}]},{\"type\":\"codeBlock\",\"attrs\":{\"language\":\"css\"},\"content\":[{\"type\":\"text\",\"text\":\"body {\\n  display: none;\\n}\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"I know, I know, this is impressive. It’s only the tip of the iceberg though. Give it a try and click a little bit around. Don’t forget to check the other examples too.\"}]},{\"type\":\"blockquote\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Wow, that’s amazing. Good work, boy! 👏 \"},{\"type\":\"hardBreak\"},{\"type\":\"text\",\"text\":\"— Mom\"}]}]}]}")
              .githubUrl("www.github.com/admin/ap")
              .visibility(PostVisibility.PUBLIC)
              .tags(List.of(golang, typescript))
              .createdBy(admin.getEmail())
              .lastModifiedBy(admin.getEmail())
              .build();
      Post p10 =
          Post.builder()
              .title("Fugiat in temporibus autem consectetur. Maiores, fuga. Accusantium aliquam blanditiis reprehenderit.")
              .description("Aliquid illum rem fugiat amet optio culpa sint distinctio! Ipsam maiores autem at facere!")
              .content("{\"type\":\"doc\",\"content\":[{\"type\":\"heading\",\"attrs\":{\"level\":2},\"content\":[{\"type\":\"text\",\"text\":\"Finally my last post...\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"}],\"text\":\"this is a basic \"},{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"},{\"type\":\"italic\"}],\"text\":\"basic\"},{\"type\":\"text\",\"marks\":[{\"type\":\"bold\"}],\"text\":\" example of tiptap. Sure, there are all kind of basic text styles you’d probably expect from a text editor. But wait until you see the lists:\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"That’s a bullet list with one …\"}]},{\"type\":\"bulletList\",\"content\":[{\"type\":\"listItem\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"… or two list items.\"}]}]}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Isn’t that great? And all of that is editable. But wait, there’s more. Let’s try a code block:\"}]},{\"type\":\"codeBlock\",\"attrs\":{\"language\":\"css\"},\"content\":[{\"type\":\"text\",\"text\":\"body {\\n  display: none;\\n}\"}]},{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"I know, I know, this is impressive. It’s only the tip of the iceberg though. Give it a try and click a little bit around. Don’t forget to check the other examples too.\"}]},{\"type\":\"blockquote\",\"content\":[{\"type\":\"paragraph\",\"content\":[{\"type\":\"text\",\"text\":\"Wow, that’s amazing. Good work, boy! 👏 \"},{\"type\":\"hardBreak\"},{\"type\":\"text\",\"text\":\"— Mom\"}]}]}]}")
              .githubUrl("www.github.com/user/fu")
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
