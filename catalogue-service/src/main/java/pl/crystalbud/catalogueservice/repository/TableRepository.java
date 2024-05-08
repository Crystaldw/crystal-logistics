package pl.crystalbud.catalogueservice.repository;

import pl.crystalbud.catalogueservice.entity.Table;

import java.util.List;
import java.util.Optional;

public interface TableRepository {
    List<Table> findAll();

    Table save(Table table);

    Optional<Table> findById(Integer tableId);

    void deleteById(Integer id);
}
