package model;

import java.time.LocalDateTime;

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

    public int getAppointment_ID() {
        return Appointment_ID;
    }

    public void setAppointment_ID(int Appointment_ID) {
        Appointment_ID = Appointment_ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        Description = Description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        Location = Location;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        Type = Type;
    }

    public LocalDateTime getStart() {
        return Start;
    }

    public void setStart(LocalDateTime Start) {
        Start = Start;
    }

    public LocalDateTime getEnd() {
        return End;
    }

    public void setEnd(LocalDateTime End) {
        End = End;
    }

    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(LocalDateTime Create_Date) {
        Create_Date = Create_Date;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public void setCreated_By(String Created_By) {
        Created_By = Created_By;
    }

    public LocalDateTime getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(LocalDateTime Last_Update) {
        Last_Update = Last_Update;
    }

    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    public void setLast_Updated_By(String Last_Updated_By) {
        Last_Updated_By = Last_Updated_By;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(int Customer_ID) {
        Customer_ID = Customer_ID;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int User_ID) {
        User_ID = User_ID;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public void setContact_ID(int Contact_ID) {
        Contact_ID = Contact_ID;
    }

    @Override
    public String toString(){return Type;}
}