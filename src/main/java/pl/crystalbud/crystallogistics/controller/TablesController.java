package pl.crystalbud.crystallogistics.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.crystalbud.crystallogistics.dto.NewTableDTO;
import pl.crystalbud.crystallogistics.entity.Table;
import pl.crystalbud.crystallogistics.services.TablesService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogue/tables")
public class TablesController {

    private final TablesService tablesService;


    @GetMapping("list")
    public String getTablesList(Model model) {
        model.addAttribute("tables", this.tablesService.findAllTables());
        return "catalogue/tables/list";
    }

    @GetMapping("create")
    public String getNewTablePage() {
        return "catalogue/tables/new_table";
    }

    @PostMapping("create")
    public String createTable(@Valid NewTableDTO tableDTO,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tableDTO", tableDTO);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "catalogue/tables/new_table";
        } else {
            Table table = this.tablesService.createTable(tableDTO.title(), tableDTO.details());
            return "redirect:/catalogue/tables/%d".formatted(table.getId());
        }
    }
}
