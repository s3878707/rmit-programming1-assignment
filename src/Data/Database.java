package Data;

import Csvprocess.CSVdata;

import java.io.FileNotFoundException;
import java.util.*;

public class Database {

    private ArrayList<Data> selectedData = new ArrayList<>();
    public Database(){};
    public Database(Geo geo, TimeRange timeRange, CSVdata data) {
        ArrayList<Data> processedData = listDataAfterChangeNewVaccinated(data);
        for (String localDate : timeRange.getArr()) {
            for (Data map : processedData) {
                if (map.getLocation().equals(geo.getCountry()) && map.getLocalDate().equals(localDate)) {
                    selectedData.add(new Data(
                            geo.getContinent(),
                            geo.getCountry(),
                            localDate,
                            Integer.toString(map.getNewCases()),
                            Integer.toString(map.getNewDeaths()),
                            Integer.toString(map.getVaccinated())
                    ));
                }
            }
        }
    }


    private ArrayList<Data> listDataAfterChangeNewVaccinated (CSVdata data) {
        ArrayList<Data> rawData = new ArrayList<>();
        ArrayList<HashMap<String, String>> csvData = data.getCsvData();
        HashMap<String,String> firstMap = csvData.get(0);
        rawData.add(new Data(
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
                rawData.add(new Data(
                        currentMap.get("continent"),
                        currentMap.get("location"),
                        currentMap.get("date"),
                        currentMap.get("new_cases"),
                        currentMap.get("new_deaths"),
                        Integer.toString(calculateNewVaccinated(previousMap, currentMap))
                ));
            }else {
                rawData.add(new Data(
                        currentMap.get("continent"),
                        currentMap.get("location"),
                        currentMap.get("date"),
                        currentMap.get("new_cases"),
                        currentMap.get("new_deaths"),
                        currentMap.get("people_vaccinated")
                ));
            }
        }
        return rawData;
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
//        int newVaccinated = Integer.parseInt(currentMapPpVaccinated);
//        if (previousMap.get("location").equals(currentMap.get("location"))) {
        int newVaccinated = Integer.parseInt(currentMapPpVaccinated) -
                        Integer.parseInt(previousMapPpVaccinated);
//        }

        return newVaccinated;
    }

    public String toString() {
        return String.format(selectedData.toString());
    }


    public ArrayList<Data> getSelectedData() {
        return selectedData;
    }


    public static void main(String[] args) throws FileNotFoundException {
        CSVdata aa = new CSVdata("test.csv");
        Database data = new Database();
        ArrayList<Data> processedData = data.listDataAfterChangeNewVaccinated(aa);

        for (Data i : processedData){
            System.out.println(i);
        }
    }
}


