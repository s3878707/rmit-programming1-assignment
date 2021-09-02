//package Display;
//import java.util.*;
//import Summary.Group;
//import Summary.Metric;
//import Summary.Results;
//import Data.*;
//import java.util.ArrayList;
//public class Display {
//    public static void tabular(Group group, Metric metric, Results results) {
//
//        ArrayList<Integer> displayValue = query(results);
//
//        ArrayList<String> range = groupRange(metric);
//
//        interface tableInterface {
//            String cellWidthFirstCol(int width, String value);
//
//            String cellWidth(int width, String value);
//
//            String horizontalBorder(int width);
//
//            String horizontalBorderFirstCol(int width);
//        }
//        tableInterface table = new tableInterface() {
//            public String cellWidth(int width, String value) {
//                String cell = " ";
//                int padding = (width - value.length());
//                cell = " " + value + cell.repeat(padding - 1) + "|";
//                return cell;
//            }
//
//            public String cellWidthFirstCol(int width, String value) {
//                String cell = cellWidth(width, value);
//                cell = "|" + cell;
//                return cell;
//            }
//
//            public String horizontalBorder(int width) {
//                String border = "-";
//                border = border.repeat(width);
//                border += "+";
//                return border;
//            }
//
//            public String horizontalBorderFirstCol(int width) {
//                String border = horizontalBorder(width);
//                border = "+" + border;
//                return border;
//            }
//        };
//
//        int rangeColWidth = 35;
//        int valueColWidth = 15;
//
//        System.out.println(
//                table.horizontalBorderFirstCol(rangeColWidth) + table.horizontalBorder(valueColWidth));
//        System.out.println(
//                table.cellWidthFirstCol(rangeColWidth, "Range") + table.cellWidth(valueColWidth, "Value"));
//        System.out.println(
//                table.horizontalBorderFirstCol(rangeColWidth) + table.horizontalBorder(valueColWidth));
//
//        for (int i = 0; i < range.size(); i++) {
//            System.out.println(
//                    table.cellWidthFirstCol(rangeColWidth, range.get(i)) + table.cellWidth(valueColWidth,
//                            displayValue.get(i).toString()));
//        }
//        System.out.println(
//                table.horizontalBorderFirstCol(rangeColWidth) + table.horizontalBorder(valueColWidth));
//
//
//    }
//
//    public static void chart(Group group, Metric metric, Results results) {
//        int vertical = 24;
//        int horizontal = 80;
//        int spacing;
//        Character[][] displayChart = new Character[vertical][horizontal];
//        ArrayList<Integer> displayValue = query(results);
//        ArrayList<Integer> chartPositionValue = new ArrayList<>();
//
//        if (group.getGrouping().size() > 79) {
//            System.out.println("Cannot perform chart with more than 79 groups.");
//            return;
//        }
//
//        int max = 0;
//        int zeroCount = 0;
//        for (Integer i : displayValue) {
//            if (max < i) {
//                max = i;
//            }
//            if (i == 0) {
//                zeroCount += 1;
//            }
//        }
//
//        for (Integer value : displayValue) {
//            int position = vertical - 2;
//            if (zeroCount != displayValue.size()) {
//                position = Math.round((vertical - 2) - (((float) value / max)) * (vertical - 2));
//            }
//            chartPositionValue.add(position);
//        }
//
//        spacing = (int) Math.floor((float) (horizontal - 1) / displayValue.size());
//        for (int i = 0; i < vertical; i++) {
//            for (int j = 0; j < horizontal; j++) {
//                displayChart[i][j] = ' ';
//            }
//        }
//
//        int index = 0;
//        for (int i = 1; i <= chartPositionValue.size() * spacing; i += spacing) {
//            displayChart[chartPositionValue.get(index)][i] = '*';
//            index += 1;
//        }
//
//        for (int i = 0; i < vertical; i++) {
//            for (int j = 0; j < horizontal; j++) {
//                if (i == vertical - 1) {
//                    displayChart[i][j] = '_';
//                }
//                if (j == 0) {
//                    displayChart[i][j] = '|';
//                }
//            }
//        }
//        for (int i = 0; i < vertical; i++) {
//            for (int j = 0; j < horizontal; j++) {
//                if (j != horizontal - 1) {
//                    System.out.print(displayChart[i][j]);
//                } else {
//                    System.out.print(displayChart[i][j]);
//                }
//            }
//        }
//    }
//
//
//    public static ArrayList<Integer> query( Results results) {
//        ArrayList<Integer> displayValue = results.getResults();
//        return displayValue;
//    }
//    public static ArrayList<String> groupRange(Metric metric) {
//        ArrayList<String> listOfRange = new ArrayList<>();
//        for (ArrayList<String> group : metric.getDateList()) {
//            String startDay = group.get(0);
//            String endDay = group.get(group.size() - 1);
//            if (group.size() > 1) {
//                listOfRange.add(startDay + "-" + endDay);
//            } else {
//                listOfRange.add(startDay);
//            }
//        }
//        return listOfRange;
//    }
//}
package Display;
import Summary.Metric;
import Summary.Results;
import java.util.*;

public class Display {
    
