package Data;

import CSVData.CSVdata;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TimeRange {
    Scanner sc = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private ArrayList<String> listOfDate = new ArrayList<>();

    public TimeRange() {
    }

    /**
     * Option a : Enter start day and end day
     * Option b :  Number of days or weeks after start date
     * Option c : Number of days or weeks before end date
     *
     * @param option
     */
    public TimeRange(Geo geo, String option) throws FileNotFoundException {

        // ENTER A START DAY AND END DAY
        if (option.equals("a")) {
            // Enter start date
            System.out.print("Enter the start date (format: MM/DD/YYYY) >>> ");
            String start = sc.next();
            start = checkIfDateIsFollowedTheFormat(start);
            while (checkIfDateIsAvailableInCsv(geo, start)) {
                System.out.print("The date has not been updated. Please choose again (format: MM/DD/YYYY) >>>");
                start = checkIfDateIsFollowedTheFormat(sc.next());
            }
            // Enter end date
            System.out.print("Enter the end date (format: MM/DD/YYYY) >>> ");
            String end = sc.next();
            end = checkIfDateIsFollowedTheFormat(end);
            while (checkIfDateIsAvailableInCsv(geo, end)) {
                System.out.print("The date has not been updated. Please choose again (format: MM/DD/YYYY) >>>");
                end = checkIfDateIsFollowedTheFormat(sc.next());
            }
            listOfDate = executeDate(start, end);
        }

        //A NUMBER OF DAYS OR WEEKS FROM A PARTICULAR DATE
        if (option.equals("b")) {
            // Chooe option for range
            System.out.print("""
                    choose the type of range:
                    \tOption a: Days
                    \tOption b: Weeks
                    >>>""");
            String choice = sc.nextLine();
            while (!choice.equals("a") && !choice.equals("b")) {
                System.out.print("Please enter the valid option >>>");
                choice = sc.next();
            }
            // Enter start date
            System.out.print("Enter the start date (format: MM/DD/YYYY) >>> ");
            String start = sc.next();
            start = checkIfDateIsFollowedTheFormat(start);
            while (checkIfDateIsAvailableInCsv(geo, start)) {
                System.out.print("The date has not been updated. Please choose again (format: MM/DD/YYYY) >>>");
                start = sc.next();
                start = checkIfDateIsFollowedTheFormat(start);
            }
            // Enter the range
            System.out.print("Please enter the range >>>");
            int range = checkValidRange(sc.next());
            // Check if the date after calculating is available in CSV file
            while (checkIfDateIsAvailableInCsv(geo, plusDaysOrWeeks(choice,start,range))) {
                System.out.print("Your latter data has not been updated. Please enter range again >>>");
                range = checkValidRange(sc.next());
            }
            String end = plusDaysOrWeeks(choice, start, range);
            listOfDate = executeDate(start, end);
        }

        //A NUMBER OF DAYS OR WEEKS TO A PARTICULAR DATE
        if (option.equals("c")) {
            // Choose option for range
            System.out.print("""
                    choose the type of range:
                    \tOption a: Days
                    \tOption b: Weeks
                    >>>""");
            String choice = sc.nextLine();
            while (!choice.equals("a") && !choice.equals("b")) {
                System.out.print("Please enter the valid option >>>>>");
                choice = sc.next();
            }
            // Enter the end date
            System.out.print("Enter the end date (format: MM/DD/YYYY) >>> ");
            String end = sc.next();
            end = checkIfDateIsFollowedTheFormat(end);
            while (checkIfDateIsAvailableInCsv(geo, end)) {
                System.out.print("The date has not been updated. Please choose again (format: MM/DD/YYYY) >>>");
                end = sc.next();
                end = checkIfDateIsFollowedTheFormat(end);
            }
            // Enter the range
            System.out.print("Please enter the range >>>");
            int range = checkValidRange(sc.next());
            // Check if the date after calculating is available in CSV file
            while (checkIfDateIsAvailableInCsv(geo, minusDaysOrWeeks(choice,end,range))) {
                System.out.print("Your start date has not been updated. Please enter range again >>>");
                range = checkValidRange(sc.next());
            }
            String start = minusDaysOrWeeks(choice, end, range);
            listOfDate = executeDate(start, end);
        }
    }

    /**
     * Make  a string list of day when all conditions are met
     * @param start
     * @param end
     * @return
     */
    private ArrayList<String> executeDate(String start, String end) {
        ArrayList<String> listOfDate = new ArrayList<>();
        LocalDate startDay = LocalDate.parse(start, formatter);
        LocalDate endDay = LocalDate.parse(end, formatter);
        // check if start day is before end day
        if (startDay.isAfter(endDay)){
            LocalDate trans = startDay;
            startDay = endDay;
            endDay = trans;
        }
        for (LocalDate d = startDay; !d.isAfter(endDay); d = d.plusDays(1)) {
            String b = d.format(formatter);
            b = makeDateSimilarToDateInCSV(b);
            listOfDate.add(b);
        }
        return listOfDate;
    }

    /**
     * because date is formatted MM/dd/yyyy
     * make it looks like the day in the csv file which is benefits for storing data
     * @param date
     * @return
     */
    private String makeDateSimilarToDateInCSV(String date){
        // remove the 0 before days
        if (date.charAt(3)=='0'){
            date = date.substring(0,3) + date.substring(4);
        }
        // remove the 0 before month
        if (date.charAt(0)=='0') {
            date = date.replaceFirst("0","");
        }
        return date;
    }

    /**
     * check if the date in a specific location that user choose is available in csv file
     * @param geo
     * @param date
     * @return
     * @throws FileNotFoundException
     */
    private boolean checkIfDateIsAvailableInCsv(Geo geo, String date) throws FileNotFoundException{
        CSVdata data = new CSVdata();
        while (!data.checkIfDataContainsNextData(geo.getCountry(),makeDateSimilarToDateInCSV(date))){
            return true;
        }
        return false;
    }

    /**
     * Check if date user enter is followed the format
     * @param date
     * @return
     */
    private String checkIfDateIsFollowedTheFormat(String date) {
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        while (!date.matches(regex)){
            System.out.print("Please follow the format (format: MM/DD/YYYY) >>>");
            date = sc.next();
        }
        return date;
    }

    /**
     * check if user enter a number of range
     * @param range
     * @return
     */
    private int checkValidRange( String range) {
        while (!range.matches("\\d+")){
            System.out.print("Please enter a number >>>");
            range = sc.next();
        }
        int intRange = Integer.parseInt(range);
        return intRange;
    }

    /**
     *
     * @param choice
     * @param date
     * @param range
     * @return
     */
    private String minusDaysOrWeeks(String choice, String date, int range) {
        String stringDayAfterProcess = null;
        LocalDate day = LocalDate.parse(date, formatter);
            switch (choice) {
                case "a":
                    LocalDate dayAfterProcess = day.minusDays(range);
                    stringDayAfterProcess = dayAfterProcess.format(formatter);
                    break;
                case "b":
                    dayAfterProcess = day.minusWeeks(range);
                    stringDayAfterProcess = dayAfterProcess.format(formatter);
                break;
            }
        return stringDayAfterProcess;
    }
    private String plusDaysOrWeeks(String choice, String date, int range) {
        String stringDayAfterProcess = null;
        LocalDate day = LocalDate.parse(date, formatter);
            switch (choice) {
                case "a":
                    LocalDate dayAfterProcess = day.plusDays(range);
                    stringDayAfterProcess = dayAfterProcess.format(formatter);
                    break;
                case "b":
                    dayAfterProcess = day.plusWeeks(range);
                    stringDayAfterProcess = dayAfterProcess.format(formatter);
                    break;
            }
        return stringDayAfterProcess;
    }
    public ArrayList<String> getListOfDate(){
        return listOfDate;
    }
}