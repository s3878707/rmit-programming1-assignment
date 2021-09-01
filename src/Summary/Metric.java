package Summary;

import Data.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Metric {
    private ArrayList<ArrayList<Integer>> metricList= new ArrayList<>();
    private ArrayList<ArrayList<Integer>> metricListForUpTo= new ArrayList<>();
    private ArrayList<ArrayList<String>> dateList = new ArrayList<>();
    private ArrayList<ArrayList<String>> dateListForUpTo = new ArrayList<>();
    public Metric (){}

    /**
     * Option a : new cases
     * Option b : new deaths
     * Option c : new vaccinated
     * @param group
     * @param option
     * @throws FileNotFoundException
     */
    public Metric(Group group, String option) throws FileNotFoundException {
        ArrayList<ArrayList<Data>> listGroup = group.getGrouping();
        ProcessedData processedData = new ProcessedData();
        ArrayList<Data> processedDataList = processedData.getProcessedData();

        if (option.equals("a")) {
            for (ArrayList<Data> i : listGroup) {
                //MAKE A LIST OF GROUPS OF NEWCASES IN SELECTED DATA
                ArrayList<Integer> newCasesList = new ArrayList<>();
                ArrayList<Integer> newCaseListFromTheBeginning = new ArrayList<>();
                ArrayList<String> dateList = new ArrayList<>();
                ArrayList<String> dateListFromTheBeginning = new ArrayList<>();
                for (Data n : i) {
                    newCasesList.add(n.getNewCases());
                    dateList.add(n.getLocalDate());

                }
                metricList.add(newCasesList);
                this.dateList.add(dateList);
                //MAKE A LIST OF GROUP OF NEWCASES FROM THE VERY FIRST BEGINNING OF A COUNTRY TO LAST DATA
                Data lastDataOfGroup = i.get(i.size() - 1);
                Data newlastDataOfGroup = new Data();
                // get the data in processed data similar to the last data of a group in selected group
                for (Data j : processedDataList) {
                    if (j.getLocalDate().equals(lastDataOfGroup.getLocalDate())
                            && j.getLocation().equals(lastDataOfGroup.getLocation())) {
                        newlastDataOfGroup = new Data(j);
                    }
                }
                //add the very first data of a country to the last data of a group
                for (Data j : processedDataList) {
                    if (j.getLocation().equals(lastDataOfGroup.getLocation()) &&
                            processedDataList.indexOf(j) <= processedDataList.indexOf(newlastDataOfGroup.data)) {
                        newCaseListFromTheBeginning.add(j.getNewCases());
                        dateListFromTheBeginning.add(j.getLocalDate());
                    }
                }
                metricListForUpTo.add(newCaseListFromTheBeginning);
                dateListForUpTo.add(dateListFromTheBeginning);
            }

        }
        if (option.equals("b")) {
            for (ArrayList<Data> i : listGroup) {
                //MAKE A LIST OF GROUPS OF NEWDEATHS IN SELECTED DATA
                ArrayList<Integer> newDeathsList = new ArrayList<>();
                ArrayList<Integer> newDeathsListFromTheBeginning = new ArrayList<>();
                ArrayList<String> dateList = new ArrayList<>();
                ArrayList<String> dateListFromTheBeginning = new ArrayList<>();
                for (Data n : i) {
                    newDeathsList.add(n.getNewDeaths());
                    dateList.add(n.getLocalDate());
                }
                metricList.add(newDeathsList);
                this.dateList.add(dateList);
                //MAKE A LIST OF GROUP OF NEWDEATHS FROM THE VERY FIRST BEGINNING OF A COUNTRY TO LAST DATA
                Data lastDataOfGroup = i.get(i.size() - 1);
                Data newlastDataOfGroup = new Data();
                // get the data in processed data similar to the last data of a group in selected group
                for (Data j : processedDataList) {
                    if (j.getLocalDate().equals(lastDataOfGroup.getLocalDate())
                            && j.getLocation().equals(lastDataOfGroup.getLocation())) {
                        newlastDataOfGroup = new Data(j);
                    }
                }
                //add the very first data of a country to the last data of a group
                for (Data j : processedDataList) {
                    if (j.getLocation().equals(lastDataOfGroup.getLocation()) &&
                            processedDataList.indexOf(j) <= processedDataList.indexOf(newlastDataOfGroup.data)) {
                        newDeathsListFromTheBeginning.add(j.getNewDeaths());
                        dateListFromTheBeginning.add(j.getLocalDate());
                    }
                }
                metricListForUpTo.add(newDeathsListFromTheBeginning);
                dateListForUpTo.add(dateListFromTheBeginning);
            }
        }
        if (option.equals("c")) {
            for (ArrayList<Data> i : listGroup) {
                //MAKE A LIST OF GROUPS OF NEWVACCINATED IN SELECTED DATA
                ArrayList<Integer> newVaccinated = new ArrayList<>();
                ArrayList<Integer> newVaccinatedListFromTheBeginning = new ArrayList<>();
                ArrayList<String> dateList = new ArrayList<>();
                ArrayList<String> dateListFromTheBeginning = new ArrayList<>();
                for (Data n : i) {
                    newVaccinated.add(n.getVaccinated());
                    dateList.add(n.getLocalDate());
                }
                metricList.add(newVaccinated);
                this.dateList.add(dateList);
                //MAKE A LIST OF GROUP OF NEWVACCINATED FROM THE VERY FIRST BEGINNING OF A COUNTRY TO LAST DATA
                Data lastDataOfGroup = i.get(i.size() - 1);
                Data newlastDataOfGroup = new Data();
                // get the data in processed data similar to the last data of a group in selected group
                for (Data j : processedDataList) {
                    if (j.getLocalDate().equals(lastDataOfGroup.getLocalDate())
                            && j.getLocation().equals(lastDataOfGroup.getLocation())) {
                        newlastDataOfGroup = new Data(j);
                    }
                }
                //add the very first data of a country to the last data of a group
                for (Data j : processedDataList) {
                    if (j.getLocation().equals(lastDataOfGroup.getLocation()) &&
                            processedDataList.indexOf(j) <= processedDataList.indexOf(newlastDataOfGroup.data)) {
                        newVaccinatedListFromTheBeginning.add(j.getVaccinated());
                        dateListFromTheBeginning.add(j.getLocalDate());
                    }
                }
                metricListForUpTo.add(newVaccinatedListFromTheBeginning);
                dateListForUpTo.add(dateListFromTheBeginning);
            }
        }
    }
    public ArrayList<ArrayList<Integer>> getMetricList(){
        return metricList;
    }
    public ArrayList<ArrayList<Integer>> getMetricListForUpTo(){
        return metricListForUpTo;
    }
    public ArrayList<ArrayList<String>> getDateList(){return dateList;}
    public ArrayList<ArrayList<String>> getDateListForUpTo(){return dateListForUpTo;}
    public static void main(String[] args) {

    }
}
