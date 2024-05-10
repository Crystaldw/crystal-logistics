package pl.crystalbud.catalogueservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@jakarta.persistence.Table(schema = "catalogue", name = "t_table")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_title")
    @NonNull
    @Size(min = 3, max = 50)
    private String title;

    @Column(name = "c_details")
    @Size(max = 1000)
    private String details;
}
