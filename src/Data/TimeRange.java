package Data;

import CSVData.CSVdata;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.Scanner;

public class TimeRange {
    Scanner sc = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private ArrayList<String> listOfDate = new ArrayList<>();
    public TimeRange(){}

    /**
     * Option a : Enter start day and end day
     * Option b :  Number of days or weeks after start date
     * Option c : Number of days or weeks before end date
     * @param option
     */
    public TimeRange( Geo geo, String option) throws FileNotFoundException{
        // Option a
        if (option.equals("a")) {
            System.out.print("Enter the start day (format: MM/DD/YYYY) : ");
            String start = sc.next();
            LocalDate startDay = convertStringToLocalDate(geo,start);
            System.out.print("Enter the end day (format: MM/DD/YYYY) : ");
            String end = sc.next();
            LocalDate endDay = convertStringToLocalDate(geo,end);
            listOfDate = executeDate(startDay, endDay);
        }
        //Option b : Choose a range from the date
        if (option.equals("b")) {
            System.out.print("""
                    choose the type of range:
                    \tOption a: Weeks
                    \tOption b: Days
                    >>>""");
            String choice = sc.nextLine();
            while (!choice.equals("a")&&!choice.equals("b")) {
                System.out.print("Please enter the valid option >>>>>");
                choice = sc.next();
            }
            System.out.print("Enter the start day (format: MM/DD/YYYY) : ");
            String start = sc.next();
            LocalDate startDay = convertStringToLocalDate(geo, start);
            System.out.print("Please enter the range :");
            String rangeInString = sc.next();
            while (!rangeInString.matches("\\d+")){
                System.out.print("Please enter a number >>>");
                rangeInString = sc.next();
            }
            int range = Integer.parseInt(rangeInString);
            String end = plusDaysOrWeeks(choice, startDay, range);
            LocalDate endDay = convertStringToLocalDate(geo, end);
            listOfDate = executeDate(startDay, endDay);
        }
        //Option c : Choose a range to a date
        if (option.equals("c")) {
            System.out.print("""
                    choose the type of range:
                    \tOption a: Weeks
                    \tOption b: Days
                    >>>""");
            String choice = sc.nextLine();
            while (!choice.equals("a")&&!choice.equals("b")) {
                System.out.print("Please enter the valid option >>>>>");
                choice = sc.next();
            }
            System.out.print("Enter the end day (format: MM/DD/YYYY) : ");
            String end = sc.next();
            LocalDate endDay = convertStringToLocalDate(geo, end);
            System.out.print("Please enter the range :");
            String rangeInString = sc.next();
            int range = Integer.parseInt(rangeInString);
            while (true){
                if(!rangeInString.matches("\\d+")) {
                    System.out.print("Please enter a number >>>");
                    rangeInString = sc.next();
                    range = Integer.parseInt(rangeInString);
                }else if (checkValidDate(geo,minusDaysOrWeeks(choice, endDay, range))) {
                    System.out.print("Your latter data has not been updated. Please enter range again >>>");
                    rangeInString = sc.next();
                    range = Integer.parseInt(rangeInString);
                }else {
                    break;
                }
            }
//            int range = Integer.parseInt(rangeInString);
            String start = minusDaysOrWeeks(choice, endDay, range);
            LocalDate startDay = convertStringToLocalDate(geo, start);
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
            // remove the 0 before day
            String b = d.format(formatter);
            makeDateSimilarToDateInCSV(b);
            listOfDate.add(b);
        }
        return listOfDate;
    }
    private String makeDateSimilarToDateInCSV(String date){
        if (date.charAt(3)=='0'){
            date = date.substring(0,3) + date.substring(4);
        }
        // remove the 0 before month
        if (date.charAt(0)=='0') {
            date = date.replaceFirst("0","");
        }
        return date;
    }
    private boolean checkValidDate(Geo geo, String date) throws FileNotFoundException{
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        CSVdata data = new CSVdata();
        if (!date.matches(regex)){
            return true;
        }else if (!data.checkIfDataContainsNextData(geo.getCountry(),makeDateSimilarToDateInCSV(date))){
            return true;
        }else {
            return false;
        }
    }
    /**
     * This method will convert String type to LocalDate type in order to calculate which related to time
     * @param date
     * @return
     */
    private LocalDate convertStringToLocalDate(Geo geo,String date) throws FileNotFoundException {
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        CSVdata data = new CSVdata();
        while (true) {
            if (!date.matches(regex)) {
                System.out.print("Please follow the format (format: MM/DD/YYYY) >>>");
                date = sc.next();
            }else if (!data.checkIfDataContainsNextData(geo.getCountry(),makeDateSimilarToDateInCSV(date))){
                System.out.print("Your day you found has not been updated. Please choose again >>>");
                date = sc.next();
            }else {
                break;
            }
        }
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
    private String minusDaysOrWeeks(String choice, LocalDate day, int range) {
        String stringDayAfterProcess = null;
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
        String afterProcess = null;
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