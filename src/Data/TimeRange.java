package Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TimeRange {
    Scanner sc = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private ArrayList<String> listOfDate = new ArrayList<>();
    public TimeRange(){};

    /**
     * Option a : Enter start day and end day
     * Option b :  Number of days or weeks after start date
     * Option c : Number of days or weeks before end date
     * @param option
     */
    public TimeRange( String option) {
        // Option a
        if (option.equals("a")) {
            System.out.println("\nENTER THE START DAY: (MM/DD/YYYY) ");
            String start = sc.nextLine();
            LocalDate startDay = convertStringToLocaleDate(start);
            System.out.println("\nENTER THE END DAY:   (MM/DD/YYYY) ");
            String end = sc.nextLine();
            LocalDate endDay = convertStringToLocaleDate(end);
            listOfDate = executeDate(startDay, endDay);
        }
        //Option b
        if (option.equals("b")) {
            System.out.print("""
                    CHOOSE THE  OPTION WEEKS OR DAYS
                    \ta---WEEKS
                    \tb---DAYS
                    >>>""");
            String choice = sc.nextLine();
            while (!choice.equals("a")&&!choice.equals("b")) {
                System.out.println("PLEASE ENTER THE VALID OPTION");
                choice = sc.nextLine();
            }
            System.out.println("\nENTER THE START DAY:\n");
            String start = sc.nextLine();
            LocalDate startDay = convertStringToLocaleDate(start);
            System.out.println("\nPLEASE ENTER THE RANGE:");
            int range = Integer.parseInt(sc.nextLine());
            String end = plusDaysOrWeeks(choice, startDay, range);
            LocalDate endDay = convertStringToLocaleDate(end);
            listOfDate = executeDate(startDay, endDay);
        }
        //Option c
        if (option.equals("c")) {
            System.out.print("""
                    CHOOSE THE  OPTION WEEKS OR DAYS
                    \ta---WEEKS
                    \tb---DAYS
                    >>>""");
            String choice = sc.nextLine();
            while (!choice.equals("a")&&!choice.equals("b")) {
                System.out.println("PLEASE ENTER THE VALID OPTION");
                choice = sc.nextLine();
            }
            System.out.println("\nENTER THE END DAY:\n");
            String end = sc.nextLine();
            LocalDate endDay = convertStringToLocaleDate(end);
            System.out.println("\nPLEASE ENTER THE RANGE:");
            int range = Integer.parseInt(sc.nextLine());
            String start = minusDaysOrWeeks(choice, endDay, range);
            LocalDate startDay = convertStringToLocaleDate(start);
            listOfDate = executeDate(startDay, endDay);
        }
    }

    /**
     * Make  a string list of day when all conditions are met
     * @param startDay
     * @param endDay
     * @return
     */
    private ArrayList<String> executeDate(LocalDate startDay, LocalDate endDay) {
        ArrayList<String> listOfDate = new ArrayList<>();
        // check if start day is before end day
        if (startDay.isAfter(endDay)){
            LocalDate trans = startDay;
            startDay = endDay;
            endDay = trans;
        }
        for (LocalDate d = startDay; !d.isAfter(endDay); d = d.plusDays(1)) {
            // because LocalDate is formatted MM/dd/yyyy
            // make it looks like the day in the csv file which is benefits for storing data
            String b = d.format(formatter);
            // remove the 0 before day
            if (b.charAt(3)=='0'){
                b = b.substring(0,3) + b.substring(4);
            }
            // remove the 0 before month
            if (b.charAt(0)=='0') {
                b = b.replaceFirst("0","");
            }
            listOfDate.add(b);
        }
        return listOfDate;
    }

    /**
     * This method will convert String type to LocalDate type in order to calculate which related to time
     * @param date
     * @return
     */
    private LocalDate convertStringToLocaleDate(String date){
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        while (!date.matches(regex)) {
            System.out.println("PLEASE ENTER DATE AGAIN");
            date = sc.nextLine();
        }
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
    private String minusDaysOrWeeks(String choice, LocalDate day, int range) {
        String stringDayAfterProcess = new String();
            switch (choice) {
                case "a":
                    LocalDate dayAfterProcess = day.minusWeeks(range);
                    stringDayAfterProcess = dayAfterProcess.format(formatter);
                    break;
                case "b":
                    dayAfterProcess = day.minusDays(range);
                    stringDayAfterProcess = dayAfterProcess.format(formatter);
                    break;
            }
        return stringDayAfterProcess;
    }
    private String plusDaysOrWeeks(String choice, LocalDate day, int range) {
        String afterProcess = new String();
            switch (choice) {
                case "a":
                    LocalDate dayAfterProcess = day.plusWeeks(range);
                    afterProcess = dayAfterProcess.format(formatter);
                    break;
                case "b":
                    dayAfterProcess = day.plusDays(range);
                    afterProcess = dayAfterProcess.format(formatter);
                    break;
            }
        return afterProcess;
    }
    public ArrayList<String> getListOfDate(){
        return listOfDate;
    }
}