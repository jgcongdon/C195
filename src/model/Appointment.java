package model;

import java.time.LocalDateTime;

/**
 * This class creates the Appointment objects.
 * @author Jackson Congdon
 */
public class Appointment {
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private LocalDateTime Start;
    private LocalDateTime End;
    private LocalDateTime Create_Date;
    private String Created_By;
    private LocalDateTime Last_Update;
    private String Last_Updated_By;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;

    public Appointment(int Appointment_ID, String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End, LocalDateTime Create_Date, String Created_By, LocalDateTime Last_Update, String Last_Updated_By, int Customer_ID, int User_ID, int Contact_ID) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Contact_ID = Contact_ID;
    }

    /**
     * @return the Appointment ID
     */
    public int getAppointment_ID() {
        return Appointment_ID;
    }

    /**
     * @param Appointment_ID the Appointment ID to set
     */
    public void setAppointment_ID(int Appointment_ID) {
        Appointment_ID = Appointment_ID;
    }

    /**
     * @return the Appointment title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param Title the Appointment title to set
     */
    public void setTitle(String Title) {
        Title = Title;
    }

    /**
     * @return the Appointment description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Appointment description to set
     */
    public void setDescription(String Description) {
        Description = Description;
    }

    /**
     * @return the Appointment location
     */
    public String getLocation() {
        return Location;
    }

    /**
     * @param Location the Appointment location to set
     */
    public void setLocation(String Location) {
        Location = Location;
    }

    /**
     * @return the Appointment type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type the Appointment type to set
     */
    public void setType(String Type) {
        Type = Type;
    }

    /**
     * @return the Appointment start
     */
    public LocalDateTime getStart() {
        return Start;
    }

    /**
     * @param Start the Appointment start to set
     */
    public void setStart(LocalDateTime Start) {
        Start = Start;
    }

    /**
     * @return the Appointment end
     */
    public LocalDateTime getEnd() {
        return End;
    }

    /**
     * @param End the Appointment end to set
     */
    public void setEnd(LocalDateTime End) {
        End = End;
    }

    /**
     * @return the Appointment create date
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * @param Create_Date the Appointment create date to set
     */
    public void setCreate_Date(LocalDateTime Create_Date) {
        Create_Date = Create_Date;
    }

    /**
     * @return the Appointment created by
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * @param Created_By the Appointment created by to set
     */
    public void setCreated_By(String Created_By) {
        Created_By = Created_By;
    }

    /**
     * @return the Appointment last update
     */
    public LocalDateTime getLast_Update() {
        return Last_Update;
    }

    /**
     * @param Last_Update the Appointment last update to set
     */
    public void setLast_Update(LocalDateTime Last_Update) {
        Last_Update = Last_Update;
    }

    /**
     * @return the Appointment last updated by
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * @param Last_Updated_By the Appointment last updated by to set
     */
    public void setLast_Updated_By(String Last_Updated_By) {
        Last_Updated_By = Last_Updated_By;
    }

    /**
     * @return the Customer ID of the Appointment
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     * @param Customer_ID the Customer ID of the Appointment to set
     */
    public void setCustomer_ID(int Customer_ID) {
        Customer_ID = Customer_ID;
    }

    /**
     * @return the User ID of the Appointment
     */
    public int getUser_ID() {
        return User_ID;
    }

    /**
     * @param User_ID the User ID of the Appointment to set
     */
    public void setUser_ID(int User_ID) {
        User_ID = User_ID;
    }

    /**
     * @return the Contact ID of the Appointment
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /**
     * @param Contact_ID the Contact ID of the Appointment to set
     */
    public void setContact_ID(int Contact_ID) {
        Contact_ID = Contact_ID;
    }

    /**
     * @return the Appointment String type
     */
    @Override
    public String toString(){return Type;}
}