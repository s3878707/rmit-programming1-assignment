import CSVData.CSVdata;
import Data.*;
import Results.*;

import java.io.FileNotFoundException;
import java.util.*;
public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("COVID TRACKER");
        System.out.println("Please enter the file");
        String path = sc.nextLine();
        CSVdata csvData = new CSVdata();


        //Let the user choose a continent
        Geo geo = new Geo();
        System.out.println("Choose the continent");
        String[] continentList = geo.listOfRegion(csvData, "continent");
        int n = 1;
        for (String i : continentList) {
            System.out.printf("\t%d - %s", n, i);
            n++;
        }
        System.out.print("\nWrite down the continent here >>>>>>>");
        String continent = sc.nextLine().trim();
        continent = csvData.printErrorWhenUserEnterWrong(continent, "continent");

        //Let the user choose the country
        System.out.println("Choose the country");
        System.out.println("BLAH BLAH BLAH");
        String[] countryList = geo.listOfRegion(csvData, "location");
        n = 1;
        for (String country : countryList) {
            if (csvData.checkIfDataContainsNextData(continent, country)) {
                System.out.printf("\t%d - %s\n", n, country);
                n++;
            }
        }
        System.out.print("\nWrite down the country here >>>>>>>");
        System.out.println("Quang");
        String country = sc.nextLine().trim();
        country = csvData.printErrorWhenUserEnterWrong(country, "location");

        geo = new Geo(continent, country);

        //Let the user choose date range
        int option;
        TimeRange arrOfTime = new TimeRange();
//        do {
        System.out.print("""
                Choose a format for date range:
                \t1 - Enter start day and end day (format : MM/dd/yyyy)
                \t2 - Number of days or weeks before end date, inclusive
                \t3 - Number of days or weeks after start date, inclusive
                \t4 - Back to menu
                >>>\s"""
        );
        option = Integer.parseInt(sc.nextLine());
        switch (option) {
            case 1:
                System.out.println("Enter start day");
                String start = sc.nextLine();
                System.out.println("Enter end day");
                String end = sc.nextLine();
                arrOfTime = new TimeRange(start, end);
                break;
            case 2:
                System.out.print("""
                        Choose days or weeks
                        \ta---Weeks
                        \tb---Days
                        >>>\s"""
                );
                String choice = sc.nextLine();
                System.out.println("Enter the range");
                int range = Integer.parseInt(sc.nextLine());
                System.out.println("Enter the end day");
                end = sc.nextLine();
                start = arrOfTime.minusDaysOrWeeks(choice, end, range);
                arrOfTime = new TimeRange(start, end);
                break;
            case 3:
                System.out.print("""
                        Choose days or weeks
                        \ta---Weeks
                        \tb---Days
                        >>>\s"""
                );
                choice = sc.nextLine();
                System.out.println("Enter the range");
                range = Integer.parseInt(sc.nextLine());
                System.out.println("Enter the start day");
                start = sc.nextLine();
                end = arrOfTime.plusDaysOrWeeks(choice, start, range);
                arrOfTime = new TimeRange(start, end);
                break;
            case 4:
        }
//        } while (option != 4) ;
        Database database = new Database(geo, arrOfTime, csvData);
//        System.out.println(database);
        Group gr = new Group(database,"b");
        for (ArrayList<Data> i : gr.getGrouping()){
            System.out.println(i);
        }
    }
}


