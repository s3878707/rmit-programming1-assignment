package Data;

import Csvprocess.CSVdata;

import java.io.FileNotFoundException;
import java.util.*;

public class Database {

    private ArrayList<Data> selectedData = new ArrayList<>();
    public Database(){};
    public Database(Geo geo, TimeRange timeRange, CSVdata data) throws FileNotFoundException{
        ProcessedData processedData = new ProcessedData();
        for (String localDate : timeRange.getArr()) {
            for (Data map : processedData.getProcessedData()) {
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


