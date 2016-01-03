package hu.tonkol.devtest.export;

import hu.tonkol.devtest.BusinessException;
import hu.tonkol.devtest.Configuration;
import hu.tonkol.devtest.backend.City;
import hu.tonkol.devtest.backend.GeoPosition;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Created by tonkol on 2016.01.03..
 */
public class CsvExport {

    private final Path directory;
    private final Path resultPath;

    public CsvExport(Path directory) {
        this.directory = directory;
        this.resultPath = directory.resolve(getResultFileName());
    }

    public void export(List<City> cities) {
        BufferedWriter writer = null;
        try {
            Files.createFile(resultPath);
            writer = Files.newBufferedWriter(resultPath, Charset.defaultCharset(), StandardOpenOption.APPEND);
            writeHeader(writer);
            writeCities(writer, cities);
        } catch (IOException e) {
            throw new BusinessException("Error occurred during csv export: " + e.getMessage());
        } finally {
            closeWriter(writer);
        }
    }

    private void writeCities(BufferedWriter writer, List<City> cities) throws IOException {
        for (City city : cities) {
            writeCity(writer, city);
            writer.newLine();
        }
    }

    private void writeCity(BufferedWriter writer, City city) throws IOException {
        writer.write(String.valueOf(city.get_id()));
        writer.write(Configuration.CSV_COLUMN_SEPARATOR);
        writer.write("\"");
        writer.write(city.getName());
        writer.write("\"");
        writer.write(Configuration.CSV_COLUMN_SEPARATOR);
        writer.write("\"");
        writer.write(city.getType());
        writer.write("\"");
        writer.write(Configuration.CSV_COLUMN_SEPARATOR);
        writeGeoPosition(writer, city.getGeo_position());
    }

    private void writeGeoPosition(BufferedWriter writer, GeoPosition geo_position) throws IOException {
        if(geo_position == null) {
            writer.write(Configuration.CSV_COLUMN_SEPARATOR);
            return;
        }
        writer.write(String.valueOf(geo_position.getLatitude()));
        writer.write(Configuration.CSV_COLUMN_SEPARATOR);
        writer.write(String.valueOf(geo_position.getLongitude()));
    }

    private void closeWriter(BufferedWriter writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                throw new BusinessException("Error occurred during csv export: " + e.getMessage());
            }
        }
    }

    private void writeHeader(BufferedWriter writer) throws IOException {
        writer.write("_id,name,type,latitude,longitude");
        writer.newLine();
    }

    public String getResultFileName() {
        return Configuration.RESULT_FILE_WITHOUT_EXTENSION + ".csv";
    }
}
