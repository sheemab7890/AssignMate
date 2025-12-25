package com.sheemab.Assignment.Management.System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "programs",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "program_name")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Program name is required")
    @Column(name = "program_name", nullable = false)
    private String programName; // B.Tech CSE, B.Tech AI

    // ⭐ Program Coordinator
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinator_id", nullable = false)
    private User coordinator; // must be FACULTY
}

