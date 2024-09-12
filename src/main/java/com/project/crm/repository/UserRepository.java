package com.project.crm.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.User;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

	List<User> findByUserRolesRoleName(String roleName);

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	List<User> findByActiveTrue();

	List<User> findByOrderByLastLoginAtDesc();

	List<User> findByCompanyCompanyCode(String companyCode);

	boolean existsByUsername(String username);

	List<User> findByUsernameContaining(String username);

	boolean existsByEmail(String email);

	@Query("SELECT u FROM User u JOIN FETCH u.userRoles WHERE u.username = :username")
	Optional<User> findByUsernameWithRoles(@Param("username") String username);

	Optional<User> findByUsernameOrEmail(String username, String email);
}
