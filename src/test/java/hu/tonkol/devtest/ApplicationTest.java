package hu.tonkol.devtest;

import org.junit.Rule;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tonkol on 2016.01.03..
 */
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
        Application.main(new String[]{"Berlin"});
        //then
        assertThat(outContent.toString()).isEqualTo("");
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
