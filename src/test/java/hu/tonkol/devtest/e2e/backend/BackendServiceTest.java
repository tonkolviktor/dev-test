package hu.tonkol.devtest.e2e.backend;

import hu.tonkol.devtest.BusinessException;
import hu.tonkol.devtest.Configuration;
import hu.tonkol.devtest.TestUtil;
import hu.tonkol.devtest.backend.BackendService;
import hu.tonkol.devtest.backend.City;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.fail;


/**
 * Created by tonkol on 2016.01.03..
 */
@Test(groups = "e2e")
public class BackendServiceTest {

    @Test
    public void shouldReturnValidCities() {
        //given
        BackendService backendService = new BackendService();
        //when
        List<City> cities = backendService.listCitiesByName(TestUtil.TEST_CITY);
        //then
        assertThat(cities).hasSize(TestUtil.TEST_CITY_RESULT_SIZE);
    }

    @Test(expectedExceptions = BusinessException.class)
    public void shouldThrow404Exception() {
        //given
        BackendService backendService = new BackendService();
        //when
        backendService.listCitiesByName("noSuchUrl/" + TestUtil.TEST_CITY);
        //then
        fail();
    }
}
