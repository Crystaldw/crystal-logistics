package pl.crystalbud.catalogueservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.crystalbud.catalogueservice.controller.payload.payload.UpdateTablePayload;
import pl.crystalbud.catalogueservice.entity.Table;
import pl.crystalbud.catalogueservice.services.TablesService;

import javax.swing.text.html.parser.Entity;
import java.util.Locale;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue-api/tables/{tableId:\\d+}")
public class TableRestController {

    private final TablesService tablesService;

    @ModelAttribute("table")
    public Table getTable(@PathVariable("tableId") int tableId) {
        return this.tablesService.findTable(tableId)
                .orElseThrow(() -> new NoSuchElementException("catalogue.errors.table.not_found"));
    }

    @GetMapping
    public Table findTable(@ModelAttribute("table") Table table) {
        return table;
    }

    @PatchMapping
    public ResponseEntity<?> updateTable(@PathVariable("tableId") int tableId,
                                         @Valid @RequestBody UpdateTablePayload payload,
                                         BindingResult bindingResult, Locale locale)
            throws BindException {
        if (bindingResult.hasErrors()) {
            if(bindingResult instanceof BindException exception){
                throw exception;
            }else {
                throw new BindException(bindingResult);
            }
        } else {
            this.tablesService.updateTable(tableId, payload.title(), payload.details());
            return ResponseEntity.noContent()
                    .build();
        }
    }
}
