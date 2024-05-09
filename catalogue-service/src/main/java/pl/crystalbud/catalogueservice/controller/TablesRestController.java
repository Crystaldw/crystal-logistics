package pl.crystalbud.catalogueservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.crystalbud.catalogueservice.controller.payload.payload.NewTablePayload;
import pl.crystalbud.catalogueservice.entity.Table;
import pl.crystalbud.catalogueservice.services.TablesService;

import java.awt.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalogue-api/tables")
public class TablesRestController {

    private final TablesService tablesService;

    @GetMapping
    public List<Table> findTables() {
        return this.tablesService.findAllTables();
    }

    @PostMapping
    public ResponseEntity<?> createTable(@Valid @RequestBody NewTablePayload payload,
                                         BindingResult bindingResult,
                                         UriComponentsBuilder uriComponentsBuilder)
            throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Table table = this.tablesService.createTable(payload.title(), payload.details());
            return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/catalogue-api/tables/{tableId}")
                            .build(Map.of("tableId", table.getId())))
                    .body(table);
        }
    }
}
