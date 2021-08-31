package Data;

import CSVData.CSVdata;
import Data.*;

import java.io.FileNotFoundException;
import java.util.*;

public class Geo {
    private String country;
    private String continent;

    public Geo() {
    }

    ;

    public Geo(String continent, String country) {
        this.continent = continent;
        this.country = country;
    }

    private ArrayList<String> listOfRegion(String dataType) throws FileNotFoundException {
        CSVdata list = new CSVdata();
        HashSet<String> set = new HashSet<>();
        ArrayList<String> arr = new ArrayList<>();
        for (HashMap<String, String> i : list.getCsvData()) {
            if (!i.get(dataType).equals("")) {
                set.add(i.get(dataType));
            }
        }
        for (String i : set){
            arr.add(i);
        }
        return arr;
    }

    public ArrayList<String> makeLocationList() throws FileNotFoundException{
        ArrayList<String> listLocation = listOfRegion("location");
        listLocation.sort(new SortPlaceByAlphabet());
        return listLocation;
    }
    public ArrayList<String> makeContinentList() throws FileNotFoundException{
        ArrayList<String> listLocation = listOfRegion("continent");
        listLocation.sort(new SortPlaceByAlphabet());
        return listLocation;
    }
    public String getCountry(){
        return country;
    }
    public String getContinent(){
        return continent;
    }

    public static void main(String[] args) throws FileNotFoundException{
        Geo geo = new Geo();
        for (String i : geo.makeLocationList()){
            System.out.println(i);
        }
    }
}
class SortPlaceByAlphabet implements Comparator<String>{
    public int compare(String p1, String p2){
        int d1 = (int)(p1.charAt(0));
        int d2 = (int)(p2.charAt(0));
        return d1-d2;
    }
}

