package pl.crystalbud.crystallogistics.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.crystalbud.crystallogistics.client.BadRequestException;
import pl.crystalbud.crystallogistics.controller.payload.payload.UpdateTablePayload;
import pl.crystalbud.crystallogistics.entity.Table;
import pl.crystalbud.crystallogistics.client.TablesRestClient;

import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping(("catalogue/tables/{tableId:\\d+}"))
public class TableController {

    private final TablesRestClient tablesRestClient;
    private final MessageSource messageSource;

    @ModelAttribute("table")
    public Table table(@PathVariable("tableId") int tableId) {
        return this.tablesRestClient.findTable(tableId)
                .orElseThrow(() -> new NoSuchElementException("catalogue.errors.table.not_found"));
    }

    @GetMapping
    public String getTable() {
        return "catalogue/tables/table";
    }

    @GetMapping("edit")
    public String getTableEditPage() {
        return "catalogue/tables/edit";
    }

    @PostMapping("edit")
    public String updateTable(@ModelAttribute(name = "table", binding = false) Table table,
                              UpdateTablePayload payload,
                              Model model) {
            try {
                this.tablesRestClient.updateTable(table.id(), payload.title(), payload.details());
                return "redirect:/catalogue/tables/%d".formatted(table.id());
            } catch (BadRequestException exception) {
                model.addAttribute("payload", payload);
                model.addAttribute("errors", exception.getErrors());
                return "catalogue/tables/edit";
            }
        }

        @PostMapping("delete")
        public String deleteTable (@ModelAttribute("table") Table table){
            this.tablesRestClient.deleteTable(table.id());
            return "redirect:/catalogue/tables/list";
        }

        @ExceptionHandler(NoSuchElementException.class)
        public String handleNoSuchElementException (NoSuchElementException exception, Model model,
                HttpServletResponse response, Locale locale){
            response.setStatus(HttpStatus.NOT_FOUND.value());
            model.addAttribute("error",
                    this.messageSource.getMessage(exception.getMessage(), new Object[0],
                            exception.getMessage(), locale));
            return "errors/404";
        }
    }
