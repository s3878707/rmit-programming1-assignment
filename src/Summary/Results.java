package Summary;

import java.util.ArrayList;

public class Results {
    private ArrayList<Integer> results = new ArrayList<>();
    public  Results(){}

    /**

     * @param metric An array list of groups containing int
     * @param option  Option a : New Total
     *       Option b : Up To
     *       Get the specific list from the metric depends on option that user chooses
     */
    public Results(Metric metric, String option) {
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

}
