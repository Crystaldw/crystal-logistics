package pl.crystalbud.crystallogistics.repository;

import org.springframework.stereotype.Repository;
import pl.crystalbud.crystallogistics.entity.Table;

import java.util.*;
import java.util.stream.IntStream;

@Repository
public class TableRepositoryImpl implements TableRepository {

    private final List<Table> tables = new ArrayList<>();

    @Override
    public List<Table> findAll() {
        return Collections.unmodifiableList(this.tables);
    }

    @Override
    public Table save(Table table) {
        table.setId(this.tables.stream()
                .max(Comparator.comparingInt(Table::getId))
                .map(Table::getId)
                .orElse(0)+1);
        this.tables.add(table);
        return table;
    }

    @Override
    public Optional<Table> findById(Integer tableId) {
        return this.tables.stream()
                .filter(table -> Objects.equals(tableId, table.getId()))
                .findFirst();
    }

    @Override
    public void deleteById(Integer id) {
        this.tables.removeIf(table -> Objects.equals(id, table.getId()));
    }
}
