package pl.crystalbud.crystallogistics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Table {

    private Integer id;

    private String title;

    private String month;
}
