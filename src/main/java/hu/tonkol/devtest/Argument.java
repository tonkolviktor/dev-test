package hu.tonkol.devtest;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by tonkol on 2016.01.03..
 */
public class Argument {

    private final String[] args;

    private final String city;

    public Argument(String[] args) {
        this.args = args;
        assertNotEmptyArray();
        this.city = StringUtils.stripToEmpty(args[0]);
        assertNotEmptyCity();
    }

    private void assertNotEmptyCity() {
        if(StringUtils.isEmpty(this.city)) {
            throw new BusinessException("First argument should not be empty!");
        }
    }

    private void assertNotEmptyArray() {
        if(args == null || args.length == 0) {
            throw new BusinessException("At least one argument must be specified!");
        }
    }

    public String getCity() {
        return city;
    }
}
