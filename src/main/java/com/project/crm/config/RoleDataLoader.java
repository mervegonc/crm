package com.project.crm.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.project.crm.entity.Role; // Role'ü entity'den import ediyoruz.
import com.project.crm.repository.RoleRepository;

@Configuration
public class RoleDataLoader {

	@Bean
	@Transactional
	public ApplicationRunner initializeRoles(RoleRepository roleRepository) {
		return args -> {
			if (!roleRepository.existsByName("ROLE_ADMIN")) {
				roleRepository.save(new Role(1L, "ROLE_ADMIN"));
			}
			if (!roleRepository.existsByName("ROLE_USER")) {
				roleRepository.save(new Role(2L, "ROLE_USER"));
			}
			if (!roleRepository.existsByName("ROLE_MANAGER")) {
				roleRepository.save(new Role(3L, "ROLE_MANAGER"));
			}
			if (!roleRepository.existsByName("ROLE_EMPLOYEE")) {
				roleRepository.save(new Role(4L, "ROLE_EMPLOYEE"));
			}
			if (!roleRepository.existsByName("ROLE_PENDING_EMPLOYEE")) {
				roleRepository.save(new Role(5L, "ROLE_PENDING_EMPLOYEE"));
			}
			if (!roleRepository.existsByName("ROLE_CANDIDATE")) {
				roleRepository.save(new Role(6L, "ROLE_CANDIDATE"));
			}
			if (!roleRepository.existsByName("ROLE_GUEST")) {
				roleRepository.save(new Role(7L, "ROLE_GUEST"));
			}
		};
	}
}
