package hu.tonkol.devtest.backend;

import hu.tonkol.devtest.BusinessException;
import hu.tonkol.devtest.Configuration;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.fail;


/**
 * Created by tonkol on 2016.01.03..
 */
@Test(groups = "e2e")
public class BackendServiceTest {

    private static final String TEST_CITY = "Berlin";

    @Test
    public void shouldReturnValidCities() {
        //given
        BackendService backendService = new BackendService();
        //when
        List<City> cities = backendService.listCitiesByName(TEST_CITY);
        //then
        assertThat(cities).hasSize(8);
    }

    @Test(expectedExceptions = BusinessException.class)
    public void shouldThrow404Exception() {
        //given
        BackendService backendService = new BackendService();
        //when
        backendService.listCitiesByName("noSuchUrl/" + TEST_CITY);
        //then
        fail();
    }
}
