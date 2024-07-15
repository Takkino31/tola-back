package esp.dgi.tola.fixtures;

import esp.dgi.tola.entities.User;
import esp.dgi.tola.repositories.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.PostConstruct;

import java.util.stream.IntStream;

@Configuration
public class UserFixtures {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserFixtures(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void loadUserFixtures() {
        // Create 5 admin users
        IntStream.rangeClosed(1, 5).forEach(i -> {
            User admin = new User()
                .setUsername("admin" + i)
                .setFirstName("Admin")
                .setLastName(String.valueOf(i))
                .setStudentNumber("A" + i)
                .setDepartment("Administration")
                .setSpecialty("Admin Management")
                .setEmail("admin" + i + "@example.com")
                .setPassword(passwordEncoder.encode("1234"))
                .setRole("ADMIN");  // Définir le rôle
            userRepository.save(admin);
        });

        // Create 100 student users
        IntStream.rangeClosed(1, 100).forEach(i -> {
            User student = new User()
                .setUsername("student" + i)
                .setFirstName("Student")
                .setLastName(String.valueOf(i))
                .setStudentNumber("S" + i)
                .setDepartment("Informatique")
                .setSpecialty("Développement Web")
                .setEmail("student" + i + "@example.com")
                .setPassword(passwordEncoder.encode("1234"))
                .setRole("STUDENT");  // Définir le rôle
            userRepository.save(student);
        });
    }
}
