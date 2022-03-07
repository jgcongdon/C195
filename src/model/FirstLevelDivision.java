package model;

import java.time.LocalDateTime;

public class FirstLevelDivision {

    private int Division_ID;
    private String Division;
    private LocalDateTime Create_Date;
    private String Created_By;
    private LocalDateTime Last_Update;
    private String Last_Updated_By;
    private int COUNTRY_ID;

    public int getDivision_ID() {
        return Division_ID;
    }

    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
    }

    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(LocalDateTime create_Date) {
        Create_Date = create_Date;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    public LocalDateTime getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(LocalDateTime last_Update) {
        Last_Update = last_Update;
    }

    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    public int getCOUNTRY_ID() {
        return COUNTRY_ID;
    }

    public void setCOUNTRY_ID(int COUNTRY_ID) {
        this.COUNTRY_ID = COUNTRY_ID;
    }

    public FirstLevelDivision(int Division_ID, String Division, LocalDateTime Create_Date, String Created_By, LocalDateTime Last_Update, String Last_Updated_By, int COUNTRY_ID){
        this.Division_ID=Division_ID;
        this.Division=Division;
        this.Create_Date=Create_Date;
        this.Created_By=Created_By;
        this.Last_Update=Last_Update;
        this.Last_Updated_By=Last_Updated_By;
        this.COUNTRY_ID=COUNTRY_ID;
    }

    @Override
    public String toString(){return Division;}

}
