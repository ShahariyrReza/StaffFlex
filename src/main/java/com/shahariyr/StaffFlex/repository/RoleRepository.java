package com.shahariyr.StaffFlex.repository;

import com.shahariyr.StaffFlex.entity.Role;
import com.shahariyr.StaffFlex.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}
