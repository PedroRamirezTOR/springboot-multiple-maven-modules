package rc.persistence2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rc.domain2.Coche;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Long>{

}
