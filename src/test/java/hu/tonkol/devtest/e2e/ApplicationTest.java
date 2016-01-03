package hu.tonkol.devtest.e2e;

import hu.tonkol.devtest.Application;
import hu.tonkol.devtest.Configuration;
import hu.tonkol.devtest.TestUtil;
import hu.tonkol.devtest.export.CsvExport;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hu.tonkol.devtest.TestUtil.readFileContent;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tonkol on 2016.01.03..
 */
@Test(groups = "e2e")
public class ApplicationTest {

    private ByteArrayOutputStream outContent;

    @BeforeMethod
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterMethod
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void shouldMainRequireOneArgument() {
        //given
        //when
        Application.main(new String[]{TestUtil.TEST_CITY});
        //then
        assertThat(outContent.toString()).isEqualTo("");
        assertResultFile();
    }

    private void assertResultFile() {
        CsvExport csvExport = new CsvExport(Paths.get(Configuration.OUTPUT_DIRECTORY));
        Path path = Paths.get(csvExport.getResultFileName());

        assertThat(path).exists();
        String content = readFileContent(path);
        assertThat(content).hasLineCount(TestUtil.TEST_CITY_RESULT_SIZE + 1);
        assertThat(content).contains(TestUtil.TEST_CITY);
    }


    @Test
    public void shouldMainPrintErrorMessageOnBusinessException() {
        //given
        //when
        Application.main(null);
        //then
        assertThat(outContent.toString()).contains("At least one argument must be specified!");
    }

    @Test
    public void shouldMainPrintHelpOnBusinessException() {
        //given
        //when
        Application.main(null);
        //then
        assertThat(outContent.toString()).contains("Usage: java -jar <jar-name> <city>");
    }


}
