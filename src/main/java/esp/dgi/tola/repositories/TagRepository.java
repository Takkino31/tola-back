package esp.dgi.tola.repositories;

import esp.dgi.tola.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
