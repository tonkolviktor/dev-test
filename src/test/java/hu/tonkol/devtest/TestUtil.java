package hu.tonkol.devtest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by tonkol on 2016.01.03..
 */
public class TestUtil {
    public static final String TEST_CITY = "Berlin";
    public static final int TEST_CITY_RESULT_SIZE = 8;


    public static String readFileContent(Path resultFile) {
        try {
            return new String(Files.readAllBytes(resultFile));
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("Error during test: " + e.getMessage());
        }
    }
}
