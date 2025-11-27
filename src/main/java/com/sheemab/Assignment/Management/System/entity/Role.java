package com.sheemab.Assignment.Management.System.entity;

import com.sheemab.Assignment.Management.System.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Store the role name as a string (e.g. "ROLE_FACULTY")
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleName name;

}
