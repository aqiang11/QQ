package com.Dao;

import com.actionadd.FriendAction;
import com.database_link.JtableSql;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FriendTabel  {
    String user_id;
    int group;   //好友group为1，同学为2，黑名单为3
    public  FriendTabel(String user_id ,int group){
        this.group=group;
        this.user_id=user_id;
    }

    public JScrollPane getFriendTable(){
        List<String[]> list=new JtableSql(user_id,group).getList();
        JPanel jp列表=new JPanel(new GridLayout(list.size(),1));
        JPanel jp中间层=new JPanel();
        jp中间层.add(jp列表);
        System.out.println(list.size());
        for(int i=0;i<list.size();i++){
            JLabel jl好友=new JLabel(list.get(i)[0]+list.get(i)[1]);
            jl好友.setFont(new Font("宋体",Font.BOLD ,20));
            jl好友.addMouseListener(new FriendAction());
           jp列表.add(jl好友);
            System.out.println(list.get(i)[0]+list.get(i)[1]);
        }
        JScrollPane  jp1=new JScrollPane(jp中间层);
        return  jp1;
    }

    public  JScrollPane getClassmateTabel(){
        JPanel jp列表=new JPanel();
        JScrollPane  jp1=new JScrollPane(jp列表);
        jp1.setSize(100,100);
        List<String[]> list=new JtableSql(user_id,group).getList();
      for(int i=0;i<list.size();i++){
            JLabel jl同学=new JLabel(list.get(i)[0]+list.get(i)[1]);
            jp列表.add(jl同学);
        }
        return  jp1;
    }
    public  static  void main(String[] args){
        JFrame jf=new JFrame("ss");
        Container c=jf.getContentPane();
        FriendTabel ft=new FriendTabel("17130120",1);
        JScrollPane js=ft.getFriendTable();
        c.add(js);
        jf.setDefaultCloseOperation(3);
        jf.setVisible(true);
      //  jf.setBounds(200,200,200,200);
    }
}
