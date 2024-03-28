package core.util;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHandler {

    public static List<List<String>> readFromFile(String path) throws IOException, CsvValidationException {
        try (CSVReader csvReader = new CSVReader(new FileReader(path))) {
            List<List<String>> records = new ArrayList<>();
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
            return records;
        }
    }

    /**
     * Update CSV by row and column
     *
     * @param fileToUpdate CSV file path to update e.g. D:\\chetan\\test.csv
     * @param replace Replacement for your cell value
     * @param row Row for which need to update
     * @param col Column for which you need to update
     * @throws IOException
     */
    public static void updateCSV(String fileToUpdate, String replace, int row, int col) throws IOException, CsvException {

        File inputFile = new File(fileToUpdate);

        // Read existing file
        CSVReader reader = new CSVReader(new FileReader(inputFile));
        List<String[]> csvBody = reader.readAll();
        // get CSV row column  and replace with by using row and column
        csvBody.get(row)[col] = "'"+replace+"'";
        reader.close();

        // Write to CSV file which is open
        CSVWriter writer = new CSVWriter(new FileWriter(inputFile));
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }
}
