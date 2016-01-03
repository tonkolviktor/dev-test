package hu.tonkol.devtest;

import org.testng.annotations.Test;

import static org.testng.Assert.fail;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tonkol on 2016.01.03..
 */
public class ArgumentTest {

    private static final String TEST_CITY = "Berlin";

    @Test(expectedExceptions = BusinessException.class)
    public void shouldThrowExceptionIfArgumentsAreNull() {
        //given
        //when
        new Argument(null);
        //then
        fail();
    }

    @Test(expectedExceptions = BusinessException.class)
    public void shouldThrowExceptionIfArgumentsAreEmpty() {
        //given
        String[] args = {};
        //when
        new Argument(args);
        //then
        fail();
    }

    @Test(expectedExceptions = BusinessException.class)
    public void shouldThrowExceptionIfArgumentsContainsEmptyString() {
        //given
        String[] args = {""};
        //when
        new Argument(args);
        //then
        fail();
    }

    @Test
    public void shouldReturnFirstArgumentAsCity() {
        //given
        String[] args = {TEST_CITY};
        //when
        Argument argument = new Argument(args);
        //then
        assertThat(argument.getCity()).isEqualTo(TEST_CITY);
    }

    @Test
    public void shouldTrimArguments() {
        //given
        String[] args = {"   " + TEST_CITY + "   "};
        //when
        Argument argument = new Argument(args);
        //then
        assertThat(argument.getCity()).isEqualTo(TEST_CITY);
    }
}
