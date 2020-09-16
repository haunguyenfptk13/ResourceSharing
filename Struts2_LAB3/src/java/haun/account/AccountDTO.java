/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.account;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author msi
 */
public class AccountDTO implements Serializable{
    @SerializedName(value = "userId")
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String address;
    private int roleID;
    private int statusID;
    @SerializedName(value = "email")
    private String email;
    @SerializedName(value = "id")
    private String googleID;
    @SerializedName(value = "success")
    private boolean verifyRECAPTCHA;

    public AccountDTO() {
    }

    @Override
    public String toString() {
        return "AccountDTO{" + "id=" + id + ", username=" + username + ", password=" + password + ", fullname=" + fullname + ", address=" + address + ", roleID=" + roleID + ", statusID=" + statusID + ", email=" + email + ", googleID=" + googleID + ", verifyRECAPTCHA=" + verifyRECAPTCHA + '}';
    }

    public AccountDTO(String username, String password, String fullname, String address, int roleID, int statusID, String email) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.roleID = roleID;
        this.statusID = statusID;
        this.email = email;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the googleID
     */
    public String getGoogleID() {
        return googleID;
    }

    /**
     * @param googleID the googleID to set
     */
    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    /**
     * @return the verifyRECAPTCHA
     */
    public boolean getVerifyRECAPTCHA() {
        return isVerifyRECAPTCHA();
    }

    /**
     * @param verifyRECAPTCHA the verifyRECAPTCHA to set
     */
    public void setVerifyRECAPTCHA(boolean verifyRECAPTCHA) {
        this.verifyRECAPTCHA = verifyRECAPTCHA;
    }

    /**
     * @return the roleID
     */
    public int getRoleID() {
        return roleID;
    }

    /**
     * @param roleID the roleID to set
     */
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    /**
     * @return the statusID
     */
    public int getStatusID() {
        return statusID;
    }

    /**
     * @param statusID the statusID to set
     */
    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    /**
     * @return the verifyRECAPTCHA
     */
    public boolean isVerifyRECAPTCHA() {
        return verifyRECAPTCHA;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
