package pl.crystalbud.crystallogistics.repository;

import org.springframework.data.repository.CrudRepository;
import pl.crystalbud.crystallogistics.entity.CrystalUser;

import java.util.Optional;

public interface CrystalUserRepository extends CrudRepository<CrystalUser, Integer> {

    Optional<CrystalUser> findByUsername(String username);

}
