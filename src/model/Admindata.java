/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author rojan
 */
public class Admindata {
    private int user_id;
    private String username;
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
 
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public Admindata(String username, String password){
        this.username=username;
        this.password=password;
    }
}
