package migrations.tests;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CompareCSVsService {

    public static String showDiff(String expectedPath, String actualPath, String name) throws FileNotFoundException, IOException {

        BufferedReader expectedCSVFile = new BufferedReader(new FileReader(expectedPath));
        int rowNum = expectedCSVFile.readLine().split(",").length;
        ArrayList<ArrayList<String>> expected = csv2Array(expectedCSVFile);
        expectedCSVFile.close();

        BufferedReader actualCSVFile = new BufferedReader(new FileReader(actualPath));
        int actualRowNum = actualCSVFile.readLine().split(",").length;
        ArrayList<ArrayList<String>> actual = csv2Array(actualCSVFile);
        actualCSVFile.close();

        List<ArrayList<String>> copy = List.copyOf(expected);
        for (int i = 0; i < actual.size(); i++) {
            for (String bs : actual.get(i)) {
                expected.get(i).remove(bs);
            }
        }


        int size = expected.size();
        String resultName = "Difference" + name;
        try {
            FileWriter writer = new FileWriter(resultName);
            for (int i = 0; i < size; i++) {
                if (expected.get(i).size() > 0) {
                    writer.append("Founded difference on line ::" + i + "::\n");
                    writer.append("Input values:\n" + String.join(", ", copy.get(i)) + "\n");
                    writer.append("Actual values:\n" + (actual.size() > i ? String.join(", ", actual.get(i)): "no such line") + "\n");
                    writer.append("Difference in:: ");
                    writer.append(String.join(", ", expected.get(i)));
                    writer.append('\n');
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultName;

    }

    private static ArrayList<ArrayList<String>> csv2Array(BufferedReader csvFile) throws IOException {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        String dataRow = csvFile.readLine();
        ArrayList row = new ArrayList();
        while (dataRow != null) {
            String[] dataArray = dataRow.split(",");
            for (String item : dataArray) {
                row.add(item);

            }
            result.add(row);
            row = new ArrayList();
            dataRow = csvFile.readLine(); // Read next line of data.
        }

        return result;
    }
}
