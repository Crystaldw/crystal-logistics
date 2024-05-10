package pl.crystalbud.catalogueservice.repository;

import org.springframework.data.repository.CrudRepository;
import pl.crystalbud.catalogueservice.entity.Table;


public interface TableRepository extends CrudRepository<Table, Integer> {

    Iterable<Table>findAllByTitleLikeIgnoreCase(String filter);
}
