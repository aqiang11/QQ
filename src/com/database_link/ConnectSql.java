package com.database_link;
import java.sql.*;
public class ConnectSql {
    Connection con;
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据库驱动加载成功！");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        try {
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database_qq?serverTimezone=UTC","root","100861");
            System.out.println("数据库连接成功");
        }catch (SQLException e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return con;
    }
    public  void closeConnect(){
        try{
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
