/***
 * This is the city class. It contains the cities and their individual requirements.
 */
public class City {

    public final String name;
    public final GeoLocation location;
    public final long id;
    private static final int cityException = 13; //the starting value of the only city that had a comma in it's name to ensure that an exception is not thrown

    /***
     * This is the main city class that defines what a city is
     * @param theCityname the name of the city
     * @param theCityLocation the location of the city as longitude and latitude
     * @param theCityId the Id of the city
     */
    public City(String theCityname, GeoLocation theCityLocation, long theCityId) {
        name = theCityname;
        location = theCityLocation;
        id = theCityId;

    }

    /***
     * This is the getName() mehtod that gets the city name
     * @return the name of the city
     */
    public static String getName() {
        var cityName= ConsoleUtil.getString("Please input a city name > ");
        return cityName;
    }

    /***
     * This is the getId() mehtod that gets the city Id
     * @return the Id of the city
     */
    public static long getId() {
        var cityId = ConsoleUtil.getInteger("Please input a city ID number > ");
        return cityId;
    }

    /***
     * This method parses each individual line in the csv file and determines the individual components of a city by
     * the commas that separate the strings & long values
     * @param line each line in the csv file that represents 1 city
     * @return the geoLocation of the city as distance
     */
    public static City parse(String line) {
        int firstCommaSeparator;
        String name;
        if (line.charAt(0) == '"') {
            firstCommaSeparator = line.indexOf(
                    ",", cityException
            );
        } else {
            firstCommaSeparator = line.indexOf(
                    ","
            );
        }
        name = line.substring(0, firstCommaSeparator);

       int secondCommaSeparator = line.indexOf(
                ",",name.length() + 1

        );

        var  lat = line.substring(firstCommaSeparator + 1, secondCommaSeparator);

        int thirdCommaSeparator = line.indexOf(
                ",", secondCommaSeparator + 1
        );

        var lon = line.substring(secondCommaSeparator + 1, thirdCommaSeparator);

        var  id = line.substring(thirdCommaSeparator + 1);

        var geoloc = new GeoLocation(Double.parseDouble(lat), Double.parseDouble(lon));
        return new City(name, geoloc, Long.parseLong(id));

    }

    /***
     * The distance method that finds the distance of the city
     * @param end the end of the city
     * @return city distance
     */
   public double distance(City end) {
        return distance(this, end);
    }

    /***
     *      * The distance method that finds the distance of the city
     * @param start the start of the city
     * @param end the end of the city
     * @return geolocation as distance
     */
    public static double distance(City start, City end) {
        return GeoLocation.getDistance(start.location, end.location);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return String.format(
                "%s, %i",
                this.getName(),
                this.getId());
    }

}