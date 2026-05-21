/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Safal
 */
public class logindata {
    private int user_id;
    private String username;
    private String email;
    private String password;
    
    public int getUSerID() {
        return user_id;
    }
    public void setUserID(int user_id) {
        this.user_id=user_id;
    }
     public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username =username;
        
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email =email;
        
    }public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public logindata(String username, String email, String password){
        this.username=username;
        this.email=email;
        this.password=password;
    }
    
    
}
