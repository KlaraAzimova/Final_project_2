package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findRoleByRoleName(String roleName);
}