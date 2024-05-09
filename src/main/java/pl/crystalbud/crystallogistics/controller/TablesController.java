package pl.crystalbud.crystallogistics.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.crystalbud.crystallogistics.client.TablesRestClient;
import pl.crystalbud.crystallogistics.controller.payload.payload.NewTablePayload;
import pl.crystalbud.crystallogistics.entity.Table;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogue/tables")
public class TablesController {

    private final TablesRestClient tablesRestClient;


    @GetMapping("list")
    public String getTablesList(Model model) {
        model.addAttribute("tables", this.tablesRestClient.findAllTables());
        return "catalogue/tables/list";
    }

    @GetMapping("create")
    public String getNewTablePage() {
        return "catalogue/tables/new_table";
    }

    @PostMapping("create")
    public String createTable(@Valid NewTablePayload tableDTO,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tableDTO", tableDTO);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "catalogue/tables/new_table";
        } else {
            Table table = this.tablesRestClient.createTable(tableDTO.title(), tableDTO.details());
            return "redirect:/catalogue/tables/%d".formatted(table.id());
        }
    }
}
