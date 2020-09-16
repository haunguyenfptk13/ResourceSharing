/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.struts2;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import haun.account.AccountDAO;
import haun.account.AccountDTO;
import haun.utils.SendGmail;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

/**
 *
 * @author msi
 */
@ResultPath("/")
@Results({
    @Result(name = "success", location = "login.jsp")
    ,
    @Result(name = "fail", type = "redirect", location = "insertErr.html")
    ,
    @Result(name = "input", location = "createNewAccount.jsp")
})
public class CreateAccountAction extends ActionSupport {

    private String username;
    private String password;
    private String confirm;
    private String name;
    private String phone;
    private String address;
    private String email;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public CreateAccountAction() {
    }

    @Action(value = "createAccount",
            exceptionMappings = {
                @ExceptionMapping(exception = "java.sql.SQLException", result = "input")
            })
    public String execute() throws Exception {
        AccountDTO dto = new AccountDTO(username, password, name, address, 1, 3, email);
        boolean result = AccountDAO.register(dto);
        String url = FAIL;
        if (result) {
            SendGmail.sendText(email, username);
            url = SUCCESS;
        }
        return url;
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
    @RequiredStringValidator(trim = true, message = "username is required", key = "insert.username.required")
    @StringLengthFieldValidator(minLength = "1", message = "username not be blank", key = "insert.username.length")
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
    @RequiredStringValidator(trim = true, message = "password is required", key = "insert.password.required")
    @StringLengthFieldValidator(minLength = "1", message = "password not be blank", key = "insert.password.length")
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the confirm
     */
    public String getConfirm() {
        return confirm;
    }

    /**
     * @param confirm the confirm to set
     */
    @FieldExpressionValidator(expression = "confirm==password", message = "confirm must match password", key = "insert.confirm.match")
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    @RequiredStringValidator(trim = true, message = "name is required", key = "insert.name.required")
    @StringLengthFieldValidator(minLength = "1", message = "name not be blank", key = "insert.name.length")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    @RequiredStringValidator(trim = true, message = "phone is required", key = "insert.phone.required")
    @StringLengthFieldValidator(minLength = "1", message = "phone not be blank", key = "insert.phone.length")
    public void setPhone(String phone) {
        this.phone = phone;
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
    @RequiredStringValidator(trim = true, message = "address is required", key = "insert.address.required")
    @StringLengthFieldValidator(minLength = "1", message = "address not be blank", key = "insert.address.length")
    public void setAddress(String address) {
        this.address = address;
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
    @EmailValidator(message = "Invalid format Email. Example: example@gmail.com", key = "insert.email.format")
    @RequiredStringValidator(trim = true, message = "email is required", key = "insert.email.required")
    @StringLengthFieldValidator(minLength = "1", message = "email not be blank", key = "insert.email.length")
    public void setEmail(String email) {
        this.email = email;
    }

}
