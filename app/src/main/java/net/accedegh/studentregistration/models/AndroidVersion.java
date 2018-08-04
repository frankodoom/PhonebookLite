package net.accedegh.studentregistration.models;

public class AndroidVersion {

    public String name;
    public String apiLevel;
    public String year;

    public AndroidVersion(String name, String apiLevel, String year) {
        this.name = name;
        this.apiLevel = apiLevel;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiLevel() {
        return apiLevel;
    }

    public void setApiLevel(String apiLevel) {
        this.apiLevel = apiLevel;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


}
