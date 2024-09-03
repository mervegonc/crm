package com.project.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByUserRolesRoleName(String roleName);

	Optional<User> findByUsernameOrEmail(String username, String email);

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	List<User> findByRole(String role);

	List<User> findByActiveTrue();

	List<User> findByOrderByLastLoginAtDesc();

	List<User> findByCompanyCompanyCode(String companyCode);

	boolean existsByUsername(String username);

	List<User> findByUsernameContaining(String username);
}
