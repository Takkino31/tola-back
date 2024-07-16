package esp.dgi.tola.fixtures;

import esp.dgi.tola.entities.Question;
import esp.dgi.tola.entities.User;
import esp.dgi.tola.repositories.QuestionRepository;
import esp.dgi.tola.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

@Component
@Order(2)
public class QuestionFixtures {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionFixtures(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void loadQuestionFixtures() {
        System.out.println("Loading Question Fixtures...");
        List<User> students = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .filter(user -> "STUDENT".equals(user.getRole()))
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("Creating questions for students: " + students.size()); // Log statement

        for (User student : students) {
            IntStream.range(0, 5).forEach(i -> {
                Question question = new Question();
                question.setTitle("Sample Question " + (i + 1) + " from " + student.getUsername());
                question.setQuestion("This is the content of question " + (i + 1) + " from user " + student.getUsername());
                question.setUser(student);
                questionRepository.save(question);
                System.out.println("Question created: " + question.getTitle());
            });
        }

        long totalQuestions = questionRepository.count();
        System.out.println("Total questions in database: " + totalQuestions);
    }
}
