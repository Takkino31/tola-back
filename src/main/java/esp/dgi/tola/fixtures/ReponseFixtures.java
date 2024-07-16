package esp.dgi.tola.fixtures;

import esp.dgi.tola.entities.Question;
import esp.dgi.tola.entities.Reponse;
import esp.dgi.tola.entities.User;
import esp.dgi.tola.repositories.QuestionRepository;
import esp.dgi.tola.repositories.ReponseRepository;
import esp.dgi.tola.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

@Component
@Order(3)
public class ReponseFixtures {

    private final ReponseRepository reponseRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public ReponseFixtures(ReponseRepository reponseRepository, QuestionRepository questionRepository, UserRepository userRepository) {
        this.reponseRepository = reponseRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void loadReponseFixtures() {
        List<User> students = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .filter(user -> "STUDENT".equals(user.getRole()))
                .limit(5)
                .collect(Collectors.toList());

        List<Question> questions = StreamSupport.stream(questionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        for (User student : students) {
            for (Question question : questions) {
                IntStream.range(0, 3).forEach(i -> {
                    Reponse reponse = new Reponse();
                    reponse.setContenu("Sample Response " + (i + 1) + " from " + student.getUsername());
                    reponse.setUser(student);
                    reponse.setQuestion(question);
                    reponseRepository.save(reponse);
                    System.out.println("Response created: " + reponse.getContenu());
                });
            }
        }
    }
}
