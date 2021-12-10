/***
 * This class contains the geolocation of the different cities.
 */
public class GeoLocation {

    private final double latitude;
    private final double longitude;
    private final double radiusOfEarth = 6371;
    private final double radians = Math.PI/180;
    private final double constantTwo = 2.0;


    /***
     * This method determines hte longitude and latitude values for a city
     * @param citylatitude the latitude value of the city
     * @param cityLongitude the latitude value of the city
     */
    public GeoLocation(double citylatitude, double cityLongitude) {
        latitude = citylatitude;
        longitude = cityLongitude;

    }

    /***
     * Gets the latitude of a city
     * @return the latitude of a city
     */
    public double getLatitude() {
        return latitude;

    }
    /***
     * Gets the longitude of a city
     * @return the longitude of a city
     */
    public double getLongitude() {
        return longitude;

    }

    /***
     * This method gets the distance between two cities
     * @param end the end location of the city
     * @return the distance between the two locations, the origin and the end point
     */
   public double getDistance(GeoLocation end) {
        var latitude1 = this.latitude;
        var latitude2 = end.latitude;
        var longitude1 = this.longitude;
        var longitude2 = end.longitude;
        var phi1 = latitude1 * radians;
        var phi2 = latitude2 * radians;
        var deltaPhi = (latitude2 - latitude1) * radians;
        var deltaLambda = (longitude2 - longitude1) * radians;
        var a = Math.sin(deltaPhi/constantTwo) * Math.sin(deltaPhi/constantTwo) + Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda/constantTwo) * Math.sin(deltaLambda/constantTwo);
        var c = constantTwo * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        var distance = radiusOfEarth * c;
        return distance;
    }
    /***
     * This method gets the distance between two cities
     * @param end the end location of the city
     * @param start the start location of a city
     * @return the distance between the two locations, the origin and the end point
     */
    public static double getDistance(GeoLocation start, GeoLocation end) {
        return start.getDistance(end);
    }

}