package pl.crystalbud.catalogueservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table {

    private Integer id;

    private String title;

    private String details;
}
