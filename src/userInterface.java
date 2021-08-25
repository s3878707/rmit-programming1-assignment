import java.io.FileNotFoundException;

import java.util.*;
public class userInterface {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileOption = new Scanner(System.in);
        System.out.println("COVID TRACKER");
        System.out.println("Please enter your file name here");
        String path = fileOption.nextLine();
        CSVdata csvData = new CSVdata(path);
        //Let the user choose the continent
//        public static void mainMenu() {
        int menuOption;
        do {
            System.out.println("**************************");
            System.out.println("|       Main Menu         |");
            System.out.println("|  1.OPTION [1]: DATA     |");
            System.out.println("|  2.OPTION [2]: SUMMARY  |");
            System.out.println("|  3.OPTION [3]: DISPLAY  |");
            System.out.println("|  4.OPTION [4]: EXIT     |");
            System.out.println("**************************");
            Scanner sc = new Scanner(System.in);
            System.out.print("Please choose your next option:");
            menuOption = sc.nextInt(); //*********************another scanner need to ask*********************//
            switch (menuOption) {
                case 1:
                    Geo geo = new Geo();
                    System.out.println("Your choice is Option 1: DATA");
                    System.out.println("");
                    System.out.println("**************************************************************************************");
                    System.out.println("                        HERE ARE THE CONTINENTS:                        ");

                    String[] continentList = geo.listOfRegion(csvData, "continent");
                    int n = 1;
                    for (String i : continentList) {
                        System.out.printf("\t %s", i);
                        n++;
                    }
                    System.out.println("\n**************************************************************************************");

                    System.out.print("\n\nPLEASE WRITE THE CONTINENT NAME:  \n\n");
                    String continent = fileOption.nextLine().trim();
                    continent = csvData.printErrorWhenUserEnterWrong(continent, "continent");

                    //Let the user choose the country:
                    System.out.println("**************************************************************************************");
                    System.out.println("*                              Please choose the Country:                            *");
                    System.out.println("**************************************************************************************");
                    String[] countryList = geo.listOfRegion(csvData, "location");
                    n = 1;
                    for (String country : countryList) {
                        if (csvData.checkIfDataContainsNextData(continent,country)) {
                            System.out.printf("\t%d - %s\n", n, country);
                            n++;
                        }
                    }
                    //ask the user to enter the country name again
                    System.out.print("\n ENTER YOUR COUNTRY HERE: \n");
                    String country = fileOption.nextLine().trim();
                    country = csvData.printErrorWhenUserEnterWrong(country, "location");

                    geo = new Geo(continent, country);

                    String dataOption;
                    do {
                        System.out.println("**************************************************************************************");
                        System.out.println("|                                      DATA                                          |");
                        System.out.println("**************************************************************************************");
                        System.out.println("*********************** PLEASE CHOOSE OPTION YOU WANT TO APPLY ***********************");
                        System.out.println("|   1. Data -> Option [a]: Enter the start date and end date (inclusive)             |");
                        System.out.println("|   2. Data -> Option [b]: A number of days or weeks from a particular date          |");
                        System.out.println("|   2. Data -> Option [c]: A number of days or weeks to a particular date            |");
                        System.out.println("|   3. Data -> Option [d]: Back to Main Menu                                         |");
                        System.out.println("**************************************************************************************");
                        System.out.println("\n\n CHOOSE YOUR OPTION THROUGH  a, b, c, d");
                        dataOption = sc.next();
                        TimeRange arrOfTime = new TimeRange();
                        switch (dataOption) {
                            case "a":
                                System.out.println("\nENTER THE START DAY: (MM/DD/YYYY) ");
                                String start = fileOption.nextLine();
                                System.out.println("\nENTER THE END DAY:   (MM/DD/YYYY) ");
                                String end = fileOption.nextLine();
                                arrOfTime = new TimeRange(start, end);
                                break;
                            case "b":
                                System.out.print("""
                                        CHOOSE THE  OPTION WEEKS OR DAYS
                                        \ta---WEEKS
                                        \tb---DAYS
                                        >>>""");
                                String choice = fileOption.nextLine();
                                System.out.println("\nPLEASE ENTER THE RANGE:");
                                int range = Integer.parseInt(fileOption.nextLine());
                                System.out.println("\nENTER THE START DAY:\n");
                                start = fileOption.nextLine();
                                end = arrOfTime.plusDaysOrWeeks(choice, start, range);
                                arrOfTime = new TimeRange(start, end);
                                break;

                            case "c":

                                System.out.print("""
                                        CHOOSE THE  OPTION WEEKS OR DAYS
                                        \ta---WEEKS
                                        \tb---DAYS
                                        >>>\s""");
                                choice = fileOption.nextLine();
                                System.out.println("\nPLEASE ENTER THE RANGE");
                                range = Integer.parseInt(fileOption.nextLine());
                                System.out.println("\nENTER THE END DAY:");
                                end = fileOption.nextLine();
                                start = arrOfTime.minusDaysOrWeeks(choice, end, range);
                                arrOfTime = new TimeRange(start, end);
                                break;
                            case "d":
                                dataOption = "d";
                            default:
                                break;
                        }
                        database database = new database(geo, arrOfTime,csvData);
                        System.out.println(database);


                    } while (!dataOption.equals("d"));
                    break;

                case 4:
                    System.out.println("EXIT");
                    break;
                default:
                    System.out.println("INVALID OPTION !");
            }
        } while (menuOption != 4);

    }
}

