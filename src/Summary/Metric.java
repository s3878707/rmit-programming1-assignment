package Summary;

import Data.Data;

import java.util.ArrayList;

public class Metric {
    private ArrayList<ArrayList<Integer>> metricList= new ArrayList<>();
    public Metric(Group group, String option){
        ArrayList<ArrayList<Data>> listGroup = group.getGrouping();
        if (option.equals("a")){
            for (ArrayList<Data> i : listGroup){
                ArrayList<Integer> positivesCaseList = new ArrayList<>();
                for (Data n : i){
                    positivesCaseList.add(n.getNewCases());
                }
                metricList.add(positivesCaseList);
            }
        }
        if (option.equals("b")){
            for (ArrayList<Data> i : listGroup){
                ArrayList<Integer> newDeathsList = new ArrayList<>();
                for (Data n : i){
                    newDeathsList.add(n.getNewDeaths());
                }
                metricList.add(newDeathsList);
            }
        }
        if (option.equals("c")){
            for (ArrayList<Data> i : listGroup){
                ArrayList<Integer> newVaccinated = new ArrayList<>();
                for (Data n : i){
                    newVaccinated.add(n.getVaccinated());
                }
                metricList.add(newVaccinated);
            }
        }
    }
    public ArrayList<ArrayList<Integer>> getMetricList(){
        return metricList;
    }
    public static void main(String[] args) {

    }
}
