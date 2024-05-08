package pl.crystalbud.catalogueservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.crystalbud.catalogueservice.entity.Table;
import pl.crystalbud.catalogueservice.services.TablesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalogue-api/tables")
public class TablesRestController {

        private final TablesService tablesService;

        @GetMapping
        public List<Table> findTables(){
            return this.tablesService.findAllTables();
        }

}
