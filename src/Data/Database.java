package Data;

import CSVData.*;

import java.io.FileNotFoundException;
import java.util.*;

public class Database {
    /**
     * this class will make a list of Data object which is selected by the user by entering
     * continent,location and timeRange
     */
    private ArrayList<Data> selectedData = new ArrayList<>();
    public Database(){};
    public Database(Geo geo, TimeRange timeRange) throws FileNotFoundException{
        ProcessedData processedData = new ProcessedData();
        for (String localDate : timeRange.getListOfDate()) {
            // The lenght of database equals to the timeRange
            for (Data map : processedData.getProcessedData()) {
                // get Data from proccessedData to have the newVaccinated instead of
                // people vaccinated in CSV file
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


    public String toString() {
        return String.format(selectedData.toString());
    }


    public ArrayList<Data> getSelectedData() {
        return selectedData;
    }


    public static void main(String[] args) throws FileNotFoundException {
        CSVdata aa = new CSVdata();
        ProcessedData processedData = new ProcessedData();
        for (Data i : processedData.getProcessedData()){
            System.out.println(i);
        }
    }
}