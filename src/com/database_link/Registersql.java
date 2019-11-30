package com.database_link;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registersql {
    private   int flag=-1;
    public  Registersql(String username,String passwd)throws SQLException {
        Connection c=new ConnectSql().getConnection();
        try {
            flag=0;    //falg =0 :连接失败；falg=1:连接成功但查找失败；falg=2:连接成功且验证成功 ；
            ResultSet res;
            String sql="select * from user_account where account_number=?";
            PreparedStatement pre=c.prepareStatement(sql);
            pre.setString(1,username);
            res=pre.executeQuery();
            if(res.next()){
                flag=1;
            }
            else {
                pre=null;
                sql="insert into user_account(account_number,passwd) values(?,?) ";
                pre=c.prepareStatement(sql);
                pre.setString(1,username);
                pre.setString(2,passwd);
                int row=pre.executeUpdate();
                if(row>0){
                    flag=2;
                    System.out.println("成功更新一条记录");
                }   //(account_number,nikname,OnlineState,headphoto)
                sql="insert into user_accountAll values(?,' ','online','src/com/img/头像.jpg','2000/01/01','20','苦学java',6000) ";
                pre=c.prepareStatement(sql);
                pre.setString(1,username);
                 row=pre.executeUpdate();
                if(row>0){
                    flag=2;
                    System.out.println("成功更新一条记录");
                }
            }
            c.close();
            pre.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    public  int getResult(){
        return  flag;
    }
}
