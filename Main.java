/*
Jamie Gashler
Java Programming I
Homework VI - Cities
 */

/***
 * This is the main class of the program. It performs the main actions for the program which include displaying the menu,
 * asking for user input and displaying the responses.
 */
public class Main {
    private static final int min = 0;
    private static final int max = 21;

    /***
     * This method prompts the user with the menu response options: Entering a city name, city ID or Quitting the program.
     * @return This returns a string as the user input for what menu response they input.
     */
    public static String getMenuResponse() {
        return ConsoleUtil.getString("Would you like to enter a (C)ity Name, City (I)D, or (Q)uit? > ");
    }

    /** This is the main method of the program. It includes the menu and files for finding the cities the user inputs
     * @param args contains the supplied command-line arguments as an array of String objects
     */
        public static void main(String[] args) {

        var citySet = CitySet.parse();
        launch(citySet);
        }

    /***
     *This method contains an if statement that determines if the user input valid data and what to do in response to
     * the user input. If the user inputs a c, the City.getName() method is called, and the method displays the process
     * to which the user can display nearest, furthest and a radius of cities. If the user inputs an I, the process is
     * the same, but with a city ID rather than a name. If the user inputs a q, exit the program. Else, report invalid
     * data.
     * @param citySet is of type CitySet which is a set of cities. It represents the list of all of the different cities
     *                found in the csv file.
     */
    public static void launch(CitySet citySet) {

            while(true) {
            String response = getMenuResponse();
            if (response.equalsIgnoreCase("c")) {
                String cityname = City.getName();
                var origin = citySet.find(cityname);
                if (origin == null)
                {
                    System.out.println("This city does not exist!");
                    continue;
                }
                var CityNumFarorNear= ConsoleUtil.getInteger("What number of cities near/far from this city would you like to see? (1-20) %n", min, max);

                System.out.printf("%nYour closest %d cities from your original city are: %n",  CityNumFarorNear);
                var nearCities = citySet.nClosestCities(origin, CityNumFarorNear);
                nearCities.printCitiesWithDistance(origin);


                System.out.printf("%nYour furthest %d cities from your original city are: %n",  CityNumFarorNear);
                var farCities = citySet.nFurthestCities(origin, CityNumFarorNear);
                System.out.println("");
                farCities.printCitiesWithDistance(origin);


                var radius = ConsoleUtil.getInteger("Please enter a radius in miles from your city > ");
                var citiesInRadius = citySet.withinRadius(origin, radius);
                citiesInRadius.printCitiesWithDistance(origin);

            } else if(response.equalsIgnoreCase("i")) {
                long id = City.getId();

                var origin = citySet.find(id);
                if (origin == null) {
                    System.out.println("This  Id does not exist!");
                    continue;

                }

                var CityNumFarorNear= ConsoleUtil.getInteger("What number of cities near/far from this city would you like to see? (1-20) > %n", min, max);

                System.out.printf("%n Your closest %d cities from your original city are: %n",  CityNumFarorNear);
                var nearCities = citySet.nClosestCities(origin, CityNumFarorNear);
                nearCities.printCitiesWithDistance(origin);


                System.out.printf("%n Your furthest %d cities from your original city are: %n",  CityNumFarorNear);
                var farCities = citySet.nFurthestCities(origin, CityNumFarorNear);
                System.out.println("");
                farCities.printCitiesWithDistance(origin);


                var radius = ConsoleUtil.getInteger("Please enter a radius in miles from your city > ");
                var citiesInRadius = citySet.withinRadius(origin, radius);
                citiesInRadius.printCitiesWithDistance(origin);
            } else if(response.equalsIgnoreCase("q")) {
                break;

            } else {
                System.out.printf("%n%n");
                System.out.println("Invalid Input, try again.");

            }

        }
    }


}