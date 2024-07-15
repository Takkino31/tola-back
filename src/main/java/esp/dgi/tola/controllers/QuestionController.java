package esp.dgi.tola.controllers;

import esp.dgi.tola.dtos.QuestionDto;
import esp.dgi.tola.dtos.QuestionResponseDto;
import esp.dgi.tola.services.QuestionService;
import esp.dgi.tola.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final JwtService jwtService;

    public QuestionController(QuestionService questionService, JwtService jwtService) {
        this.questionService = questionService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<QuestionResponseDto> addQuestion(@RequestBody QuestionDto questionDto, @RequestHeader("Authorization") String token) {
        String username = jwtService.extractUsername(token.substring(7));
        QuestionResponseDto question = questionService.addQuestion(questionDto, username);
        return ResponseEntity.ok(question);
    }

    @GetMapping
    public ResponseEntity<List<QuestionResponseDto>> getAllQuestions() {
        List<QuestionResponseDto> questions = questionService.getAllQuestions();
        System.out.println("Fetched questions: " + questions); // Log statement
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> getQuestionById(@PathVariable Long id) {
        QuestionResponseDto question = questionService.getQuestionById(id);
        System.out.println("Fetched question: " + question); // Log statement
        return ResponseEntity.ok(question);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> updateQuestion(@PathVariable Long id, @RequestBody QuestionDto questionDto) {
        QuestionResponseDto updatedQuestion = questionService.updateQuestion(id, questionDto);
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
