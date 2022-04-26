package model;

/**
 * This class creates the Contact objects.
 * @author Jackson Congdon
 */
public class Contact {
    private int Contact_ID;
    private String Contact_Name;
    private String Email;

    public Contact(int Contact_ID, String Contact_Name, String Email) {
        this.Contact_ID = Contact_ID;
        this.Contact_Name = Contact_Name;
        this.Email = Email;
    }

    /**
     * @return the Contact ID
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /**
     * @param contact_ID the Contact ID to set
     */
    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    /**
     * @return the Contact name
     */
    public String getContact_Name() {
        return Contact_Name;
    }

    /**
     * @param contact_Name the Contact name to set
     */
    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    /**
     * @return the Contact email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param email the Contact email to set
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * @return the Contact String name
     */
    @Override
    public String toString(){return Contact_Name;}
}