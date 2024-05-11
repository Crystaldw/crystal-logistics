package pl.crystalbud.catalogueservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.crystalbud.catalogueservice.entity.Table;


public interface TableRepository extends CrudRepository<Table, Integer> {

    @Query(value = "select p from Table p where p.title ilike :filter")
    Iterable<Table>findAllByTitleLikeIgnoreCase(@Param("filter") String filter);
}
