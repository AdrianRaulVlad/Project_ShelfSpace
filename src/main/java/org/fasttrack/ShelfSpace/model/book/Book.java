package org.fasttrack.ShelfSpace.model.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.fasttrack.ShelfSpace.model.person.Person;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String title;
    @OneToOne(cascade = CascadeType.ALL)
    private Person author;
    @Column
    private String genre;
    @JsonIgnore
    private LocalDate releaseDate;
    @Column
    private String description;
    @Column(columnDefinition = "varchar(10) default null")
    private String state;
}
