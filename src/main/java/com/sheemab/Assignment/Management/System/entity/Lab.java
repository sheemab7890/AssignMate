package com.sheemab.Assignment.Management.System.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "labs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lab_number", nullable = false, unique = true, length = 20)
    private String labNumber;

    @Column(name = "lab_name", nullable = false, length = 100)
    private String labName;

    @OneToOne
    @JoinColumn(name = "lab_incharge_id", unique = true, nullable = false)
    private User labIncharge;
}

