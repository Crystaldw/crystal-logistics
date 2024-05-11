package pl.crystalbud.catalogueservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.crystalbud.catalogueservice.entity.Table;


public interface TableRepository extends CrudRepository<Table, Integer> {

    @Query(name = "Table.findAllByTitleLikeIgnoringCase", nativeQuery = true)
    Iterable<Table>findAllByTitleLikeIgnoreCase(@Param("filter") String filter);
}
