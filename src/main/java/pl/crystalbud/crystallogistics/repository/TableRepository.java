package pl.crystalbud.crystallogistics.repository;

import pl.crystalbud.crystallogistics.entity.Table;

import java.util.List;

public interface TableRepository {
    List<Table> findAll();

    Table save(Table table);
}
