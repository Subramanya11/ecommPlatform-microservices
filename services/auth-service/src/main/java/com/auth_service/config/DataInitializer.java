package com.auth_service.config;

import com.auth_service.entity.Role;
import com.auth_service.entity.enums.RoleName;
import com.auth_service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        createRoleIfNotExists(RoleName.ROLE_USER);
        createRoleIfNotExists(RoleName.ROLE_ADMIN);
        createRoleIfNotExists(RoleName.ROLE_SELLER);

    }

    private void createRoleIfNotExists(RoleName roleName) {

        if (!roleRepository.existsByName(roleName)) {

            Role role = Role.builder()
                    .name(roleName)
                    .build();

            roleRepository.save(role);

            System.out.println(roleName + " created.");
        }

    }
}