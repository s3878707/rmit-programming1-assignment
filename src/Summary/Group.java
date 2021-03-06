package Summary;

import Data.*;

import java.util.ArrayList;
import java.util.Scanner;
public class Group {
    Scanner sc = new Scanner(System.in);
    private ArrayList<ArrayList<Data>> grouping = new ArrayList<>();
    public Group (){}

    /**

     * @param data a list of data that user has selected after entering location and time
     * @param option Option a : No grouping
     *       Option b : number of groups (divide data into that number of groups)
     *      Option c :  number of days (divide data into groups so that each group contains number of days)
     */
    public Group(Database data, String option) {
        ArrayList<Data> listData = data.getSelectedData();
        int size = listData.size();
        // no grouping
        if (option.equals("a"))
            for (Data i : listData){
                ArrayList<Data> groupContainOneData = new ArrayList<>();
                groupContainOneData.add(i);
                grouping.add(groupContainOneData);
            }
        // number of groups
        if (option.equals("b")){
            System.out.print("Enter number of group >>>");
            int numberOfGroup = checkValidNumber(sc.next());
            while (numberOfGroup>listData.size()) {
                System.out.print("The number of groups must smaller than the number of data >>>");
                numberOfGroup = sc.nextInt();
            }
            int[] groupSize = new int[numberOfGroup];
            int i = 0;
            // Make a list of integer which every integer stands for the maximum of that group can contains respectively
            // For example: [3,2,2] means first group has 3 data, second group has 2 data and so on
            while (size!=0){
                groupSize[i]+=1;
                i++;
                size -=1;
                if (i == numberOfGroup){
                    i = 0;
                }
            }
            int j = 0;
            for (int n = 0; n< numberOfGroup; n++ ){
                ArrayList<Data> l = new ArrayList<>();
                for (int k = 0; k < groupSize[n]; k++){
                    l.add(listData.get(j));
                    j++;
                }
                grouping.add(l);
            }
        }
        if (option.equals("c")){
            System.out.print("Enter the number of days in a group >>>");
            int numberOfDays = checkValidNumber(sc.next());
            while (size % numberOfDays != 0||numberOfDays > size){
                System.out.print("The data must be devided equally in ever group >>>>");
                numberOfDays = sc.nextInt();
            }
            int numberOfGroups = size/numberOfDays;
            int j =0;
            for (int n = 0; n< numberOfGroups; n++){
                ArrayList<Data> l = new ArrayList<>();
                for (int i = 0; i<numberOfDays; i++){
                    l.add(listData.get(j));
                    j++;
                }
                grouping.add(l);
            }
        }
    }
    private int checkValidNumber( String range) {
        while (!range.matches("\\d+")){
            System.out.print("Please enter a number >>>");
            range = sc.next();
        }
        int intNumber = Integer.parseInt(range);
        return intNumber;
    }
    public ArrayList<ArrayList<Data>> getGrouping(){
        return grouping;
    }
}