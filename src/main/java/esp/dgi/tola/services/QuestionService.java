package esp.dgi.tola.services;

import esp.dgi.tola.dtos.QuestionDto;
import esp.dgi.tola.dtos.QuestionResponseDto;
import esp.dgi.tola.entities.Question;
import esp.dgi.tola.entities.User;
import esp.dgi.tola.repositories.QuestionRepository;
import esp.dgi.tola.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public QuestionResponseDto addQuestion(QuestionDto questionDto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Question question = new Question();
        question.setTitle(questionDto.getTitle());
        question.setQuestion(questionDto.getQuestion());
        question.setUser(user);
        Question savedQuestion = questionRepository.save(question);
        return convertToDto(savedQuestion);
    }

    public List<QuestionResponseDto> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        questionRepository.findAll().forEach(questions::add);
        return questions.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public QuestionResponseDto getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        return convertToDto(question);
    }

    public QuestionResponseDto updateQuestion(Long id, QuestionDto questionDto) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        question.setTitle(questionDto.getTitle());
        question.setQuestion(questionDto.getQuestion());
        return convertToDto(questionRepository.save(question));
    }

    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        questionRepository.delete(question);
    }

    private QuestionResponseDto convertToDto(Question question) {
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setId(question.getId());
        questionResponseDto.setTitle(question.getTitle());
        questionResponseDto.setQuestion(question.getQuestion());
        questionResponseDto.setDateAsked(question.getDateAsked());
        questionResponseDto.setUsername(question.getUser().getUsername());
        return questionResponseDto;
    }
}
