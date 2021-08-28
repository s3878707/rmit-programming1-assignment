package Summary;

import Data.*;

import java.util.ArrayList;
import java.util.Scanner;
public class Group {
    Scanner sc = new Scanner(System.in);
    private ArrayList<ArrayList<Data>> grouping = new ArrayList<>();
    public Group (){}
    public Group(Database data, String option) {
        ArrayList<Data> listData = data.getSelectedData();
        int size = listData.size();
        if (option.equals("a"))
            return;
        if (option.equals("b")){
            System.out.println("Enter number of groups:");
            int numberOfGroup = sc.nextInt();
            int[] groupSize = new int[numberOfGroup];
            int i = 0;
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
            System.out.println("Please enter the day in a group");
            int numberOfDays = sc.nextInt();
            while (size % numberOfDays != 0){
                System.out.println("Please enter again");
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
    //    public String toString(){
//        return String.format("")
//    }
    public ArrayList<ArrayList<Data>> getGrouping(){
        return grouping;
    }
    public static void main(String[] args) {
    }
}