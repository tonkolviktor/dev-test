package hu.tonkol.devtest.backend;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by tonkol on 2016.01.03..
 */
public class GeoPosition implements Serializable {

    private BigDecimal latitude;
    private BigDecimal longitude;

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeoPosition that = (GeoPosition) o;

        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        return !(longitude != null ? !longitude.equals(that.longitude) : that.longitude != null);

    }

    @Override
    public int hashCode() {
        int result = latitude != null ? latitude.hashCode() : 0;
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }
}
