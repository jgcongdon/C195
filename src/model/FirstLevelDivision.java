package model;

import java.time.LocalDateTime;

/**
 * This class creates the FirstLevelDivision objects.
 * @author Jackson Congdon
 */
public class FirstLevelDivision {
    private int Division_ID;
    private String Division;
    private LocalDateTime Create_Date;
    private String Created_By;
    private LocalDateTime Last_Update;
    private String Last_Updated_By;
    private int COUNTRY_ID;

    public FirstLevelDivision(int Division_ID, String Division, LocalDateTime Create_Date, String Created_By, LocalDateTime Last_Update, String Last_Updated_By, int COUNTRY_ID){
        this.Division_ID=Division_ID;
        this.Division=Division;
        this.Create_Date=Create_Date;
        this.Created_By=Created_By;
        this.Last_Update=Last_Update;
        this.Last_Updated_By=Last_Updated_By;
        this.COUNTRY_ID=COUNTRY_ID;
    }

    /**
     * @return the FirstLevelDivision ID
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     * @param division_ID the FirstLevelDivision ID to set
     */
    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    /**
     * @return the FirstLevelDivision name
     */
    public String getDivision() {
        return Division;
    }

    /**
     * @param division the FirstLevelDivision name to set
     */
    public void setDivision(String division) {
        Division = division;
    }

    /**
     * @return the FirstLevelDivision create date
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * @param create_Date the FirstLevelDivision create date to set
     */
    public void setCreate_Date(LocalDateTime create_Date) {
        Create_Date = create_Date;
    }

    /**
     * @return the FirstLevelDivision created by
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * @param created_By the FirstLevelDivision created by to set
     */
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    /**
     * @return the FistLevelDivision last update
     */
    public LocalDateTime getLast_Update() {
        return Last_Update;
    }

    /**
     * @param last_Update the FistLevelDivision last update to set
     */
    public void setLast_Update(LocalDateTime last_Update) {
        Last_Update = last_Update;
    }

    /**
     * @return the FirstLevelDivision last updated by
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * @param last_Updated_By the FirstLevelDivision last updated by to set
     */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /**
     * @return the Country ID of the FirstLevelDivision
     */
    public int getCOUNTRY_ID() {
        return COUNTRY_ID;
    }

    /**
     * @param COUNTRY_ID the Country ID of the FirstLevelDivision to set
     */
    public void setCOUNTRY_ID(int COUNTRY_ID) {
        this.COUNTRY_ID = COUNTRY_ID;
    }

    /**
     * @return the FistLevelDivision String name
     */
    @Override
    public String toString(){return Division;}
}