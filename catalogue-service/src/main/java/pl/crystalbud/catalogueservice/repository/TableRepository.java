package pl.crystalbud.catalogueservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.crystalbud.catalogueservice.entity.Table;


public interface TableRepository extends CrudRepository<Table, Integer> {

    @Query(value = "select * from catalogue.t_table where c_title ilike :filter", nativeQuery = true)
    Iterable<Table>findAllByTitleLikeIgnoreCase(@Param("filter") String filter);
}
