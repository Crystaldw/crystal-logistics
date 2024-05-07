package pl.crystalbud.crystallogistics.services;


import pl.crystalbud.crystallogistics.entity.Table;

import java.util.List;
import java.util.Optional;

public interface TablesService {


    List<Table> findAllTables();

    Table createTable(String title, String details);

    Optional<Table> findTable(int tableId);

    void updateTable(Integer id, String title, String details);

    void deleteTable(Integer id);
}
