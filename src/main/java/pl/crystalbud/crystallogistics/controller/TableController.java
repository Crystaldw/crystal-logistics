package pl.crystalbud.crystallogistics.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.crystalbud.crystallogistics.dto.UpdateTableDTO;
import pl.crystalbud.crystallogistics.entity.Table;
import pl.crystalbud.crystallogistics.services.TablesService;

import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping(("catalogue/tables/{tableId:\\d+}"))
public class TableController {

    private final TablesService tablesService;
    private final MessageSource messageSource;

    @ModelAttribute("table")
    public Table table(@PathVariable("tableId") int tableId) {
        return this.tablesService.findTable(tableId)
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
    public String updateTable(@ModelAttribute(name = "table", binding = false) Table table, @Valid UpdateTableDTO tableDTO,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tableDTO", tableDTO);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "catalogue/tables/edit";
        } else {
            this.tablesService.updateTable(table.getId(), tableDTO.title(), tableDTO.details());
            return "redirect:/catalogue/tables/%d".formatted(table.getId());
        }
    }

    @PostMapping("delete")
    public String deleteTable(@ModelAttribute("table") Table table) {
        this.tablesService.deleteTable(table.getId());
        return "redirect:/catalogue/tables/list";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception, Model model,
                                               HttpServletResponse response, Locale locale) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error",
                this.messageSource.getMessage(exception.getMessage(), new Object[0],
                        exception.getMessage(), locale));
        return "errors/404";
    }
}