    public void tabularDisplay(Results results, Metric metric, String resultType){
        ArrayList<String> rangeList = new ArrayList<>();
        if (resultType.equals("up_to")){
            for (ArrayList<String> i : metric.getDateListForUpTo()) {
                if (i.size() > 1) {
                    String labelFormat = i.get(0) + "-" + i.get(i.size() - 1);
                    rangeList.add(labelFormat);
                }else {
                    rangeList.add(i.get(0)+"\t\t");
                }
            }
            System.out.println();
            System.out.println("-".repeat(33));
            System.out.println("\t\t\tRange\t"+"\t|\s"+"Value");
            for (int i = 0; i < rangeList.size(); i++) {
                System.out.print("|\t");
                System.out.print(rangeList.get(i)+"\t|\t"+results.getResults().get(i));
                System.out.print("\t|");
                System.out.println();
            }
            System.out.println("-".repeat(33));
            System.out.println();

        }else if (resultType.equals("new_total")){
            for (ArrayList<String> i : metric.getDateList()) {
                if (i.size() > 1) {
                    String labelFormat = i.get(0) + "-" + i.get(i.size() - 1);
                    rangeList.add(labelFormat);
                } else {
                    rangeList.add(i.get(0)+"\t\t");
                }
            }
            System.out.println();
            System.out.println("-".repeat(33));
            System.out.println("\t\t\tRange\t"+"\t|\s"+"Value");
            System.out.println("-".repeat(33));

            for (int i = 0; i < rangeList.size(); i++) {
                System.out.print("|\t");
                System.out.print(rangeList.get(i)+"\t|\t"+results.getResults().get(i));
                System.out.print("\t|");
                System.out.println();
            }
            System.out.println("-".repeat(33));
            System.out.println();
        }
    }
    public void chartDisplay(Results results){
        long[] data = new long[results.getResults().size()];
        for (int i = 0 ; i < results.getResults().size();i++){
            data[i] = results.getResults().get(i);
        }
        long[] organizeData = bubbleSort(data, "value", "descending");
        long[] organizeIndex = {3,2,4,5,1,6};
        long powerCounter = 1;
        long groupNumber = organizeData.length;
        while (true){
            if (Math.pow(10,powerCounter)>=organizeData[0]){
                break;
            }else {
                powerCounter++;
            }
        }
        long groupIndex = 0;
        long spaceCounter = 0;
        long labelLength = 6;
        long totalSpace = spaceCounter*groupNumber+labelLength*groupNumber;
        while (totalSpace<=80){
            spaceCounter++;
            totalSpace = spaceCounter*groupNumber+labelLength*groupNumber;
        }
        spaceCounter--;
        for (int i = 0; i < 23; i++) {
            System.out.print("|");
            long tab_count=0;
            ArrayList<Long> indexInARow = new ArrayList<Long>();
            for (int j = 0; j < organizeData.length; j++) {
                int rowScale = (int) Math.ceil((23 * organizeData[j]) / (organizeData[0]+Math.pow(10, powerCounter-1)));
                if (rowScale==(23-i)){
                    indexInARow.add(bubbleSort(data, "index", "descending")[j]);
                }
            }
            long[] indexSort = new long[indexInARow.size()];
            for (int k = 0; k < indexInARow.size(); k++) {
                indexSort[k] = indexInARow.get(k);
            }
            indexSort = bubbleSort(indexSort, "value", "ascending");
            for (int k = 0; k < (spaceCounter+labelLength/2-1); k++) {
                System.out.print(" ");
            }
            int starInARow = 0;
            for (int p = 0; p < indexSort.length; p++) {
                while (tab_count<(indexSort[p]-1)*(spaceCounter+labelLength)-starInARow){
                    System.out.print(" ");
                    tab_count++;
                }
                System.out.print("*");
                starInARow++;
            }
            System.out.println();
        }
        System.out.print("|");
        for (int q = 0; q < 79; q++) {
            System.out.print("_");
        }
        System.out.println();
        for (int i = 1; i <= groupNumber; i+=1) {
            for (int j = 0; j < spaceCounter; j++) {
                System.out.print(" ");
            }
            System.out.print("group"+i);
        }
    }
    static long[] bubbleSort(long[] originalArray, String returnType, String sortOrder) {
        long n = originalArray.length;
        long[] arrayIndex = new long[originalArray.length];
        for (int i = 0; i < arrayIndex.length; i++) {
            arrayIndex[i] = i+1;
        }
        long[] sortedArray = originalArray.clone();
        long tempValue = 0;
        long tempIndex = 0;
        if (sortOrder.equals("descending")){
            for(int i=0; i < n; i++){
                for(int j=1; j < (n-i); j++){
                    if(sortedArray[j-1] < sortedArray[j]){
                        //swap elements and index
                        tempValue = sortedArray[j-1];
                        tempIndex = arrayIndex[j-1];
                        sortedArray[j-1] = sortedArray[j];
                        arrayIndex[j-1] = arrayIndex[j];
                        sortedArray[j] = tempValue;
                        arrayIndex[j] = tempIndex;
                    }
                }
            }
        }else if (sortOrder.equals("ascending")){
            for(int i=0; i < n; i++){
                for(int j=1; j < (n-i); j++){
                    if(sortedArray[j-1] > sortedArray[j]){
                        //swap elements and index
                        tempValue = sortedArray[j-1];
                        tempIndex = arrayIndex[j-1];
                        sortedArray[j-1] = sortedArray[j];
                        arrayIndex[j-1] = arrayIndex[j];
                        sortedArray[j] = tempValue;
                        arrayIndex[j] = tempIndex;
                    }
                }
            }
        }
        if (returnType.equals("value")){
            return sortedArray;
        }else if (returnType.equals("index")){
            return arrayIndex;
        }else{
            long[] invalidResult = {0,0,0,0,0,0};
            return invalidResult;
        }
    }
}
