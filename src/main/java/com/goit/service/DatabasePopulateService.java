package com.goit.service;

import com.goit.exception.DatabaseException;
import com.goit.utils.Database;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DatabasePopulateService {
    public static void main(String[] args) {
        try (Connection connection = Database.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    Files.readString(Path.of("src/main/resources/sql/populate_db.sql"), StandardCharsets.UTF_8)
            );
            preparedStatement.execute();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }
}
