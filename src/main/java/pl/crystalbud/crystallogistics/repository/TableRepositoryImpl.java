package pl.crystalbud.crystallogistics.repository;

import org.springframework.stereotype.Repository;
import pl.crystalbud.crystallogistics.entity.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class TableRepositoryImpl implements TableRepository {

    private final List<Table> tables = new ArrayList<>();

    public TableRepositoryImpl() {
        IntStream.range(1,4)
                .forEach(i-> this.tables.add(new Table(i, "Таблица №%d" .formatted(i),
                        "Описание таблицы №%d" .formatted(i))));
    }

    @Override
    public List<Table> findAll() {
        return Collections.unmodifiableList(this.tables);
    }
}
