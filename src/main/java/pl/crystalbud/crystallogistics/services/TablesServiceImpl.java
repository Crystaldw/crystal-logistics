package pl.crystalbud.crystallogistics.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.crystalbud.crystallogistics.entity.Table;
import pl.crystalbud.crystallogistics.repository.TableRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TablesServiceImpl implements TablesService {

    private final TableRepository tableRepository;
    @Override
    public List<Table> findAllTables() {
        return this.tableRepository.findAll();
    }

    @Override
    public Table createTable(String title, String details) {
        return this.tableRepository.save(new Table(null, title, details));
    }
}

