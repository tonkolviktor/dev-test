package hu.tonkol.devtest;

import hu.tonkol.devtest.backend.BackendService;
import hu.tonkol.devtest.backend.City;
import hu.tonkol.devtest.export.CsvExport;

import java.nio.file.Paths;
import java.util.List;

/**
 * Created by tonkol on 2016.01.03..
 */
public class Application {

    private Argument argument;
    private BackendService backendService;
    private CsvExport csvExport;

    public static void main(String[] args) {
        try {
            new Application().run(args);
        } catch (BusinessException be) {
            System.out.println(be.getMessage());
            printHelp();
            be.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unknown error occurred: " + e.getMessage());
            printHelp();
            e.printStackTrace();
        }
    }

    private static void printHelp() {
        System.out.println("Usage: java -jar <jar-name> <city>");
    }

    public void run(String[] args) {
        init(args);

        List<City> cities = backendService.listCitiesByName(this.argument.getCity());
        csvExport.export(cities);
    }

    private void init(String[] args) {
        this.argument = new Argument(args);
        this.backendService = new BackendService();
        this.csvExport = new CsvExport(Paths.get(Configuration.OUTPUT_DIRECTORY));
    }
}
