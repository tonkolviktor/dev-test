package hu.tonkol.devtest.export;

import hu.tonkol.devtest.BusinessException;
import hu.tonkol.devtest.Configuration;
import hu.tonkol.devtest.backend.City;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tonkol on 2016.01.03..
 */
public class CsvExportTest {

    private Path directory;
    private Path resultFile;
    private CsvExport csvExport;

    @BeforeMethod
    public void createTempFolder() throws IOException {
        directory = Files.createTempDirectory("csv-test");
        csvExport = new CsvExport(directory);
        resultFile = directory.resolve(csvExport.getResultFileName());
    }

    @Test
    public void shouldCreateFile() {
        //given
        List<City> cities = Collections.emptyList();
        //when
        csvExport.export(cities);
        //then
        assertThat(resultFile).exists();
    }

    @Test
    public void shouldWriteHeader() {
        //given
        List<City> cities = Collections.emptyList();
        //when
        csvExport.export(cities);
        //then
        assertThat(resultFile).hasContent("_id,name,type,latitude,longitude");
        String content = readFileContent();
        assertThat(content).hasLineCount(1);
    }

    @Test
    public void shouldWriteCities() {
        //given
        List<City> cities = createCities(5);
        //when
        csvExport.export(cities);
        //then
        String content = readFileContent();
        assertThat(content).hasLineCount(6);
        assertThat(content).contains(getCityLine(cities.get(0)));
    }

    private CharSequence getCityLine(City city) {
        StringBuffer sb = new StringBuffer();
        sb.append(city.get_id());
        sb.append(Configuration.CSV_COLUMN_SEPARATOR);
        sb.append("\"").append(city.getName()).append("\"");
        sb.append(Configuration.CSV_COLUMN_SEPARATOR);
        sb.append("\"").append(city.getType()).append("\"");
        sb.append(Configuration.CSV_COLUMN_SEPARATOR);
        sb.append(city.getGeo_position().getLatitude());
        sb.append(Configuration.CSV_COLUMN_SEPARATOR);
        sb.append(city.getGeo_position().getLongitude());

        return sb.toString();
    }

    private String readFileContent() {
        try {
            return new String(Files.readAllBytes(resultFile));
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("Error during test: " + e.getMessage());
        }
    }

    private List<City> createCities(int numberOfCities) {
        List<City> cities = new ArrayList<City>();
        for(int i = 0; i < numberOfCities; i++) {
            cities.add(createCity());
        }

        return cities;
    }

    private City createCity() {
        PodamFactory factory = new PodamFactoryImpl();
        return factory.manufacturePojo(City.class);
    }
}
