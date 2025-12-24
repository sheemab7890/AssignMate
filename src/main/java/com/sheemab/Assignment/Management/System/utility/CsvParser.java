package com.sheemab.Assignment.Management.System.utility;

import com.sheemab.Assignment.Management.System.dto.UserCsvRow;
import com.sheemab.Assignment.Management.System.enums.RoleName;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    public static List<UserCsvRow> parse(MultipartFile file) {

        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
        ) {

            CSVParser csvParser = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim()
                    .parse(reader);

            List<UserCsvRow> rows = new ArrayList<>();

            for (CSVRecord record : csvParser) {

                rows.add(new UserCsvRow(
                        record.get("fullName"),
                        record.get("email"),
                        record.get("password"),
                        RoleName.valueOf(record.get("role").toUpperCase())
                ));
            }

            return rows;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse CSV file", e);
        }
    }
}

