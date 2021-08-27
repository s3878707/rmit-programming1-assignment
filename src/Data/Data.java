package Data;

public class Data {
    private String continent;
    private String location;
    private String localDate;
    private int newCases;
    private int newDeaths;
    private int vaccinated;
    public Data data;
    public Data(Data data){
        this.data = data;
    }
    public Data(){}
    public Data(String continent, String location, String localDate, String newCases, String newDeaths, String newVaccinated) {
        this.continent = continent;
        this.location = location;
        this.localDate = localDate;
        if (newCases == null||newCases.equals("")) {
            this.newCases = 0;
        } else {
            this.newCases = Integer.parseInt(newCases);
        }
        if (newDeaths == null || newDeaths.equals("")) {
            this.newDeaths = 0;
        } else {
            this.newDeaths = Integer.parseInt(newDeaths);
        }
        if (newVaccinated == null|| newVaccinated.equals("")) {
            this.vaccinated = 0;
        } else {
            this.vaccinated = Integer.parseInt(newVaccinated);
        }
    }
    public String toString(){
        return String.format("%s - %s - %s - %d - %d - %d ",continent,location,localDate,newCases,newDeaths, vaccinated);
    }
    public String getContinent(){return continent;}
    public String getLocation(){return location;}
    public String getLocalDate(){return localDate;}
    public int getNewCases(){return newCases;}
    public int getNewDeaths(){return newDeaths;}
    public int getVaccinated(){return vaccinated;}




}