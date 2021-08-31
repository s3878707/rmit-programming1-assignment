import CSVData.CSVdata;
import Data.Data;
import Data.Database;
import Data.Geo;
import Data.TimeRange;
import Summary.Group;
import Summary.Metric;
import Summary.Results;
import java.io.FileNotFoundException;

import java.util.*;
public class userInterface {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("COVID TRACKER");
        System.out.println("**************************************************************************************");
        CSVdata csvData = new CSVdata();
        Database database = new Database();
        Results results = new Results();
        Geo geo = new Geo();
        TimeRange arrOfTime = new TimeRange();
        Group gr = new Group();
        Metric metric = new Metric();

        String menuOption;
        System.out.print("DO YOU WANT TO CONTINUE ? (Y/N)");
        Scanner sc = new Scanner(System.in);
        menuOption = sc.nextLine();
        while (menuOption.equals("y") || menuOption.equals("Y")) {

//        if (menuOption.equals("n") || menuOption.equals("N")) {
//            System.out.println("EXIT ....");
//            return;
//        } else {
            String menuChoice;
            //Let the user choose the continents
            System.out.println("YOUR FIRST STEP IS 1: DATA\n");
            System.out.println("**************************************************************************************");
            System.out.println("                        HERE ARE THE CONTINENTS:                        ");

            ArrayList<String> continentList = geo.makeContinentList();
            int n = 1;
            for (String i : continentList) {
                System.out.printf("%3d - %-10s", n, i);
                n++;
            }
            System.out.println("\n**************************************************************************************");

            System.out.print("\n\nPLEASE WRITE THE CONTINENT NAME:  \n\n");
            String continent = sc.nextLine().trim();
            continent = geo.printErrorWhenUserEnterWrong(continent, "continent");

            //Let the user choose the country:
            System.out.println("**************************************************************************************");
            System.out.println("*                              Please choose the Country:                            *");
            System.out.println("**************************************************************************************");
            ArrayList<String> countryList = geo.makeLocationList();
            n = 1;
            for (String country : countryList) {
                if (csvData.checkIfDataContainsNextData(country, continent)) {
                    if (n % 3 != 0) {
                        System.out.printf("%3d - %s", n, String.format("%-40s", country));
                        n++;
                    } else {
                        System.out.printf("%3d - %s\n", n, String.format("%-40s", country));
                        n++;
                    }
                }
            }
            //ask the user to enter the country name again
            System.out.print("\n ENTER YOUR COUNTRY HERE: \n");
            String country = sc.nextLine().trim();
            country = geo.printErrorWhenUserEnterWrong(country, "location");

            geo = new Geo(continent, country);

            String dataOption;
            do {
                System.out.println("");
                System.out.println("                                     DATE RANGE                                       ");
                System.out.println("**************************************************************************************");
                System.out.println("*********************** PLEASE CHOOSE OPTION YOU WANT TO APPLY ***********************");
                System.out.println("|   1. Data -> Option [a]: Enter the start date and end date (inclusive)             |");
                System.out.println("|   2. Data -> Option [b]: A number of days or weeks from a particular date          |");
                System.out.println("|   3. Data -> Option [c]: A number of days or weeks to a particular date            |");
                System.out.println("**************************************************************************************");
                System.out.println("\n\n CHOOSE YOUR OPTION THROUGH  a, b, c");
                dataOption = sc.next();
                arrOfTime = new TimeRange(dataOption);
            } while (!dataOption.equals("a") && !dataOption.equals("b") && !dataOption.equals("c"));
            System.out.println("DATA IS RECORDED SUCCESSFULLY, PLEASE CONTINUE WITH STEP 2");
            database = new Database(geo, arrOfTime);
            //            System.out.println("YOUR SECOND STEP IS 2:SUMMARY:");
            //            System.out.println("|                                    SUMMARY                                         |");
            //            System.out.println("**************************************************************************************");
            //            System.out.println("|   1. SUM -> STEP 1 : GROUPING DATA                                              |");
            //            System.out.println("|   2. SUM -> STEP 2 : METRIC                                                     |");
            //            System.out.println("|   3. SUM -> STEP 3 : RESULT                                                     |");
            //            System.out.println("**************************************************************************************");
            //            System.out.println("PRESS 1 TO CONTINUE");

            //Let the user choose option to devide group
            System.out.println("*******************GROUPING DATA*******************");
            String groupingOp;
            do {
                System.out.println("");
                System.out.println("|                                 SUMMARY GROUPING DATA                              |");
                System.out.println("**************************************************************************************");
                System.out.println("|   1. GROUPING DATA -> OPTION [a]: NO GROUPING                                      |");
                System.out.println("|   2. GROUPING DATA -> OPTION [b]: NUMBER OF GROUPS                                 |");
                System.out.println("|   3. GROUPING DATA -> OPTION [c]: NUMBER OF DAYS                                   |");
                System.out.println("**************************************************************************************");
                System.out.println("CHOOSE YOUR OPTION THROUGH a,b,c");
                groupingOp = sc.next();
                gr = new Group(database, groupingOp);
            } while (!groupingOp.equals("a") && !groupingOp.equals("b") && !groupingOp.equals("c"));

            //Let the user choose the metric
            System.out.println("*******************METRIC*******************");
            String metOp;
            do {
                System.out.println("");
                System.out.println("|                                 SUMMARY METRIC                                     |");
                System.out.println("**************************************************************************************");
                System.out.println("|   1. METRIC -> OPTION [a]: POSITIVE CASES                                          |");
                System.out.println("|   2. METRIC -> OPTION [b]: DEATHS                                                  |");
                System.out.println("|   3. METRIC -> OPTION [c]: VACCINATED PEOPLE                                       |");
                System.out.println("**************************************************************************************");
                System.out.println("CHOOSE YOUR OPTION THROUGH a, b, c");
                metOp = sc.next();
                metric = new Metric(gr, metOp);
            } while (!metOp.equals("a") && !metOp.equals("b") && !metOp.equals("c"));

            //Let the user choose the results want to display
            System.out.println("*******************RESULT*******************");
            String reOp;
            do {
                System.out.println("");
                System.out.println("|                                 SUMMARY RESULT                                     |");
                System.out.println("**************************************************************************************");
                System.out.println("|   1. RESULT -> OPTION [a]: NEW TOTAL                                               |");
                System.out.println("|   2. RESULT -> OPTION [b]: UP TO                                                   |");
                System.out.println("|   3. RESULT -> OPTION [c]: BACK TO THE PROCESS                                     |");
                System.out.println("**************************************************************************************");
                System.out.println("CHOOSE YOUR OPTION THROUGH a, b, c");
                reOp = sc.next();
                results = new Results(metric, reOp);
            } while (!reOp.equals("a") && !reOp.equals("b") && !reOp.equals("c"));
            System.out.println("CONTINUE ? (Y/N)");
            menuOption = sc.nextLine();
//        } while (!menuOption.equals("n") && !menuOption.equals("N"));
        }
    }
}


