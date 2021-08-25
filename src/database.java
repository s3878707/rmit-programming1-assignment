import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class database {

    private ArrayList<Data>  arr = new ArrayList<>();

    public database(Geo geo, TimeRange timeRange, CSVdata data) {
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
class Data {
    String continent;
    String location;
    String localDate;
    int newCases;
    int newDeaths;
    int newVaccinated;

    public Data(String continent, String location, String localDate, String newCases, String newDeaths, String newVaccinated) {
        this.continent = continent;
        this.location = location;
        this.localDate = localDate;
        if (newCases.equals("")) {
            this.newCases = 0;
        } else {
            this.newCases = Integer.parseInt(newCases);
        }
        if (newDeaths.equals("")) {
            this.newDeaths = 0;
        } else {
            this.newDeaths = Integer.parseInt(newDeaths);
        }
        if (newVaccinated.equals("")) {
            this.newVaccinated = 0;
        } else {
            this.newVaccinated = Integer.parseInt(newVaccinated);
        }
    }
    public String toString(){
        return String.format("%s - %s - %s - %d - %d - %d ",continent,location,localDate,newCases,newDeaths,newVaccinated);
    }
}
