import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

/***
 * This is the City Set class. It contains the set of cities and the information contained for each set of cities.
 */
public class CitySet {
  private final Set<City> cities = new HashSet<City>();
  private final double conversionRateToMiles = 0.621371; //This is the conversion rate from Km to Mi

    /***
     * This is the print method that displays the city name, distance in Km and distance in Mi for each city in the
     * cities set.
     * @param origin this is the original city that the user input.
     */
    public void printCitiesWithDistance(City origin){
        for (City city : cities){
            System.out.printf("City Name: %s, City Distance in Km: %.2f, Distance in Miles: %.2f%n", city.name, origin.location.getDistance(city.location), origin.location.getDistance(city.location) * conversionRateToMiles);

        }
    }

    /***
     * This is a find method that finds if a city exists in the list of cities.
     * @param cityName This is the string that represents the user input.
     * @return null if a city name is not found for the cityName, represented by the user input.
     */
    public City find(String cityName) {
        for(City city : cities) {
            if (city.name.equals(cityName))
                return city;
        }
        return null;
    }
    /***
     * This is a find method that finds if an Id exists in the list of city Ids.
     * @param cityId This is the long that represents the user input for Id.
     * @return null if an Id is not found for the cityId, represented by the user input.
     */
    public City find(long cityId) {
        for(City city : cities) {
            if (city.id == (cityId))
                return city;
        }
        return null;
    }

    /***
     * This method adds each individual city to the cities set.
     * @param city represents an individual city within the cities set.
     */
    public void add(City city) {
        cities.add(city);
    }

    /***
     * This method sees if city is contained in cities and returns false if not.
     * @param city represents an individual city within the cities set.
     * @return a boolean value that determines if the City.getName() was found.
     */
    public boolean contains(City city) {
        if (cities.contains(City.getName())){
            return true;
        }
        return false;
    }

    /***
     * This method will determine the closest cities from the starting city
     * @param origin the starting or user input city
     * @param nValue the value that the user input that will determine how many closest cities to display
     * @return the cities that are the closest
     */
    public CitySet nClosestCities(City origin, int nValue) {
        var closeCities = new ArrayList<City>();
        for (City city : cities){
            closeCities.add(city);
        }
        closeCities.sort(new CustomComparator(origin));
        var closeCitySet = new CitySet();
        for (int i = 0; i < nValue; i ++){
            closeCitySet.add(closeCities.get(i));
        }
        return closeCitySet;
    }

    /***
     * This method will determine the furthest cities from the starting city
     * @param origin the starting or user input city
     * @param nValue the value that the user input that will determine how many furthest cities to display
     * @return the cities that are the furthest
     */
    public CitySet nFurthestCities(City origin, int nValue) {
        var farCities = new ArrayList<City>();
        for (City city : cities){
            farCities.add(city);
        }
        farCities.sort(new CustomComparator(origin));
        var farCitySet = new CitySet();
        for (int i = 0; i < nValue; i ++){
            farCitySet.add(farCities.get(farCities.size() - 1 - i));
        }
        return farCitySet;
    }

    /***
     * This method will determine if the cities are within the radius
     * @param center the origin or user input city
     * @param radius the radius that the user input from the origin
     * @return the cities within the radius
     */
    public CitySet withinRadius(City center, double radius) {

        var radiusCities = new CitySet();
        for(City city : cities) {
            if (GeoLocation.getDistance(center.location, city.location) * conversionRateToMiles <= radius)
                radiusCities.add(city);
        }
        return radiusCities;
    }

    /***
     * The parse method that parses the file and returns the next line, and applys it to citySet.
     * @return a set of cities with the input from the parsed lines
     */
    public static CitySet parse()
    {
        var citySet = new CitySet();
        try (var input = new Scanner(new BufferedReader(new FileReader("uscities.csv")))) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                var city = City.parse(line);
                 citySet.add(city);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return citySet;
    }


}

/***
 * This is a comparator class that will determine if the distance between two points is greater or smaller.
 * Allows sorting to be done in a list.
 */
class CustomComparator implements Comparator<City> {
    City center;

    CustomComparator(City thecenter){
        center = thecenter;
    }

    @Override
    public int compare(City a, City b) {
        double distA = center.distance(a);
        double distB = center.distance(b);
        if (distA < distB)
            return -1;
        else if (distA > distB)
            return 1;
        else
            return 0;
    }
}

