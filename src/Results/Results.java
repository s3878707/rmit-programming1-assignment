package Results;

import CSVData.CSVdata;
import Data.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Results {
    private ArrayList<Integer> results = new ArrayList<>();
    public Results(Metric metric, String option, Group group) throws FileNotFoundException {
        ArrayList<ArrayList<Data>> groupingList = group.getGrouping();
        if (option.equals("a")) {
            ArrayList<ArrayList<Integer>> metricList = metric.getMetricList();
            for (ArrayList<Integer> i : metricList) {
                int newTotalinAGroup = 0;
                for (int n = 0; n < i.size(); n++) {
                    newTotalinAGroup += i.get(n);
                }
                results.add(newTotalinAGroup);
            }
        }
        if (option.equals("b")) {
            ArrayList<ArrayList<Integer>> metricListForUpTo = metric.getMetricListForUpTo();
            for (ArrayList<Integer> i : metricListForUpTo) {
                int newToTalFromTheBeginning = 0;
                for (int n = 0; n<i.size(); n++){
                    newToTalFromTheBeginning += i.get(n);
                }
                results.add(newToTalFromTheBeginning);
            }
        }
    }
    public ArrayList<Integer> getResults(){
        return results;
    }
//    private int upToData(int lastDate, Csvprocess.CSVdata data){
//
//    }
}
