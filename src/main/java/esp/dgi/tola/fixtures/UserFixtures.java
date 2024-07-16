package esp.dgi.tola.fixtures;

import esp.dgi.tola.entities.User;
import esp.dgi.tola.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@Order(1)
public class UserFixtures {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserFixtures(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void loadUserFixtures() {
        System.out.println("Loading User Fixtures...");

        // Add 5 admin users
        IntStream.range(0, 5).forEach(i -> {
            User user = new User();
            user.setUsername("admin" + i);
            user.setFirstName("AdminFirstName" + i);
            user.setLastName("AdminLastName" + i);
            user.setStudentNumber("A" + i);
            user.setDepartment("Department" + i);
            user.setSpecialty("Specialty" + i);
            user.setEmail("admin" + i + "@example.com");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setRole("ADMIN");
            userRepository.save(user);
            System.out.println("Admin user created: " + user.getUsername());
        });

        // Add 100 student users
        IntStream.range(0, 100).forEach(i -> {
            User user = new User();
            user.setUsername("student" + i);
            user.setFirstName("StudentFirstName" + i);
            user.setLastName("StudentLastName" + i);
            user.setStudentNumber("S" + i);
            user.setDepartment("Department" + i);
            user.setSpecialty("Specialty" + i);
            user.setEmail("student" + i + "@example.com");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setRole("STUDENT");
            userRepository.save(user);
            System.out.println("Student user created: " + user.getUsername());
        });

        long totalUsers = userRepository.count();
        System.out.println("Total users in database: " + totalUsers);
    }
}
