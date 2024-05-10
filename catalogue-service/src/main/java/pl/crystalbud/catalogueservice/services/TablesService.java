package pl.crystalbud.catalogueservice.services;


import pl.crystalbud.catalogueservice.entity.Table;

import java.util.List;
import java.util.Optional;

public interface TablesService {


    Iterable<Table> findAllTables(String filter);

    Table createTable(String title, String details);

    Optional<Table> findTable(int tableId);

    void updateTable(Integer id, String title, String details);

    void deleteTable(Integer id);
}
