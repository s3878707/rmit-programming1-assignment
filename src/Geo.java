import java.io.FileNotFoundException;
import java.util.*;

public class Geo {
    private String country;
    private String continent;
    Geo(){};
    Geo (String continent,String country) {
        this.continent = continent;
        this.country = country;
    }
    public String[] listOfRegion(CSVdata list, String dataType) {
        HashSet<String> set = new HashSet<>();
        for (HashMap<String, String> i : list.getCsvData()) {
            if (!i.get(dataType).equals("")) {
                set.add(i.get(dataType));
            }
        }
        String[] arr = set.toArray(new String[0]);
        return arr;
    }
    public String getCountry(){
        return country;
    }
    public String getContinent(){
        return continent;
    }
}

