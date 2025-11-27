package com.sheemab.Assignment.Management.System.repository;

import com.sheemab.Assignment.Management.System.entity.Role;
import com.sheemab.Assignment.Management.System.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
