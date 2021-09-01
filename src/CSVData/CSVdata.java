package CSVData;
import java.util.*;
import java.io.*;
public class CSVdata {
    /**
     * read and execute CSV file. Convert it into A list of map which key is header like
     * continent,location,date,... and value is the data of each header
     */
    private ArrayList<HashMap<String, String>> csvData = new ArrayList<>();
    public CSVdata() throws  FileNotFoundException{
        Scanner sc = new Scanner(new File("test.csv"));
        String[] header = sc.nextLine().split(",");
        while (sc.hasNextLine()) {
            HashMap<String, String> row = new HashMap<>();
            String[] line = sc.nextLine().split(",");
            for (int i = 0; i < line.length; i++) {
                row.put(header[i], line[i]);
            }
            csvData.add(row);
        }
    }

    /**
     * this method use to check if a country is in a continent, which will use to filter
     * the contry after user choose a continent in userInterface
     * @param data1
     * @param data2
     * @return
     */
    public  boolean checkIfDataContainsNextData(String data1, String data2)  {
        for (HashMap<String, String> i : this.csvData) {
            if (i.containsValue(data1) && i.containsValue(data2)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<HashMap<String, String>> getCsvData(){
        return csvData;
    }
}
