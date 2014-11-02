package database;

/**
 * Created by Mario Pagger on 02.11.2014.
 */
public class Stop {
    private final int id;
    private final float lat;
    private final float lon;
    private final String location;

    public Stop(int id, float lat, float lon, String location)
    {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString()
    {
        return location + "(Lat: " + lat + ", Lon: " + lon + ")";
    }
}

