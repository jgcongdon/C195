package model;

import java.time.LocalDateTime;

/**
 * This class creates the Country objects.
 * @author Jackson Congdon
 */
public class Country {
    private int Country_ID;
    private String Country;
    private LocalDateTime Create_Date;
    private String Created_By;
    private LocalDateTime Last_Update;
    private String Last_Updated_By;

    public Country(int Country_ID, String Country, LocalDateTime Create_Date, String Created_By, LocalDateTime Last_Update, String Last_Updated_By){
        this.Country_ID = Country_ID;
        this.Country = Country;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
    }

    /**
     * @return the Country ID
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     * @param country_ID the Country ID to set
     */
    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }

    /**
     * @return the Country name
     */
    public String getCountry() {
        return Country;
    }

    /**
     * @param country the Country name to set
     */
    public void setCountry(String country) {
        Country = country;
    }

    /**
     * @return the Country create date
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * @param create_Date the Country create date to set
     */
    public void setCreate_Date(LocalDateTime create_Date) {
        Create_Date = create_Date;
    }

    /**
     * @return the Country created by
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * @param created_By the Country created by to set
     */
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    /**
     * @return the Country last update
     */
    public LocalDateTime getLast_Update() {
        return Last_Update;
    }

    /**
     * @param last_Update the Country last update to set
     */
    public void setLast_Update(LocalDateTime last_Update) {
        Last_Update = last_Update;
    }

    /**
     * @return the Country last updated by
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * @param last_Updated_By the Country last updated by to set
     */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /**
     * @return the Country String name
     */
    @Override
    public String toString(){return Country;}
}