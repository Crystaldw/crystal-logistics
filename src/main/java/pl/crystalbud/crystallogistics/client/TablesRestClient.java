package pl.crystalbud.crystallogistics.client;

import pl.crystalbud.crystallogistics.entity.Table;

import java.util.List;
import java.util.Optional;

public interface TablesRestClient {

    List<Table> findAllTables(String filter);

    Table createTable(String title, String details);

    Optional <Table> findTable(int tableId);

    void updateTable(int tableId, String title, String details);

    void deleteTable(int tableId);
}