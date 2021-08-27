package Data;

import CSVData.*;

import java.util.*;

public class Database {

    private ArrayList<Data>  arr = new ArrayList<>();

    public Database(Geo geo, TimeRange timeRange, CSVdata data) {
        for (String localDate : timeRange.getArr()) {
            for (HashMap<String, String> map : data.getCsvData()) {
                if (map.containsValue(geo.getCountry())&&map.containsValue(localDate)) {
                    arr.add(new Data(
                            geo.getContinent(),
                            geo.getCountry(),
                            localDate,
                            map.get("new_cases"),
                            map.get("new_deaths"),
                            map.get("people_vaccinated")
                    ));
                }
            }
        }
    }
    public String toString(){
        return String.format(arr.toString());
    }
    public ArrayList<Data> getArr(){
        return arr;
    }
}