package com.sheemab.Assignment.Management.System.entity;

import com.sheemab.Assignment.Management.System.enums.SubjectType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "subjects",
        uniqueConstraints = @UniqueConstraint(columnNames = "subject_code")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "subject_code", nullable = false)
    private String subjectCode; // CS501

    @NotBlank
    @Column(name = "subject_name", nullable = false)
    private String subjectName; // Cryptography

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubjectType type; // THEORY / LAB

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;
}

