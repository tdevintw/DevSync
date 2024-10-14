package dev.yassiraitelghari.utils;

import dev.yassiraitelghari.domain.User;

public class ProfileUpdateValidation {
    private User updatedUser ;
    private String firstName ;
    private String lastName ;
    private String password ;

    public ProfileUpdateValidation(){};

    public void setAll(String firstName , String lastName , String password , String confirmPassword){
        if(firstName.length()<3){
            this.firstName = "First name is too short";
        }

        if(lastName.length()<3){
            this.firstName = "Last name is too short";
        }
        if(password.isEmpty() && confirmPassword.isEmpty()){
            password = null;
        }else{

            if(!password.equals(confirmPassword)){
                this.password = "Password and confirm password are different";
            }else{
                if(password.length()<8){
                    this.password = "password must be at least 8 characters";
                }
            }
        }

    }

    public boolean allNull(){
        return firstName==null && lastName ==null && password ==null;
    }

    public User getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(User updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
