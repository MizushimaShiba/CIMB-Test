package com.test.CIMBTest.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="Blogs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BlogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String body;

    @Column
    private String author;
}
