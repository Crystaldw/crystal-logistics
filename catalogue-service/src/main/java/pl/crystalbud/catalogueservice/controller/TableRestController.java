package pl.crystalbud.catalogueservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
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
    private final MessageSource messageSource;

    @ModelAttribute("table")
    public Table getTable(@PathVariable("tableId") int tableId) {
        return this.tablesService.findTable(tableId)
                .orElseThrow(() -> new NoSuchElementException("catalogue.errors.table.not_found"))
    }

    @GetMapping
    public Table findTable(@ModelAttribute("table") Table table) {
        return table;
    }

    @PatchMapping
    public ResponseEntity<?> updateTable(@PathVariable("tableId") int tableId,
                                         @Valid @RequestBody UpdateTablePayload payload,
                                         BindingResult bindingResult, Locale locale) {
        if (bindingResult.hasErrors()) {
            ProblemDetail problemDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatus.BAD_REQUEST,
                            this.messageSource.getMessage("errors.400.title", new Object[0],
                                    "errors.400.title", locale));
            problemDetail.setProperty("errors",
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .toList());
            return ResponseEntity.badRequest()
                    .body(problemDetail);
        } else {
            this.tablesService.updateTable(tableId, payload.title(), payload.details());
            return ResponseEntity.noContent()
                    .build();
        }
    }
}
