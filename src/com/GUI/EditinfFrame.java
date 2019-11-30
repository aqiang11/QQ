package com.GUI;

import com.actionadd.SaveinfAction;
import com.database_link.PersonalSql;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public  class  EditinfFrame implements  MouseListener
{
    String user_id;
    public  EditinfFrame(String user_id){
        this.user_id=user_id;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        new  ChangeinfFrame(user_id );
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}





class ChangeinfFrame extends JFrame implements ActionListener{
    private   String nikneme;
    private   String user_id;
    private   String  OnlineState;
    private  String birthday;
    private  String age;
    private  String sign_name;
    private  int port_id;
    JFrame jf;
    JLabel jLabel昵称=new JLabel(" 昵称：");
    JTextField jT昵称=new JTextField();
    JComboBox  jc年份= new JComboBox();
    JComboBox jc月份=new JComboBox();
    JComboBox jc日=new JComboBox();
    JLabel jL状态=new JLabel("选择状态:");
    JComboBox  jc状态= new JComboBox();

    JLabel jl端口=new JLabel("端口: ");
    JTextField jt端口号=new JTextField();

    JLabel jl生日=new JLabel("出生日期：");
    JButton jb保存=new JButton("保存");
    JButton jb取消=new JButton("取消");

    JLabel jLabel个性签名=new JLabel("个性签名");
    JTextField jT个性签名=new JTextField();


    public  ChangeinfFrame(String user_id){
        PersonalSql persql=new PersonalSql(user_id);
        Container c=getContentPane();
        this.user_id=user_id;
        persql=new PersonalSql(user_id);
        nikneme=persql.getNikneme();
        OnlineState=persql.getOnlineState();
        birthday=persql.getBirthday();
        age=persql.getAge();
        sign_name=persql.getSign_name();
        port_id=persql.getPort_id();

        jT昵称.setText(nikneme);
        jLabel昵称.setBounds(10,10,50,30);
        jT昵称.setBounds(70,10,330,30);
        jT昵称.setEditable(true);


        jT个性签名.setEditable(true);
        jT个性签名.setText(sign_name);
        jLabel个性签名.setBounds(10,50,50,30);
        jT个性签名.setBounds(70,50,330,30);

        jL状态.setBounds(10,100,80,30);
        String[] State={"--选择状态--","[在线]","[离线]","[忙碌]"};
        jc状态.setEditable(true);
        for(String s:State){
            jc状态.addItem(s);
        }
        jc状态.setBounds(90,100,110,30);
        jl端口.setBounds(220,100,50,30);
        jt端口号.setText(String.valueOf(port_id));
        jt端口号.setBounds(250,100,100,30);

        if(OnlineState.equals("outline"))             //设置初始状态
            jc状态.setSelectedIndex(2);
        else  if(OnlineState.equals("buzy"))
            jc状态.setSelectedIndex(3);
        else
            jc状态.setSelectedIndex(1);


        jl生日.setBounds(10,150,90,30);
        for (int year=1950;year<2020;year++){
            jc年份.addItem(String.valueOf(year));
        }
        for(int mounth=1;mounth<13;mounth++){
            if (mounth<10)
                jc月份.addItem('0'+String.valueOf(mounth));
            else
                jc月份.addItem(String.valueOf(mounth));
        }
        for (int day=1;day<32;day++){
            if(day<10)
                jc日.addItem('0'+String.valueOf(day));
            else
                jc日.addItem(String.valueOf(day));
        }

        jc年份.setBounds(90,150,90,30);
        jc月份.setBounds(180,150,90,30);
        jc日.setBounds(270,150,90,30);
        jc年份.setEditable(true);
        jc月份.setEditable(true);
        jc日.setEditable(true);
        //设置初始出生日期
        jc年份.setSelectedIndex(Integer.parseInt(birthday.substring(0,4))-1950);
        jc月份.setSelectedIndex(Integer.parseInt(birthday.substring(5,7))-1);
        jc日.setSelectedIndex(Integer.parseInt(birthday.substring(8,birthday.length()))-1);

        jb保存.setBounds(110,230,80,30);
        jb取消.setBounds(210,230,80,30);
        jb保存.addActionListener(this::actionPerformed);   //

        JFrame thisFrame=this;         //把当当前的窗口让取消按钮关闭
        jb取消.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                thisFrame.dispose();
            }
        });

        c.add(jLabel昵称);
        c.add(jT昵称);
        c.add(jLabel个性签名);
        c.add(jT个性签名);
        c.add(jL状态);
        c.add(jc状态);
        c.add(jl端口);
        c.add(jt端口号);

        c.add(jl生日);
        c.add(jc年份);
        c.add(jc月份);
        c.add(jc日);
        c.add(jb保存);
        c.add(jb取消);

        setLayout(null);
        setDefaultCloseOperation(2);
        setVisible(true);
        setBounds(600,200,400,350);
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/com/img/QQ.jpg")));
        setTitle("编辑个人资料");
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.jT昵称.setText(jT昵称.getText());
        this.jT个性签名.setText(jT个性签名.getText());
        String flag=jc状态.getSelectedItem().toString().trim();
        this.jt端口号.setText(jt端口号.getText());
        if(flag.equals("[离线]"))
            OnlineState="outline";
        else  if(flag.equals("[忙碌]"))
            OnlineState="buzy";
        else
            OnlineState="online";
        jb保存.addMouseListener(new SaveinfAction(user_id,jT昵称.getText(),OnlineState, jT个性签名.getText(),
                jc年份.getSelectedItem().toString().trim()+'/'+jc月份.getSelectedItem().toString().trim()+'/'+jc日.getSelectedItem().toString().trim(),Integer.parseInt(jt端口号.getText())));
    }
}
