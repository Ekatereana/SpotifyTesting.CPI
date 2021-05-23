package migrations.tests;

import com.Config;
import com.CreateConnectionPropertiesService;
import lombok.SneakyThrows;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class DataMigrationTest implements Config {

    static Connection conn;
    static Statement statement;

    @SneakyThrows
    @BeforeClass
    public static void setUp() {
        conn = DriverManager.getConnection(url, CreateConnectionPropertiesService.populateProps(user, password));

    }

    @SneakyThrows
    @Test
    public void getPop() {
        statement = conn.createStatement();
        String csvFilePath = "migration/results/ActualResultPop.csv";
        ResultSet result = statement.executeQuery(getPop);
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

        fileWriter.write("id,track_name,artist_name,genre");
        while (result.next()) {
            int recordId = result.getInt("id");
            String trackName = result.getString("track_name");
            String artistName = result.getString("artist_name");
            String genre = result.getString("genre");

            String line = String.format("%d,%s,%s,%s",
                    recordId, trackName, artistName, genre);

            fileWriter.newLine();
            fileWriter.write(line);

        }
        statement.close();
        fileWriter.close();
        File diff = new File(
                CompareCSVsService
                        .showDiff("migration/results/ExpectedResultGetPop.csv",
                                csvFilePath,
                                "migration/results/PopRequest.txt"));

//        difference file should be empty
        assertEquals(diff.length(), 0);

    }

    @SneakyThrows
    @Test
    public void getCount() {
        statement = conn.createStatement();
        String csvFilePath = "migration/results/ActualResultCount.csv";
        ResultSet result = statement.executeQuery(getCount);
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

        fileWriter.write("count,genre");
        while (result.next()) {
            int count = result.getInt("count");

            String genre = result.getString("genre");

            String line = String.format("%d,%s",
                    count, genre);

            fileWriter.newLine();
            fileWriter.write(line);

        }
        statement.close();
        fileWriter.close();
        File diff = new File(
                CompareCSVsService
                        .showDiff("migration/results/ExpectedResultCount.csv",
                                csvFilePath,
                                "migration/results/CountRequest.txt"));

//        difference file should be empty
        assertEquals(0, diff.length());
    }
}
