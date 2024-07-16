package esp.dgi.tola.services;

import esp.dgi.tola.dtos.TagDto;
import esp.dgi.tola.entities.Tag;
import esp.dgi.tola.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public TagDto addTag(TagDto tagDto) {
        Tag tag = new Tag();
        tag.setLibelle(tagDto.getLibelle());
        Tag savedTag = tagRepository.save(tag);
        return convertToDto(savedTag);
    }

    public List<TagDto> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public TagDto getTagById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        return convertToDto(tag);
    }

    public TagDto updateTag(Long id, TagDto tagDto) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tag.setLibelle(tagDto.getLibelle());
        Tag updatedTag = tagRepository.save(tag);
        return convertToDto(updatedTag);
    }

    public void deleteTag(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tagRepository.delete(tag);
    }

    private TagDto convertToDto(Tag tag) {
        TagDto tagDto = new TagDto();
        tagDto.setId(tag.getId());
        tagDto.setLibelle(tag.getLibelle());
        return tagDto;
    }
}
