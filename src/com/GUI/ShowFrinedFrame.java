package com.GUI;

import com.actionadd.SaveinfAction;
import com.database_link.PersonalSql;
import com.database_link.RelationSql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShowFrinedFrame extends JFrame {
    private Color color = Color.white;
    private String nikneme;
    private String user_id;
    private String OnlineState;
    private String myicon;
    private String birthday;
    private String age;
    private String sign_name;
    private int Group_id;
    private String firends_id;

    private JLabel jLabel头像 = new JLabel();
    private JLabel jLabel账号 = new JLabel();
    private JLabel jLabel生日 = new JLabel();
    private JLabel jLabel年龄 = new JLabel();
    private JLabel jLabel个性签名 = new JLabel();
    private JLabel jLabel修改备注 = new JLabel();
    private JTextField jt备注 = new JTextField();
    private JLabel jl好友分组 = new JLabel();
    private JButton jb保存 = new JButton("保存");
    private JButton jb取消 = new JButton("取消");
    private JComboBox jc分组 = new JComboBox();

    private String[] groupname = {"--选择分组--", "好友", "同学", "黑名单"};
    private PersonalSql persql = null;


    public ShowFrinedFrame(String user_id, String friend_id) {

        this.user_id = user_id;
        this.firends_id = friend_id;
        RelationSql relsql = new RelationSql(user_id, friend_id);

        this.Group_id = relsql.getGroup_id();
        //好友的信息


        persql = new PersonalSql(friend_id);
        OnlineState = persql.getOnlineState();
        myicon = persql.getMyicon();
        birthday = persql.getBirthday();
        age = persql.getAge();
        sign_name = persql.getSign_name();
        if (relsql.getFriends_nickname() != null)
            nikneme = relsql.getFriends_nickname();
        else
            nikneme = persql.getNikneme();

        Container c = getContentPane();
        ImageIcon img头像 = new ImageIcon(myicon + "");
        img头像.setImage(img头像.getImage().getScaledInstance(120, 100, Image.SCALE_DEFAULT));
        jLabel头像.setIcon(img头像);
        jLabel头像.setBounds(10, 10, 120, 100);

        String text = "<html>" + nikneme + "  (" + friend_id + ")" + "<br/>" + OnlineState + "<html/>";
        jLabel账号.setText(text);
        jLabel账号.setFont(new Font("宋体", Font.BOLD, 20));
        jLabel账号.setBounds(140, 10, 290, 100);

        jLabel个性签名.setText(" 个性签名 :" + sign_name);
        jLabel个性签名.setBounds(0, 110, 400, 60);
        jLabel个性签名.setFont(new Font("宋体", Font.BOLD, 20));
        jLabel个性签名.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));

        jLabel年龄.setText(" 年龄:" + age);
        jLabel年龄.setBounds(0, 170, 200, 60);
        jLabel年龄.setFont(new Font("宋体", Font.BOLD, 20));
        jLabel年龄.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));

        jLabel生日.setText(" 生日:" + birthday);
        jLabel生日.setBounds(200, 170, 200, 60);
        jLabel生日.setFont(new Font("宋体", Font.BOLD, 20));
        jLabel生日.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));

        jLabel修改备注.setText("修改备注:");
        jLabel修改备注.setBounds(0, 230, 80, 40);
        jLabel修改备注.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));

        jt备注.setText(nikneme);
        jt备注.setBounds(80, 230, 120, 40);
        jt备注.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));


        jl好友分组.setText("好友分组");
        jl好友分组.setBounds(200, 230, 80, 40);
        jl好友分组.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));

        jc分组.setEditable(true);

        jc分组.setBounds(280, 230, 110, 40);

        jb保存.setBounds(120, 300, 60, 40);
        jb取消.setBounds(220, 300, 60, 40);


        for (String s : groupname) {
            jc分组.addItem(s);
        }

        jc分组.setSelectedIndex(Group_id);

        jb保存.addActionListener(this::actionPerformed);
        c.add(jLabel头像);
        c.add(jLabel账号);
        c.add(jLabel个性签名);
        c.add(jLabel年龄);
        c.add(jLabel生日);
        c.add(jl好友分组);

        c.add(jLabel修改备注);
        c.add(jt备注);
        c.add(jc分组);
        c.add(jb保存);
        c.add(jb取消);


        c.setBackground(color);
        setLayout(null);
        setDefaultCloseOperation(2);
        setVisible(true);
        setBounds(500, 200, 400, 400);
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/com/img/QQ.jpg")));
        setTitle("好友资料");
        setResizable(false);
    }


    public void actionPerformed(ActionEvent actionEvent) {
        this.jt备注.setText(jt备注.getText());
        int flag = jc分组.getSelectedIndex();

        if (flag == 2)
            Group_id = 2;
        else if (flag == 3)
            Group_id = 3;
        else
            Group_id = 1;
        jb保存.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                RelationSql relSql = new RelationSql(user_id, firends_id);
                relSql.setFriends_nickname(jt备注.getText());
                relSql.setGroup_id(Group_id);
                relSql.Update_Friends();
            }
        });
    }
}







