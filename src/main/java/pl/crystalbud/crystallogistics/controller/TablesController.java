package pl.crystalbud.crystallogistics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.crystalbud.crystallogistics.services.TablesService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogue/tables")
public class TablesController {

    private final TablesService tablesService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getTablesList(Model model) {
        model.addAttribute("tables", this.tablesService.findAllTables());
        return "catalogue/tables/list";
    }
}
