import CSVData.CSVdata;
import Data.*;
import Display.Display;
import Summary.*;

import java.io.FileNotFoundException;
import java.util.*;
public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("COVID TRACKER");
        System.out.println("Please enter the file");
        String path = sc.nextLine();
        CSVdata csvData = new CSVdata();
        String resultOptionString = null;


        //Let the user choose a continent
        Geo geo = new Geo();
        System.out.println("Choose the continent");
        ArrayList<String> continentList = geo.makeContinentList();
        int n = 1;
        for (String i : continentList) {
            System.out.printf("%3d - %-10s", n, i);
            n++;
        }
        System.out.print("\nWrite down the continent here >>>>>>>");
        String continent = sc.nextLine().trim();
        continent = geo.printErrorWhenUserEnterWrong(continent, "continent");

        //Let the user choose the country
        System.out.println("Choose the country");
        System.out.println("BLAH BLAH BLAH");
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
        System.out.print("\nWrite down the country here >>>>>>>");
        System.out.println("Quang");
        String country = sc.nextLine().trim();
        country = geo.printErrorWhenUserEnterWrong(country, "location");

        geo = new Geo(continent, country);

        //Let the user choose date range
        int option;
        TimeRange arrOfTime = new TimeRange();
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

                    arrOfTime = new TimeRange(dataOption);
                    break;
                case "b":
                    arrOfTime = new TimeRange(dataOption);
                    break;
                case "c":
                    arrOfTime = new TimeRange(dataOption);
                    break;
                default:
                    System.out.println("PLEASE CHOOSE AGAIN");
            }
        } while (!dataOption.equals("a")&&!dataOption.equals("b")&&!dataOption.equals("c")) ;
        Database database = new Database(geo, arrOfTime);
//        System.out.println(database);
        Group gr = new Group(database,"b");
        for (ArrayList<Data> i : gr.getGrouping()){
            System.out.println(i);
        }
        Metric metric = new Metric(gr,"a");
        for (ArrayList<Integer> i : metric.getMetricList()){
            System.out.println(i);
        }

        Results result = new Results(metric,"a");
        for (Integer i : result.getResults()){
            System.out.println(i);
        }
        resultOptionString = "new_total";
        Display display = new Display();
        display.tableDisplay(result,metric,resultOptionString);

        ;
        System.out.println();

    }
}


