package CSVData;

import java.util.*;
import java.io.*;
public class CSVdata {
    private ArrayList<HashMap<String, String>> csvData = new ArrayList<>();
    public CSVdata() throws  FileNotFoundException{
        Scanner sc = new Scanner(new File("covid-data.csv"));
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
    public  boolean checkIfDataExist(String data,String datatype) {
        for (HashMap<String, String> i : this.csvData) {
            if (i.get(datatype).equals(data)) {
                return true;
            }
        }
        return false;
    }

    public  boolean checkIfDataContainsNextData(String data1, String data2)  {
        for (HashMap<String, String> i : this.csvData) {
            if (i.containsValue(data1) && i.containsValue(data2)) {
                return true;
            }
        }
        return false;
    }

    public  String printErrorWhenUserEnterWrong(String data, String datatype) {
        Scanner sc = new Scanner(System.in);
        while (!checkIfDataExist(data,datatype)) {
            System.out.print("Write down the input again >>>>>>>");
            data = sc.nextLine().trim();
        }
        return data;
    }
    public ArrayList<HashMap<String, String>> getCsvData(){
        return csvData;
    }
}
