package com.actionadd;

import Server.StartServer;
import com.GUI.AddFriendFrame;
import com.GUI.ChatFrame;
import com.GUI.ShowFrinedFrame;
import com.database_link.PersonalSql;
import com.database_link.Registersql;
import com.database_link.RelationSql;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class LinkTreeAction extends MouseAdapter {
    private String SelectFriend_id;
    private String user_id;
    private  StartServer startServer;
    public LinkTreeAction(String user_id, String selectFriend_id, StartServer startServer) {
        this.SelectFriend_id = selectFriend_id;
        this.user_id = user_id;
        this.startServer=startServer;
    }

    public void mouseClicked(MouseEvent e) {
        JPopupMenu jPop菜单 = new JPopupMenu();                 //添加弹出式菜单；
        JMenuItem jmitem删除 = new JMenuItem("删除");
        JMenuItem jmitem查看好友信息 = new JMenuItem("查看好友信息");
        JMenuItem jmitem聊天 = new JMenuItem("聊天");
        JMenuItem jmitem拉黑 = new JMenuItem("拉黑");
        JMenuItem jmitem添加好友=new JMenuItem("添加好友");
        jPop菜单.add(jmitem聊天);
        jPop菜单.add(jmitem查看好友信息);
        jPop菜单.add(jmitem添加好友);
        jPop菜单.add(jmitem删除);
        jPop菜单.add(jmitem拉黑);
        if (e.getButton() == MouseEvent.BUTTON3) {
            //弹出右键菜单
            jPop菜单.show(e.getComponent(), e.getX(), e.getY());
            jmitem聊天.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {//只能检测到mousePressed事件
                    if (SelectFriend_id!=null)
                    new ChatFrame(user_id,SelectFriend_id,startServer);
                }
            });
            jmitem删除.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {//只能检测到mousePressed事件
                    if (SelectFriend_id!=null) {
                        RelationSql regsql = new RelationSql(user_id, SelectFriend_id);
                        regsql.Delete_friends();
                    } }
            });
            jmitem拉黑.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {//只能检测到mousePressed事件
                    if (SelectFriend_id!=null) {
                        RelationSql regsql = new RelationSql(user_id, SelectFriend_id);
                        regsql.setGroup_id(3);
                        regsql.Update_Friends();
                    } }
            });

            jmitem添加好友.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {//只能检测到mousePressed事件
                        new AddFriendFrame(user_id);
                    }
            });
           jmitem查看好友信息.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {//只能检测到mousePressed事件
                    if (SelectFriend_id!=null) {
                        new ShowFrinedFrame(user_id,SelectFriend_id);
                    } }
            });
    }}
}

