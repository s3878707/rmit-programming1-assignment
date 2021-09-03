package Data;
import CSVData.CSVdata;


import java.io.FileNotFoundException;
import java.util.*;

public class Geo {
    private String country;
    private String continent;

    public Geo() {
    }


    public Geo(String continent, String country) {
        this.continent = continent;
        this.country = country;
    }

    /**
     * To have a list of country or location where a country appears only once, the same with location
     * @param dataType the key that you want to make a list of its value. For example: continent, location,...
     * @return  a set of value
     * @throws FileNotFoundException
     */
    private HashSet<String> listOfRegion(String dataType) throws FileNotFoundException {
        CSVdata list = new CSVdata();
        HashSet<String> set = new HashSet<>();
        for (HashMap<String, String> i : list.getCsvData()) {
            if (!i.get(dataType).equals("")) {
                set.add(i.get(dataType));
            }
        }
        return set;
    }

    public ArrayList<String> makeLocationList() throws FileNotFoundException{
        ArrayList<String> listLocation = new ArrayList<>();
        for (String i : listOfRegion("location")){
            listLocation.add(i);
        }
        listLocation.sort(new SortPlaceByAlphabet());
        return listLocation;
    }
    public ArrayList<String> makeContinentList() throws FileNotFoundException{
        ArrayList<String> listContinent = new ArrayList<>();
        for (String i : listOfRegion("continent")){
            listContinent.add(i);
        }
        listContinent.sort(new SortPlaceByAlphabet());
        return listContinent;
    }
    public  String printErrorWhenUserEnterWrong(String data, String datatype) throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        while (!listOfRegion(datatype).contains(data)) {
            System.out.print("Write down the input again >>>>>>>");
            data = sc.nextLine().trim();
        }
        return data;
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
        int d1 = (p1.charAt(0));
        int d2 = (p2.charAt(0));
        return d1-d2;
    }
}

