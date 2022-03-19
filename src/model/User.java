package model;

import java.time.LocalDateTime;
import java.util.Calendar;

public class User {

        private int userId;
        private String userName;
        private String password;
        private LocalDateTime createDate;
        private String createdBy;
        private LocalDateTime lastUpdate;
        private String lastUpdateBy;

        public User(int userId, String userName, String password, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdateBy) {
            this.userId = userId;
            this.userName = userName;
            this.password = password;
            this.createDate = createDate;
            this.createdBy = createdBy;
            this.lastUpdate = lastUpdate;
            this.lastUpdateBy = lastUpdateBy;
        }

    /**
         * @return the userId
         */
        public int getUserId() {
            return userId;
        }

        /**
         * @param userId the userId to set
         */
        public void setUserId(int userId) {
            this.userId = userId;
        }

        /**
         * @return the userName
         */
        public String getUserName() {
            return userName;
        }

        /**
         * @param userName the userName to set
         */
        public void setUserName(String userName) {
            this.userName = userName;
        }

        /**
         * @return the password
         */
        public String getPassword() {
            return password;
        }

        /**
         * @param password the password to set
         */
        public void setPassword(String password) {
            this.password = password;
        }

        /**
         * @return the createDate
         */
        public LocalDateTime getCreateDate() {
            return createDate;
        }

        /**
         * @param createDate the createDate to set
         */
        public void setCreateDate(LocalDateTime createDate) {
            this.createDate = createDate;
        }

        /**
         * @return the createdBy
         */
        public String getCreatedBy() {
            return createdBy;
        }

        /**
         * @param createdBy the createdBy to set
         */
        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        /**
         * @return the lastUpdate
         */
        public LocalDateTime getLastUpdate() {
            return lastUpdate;
        }

        /**
         * @param lastUpdate the lastUpdate to set
         */
        public void setLastUpdate(LocalDateTime lastUpdate) {
            this.lastUpdate = lastUpdate;
        }

        /**
         * @return the lastUpdateBy
         */
        public String getLastUpdateBy() {
            return lastUpdateBy;
        }

        /**
         * @param lastUpdateBy the lastUpdateBy to set
         */
        public void setLastUpdateBy(String lastUpdateBy) {
            this.lastUpdateBy = lastUpdateBy;
        }

        @Override
        public String toString(){return userName;}

    }