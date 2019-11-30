package com.database_link;

import java.sql.*;

public class LogTestSql {
    public String username;
    public  String passwd;
    public  void setUsername(String username){
        this.username=username;
    }
    public  void setPasswd(String passwd){
        this.passwd=passwd;
    }
    public  int  userAccount_Test() throws SQLException {
        Connection c=new ConnectSql().getConnection();
        try {
            int flag=0;    //falg =0 :连接失败；falg=1:连接成功但查找失败；falg=2:连接成功且验证成功 ；
            ResultSet res;
            String sql="select * from user_account where account_number=?";
            PreparedStatement pre=c.prepareStatement(sql);
            pre.setString(1,username);
            res=pre.executeQuery();
            if(res.next()){
                flag=1;
                String pwd=res.getString("passwd");
                if(pwd.equals(passwd)) {
                    flag=2;
                }
            }
            return flag;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
    }
}
