package com.sheemab.Assignment.Management.System.service;

import com.sheemab.Assignment.Management.System.dto.UserCsvRow;
import com.sheemab.Assignment.Management.System.entity.Role;
import com.sheemab.Assignment.Management.System.entity.User;
import com.sheemab.Assignment.Management.System.repository.RoleRepository;
import com.sheemab.Assignment.Management.System.repository.UserRepository;
import com.sheemab.Assignment.Management.System.utility.CsvParser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BulkUserUploadService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public void uploadUsers(MultipartFile file) {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("CSV file is empty");
        }

        List<UserCsvRow> rows = CsvParser.parse(file);

        for (UserCsvRow row : rows) {

            if (userRepository.existsByEmail(row.getEmail())) {
                continue; // skip duplicates safely
            }

            Role role = roleRepository.findByName(row.getRole())
                    .orElseThrow(() ->
                            new RuntimeException("Role not found: " + row.getRole()));

            User user = new User();
            user.setFullName(row.getFullName());
            user.setEmail(row.getEmail());

            // IMPORTANT: password already hashed
            user.setPassword(row.getPassword());

            // 🔥 THIS POPULATES users_roles
            user.getRoles().add(role);

            userRepository.save(user);
        }
    }
}

