package esp.dgi.tola.fixtures;

import esp.dgi.tola.entities.Tag;
import esp.dgi.tola.repositories.TagRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TagFixtures {

    private final TagRepository tagRepository;

    public TagFixtures(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @PostConstruct
    public void loadTagFixtures() {
        List<String> tagLabels = Arrays.asList("PHP", "Java", "Programmation", "Medecine");
        tagLabels.forEach(label -> {
            Tag tag = new Tag();
            tag.setLibelle(label);
            tagRepository.save(tag);
            System.out.println("Tag created: " + tag.getLibelle());
        });
    }
}
