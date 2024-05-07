package pl.crystalbud.crystallogistics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.crystalbud.crystallogistics.dto.TableDTO;
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
    public String getNewTablePage(){
        return "catalogue/tables/new_table";
    }

    @PostMapping("create")
    public String createTable(TableDTO tableDTO){
        Table table = this.tablesService.createTable(tableDTO.title(), tableDTO.details());
        return "redirect:/catalogue/tables/%d".formatted(table.getId());
    }

}
