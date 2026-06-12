/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class UserData {
    private int id ; 
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id =id;
    }
    
    private String username;
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username){
        this.username=username;
    }
    
    private String email;
    public String getEmail() {
        return email;
    }
    
      public void setEmail(String email){
        this.email=email;
    }
    
    private String password;
    public String getPassword() {
        return password;
    }
    
      public void setPassword(String password){
        this.password=password;
    }
    
   public UserData(String username,String email,String password){
       this.username=username;
       this.email = email;
       this.password=password;
   }
}
