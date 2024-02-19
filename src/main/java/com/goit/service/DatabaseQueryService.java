package com.goit.service;

import com.goit.exception.DatabaseException;
import com.goit.result_clases.LongestProject;
import com.goit.result_clases.MaxProjectCountClient;
import com.goit.result_clases.MaxSalaryWorker;
import com.goit.result_clases.YoungestEldestWorker;
import com.goit.utils.Database;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> list = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Files.readString(
                     Path.of("src/main/resources/sql/find_max_projects_client.sql"), StandardCharsets.UTF_8))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MaxProjectCountClient client =
                        new MaxProjectCountClient(resultSet.getString(1), resultSet.getInt(2));
                list.add(client);

            }
            return list;
        } catch (Exception e) {
            throw new DatabaseException(e);

        }
    }

    public List<LongestProject> findLongestProject() {
        List<LongestProject> list = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Files.readString(
                     Path.of("src/main/resources/sql/find_longest_project.sql"), StandardCharsets.UTF_8))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LongestProject project =
                        new LongestProject(resultSet.getInt(1), resultSet.getInt(2));
                list.add(project);

            }
            return list;
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> list = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Files.readString(
                     Path.of("src/main/resources/sql/find_max_salary_worker.sql"), StandardCharsets.UTF_8))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MaxSalaryWorker worker =
                        new MaxSalaryWorker(resultSet.getString(1), resultSet.getInt(2));
                list.add(worker);
            }
            return list;
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() {
        List<YoungestEldestWorker> list = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Files.readString(
                     Path.of("src/main/resources/sql/find_youngest_eldest_workers.sql"), StandardCharsets.UTF_8))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                YoungestEldestWorker worker =
                        new YoungestEldestWorker(resultSet.getString(1), resultSet.getString(2),
                                resultSet.getDate(3).toLocalDate());
                list.add(worker);
            }
            return list;
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }
}
