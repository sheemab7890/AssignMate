package com.sheemab.Assignment.Management.System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "labs",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "lab_incharge_id")
        }
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Lab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String labName; // Lab-101

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_incharge_id", nullable = false)
    private User labIncharge;
}


