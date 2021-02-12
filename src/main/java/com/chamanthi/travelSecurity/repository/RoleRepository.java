package com.chamanthi.travelSecurity.repository;

import com.chamanthi.travelSecurity.entity.Role;
import com.chamanthi.travelSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);


}
