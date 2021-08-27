package Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TimeRange {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private ArrayList<String> arr = new ArrayList<>();

    public TimeRange(String start, String end) {
        LocalDate startDay = convertStringToLocaleDate(start, startDayError());
        LocalDate endDay = convertStringToLocaleDate(end, endDayError());
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
            arr.add(b);
        }
    }
    public TimeRange(){};
   private static LocalDate convertStringToLocaleDate(String date, String Error) {
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        Scanner sc = new Scanner(System.in);
        while (!date.matches(regex)) {
            System.out.println(Error);
            date = sc.nextLine();
        }
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
    public String minusDaysOrWeeks(String choice, String day, int range) {
        LocalDate date = convertStringToLocaleDate(day, DayError());
        String stringDayAfterProcess = new String();
        switch (choice) {
            case "a":
                LocalDate dayAfterProcess = date.minusWeeks(range);
                stringDayAfterProcess = dayAfterProcess.format(formatter);
                break;
            case "b":
                dayAfterProcess = date.minusDays(range);
                stringDayAfterProcess = dayAfterProcess.format(formatter);
                break;
            default:
                System.out.println("Please enter the right choice");
                break;
        }
        return stringDayAfterProcess;
    }
    public String plusDaysOrWeeks(String choice, String day, int range) {
        LocalDate date = convertStringToLocaleDate(day, DayError());
        String afterProcess = new String();
        switch (choice) {
            case "a":
                LocalDate dayAfterProcess = date.plusWeeks(range);
                afterProcess = dayAfterProcess.format(formatter);
                break;
            case "b":
                dayAfterProcess = date.plusDays(range);
                afterProcess = dayAfterProcess.format(formatter);
                break;
            default:
                System.out.println("Please enter the right choice");
                break;
        }
        return afterProcess;
    }
    public static String DayError() {
        return "please enter day again";
    }
    public static String startDayError() {
        return "Please enter start day again:";
    }

    public static String endDayError() {
        return "Please enter end day again:";
    }
    public ArrayList<String> getArr(){
        return arr;
    }

    public static void main(String[] args) {

    }
}