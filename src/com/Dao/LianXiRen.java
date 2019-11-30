package com.Dao;
import Server.StartServer;
import com.actionadd.LinkTreeAction;
import com.database_link.JtableSql;
import com.database_link.PersonalSql;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.util.List;

public class LianXiRen extends JFrame {
    private  String user_id;
    private  String SelectFriend_id;
    private StartServer startServer;
    JScrollPane scrollPane = new JScrollPane();
    public  LianXiRen (String user_id , StartServer startserver) {
        this.user_id=user_id;
        this.startServer=startserver;
        FriendNode root好友 = new FriendNode("好友列表", "");
        FriendNode root同学 = new FriendNode("同学列表", "");
        FriendNode root黑名单=new FriendNode("黑名单","");
        FriendNode Root根 = new FriendNode("联系人名单", "");
        List<String[]> list好友=  new JtableSql(user_id,1).getList();
        List<String[]>  list同学=new JtableSql(user_id,2).getList();
        List<String[]>  list黑名单=new JtableSql(user_id,3).getList();

        FriendNode node好友;
        FriendNode node同学;
        FriendNode node黑名单;

        if(list好友.size()>=1)
        for(int i=0;i<list好友.size();i++){
            PersonalSql persql= new PersonalSql(list好友.get(i)[0]);
            if(persql.getMyicon()!=null)
                node好友 = new FriendNode(new ImageIcon(persql.getMyicon() + ""), list好友.get(i)[1], "outline", list好友.get(i)[0]);
             else
                node好友=new FriendNode(new ImageIcon("src/com/img/"+"2.jpg"), list好友.get(i)[1],"outline", list好友.get(i)[0]);
            root好友.add(node好友);
        }
     if(list同学.size()>=1)
        for(int i=0;i<list同学.size()&&list同学.get(i)[0]!=null;i++){
            PersonalSql persql= new PersonalSql(list同学.get(i)[0]);
            if(persql.getMyicon()!=null)
                node同学=new FriendNode(new ImageIcon(persql.getMyicon() + ""), list同学.get(i)[1],"buzy", list同学.get(i)[0]);
            else
                node同学=new FriendNode(new ImageIcon("src/com/img/2.jpg"), list同学.get(i)[1],"buzy", list同学.get(i)[0]);
            root同学.add(node同学);
       }
       if(list黑名单.size()>=1)
        for(int i=0;i<list黑名单.size();i++){
            PersonalSql persql= new PersonalSql(list黑名单.get(i)[0]);
            if(persql.getMyicon()!=null)
             node黑名单=new FriendNode(new ImageIcon(persql.getMyicon() + ""), list黑名单.get(i)[1],"online", list黑名单.get(i)[0]);
           else
                node黑名单=new FriendNode(new ImageIcon("src/com/img/2.jpg"), list黑名单.get(i)[1],"online", list黑名单.get(i)[0]);
            root黑名单.add(node黑名单);
        }

//将所有的子节点加入到根节点中，当然也可以一个节点再次添加
//下一个节点，这样的话就可以呈现出多级的树形结果
        Root根.add(root好友);
        Root根.add(root同学);
        Root根.add(root黑名单);
        //将刚才的根节点添加到JTree中
        JTree tree = new JTree(Root根);
//将树的前面连接去掉
        tree.putClientProperty("JTree.lineStyle", "None");
//设置树的字体大小，样式
        tree.setFont(new Font("微软雅黑", Font.PLAIN, 15));
//设置树节点的高度
        tree.setRowHeight(70);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
                FriendNode note = (FriendNode) tree.getLastSelectedPathComponent();
                if(note.isLeaf()){
                    SelectFriend_id=note.getFriendAccount();

                    tree.addMouseListener(new LinkTreeAction(user_id,SelectFriend_id, startserver));    //添加事件--菜单
                }
            }
        });
//设置单元描述

        tree.setCellRenderer(new FriendNodeRenderer());
       // tree.setRootVisible(false);    //根部不可见
        tree.setShowsRootHandles(false);
        //将tree添加到JScrollPane 中
        scrollPane.setViewportView(tree);

//将scrollPane添加到JPanel中用于显示
    }
    public  JScrollPane  getJsc(){

        return  scrollPane;
    }
}
