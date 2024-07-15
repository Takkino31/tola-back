package esp.dgi.tola.fixtures;

import esp.dgi.tola.entities.User;
import esp.dgi.tola.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.stream.IntStream;

@Component
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
                    .setFirstName("AdminFirstName" + i)
                    .setLastName("AdminLastName" + i)
                    .setStudentNumber("admin" + i)
                    .setDepartment("Department" + i)
                    .setSpecialty("Specialty" + i)
                    .setEmail("admin" + i + "@example.com")
                    .setPassword(passwordEncoder.encode("1234"))
                    .setRole("ADMIN");
            userRepository.save(admin);
        });

        // Create 100 student users
        IntStream.rangeClosed(1, 100).forEach(i -> {
            User student = new User()
                    .setUsername("student" + i)
                    .setFirstName("StudentFirstName" + i)
                    .setLastName("StudentLastName" + i)
                    .setStudentNumber("student" + i)
                    .setDepartment("Department" + i)
                    .setSpecialty("Specialty" + i)
                    .setEmail("student" + i + "@example.com")
                    .setPassword(passwordEncoder.encode("1234"))
                    .setRole("STUDENT");
            userRepository.save(student);
        });
    }
}
