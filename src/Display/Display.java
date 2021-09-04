
package Display;
import Summary.Metric;
import Summary.Results;
import java.util.*;

public class Display {

    //create method tabular display//
    //Display the table with the existing metrics and results//
    public void tabularDisplay(Results results, Metric metric, String resultType){
        //---------------------setting the solution for up_to option------------------------//
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
            System.out.println("-".repeat(33));
            for (int i = 0; i < rangeList.size(); i++) {
                System.out.print("|\t");
                System.out.print(rangeList.get(i)+"\t|\t"+results.getResults().get(i));
                System.out.print(" ".repeat(3)+"|");
                System.out.println();
            }
            System.out.println("-".repeat(33));
            System.out.println();

            //---------------setting the solution for new_total option------------------//
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
                System.out.print(" ".repeat(3)+"|");
                System.out.println();
            }
            System.out.println("-".repeat(33));
            System.out.println();
        }
    }
    public void chartDisplay(Results results){
        //--display the chart with the chosen materials--//
        long[] data = new long[results.getResults().size()];
        for (int i = 0 ; i < results.getResults().size();i++){
            data[i] = results.getResults().get(i);
        }
        long[] organizeData = bubbleSort(data, "value", "descending");
        long counter = 1;
        long grpNum = organizeData.length;
        while (true){
            //loop the counter equal the data that has been sorted in previous step//
            if (Math.pow(10,counter)>=organizeData[0]){
                break;
            }else {
                counter++;
            }
        }

        long spaceCounter = 0;
        long labelLength = 6;
        long totalSpace = spaceCounter*grpNum+labelLength*grpNum;
        while (totalSpace<=80){
            spaceCounter++;
            totalSpace = spaceCounter*grpNum+labelLength*grpNum;
        }
        //Working on the drawing section of the chart display //
        spaceCounter--;
        for (int i = 0; i < 23; i++) {
            System.out.print("|");
            long tab_count=0;
            //--using the loop to run through the chart--//
            ArrayList<Long> rowInd = new ArrayList<>();
            for (int j = 0; j < organizeData.length; j++) {
                int rowScale = (int) Math.ceil((23 * organizeData[j]) / (organizeData[0]+Math.pow(10, counter-1)));
                if (rowScale==(23-i)){
                    rowInd.add(bubbleSort(data, "index", "descending")[j]);
                }
            }
            //bring up the new array to get the total number of different groups
            long[] sortInd = new long[rowInd.size()];
            for (int k = 0; k < rowInd.size(); k++) {
                sortInd[k] = rowInd.get(k);
            }
            sortInd = bubbleSort(sortInd, "value", "ascending");
            for (int k = 0; k < (spaceCounter+labelLength/2-1); k++) {
                System.out.print(" ");
            }
            //placing the * elements into demonstrating the value
            int starCounter = 0;
            for (int p = 0; p < sortInd.length; p++) {
                while (tab_count<(sortInd[p]-1)*(spaceCounter+labelLength)-starCounter){
                    System.out.print(" ");
                    tab_count++;
                }
                System.out.print("*");
                starCounter++;
            }
            System.out.println();
        }
        //drawing the line for the both axis//
        System.out.print("|");
        for (int q = 0; q < 79; q++) {
            System.out.print("_");
        }
        System.out.println();
        for (int i = 1; i <= grpNum; i+=1) {
            for (int j = 0; j < spaceCounter; j++) {
                System.out.print(" ");
            }
            System.out.print("group"+i);
        }
    }
    static long[] bubbleSort(long[] orgAr, String returnVal, String ordSorter) {
        long n = orgAr.length;
        long[] arrInd = new long[orgAr.length];
        for (int i = 0; i < arrInd.length; i++) {
            arrInd[i] = i+1;
        }
        long[] arrSorter = orgAr.clone();
        long instntVal = 0;
        long instntInd = 0;
        if (ordSorter.equals("descending")){
            for(int i=0; i < n; i++){
                for(int j=1; j < (n-i); j++){
                    if(arrSorter[j-1] < arrSorter[j]){
                        instntVal = arrSorter[j-1];
                        instntInd = arrInd[j-1];
                        arrSorter[j-1] = arrSorter[j];
                        arrInd[j-1] = arrInd[j];
                        arrSorter[j] = instntVal;
                        arrInd[j] = instntInd;
                    }
                }
            }
        }else if (ordSorter.equals("ascending")){
            for(int i=0; i < n; i++){
                for(int j=1; j < (n-i); j++){
                    if(arrSorter[j-1] > arrSorter[j]){
                        instntVal = arrSorter[j-1];
                        instntInd = arrInd[j-1];
                        arrSorter[j-1] = arrSorter[j];
                        arrInd[j-1] = arrInd[j];
                        arrSorter[j] = instntVal;
                        arrInd[j] = instntInd;
                    }
                }
            }
        }
        if (returnVal.equals("value")){
            return arrSorter;
        }else if (returnVal.equals("index")){
            return arrInd;
        }else{
            long[] unavaiRe = {0,0,0,0,0,0};
            return unavaiRe;
        }
    }
}
