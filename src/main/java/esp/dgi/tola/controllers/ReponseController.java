package esp.dgi.tola.controllers;

import esp.dgi.tola.dtos.ReponseDto;
import esp.dgi.tola.services.ReponseService;
import esp.dgi.tola.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions/{questionId}/reponses")
public class ReponseController {
    private final ReponseService reponseService;
    private final JwtService jwtService;

    public ReponseController(ReponseService reponseService, JwtService jwtService) {
        this.reponseService = reponseService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<ReponseDto> addReponse(@PathVariable Long questionId, @RequestBody ReponseDto reponseDto, @RequestHeader("Authorization") String token) {
        String username = jwtService.extractUsername(token.substring(7));
        
        // Log les données reçues
        System.out.println("Adding response for questionId: " + questionId + " by user: " + username);
        System.out.println("Response content: " + reponseDto.getContenu());
        
        ReponseDto savedReponse = reponseService.addReponse(questionId, reponseDto, username);
        return ResponseEntity.ok(savedReponse);
    }

    @GetMapping
    public ResponseEntity<List<ReponseDto>> getReponsesByQuestionId(@PathVariable Long questionId) {
        List<ReponseDto> reponses = reponseService.getReponsesByQuestionId(questionId);
        return ResponseEntity.ok(reponses);
    }

    @PutMapping("/{reponseId}")
    public ResponseEntity<ReponseDto> updateReponse(@PathVariable Long questionId, @PathVariable Long reponseId, @RequestBody ReponseDto reponseDto, @RequestHeader("Authorization") String token) {
        String username = jwtService.extractUsername(token.substring(7));
        
        // Log les données reçues
        System.out.println("Updating response with id: " + reponseId + " by user: " + username);
        System.out.println("New response content: " + reponseDto.getContenu());

        ReponseDto updatedReponse = reponseService.updateReponse(reponseId, reponseDto, username);
        return ResponseEntity.ok(updatedReponse);
    }

    @DeleteMapping("/{reponseId}")
    public ResponseEntity<Void> deleteReponse(@PathVariable Long questionId, @PathVariable Long reponseId, @RequestHeader("Authorization") String token) {
        String username = jwtService.extractUsername(token.substring(7));
        
        // Log les données reçues
        System.out.println("Deleting response with id: " + reponseId + " by user: " + username);

        reponseService.deleteReponse(reponseId, username);
        return ResponseEntity.noContent().build();
    }
}
