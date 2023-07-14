package com.wang.pojo;

public class User {
	private int id;//id
    private String username;//Ïû·ÑÃû³Æ
    private String password;
    private String email;
    private String date;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return username;
    }
    public void setUserName(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    
    @Override
   	public String toString() {
   		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email+", date=" + date + "]";
   	}
    
    public User(String username,String password)
    {
    	super();
    	this.username=username;
    	this.password=password;
    }
    
    public User(String username,String password,String email) {
    	super();
    	this.username=username;
    	this.password=password;
    	this.email=email;
    }
    
}
