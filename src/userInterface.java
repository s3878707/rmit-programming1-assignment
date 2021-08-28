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
        Scanner fileOption = new Scanner(System.in);
        System.out.println("COVID TRACKER");
        System.out.println("PRESS ENTER TO CONTINUE");
        CSVdata csvData = new CSVdata();
        Database database = new Database();
        Geo geo = new Geo();
        TimeRange arrOfTime = new TimeRange();
        Group gr = new Group();
        Metric metric = new Metric();
        //Let the user choose the continent
//        public static void mainMenu() {
        int menuOption;
        do {
            System.out.println("**************************");
            System.out.println("|     WORKING PROCESS     |");
            System.out.println("|    1.STEP [1]: DATA     |");
            System.out.println("|    2.STEP [2]: SUMMARY  |");
            System.out.println("|    3.STEP [3]: DISPLAY  |");
            System.out.println("|    4.OPTION[4]: EXIT    |");
            System.out.println("***************************");
            Scanner sc = new Scanner(System.in);
            System.out.print("PLEASE FOLLOW THE STEP:");
            menuOption = sc.nextInt();
            if (menuOption == 4) {
                return;
            } else {

                //*********************another scanner need to ask*********************//
//            switch (menuOption) {
//                case 1:
                System.out.println("YOUR FIRST STEP IS 1: DATA\n");
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
                    if (csvData.checkIfDataContainsNextData(continent, country)) {
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
                    System.out.println("");
                    System.out.println("                                       DATA                                           ");
                    System.out.println("**************************************************************************************");
                    System.out.println("*********************** PLEASE CHOOSE OPTION YOU WANT TO APPLY ***********************");
                    System.out.println("|   1. Data -> Option [a]: Enter the start date and end date (inclusive)             |");
                    System.out.println("|   2. Data -> Option [b]: A number of days or weeks from a particular date          |");
                    System.out.println("|   3. Data -> Option [c]: A number of days or weeks to a particular date            |");
//                    System.out.println("|   4. Data -> Option [d]: Back to the main menu                                     |");
                    System.out.println("**************************************************************************************");
                    System.out.println("\n\n CHOOSE YOUR OPTION THROUGH  a, b, c, d");
                    dataOption = sc.next();

                    switch (dataOption) {
                        case "a":
                            System.out.println("\nENTER THE START DAY: (MM/DD/YYYY) ");
                            String start = fileOption.nextLine();
                            System.out.println("\nENTER THE END DAY:   (MM/DD/YYYY) ");
                            String end = fileOption.nextLine();
                            arrOfTime = new TimeRange(start, end);
                            dataOption = "d";
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
                            dataOption = "d";
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
                            dataOption = "d";
                            break;
//                        case "d":
//                            menuOption = 4;
//                        default:
//                            break;
                    }
                } while (!dataOption.equals("d"));
                System.out.println("DATA IS RECORDED SUCCESSFULLY, PLEASE CONTINUE WITH STEP 2");
                database = new Database(geo, arrOfTime, csvData);
                System.out.println("YOUR SECOND STEP IS 2:SUMMARY:");
                String summOption;

                do {
                    System.out.println("|                                    SUMMARY                                         |");
                    System.out.println("**************************************************************************************");
                    System.out.println("*********************** PLEASE CHOOSE OPTION YOU WANT TO APPLY ***********************");
                    System.out.println("|   1. SUM -> STEP 1 [a]: GROUPING DATA                                              |");
                    System.out.println("|   2. SUM -> STEP 2 [b]: METRIC                                                     |");
                    System.out.println("|   3. SUM -> STEP 3 [c]: RESULT                                                     |");
//                    System.out.println("|   4. SUM -> STEP 4 [d]: BACK TO PROGRESS                                           |");
                    System.out.println("**************************************************************************************");
                    System.out.println("\n\n CHOOSE YOUR OPTION THROUGH  a, b, c, d");

                    summOption = fileOption.next();
//                    switch (summOption) {
//                        case "a":
                            System.out.println("YOUR CHOICE IS [a]: GROUPING DATA ");
                            String groupingOp;
                            do {
                                System.out.println("");
                                System.out.println("|                                 SUMMARY GROUPING DATA                              |");
                                System.out.println("**************************************************************************************");
                                System.out.println("|   1. GROUPING DATA -> OPTION [1]: NO GROUPING                                      |");
                                System.out.println("|   2. GROUPING DATA -> OPTION [2]: NUMBER OF GROUPS                                 |");
                                System.out.println("|   3. GROUPING DATA -> OPTION [3]: NUMBER OF DAYS                                   |");
//                                System.out.println("|   4. GROUPING DATA -> OPTION [4]: BACK TO THE PROCESS                              |");
                                System.out.println("**************************************************************************************");
                                System.out.println("CHOOSE YOUR OPTION THROUGH 1, 2, 3, 4");
                                groupingOp = fileOption.next();
                                switch (groupingOp) {
                                    case "1":
                                        System.out.println("NO GROUPING:");
                                        gr = new Group(database, "a");
                                        for (ArrayList<Data> i : gr.getGrouping()) {
                                            System.out.println(i);
                                        }
                                        groupingOp = "4";
                                        break;
                                    case "2":
                                        System.out.println("NUMBER OF GROUPS:");
                                        gr = new Group(database, "b");
                                        for (ArrayList<Data> i : gr.getGrouping()) {
                                            System.out.println(i);
                                        }
                                        groupingOp = "4";
                                        break;
                                    case "3":
                                        System.out.println("NUMBER OF DAYS");
                                        gr = new Group(database, "c");
                                        for (ArrayList<Data> i : gr.getGrouping()) {
                                            System.out.println(i);
                                        }
                                        groupingOp = "4";
                                        break;
                                    case "4":
                                        groupingOp = "4";
                                        break;
                                    default:
                                        System.out.println("UNAVAILABLE");
                                        break;
                                }
                            } while (!groupingOp.equals("4"));
//                            break;
//                        case "b":
                            System.out.println("METRIC");
                            String metOp;
                            do {
                                System.out.println("");
                                System.out.println("|                                 SUMMARY METRIC                                     |");
                                System.out.println("**************************************************************************************");
                                System.out.println("|   1. METRIC -> OPTION [1]: POSITIVE CASES                                          |");
                                System.out.println("|   2. METRIC -> OPTION [2]: DEATHS                                                  |");
                                System.out.println("|   3. METRIC -> OPTION [3]: VACCINATED PEOPLE                                       |");
//                                System.out.println("|   4. METRIC -> OPTION [4]: BACK TO THE PROCESS                                     |");
                                System.out.println("**************************************************************************************");
                                System.out.println("CHOOSE YOUR OPTION THROUGH 1, 2, 3, 4");
                                metOp = fileOption.next();
                                switch (metOp) {
                                    case "1":
                                        System.out.println("POSITIVE CASES");
                                        metric = new Metric(gr, "a");
                                        for (ArrayList<Integer> i : metric.getMetricListForUpTo()) {
                                            System.out.println(i);
                                        }
                                        metOp = "4";
                                        break;
                                    case "2":
                                        System.out.println("DEATHS");
                                        metric = new Metric(gr, "b");
                                        for (ArrayList<Integer> i : metric.getMetricListForUpTo()) {
                                            System.out.println(i);
                                        }
                                        metOp = "4";
                                        break;
                                    case "3":
                                        System.out.println("VACCINATED PEOPLE");
                                        metric = new Metric(gr, "c");
                                        for (ArrayList<Integer> i : metric.getMetricListForUpTo()) {
                                            System.out.println(i);
                                        }
                                        metOp = "4";
                                        break;
//                                    case "4":
//                                        metOp = "4";
//                                        break;
//                                    default:
//                                        System.out.println("UNAVAILABLE");
//                                        break;
                                }
                            } while (!metOp.equals("4"));
//                            break;
//                        case "c":
                            System.out.println("RESULT");
                            String reOp;
                            do {
                                System.out.println("");
                                System.out.println("|                                 SUMMARY RESULT                                     |");
                                System.out.println("**************************************************************************************");
                                System.out.println("|   1. RESULT -> OPTION [1]: NEW TOTAL                                               |");
                                System.out.println("|   2. RESULT -> OPTION [2]: UP TO                                                   |");
                                System.out.println("|   3. RESULT -> OPTION [3]: BACK TO THE PROCESS                                     |");
                                System.out.println("**************************************************************************************");
                                System.out.println("CHOOSE YOUR OPTION THROUGH 1, 2, 3, 4");
                                reOp = fileOption.next();
                                switch (reOp) {
                                    case "1":
                                        System.out.println("NEW TOTAL");
                                        Results results = new Results(metric, "a", gr);
                                        for (Integer i : results.getResults()) {
                                            System.out.println(i);
                                        }
                                        break;
                                    case "2":
                                        System.out.println("UP TO");
                                        results = new Results(metric, "b", gr);
                                        for (Integer i : results.getResults()) {
                                            System.out.println(i);
                                        }
                                        break;
                                    case "3":
                                        reOp = "4";
                                        break;
                                    default:
                                        System.out.println("UNAVAILABLE");
                                        break;
                                }
                            } while (!reOp.equals("4"));
                            break;

                } while (!summOption.equals("d"));
//                case 4:
//                    System.out.println("EXIT");
//                    break;
//                default:
//                    System.out.println("INVALID OPTION !");
            }
        }
        while (menuOption != 4);


    }
}

