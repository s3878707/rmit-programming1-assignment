import CSVData.CSVdata;
import Data.Data;
import Data.Database;
import Data.Geo;
import Data.TimeRange;
import Summary.Group;
import Summary.Metric;
import Summary.Results;
import java.io.FileNotFoundException;
import Display.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("-".repeat(86));
        System.out.println("|"+" ".repeat(35)+"COVID TRACKER"+" ".repeat(36)+"|");
        System.out.println("-".repeat(86));
        CSVdata csvData = new CSVdata();
        Database database;
        Results results;
        Geo geo = new Geo();
        TimeRange arrOfTime;
        Group gr;
        Metric metric;
        String menuOption;
        Display display = new Display();
        System.out.print("DO YOU WANT TO CONTINUE (Y/N) ? >>>");
        Scanner sc = new Scanner(System.in);
        menuOption = sc.next();
        while (menuOption.equals("y") || menuOption.equals("Y")) {
            System.out.println("RUNNING .....");
            System.out.println("-".repeat(31)+"WELCOME TO COVID TRACKER"+"-".repeat(31));
            /*----------------------Let the user choose the continents-----------------*/
            System.out.println("Choose a continent:");
            ArrayList<String> continentList = geo.makeContinentList();
            int n = 1;
            for (String i : continentList) {
                System.out.printf("%3d - %-10s", n, i);
                n++;
            }
            System.out.print("\nWrite the continent here >>>>>>");
            String continent = sc.next().trim();
            //ask the user to enter the country name again
            continent = geo.printErrorWhenUserEnterWrong(continent, "continent");

            /*--------------Let the user choose the country--------------------------*/
            System.out.println("-".repeat(107));
            System.out.println("|"+" ".repeat(48)+"Countries"+" ".repeat(48)+"|");
            System.out.println("-".repeat(107));
            ArrayList<String> countryList = geo.makeLocationList();
            n = 1;
            for (String country : countryList) {
                if (csvData.checkIfDataContainsNextData(country, continent)) {
                    if (n % 3 != 0) {
                        System.out.printf("%3d - %-40s", n, country);
                        n++;
                    } else {
                        System.out.printf("%3d - %-40s\n", n, country);
                        n++;
                    }
                }
            }
            System.out.print("\nWrite down the country here >>>>>>");
            String country = sc.next().trim();
            //ask the user to enter the country name again
            country = geo.printErrorWhenUserEnterWrong(country, "location");
            geo = new Geo(continent, country);

            /*---------------------Let the user enter the date range------------------*/
            String dataOption;
            System.out.println(" ".repeat(27)+"DATE RANGE"+" ".repeat(27));
            System.out.println("-".repeat(13)+"Please choose option you want to apply"+"-".repeat(13));
            System.out.println("| Option [a]: Enter the start date and end date (inclusive)    |");
            System.out.println("| Option [b]: A number of days or weeks from a particular date |");
            System.out.println("| Option [c]: A number of days or weeks to a particular date   |");
            System.out.println("-".repeat(64));
            do{
                System.out.print("Choose the option here >>>>>>");
                dataOption = sc.next();
                arrOfTime = new TimeRange(geo, dataOption);
            } while (!dataOption.equals("a") && !dataOption.equals("b") && !dataOption.equals("c"));
            System.out.print("Days:");
            //Print out date
            for (String i : arrOfTime.getListOfDate()){
                System.out.print("\t"+i);
            }
            database = new Database(geo, arrOfTime);

            /*---------------------Ask the user to choose the grouping type--------------------*/
            String groupingOp;
            System.out.println("\n"+" ".repeat(12)+"GROUPING"+" ".repeat(12));
            System.out.println("-".repeat(7)+"Choose your option"+"-".repeat(7));
            System.out.println("| Option [a]: No grouping      |");
            System.out.println("| Option [b]: Number of groups |");
            System.out.println("| Option [c]: Number of days   |");
            System.out.println("-".repeat(32));
            do {
                System.out.print("Choose the option here >>>>>>");
                groupingOp = sc.next();
                gr = new Group(database, groupingOp);
            } while (!groupingOp.equals("a") && !groupingOp.equals("b") && !groupingOp.equals("c"));
            //Print out data after being grouped
            System.out.println("Your data has after being grouped :");
            for (ArrayList<Data> i : gr.getGrouping()){
                System.out.print("\t"+i+"\n");
            }
            /*-----------------------Let the user choose the metric---------------------*/
            String metOp;
            System.out.println("\n"+" ".repeat(12)+"METRIC"+" ".repeat(12));
            System.out.println("-".repeat(6)+"Choose your option"+"-".repeat(6));
            System.out.println("| Option [a]: Positive Cases |");
            System.out.println("| Option [b]: Deaths         |");
            System.out.println("| Option [c]: New Vaccinated |");
            System.out.println("-".repeat(30));
            do {
                System.out.print("Choose the option here >>>>>>");
                metOp = sc.next();
                metric = new Metric(gr, metOp);
            } while (!metOp.equals("a") && !metOp.equals("b") && !metOp.equals("c"));
            //Print out the metrics
            System.out.println("Your number of metrics you have chosen :");
            for (ArrayList<Integer> i : metric.getMetricList()){
                System.out.print("\t"+i);
            }

            /*----------------------Let the user choose the results want to display--------------------*/
            String reOp;
            String resultType = null;
            System.out.println("\n"+" ".repeat(9)+"RESULT"+" ".repeat(9));
            System.out.println("-".repeat(3)+"Choose your option"+"-".repeat(3));
            System.out.println("| Option [a]: New total |");
            System.out.println("| Option [b]: Up to     |");
            System.out.println("-".repeat(25));
            do {
                System.out.print("Choose the option here >>>>>>");
                reOp = sc.next();
                results = new Results(metric, reOp);
                if (reOp.equals("a")){
                    resultType = "new_total";
                }else if (reOp.equals("b")){
                    resultType = ("up_to");
                }
            } while (!reOp.equals("a") && !reOp.equals("b"));
            //Print out the results
            System.out.print("Your results : ");
            for (int i : results.getResults()){
                System.out.print("\t"+i);
            }

            /*----------------------Let the user choose the results want to display--------------------*/
            String disOp;
            System.out.println("\n"+" ".repeat(8)+"DISPLAY"+" ".repeat(8));
            System.out.println("-".repeat(2)+"Choose your option"+"-".repeat(2));
            System.out.println("| Option [a]: Tabular |");
            System.out.println("| Option [b]: Chart   |");
            System.out.println("-".repeat(25));
            do {
                System.out.print("Choose the option here >>>>>>");
                disOp = sc.next();
                if (disOp.equals("a")){
                    display.tabularDisplay(results,metric,resultType);
                }else if (disOp.equals("b")){
                    display.chartDisplay(results);
                }
            } while (!disOp.equals("a") && !disOp.equals("b"));

            //Ask user want to exit again
            System.out.print("\nCONTINUE (Y/N) ? >>>");
            menuOption = sc.next();
        }
    }
}


