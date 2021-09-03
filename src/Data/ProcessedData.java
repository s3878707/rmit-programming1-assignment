package Data;

import CSVData.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProcessedData{
    private ArrayList<Data> processedData = new ArrayList<>();

    /**
     * Make a list of Data which the people vaccinated column are converted to newVaccinated
     * @throws FileNotFoundException
     */
    public ProcessedData() throws FileNotFoundException {
        CSVdata data = new CSVdata();
        ArrayList<HashMap<String, String>> csvData = data.getCsvData();
        HashMap<String,String> firstMap = csvData.get(0);
        // add the very first data
        processedData.add(new Data(
                firstMap.get("continent"),
                firstMap.get("location"),
                firstMap.get("date"),
                firstMap.get("new_cases"),
                firstMap.get("new_deaths"),
                firstMap.get("people_vaccinated")
        ));
        for (int i =1; i< csvData.size(); i++){
            HashMap<String,String> previousMap = csvData.get(i-1);
            HashMap<String,String> currentMap = csvData.get(i);
            // if both data have the same country, the value people vaccinated will be changed to new vaccinated
            if (previousMap.get("location").equals(currentMap.get("location"))) {
                processedData.add(new Data(
                        currentMap.get("continent"),
                        currentMap.get("location"),
                        currentMap.get("date"),
                        currentMap.get("new_cases"),
                        currentMap.get("new_deaths"),
                        Integer.toString(calculateNewVaccinated(previousMap, currentMap))
                ));
            }else {
                // Copy the same data from csv file when adding the first data of new country
                processedData.add(new Data(
                        currentMap.get("continent"),
                        currentMap.get("location"),
                        currentMap.get("date"),
                        currentMap.get("new_cases"),
                        currentMap.get("new_deaths"),
                        currentMap.get("people_vaccinated")
                ));
            }
        }
    }


    private Integer calculateNewVaccinated(HashMap<String,String> previousMap, HashMap<String,String> currentMap) {
        String currentMapPpVaccinated;
        String previousMapPpVaccinated;
        if (previousMap.get("people_vaccinated")==null||previousMap.get("people_vaccinated").equals("")) {
            previousMapPpVaccinated ="0";
        }else{
            previousMapPpVaccinated = previousMap.get("people_vaccinated");
        }
        if (currentMap.get("people_vaccinated")==null||currentMap.get("people_vaccinated").equals("")) {
            currentMapPpVaccinated="0";
        }else {
            currentMapPpVaccinated = currentMap.get("people_vaccinated");
        }
        int newVaccinated = Integer.parseInt(currentMapPpVaccinated) -
                Integer.parseInt(previousMapPpVaccinated);
//        }

        return newVaccinated;
    }
    public ArrayList<Data> getProcessedData(){
        return processedData;
    }

    public static void main(String[] args) throws  FileNotFoundException{
        ProcessedData data = new ProcessedData();
        for (Data i : data.getProcessedData()){
            System.out.println(i);
        }
    }
}
