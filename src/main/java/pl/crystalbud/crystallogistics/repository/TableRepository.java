package pl.crystalbud.crystallogistics.repository;

import pl.crystalbud.crystallogistics.entity.Table;

import java.util.List;
import java.util.Optional;

public interface TableRepository {
    List<Table> findAll();

    Table save(Table table);

    Optional<Table> findById(Integer tableId);
}
