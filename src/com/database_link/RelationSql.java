package com.database_link;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RelationSql  {
    private   String  user_id;
    private   String   friends_id;
    private   String    friends_nickname;
    private   int   Group_id;
    public  void setFriends_id(String friends_id){
        this.friends_id=friends_id;
    }
    public String getFriends_id(){
        return  friends_id;
    }

    public  void setFriends_nickname(String newnikname) {
        this.friends_nickname=newnikname;
    }

    public void setGroup_id(int Group_id)
    {
        this.Group_id=Group_id;
    }

    public int getGroup_id(){
        return  Group_id;
    }

    public  String getFriends_nickname(){
       return  friends_nickname;
    }

    public RelationSql(String user_id, String friends_id){
        this.friends_id=friends_id;
        this.user_id=user_id;
         Connection c = new ConnectSql().getConnection();
        try {
        ResultSet res;
        String sql = "select *  from  relation_account  where (account_number=? and friends_id=?)";
        PreparedStatement pre = c.prepareStatement(sql);
        pre.setString(1, user_id);
        pre.setString(2,friends_id);
        res = pre.executeQuery();
        if (res.next()) {
            friends_nickname = res.getString("friends_nickname");
            Group_id=res.getInt("Group_id");
        }
        c.close();
        pre.close();
        } catch (SQLException ex) {
        ex.printStackTrace();
        }
    }
    public  void Update_Friends(){
        try {
            Connection c = new ConnectSql().getConnection();
            String sql = "update  Relation_account  set  friends_nickname=? ,Group_id=? where (account_number=? and friends_id=?)";
            PreparedStatement pre = c.prepareStatement(sql);
            pre.setString(1, friends_nickname);
            pre.setInt(2,Group_id);
            pre.setString(3,user_id);
            pre.setString(4,friends_id);
            pre.executeUpdate();
            c.close();
            pre.close();;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void Delete_friends(){
        try {
            Connection c = new ConnectSql().getConnection();
            String sql = "delete   from  relation_account  where account_number=? and friends_id=?";
            PreparedStatement pre = c.prepareStatement(sql);
            pre.setString(1,user_id);
            pre.setString(2,friends_id);
            pre.executeUpdate();
            c.close();
            pre.close();;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert_Friends(){
        try {
            Connection c = new ConnectSql().getConnection();
            String sql = "insert  into relation_account values (?,?,?,?)";
            PreparedStatement pre = c.prepareStatement(sql);
            pre.setString(1,user_id);
            pre.setString(2,friends_id);
            pre.setString(3,friends_nickname);
            pre.setInt(4,Group_id);
            pre.executeUpdate();
            c.close();
            pre.close();;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
