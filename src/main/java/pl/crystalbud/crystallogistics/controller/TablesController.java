package pl.crystalbud.crystallogistics.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.crystalbud.crystallogistics.client.BadRequestException;
import pl.crystalbud.crystallogistics.client.TablesRestClient;
import pl.crystalbud.crystallogistics.controller.payload.payload.NewTablePayload;
import pl.crystalbud.crystallogistics.entity.Table;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogue/tables")
public class TablesController {

    private final TablesRestClient tablesRestClient;


    @GetMapping("list")
    public String getTablesList(Model model, @RequestParam(name = "filter", required = false) String filter) {
        model.addAttribute("tables", this.tablesRestClient.findAllTables(filter));
        model.addAttribute("filter", filter);
        return "catalogue/tables/list";
    }

    @GetMapping("create")
    public String getNewTablePage() {
        return "catalogue/tables/new_table";
    }

    @PostMapping("create")
    public String createTable(NewTablePayload payload,
                              Model model) {
        try {
            Table table = this.tablesRestClient.createTable(payload.title(), payload.details());
            return "redirect:/catalogue/tables/%d".formatted(table.id());
        } catch (BadRequestException exception) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", exception.getErrors());
            return "catalogue/tables/new_table";
        }
    }
}
