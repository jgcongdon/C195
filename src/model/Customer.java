package model;

import java.time.LocalDateTime;

/**
 * This class creates the Customer objects.
 * @author Jackson Congdon
 */
public class Customer {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String postalCode;
    private String customerPhone;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdateBy;
    private int Division_ID;

    public Customer(int customerId, String customerName, String customerAddress, String postalCode, String customerPhone, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdateBy, int Division_ID) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.postalCode = postalCode;
        this.customerPhone = customerPhone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
        this.Division_ID = Division_ID;
    }

    /**
     * @return the Customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the Customer ID to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the Customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the Customer name to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the Customer address
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * @param customerAddress the Customer address to set
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * @return the Customer postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the Customer postal code to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the Customer phone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * @param customerPhone the Customer phone to set
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * @return the Customer create date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the Customer create date to set
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the Customer created by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the Customer created by to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the Customer last update
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate the Customer last update to set
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return the Customer last update by
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * @param lastUpdateBy the Customer last update by to set
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * @return the Division ID of the Customer
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     * @param Division_ID the Division ID of the Customer to set
     */
    public void setDivisionId(int Division_ID) {
        this.Division_ID = Division_ID;
    }

    /**
     * @return the Customer String name
     */
    @Override
    public String toString(){return customerName;}
}