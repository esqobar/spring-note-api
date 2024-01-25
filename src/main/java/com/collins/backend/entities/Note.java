package com.collins.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notes", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", nullable = false)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
