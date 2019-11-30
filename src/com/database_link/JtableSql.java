package com.database_link;

import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JtableSql {
    String user_id;
    int group;
    List<String[]> list = new ArrayList<>();
    public JtableSql(String user_id ,int group){
        this.user_id=user_id;
        this.group=group;
    }
    public List<String[]> getList() {
        int flag = -1;
        try {
            Connection c = new ConnectSql().getConnection();
            flag = 0;    //falg =0 :连接失败；falg=1:连接成功但查找失败；falg=2:连接成功且验证成功 ；
            ResultSet res;
            String sql = "select friends_id , friends_nickname from Relation_account  where  account_number =? and  Group_id=? ";
            PreparedStatement pre = c.prepareStatement(sql);
            pre.setString(1, user_id);
            pre.setInt(2, group);
            res = pre.executeQuery();
            while (res.next()) {
                String[] col = new String[2];
                col[0]=res.getString("friends_id");
                col[1]=res.getString("friends_nickname");
                list.add(col);
                flag=1;
            }
            c.close();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
