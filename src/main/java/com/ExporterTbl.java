package com;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class ExporterTbl implements Config {

    public static void main(String[] args) {
        String csvFilePath = "migration/results/DataFromSpotifyDB.csv";
//        String csvFilePath = "migration/results/ExpectedResultGetPop.csv";
//        String csvFilePath = "migration/results/ExpectedResultCount.csv";

        try (Connection connection = DriverManager.getConnection(url, CreateConnectionPropertiesService.populateProps(user, password))) {
            String sql = "select s.id, s.track_name, s.artist_name, s.genre  from spotify_data as s";
////            String sql = getPop;
//            String sql = getCount;
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

            // write header line containing column names
            fileWriter.write("id,track_name,artist_name,genre");
//            fileWriter.write("count,genre");
            while (result.next()) {

//                int count = result.getInt("count");

                int recordId = result.getInt("id");
                String trackName = result.getString("track_name");
                String artistName = result.getString("artist_name");
                String genre = result.getString("genre");

                String line = String.format("%d,%s,%s,%s;",
                        recordId, trackName, artistName, genre);

//                String line = String.format("%d,%s;",
//                        count, genre);

                fileWriter.newLine();
                fileWriter.write(line);
            }

            statement.close();
            fileWriter.close();

        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
    }
}
