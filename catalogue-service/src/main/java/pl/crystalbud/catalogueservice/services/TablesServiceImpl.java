package pl.crystalbud.catalogueservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.crystalbud.catalogueservice.entity.Table;
import pl.crystalbud.catalogueservice.repository.TableRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TablesServiceImpl implements TablesService {

    private final TableRepository tableRepository;

    @Override
    public Iterable<Table> findAllTables(String filter) {
        if (filter != null && !filter.isBlank()) {
            return this.tableRepository.findAllByTitleLikeIgnoreCase(filter);
        } else {
            return this.tableRepository.findAll();
        }
    }

    @Override
    @Transactional
    public Table createTable(String title, String details) {
        return this.tableRepository.save(new Table(null, title, details));
    }

    @Override
    public Optional<Table> findTable(int tableId) {
        return this.tableRepository.findById(tableId);
    }

    @Override
    @Transactional
    public void updateTable(Integer id, String title, String details) {
        this.tableRepository.findById(id)
                .ifPresentOrElse(table -> {
                    table.setTitle(title);
                    table.setDetails(details);
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Override
    @Transactional
    public void deleteTable(Integer id) {
        this.tableRepository.deleteById(id);
    }
}

