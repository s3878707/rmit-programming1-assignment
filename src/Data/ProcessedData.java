package Data;

import Csvprocess.CSVdata;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProcessedData{
    private ArrayList<Data> processedData = new ArrayList<>();
    public ProcessedData() throws FileNotFoundException {
    CSVdata data = new CSVdata();
    ArrayList<HashMap<String, String>> csvData = data.getCsvData();
    HashMap<String,String> firstMap = csvData.get(0);
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
}
