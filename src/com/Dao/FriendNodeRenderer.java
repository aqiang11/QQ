package com.Dao;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class FriendNodeRenderer extends DefaultTreeCellRenderer {

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
                                                  boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        //将value转化为节点对象
        FriendNode friendNode = (FriendNode) value;
        //从节点中读取图片并且将图片自适应大小、居中
        ImageIcon icon = new ImageIcon(friendNode.getIcon()+"   ");
        icon.setImage(icon.getImage().getScaledInstance(70, 65,Image.SCALE_DEFAULT));
        //从节点中读取昵称和是否在线
        String isOnline ;
        String text = "";

        //因为在Label中文本文字不能够通过调用相应的方法进行换行，
        //所以通过使用html的语法对文字进行换行
        String nullString="                    ";
        if (friendNode.getIsOnline() == "outline") {
            isOnline = "[离线]          ";
            text = "<html>" + friendNode.getNickName() +"("+friendNode.getFriendAccount()+ ")"+"<br/>" + isOnline + " <html/>";
        } else if (friendNode.getIsOnline() == "online") {
            isOnline = "[在线]          ";
            text ="<html>" + friendNode.getNickName() +"("+friendNode.getFriendAccount()+ ")" +"<br/>"+ isOnline + " <html/>";
        }else if (friendNode.getIsOnline() == "buzy") {
            isOnline = "[忙碌] ";
            text = "<html>" + friendNode.getNickName() +"("+friendNode.getFriendAccount()+ ")" +"<br/>" + isOnline + " <html/>";
        }
        else if (friendNode.getIsOnline() =="") {
            text = friendNode.getNickName()+nullString;
        }

        //设置图片
        setIcon(icon);
        //设置文本
        setText(text);
        //设置图片和文本之间的距离
        setIconTextGap(10);
        setBackgroundSelectionColor(Color.white);
        setBackgroundSelectionColor(Color.white);
        setFont(new Font("宋体",Font.BOLD,20));

        return this;
    }
}