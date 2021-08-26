package Summary;

import java.util.ArrayList;

public class Result {
    private ArrayList<Integer> results = new ArrayList<>();
    public Result(Metric metric, String option){
        ArrayList<ArrayList<Integer>> metricList = metric.getMetricList();
        if (option.equals("a")){
            for (ArrayList<Integer> i : metricList) {
                int newTotalinAGroup = 0;
                for (int n = 0; n < i.size(); n++) {
                    newTotalinAGroup += i.get(n);
                }
                results.add(newTotalinAGroup);
            }
        }
        if (option.equals("b")) {

        }
    }
    public ArrayList<Integer> getResults(){
        return results;
    }
//    private int upToData(int lastDate, Csvprocess.CSVdata data){
//
//    }
}
