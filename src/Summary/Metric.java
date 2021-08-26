package Summary;

import Data.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Metric {
    private ArrayList<ArrayList<Integer>> metricList= new ArrayList<>();
    private ArrayList<ArrayList<Integer>> metricListForUpTo= new ArrayList<>();


    public Metric(Group group, String option) throws FileNotFoundException {
        ArrayList<ArrayList<Data>> listGroup = group.getGrouping();
        ProcessedData processedData = new ProcessedData();
        ArrayList<Data> processedDataList = processedData.getProcessedData();

        if (option.equals("a")){
            for (ArrayList<Data> i : listGroup){
                ArrayList<Integer> positivesCaseList = new ArrayList<>();

                ArrayList<Integer> positiveCaseListFromTheBeginning = new ArrayList<>();
                for (Data n : i){
                    positivesCaseList.add(n.getNewCases());
                }
                metricList.add(positivesCaseList);

                Data lastDataOfGroup = i.get(i.size() - 1);
                Data newlastDataOfGroup = new Data();
                for (Data j : processedDataList){
                    if (j.getLocalDate().equals(lastDataOfGroup.getLocalDate())){
                        newlastDataOfGroup = new Data(j);
                    }
                }
                for (Data j : processedDataList) {

                    if (j.getLocation().equals(lastDataOfGroup.getLocation())&&
                            processedDataList.indexOf(j)<= processedDataList.indexOf(newlastDataOfGroup.data)) {
                        positiveCaseListFromTheBeginning.add(j.getNewCases());
                    }
                }
                metricListForUpTo.add(positiveCaseListFromTheBeginning);
            }

        }
        if (option.equals("b")) {
            ArrayList<Integer> newDeathsList = new ArrayList<>();

            ArrayList<Integer> newDeathsListFromTheBeginning = new ArrayList<>();
            for (ArrayList<Data> i : listGroup) {
                for (Data n : i) {
                    newDeathsList.add(n.getNewDeaths());
                }
                metricList.add(newDeathsList);

                Data lastDataOfGroup = i.get(i.size() - 1);
                Data newlastDataOfGroup = new Data();
                for (Data j : processedDataList) {
                    if (j.getLocalDate().equals(lastDataOfGroup.getLocalDate())) {
                        newlastDataOfGroup = new Data(j);
                    }
                }
                for (Data j : processedDataList) {

                    if (j.getLocation().equals(lastDataOfGroup.getLocation()) &&
                            processedDataList.indexOf(j) <= processedDataList.indexOf(newlastDataOfGroup.data)) {
                        newDeathsListFromTheBeginning.add(j.getNewCases());
                    }
                }
                metricListForUpTo.add(newDeathsListFromTheBeginning);
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
    public ArrayList<ArrayList<Integer>> getMetricListForUpTo(){
        return metricListForUpTo;
    }

    public static void main(String[] args) {

    }
}
