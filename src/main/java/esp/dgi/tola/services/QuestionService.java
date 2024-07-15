package esp.dgi.tola.services;

import esp.dgi.tola.dtos.QuestionDto;
import esp.dgi.tola.entities.Question;
import esp.dgi.tola.entities.User;
import esp.dgi.tola.repositories.QuestionRepository;
import esp.dgi.tola.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public Question addQuestion(QuestionDto questionDto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Question question = new Question();
        question.setTitle(questionDto.getTitle());
        question.setQuestion(questionDto.getQuestion());
        question.setUser(user);
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        questionRepository.findAll().forEach(questions::add);
        return questions;
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public Question updateQuestion(Long id, QuestionDto questionDto) {
        Question question = getQuestionById(id);
        question.setTitle(questionDto.getTitle());
        question.setQuestion(questionDto.getQuestion());
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long id) {
        Question question = getQuestionById(id);
        questionRepository.delete(question);
    }
}
