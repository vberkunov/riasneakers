package com.shopservice.riasneakers.repository;



import com.shopservice.riasneakers.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {

    Role findByRoleName(String name);
}
