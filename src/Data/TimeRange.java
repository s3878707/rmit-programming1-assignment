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
    public TimeRange( int option) {
        if (option == 1) {
            System.out.println("\nENTER THE START DAY: (MM/DD/YYYY) ");
            String start = sc.nextLine();
            LocalDate startDay = convertStringToLocaleDate(start);
            System.out.println("\nENTER THE END DAY:   (MM/DD/YYYY) ");
            String end = sc.nextLine();
            LocalDate endDay = convertStringToLocaleDate(end);
            listOfDate = executeDate(startDay, endDay);
        }
        if (option == 2) {
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
        if (option == 3) {
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
    private ArrayList<String> executeDate(LocalDate startDay, LocalDate endDay) {
        ArrayList<String> listOfDate = new ArrayList<>();
        if (startDay.isAfter(endDay)){
            LocalDate trans = startDay;
            startDay = endDay;
            endDay = trans;
        }
        for (LocalDate d = startDay; !d.isAfter(endDay); d = d.plusDays(1)) {
            String b = d.format(formatter);
            if (b.charAt(3)=='0'){
                b = b.substring(0,3) + b.substring(4);
            }
            if (b.charAt(0)=='0') {
                b = b.replaceFirst("0","");
            }
            listOfDate.add(b);
        }
        return listOfDate;
    }

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