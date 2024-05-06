package pl.crystalbud.crystallogistics.services;


import pl.crystalbud.crystallogistics.entity.Table;

import java.util.List;

public interface TablesService {


    List<Table> findAllTables();

    Table createTable(String title, String details);
}
