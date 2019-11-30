package com.GUI;

import com.Dao.FriendTabel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;

public class Jtree_create extends JTree {
    String user_id;
    final  int[] group={1,2,3};
    DefaultMutableTreeNode root=new DefaultMutableTreeNode("联系人分类");
    public  JTree creat(){
        DefaultMutableTreeNode node1 =new DefaultMutableTreeNode("好友");
        DefaultMutableTreeNode node2=new DefaultMutableTreeNode("家人");
        DefaultMutableTreeNode node3=new DefaultMutableTreeNode("同学");
        root.add(node1);
        root.add(node2);
        root.add(node3);
        DefaultMutableTreeNode node4=new DefaultMutableTreeNode(new JLabel("rrr"));   //好友的展开列表

        DefaultMutableTreeNode node5=new DefaultMutableTreeNode(new JLabel("sss")); // 家人展开的列表

        DefaultMutableTreeNode node6=new DefaultMutableTreeNode(new JLabel("jhhhh")); // 家人展开的列表

        node1.add(node4);
        node2.add(node5);
        node3.add(node6);

        JTree tree1=new JTree(root);
        tree1.setRootVisible(false);   //不显示手柄;
        tree1.setRowHeight(20);
        tree1.setRootVisible(false);  //不显示树根
        tree1.putClientProperty("JTree.lineStyle","None");

        return  tree1;
    }
    public  Jtree_create(String user_id ){
        this.user_id=user_id;
    }
}
