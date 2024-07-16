package esp.dgi.tola.services;

import esp.dgi.tola.dtos.ReponseDto;
import esp.dgi.tola.entities.Question;
import esp.dgi.tola.entities.Reponse;
import esp.dgi.tola.entities.User;
import esp.dgi.tola.repositories.QuestionRepository;
import esp.dgi.tola.repositories.ReponseRepository;
import esp.dgi.tola.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReponseService {
    private final ReponseRepository reponseRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public ReponseService(ReponseRepository reponseRepository, QuestionRepository questionRepository, UserRepository userRepository) {
        this.reponseRepository = reponseRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public ReponseDto addReponse(Long questionId, ReponseDto reponseDto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        Reponse reponse = new Reponse();
        reponse.setContenu(reponseDto.getContenu());
        reponse.setUser(user);
        reponse.setQuestion(question);

        Reponse savedReponse = reponseRepository.save(reponse);
        return convertToDto(savedReponse);
    }

    public List<ReponseDto> getReponsesByQuestionId(Long questionId) {
        List<Reponse> reponses = reponseRepository.findByQuestionId(questionId);
        return reponses.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ReponseDto updateReponse(Long reponseId, ReponseDto reponseDto, String username) {
        Reponse reponse = reponseRepository.findById(reponseId)
                .orElseThrow(() -> new RuntimeException("Reponse not found"));

        if (!reponse.getUser().getUsername().equals(username)) {
            throw new RuntimeException("User not authorized to update this response");
        }

        reponse.setContenu(reponseDto.getContenu());
        Reponse updatedReponse = reponseRepository.save(reponse);

        return convertToDto(updatedReponse);
    }

    public void deleteReponse(Long reponseId, String username) {
        Reponse reponse = reponseRepository.findById(reponseId)
                .orElseThrow(() -> new RuntimeException("Reponse not found"));

        if (!reponse.getUser().getUsername().equals(username)) {
            throw new RuntimeException("User not authorized to delete this response");
        }

        reponseRepository.delete(reponse);
    }

    private ReponseDto convertToDto(Reponse reponse) {
        ReponseDto reponseDto = new ReponseDto();
        reponseDto.setId(reponse.getId());
        reponseDto.setContenu(reponse.getContenu());
        reponseDto.setDateCreation(reponse.getDateCreation());
        reponseDto.setDateModification(reponse.getDateModification());
        reponseDto.setUsername(reponse.getUser().getUsername());
        return reponseDto;
    }
}
