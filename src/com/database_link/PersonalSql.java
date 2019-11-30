package com.database_link;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalSql {
    private   String nikneme;
    private   String  usename;
    private   String  OnlineState;
    private   String  myicon;
    private  String birthday;
    private  String age;
    private  String sign_name;
    private  int    port_id;
    public  void setOnlineState(String  state) {
        OnlineState=state;
    }
    public  String getOnlineState(){
        return OnlineState;
    }


    public  String getBirthday(){
        return  birthday;
    }
    public  void setBirthday(String birthday){
        this.birthday=birthday;
    }


    public String getSign_name(){
        return  sign_name;
    }
    public  void setSign_name(String sign_name){
        this.sign_name=sign_name;
    }


    public  String getAge(){
        return  age;
    }
    public void setAge(String age){
        this.age=age;
    }


    public String getNikneme(){
        return  nikneme;
    }
    public void setNikneme(String newnikename){
        nikneme=newnikename;
    }


    public  void setMyicon(String myicon) {
        this.myicon=myicon;
    }
    public  String getMyicon() {
        return  myicon;
    }

    public  void  setPort_id(int port_id){
        this.port_id=port_id;
    }

    public  int getPort_id(){
        return  this.port_id;
    }


    public   PersonalSql(String username) {
        this.usename = username;
        Connection c = new ConnectSql().getConnection();
        try {
            ResultSet res;
            String sql = "select *  from user_accountAll  where account_number=?";
            PreparedStatement pre = c.prepareStatement(sql);
            pre.setString(1, username);
            res = pre.executeQuery();
            if (res.next()) {
                birthday=res.getString("birthday");
                age=res.getString("age");
                nikneme = res.getString("nikname");
                myicon=res.getString("headphoto");
                sign_name=res.getString("sign_name");
                OnlineState = res.getString("OnlineState");
                port_id=res.getInt("port_id");
            }
            c.close();
            pre.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public  void Update_useraccount(){
        Connection c = new ConnectSql().getConnection();
        try {
            String sql = "update  user_accountAll set  nikname=?,OnlineState=?, birthday=?,age=?,sign_name=?,headphoto=?,port_id=? where account_number=?";
            PreparedStatement pre = c.prepareStatement(sql);
            pre.setString(1, nikneme);
            pre.setString(2,OnlineState);
            pre.setString(3,birthday);
            pre.setString(4,String.valueOf(2019-Integer.parseInt(birthday.substring(0,4))));
            pre.setString(5,sign_name);
            pre.setString(6,myicon);
            pre.setInt(7,port_id);
            pre.setString(8,usename);
            pre.executeUpdate();
            c.close();
            pre.close();;
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

